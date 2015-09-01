package org.spongepowered.api.event.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        CallbackFlow<A> that = new CallbackFlow<>();
        this.forEach(new Consumer<A>() {

            private A first = last;

            @Override
            public void accept(A a) {
                if (first == null) {
                    first = a;
                } else {
                    if (that.last == null) {
                        that.push(reducer.apply(first, a));
                    } else {
                        that.push(reducer.apply(last, a));
                    }
                }
            }

        });
        return that;
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
    public Optional<A> lastValue() {
        return Optional.ofNullable(last);
    }

}
