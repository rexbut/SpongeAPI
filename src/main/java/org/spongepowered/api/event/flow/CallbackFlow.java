package org.spongepowered.api.event.flow;

import org.spongepowered.api.util.Either;
import org.spongepowered.api.util.Tuple;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class CallbackFlow<A> implements Flow<A> {

    private A last = null;
    private List<Consumer<? super A>> consumers = new ArrayList<>();

    @Override
    public void forEach(Consumer<? super A> consumer, boolean acceptLast) {
        consumers.add(consumer);
        if (acceptLast && last != null) {
            consumer.accept(last);
        }
    }

    @Override
    public Flow<A> async(Executor executor) {
        CallbackFlow<A> that = new CallbackFlow<>();
        forEach(a ->
            executor.execute(() -> that.push(a)));
        return that;
    }

    public void push(A value) {
        last = value;
        for (Consumer<? super A> consumer: consumers) {
            consumer.accept(value);
        }
    }

    @Override
    public <B> Flow<B> fold(B initial, BiFunction<B, A, B> folder) {
        CallbackFlow<B> that = new CallbackFlow<>();
        that.push(initial);
        forEach(a -> {
            B last = that.last;
            that.push(folder.apply(last, a));
        });
        return that;
    }

    @Override
    public Flow<A> reduce(BiFunction<A, A, A> reducer) {
        this.zip(this.drop(1));
    }

    @Override
    public <B> Flow<B> map(Function<A, B> function) {
        CallbackFlow<B> that = new CallbackFlow<>();
        forEach(a -> that.push(function.apply(a)));
        return that;
    }

    @Override
    public <B> Flow<B> flatMap(Function<A, Flow<B>> function) {
        CallbackFlow<B> that = new CallbackFlow<>();
        forEach(a -> function.apply(a).forEach(that::push));
        return that;
    }

    @Override
    public <B extends A> Flow<A> merge(Flow<B> that) {
        CallbackFlow<A> merged = new CallbackFlow<>();
        forEach(merged::push);
        that.forEach(merged::push);
        return merged;
    }

    @Override
    public Flow<A> drop(int number) {
        CallbackFlow<A> that = new CallbackFlow<>();
        forEach(new Consumer<A>() {

            private int numberDropped;

            @Override
            public void accept(A a) {
                if (numberDropped < number) {
                    numberDropped++;
                } else {
                    that.push(a);
                }
            }
        });
        return that;
    }

    @Override
    public <B> Flow<Either<A, B>> choose(Flow<B> that) {
        CallbackFlow<Either<A, B>> chosen = new CallbackFlow<>();
        forEach(a -> chosen.push(Either.left(a)));
        that.forEach(b -> chosen.push(Either.right(b)));
        return chosen;
    }

    @Override
    public <B> Flow<Tuple<A, B>> zip(Flow<B> that) {
        CallbackFlow<Tuple<A, B>> zipped = new CallbackFlow<>();
        LinkedList<A> thisBuffer = new LinkedList<>();
        LinkedList<B> thatBuffer = new LinkedList<>();
        forEach(a -> {
            if (thatBuffer.isEmpty()) {
                thisBuffer.push(a);
            } else {
                zipped.push(new Tuple<>(a, thatBuffer.pop()));
            }
        });
        that.forEach(b -> {
            if (thisBuffer.isEmpty()) {
                thatBuffer.push(b);
            } else {
                zipped.push(new Tuple<>(thisBuffer.pop(), b));
            }
        });
        return zipped;
    }

    @Override
    public Optional<A> lastValue() {
        return Optional.ofNullable(last);
    }

}
