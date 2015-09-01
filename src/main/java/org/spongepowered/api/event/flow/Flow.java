package org.spongepowered.api.event.flow;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Flow<A> {

    default void forEach(Consumer<? super A> action) {
        forEach(action, true);
    }

    void forEach(Consumer<? super A> consumer, boolean acceptLast);

    Flow<A> async(Executor executor);

    <B> Flow<B> fold(B initial, BiFunction<B, A, B> folder);

    Flow<A> reduce(BiFunction<A, A, A> reducer);

    <B> Flow<B> map(Function<A, B> function);

    <B> Flow<B> flatMap(Function<A, Flow<B>> function);

    <B extends A> Flow<A> merge(Flow<B> that);

    Optional<A> lastValue();

    static <A> Flow<A> flatten(Flow<Flow<A>> flow) {
        return flow.flatMap(Function.<Flow<A>>identity());
    }

}
