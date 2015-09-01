package org.spongepowered.api.event.flow;

import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class ProxyFlow<A> implements Flow<A> {

    protected abstract Flow<A> proxy();

    @Override
    public void forEach(Consumer<? super A> consumer, boolean acceptLast) {
        proxy().forEach(consumer, acceptLast);
    }

    @Override
    public Flow<A> async(Executor executor) {
        return proxy().async(executor);
    }

    @Override
    public <B> Flow<B> fold(B initial, BiFunction<B, A, B> folder) {
        return proxy().fold(initial, folder);
    }

    @Override
    public Flow<A> reduce(BiFunction<A, A, A> reducer) {
        return proxy().reduce(reducer);
    }

    @Override
    public <B> Flow<B> map(Function<A, B> function) {
        return proxy().map(function);
    }

    @Override
    public <B> Flow<B> flatMap(Function<A, Flow<B>> function) {
        return proxy().flatMap(function);
    }

    @Override
    public <B extends A> Flow<A> merge(Flow<B> that) {
        return proxy().merge(that);
    }

    @Override
    public Optional<A> lastValue() {
        return proxy().lastValue();
    }

    @Override
    public Iterator<A> iterator() {
        return proxy().iterator();
    }

}
