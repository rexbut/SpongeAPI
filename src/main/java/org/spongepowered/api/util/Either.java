package org.spongepowered.api.util;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public final class Either<A, B> {

    private @Nullable final A left;
    private @Nullable final B right;

    private Either(@Nullable A left, @Nullable B right) {
        if ((left == null && right == null) || (left != null && right != null)) {
            throw new IllegalStateException("One of Either.left or Either.right must be null");
        }
        this.left = left;
        this.right = right;
    }

    public static <A, B> Either<A, B> left(A left) {
        return new Either<>(Objects.requireNonNull(left), null);
    }

    public static <A, B> Either<A, B> right(B right) {
        return new Either<>(null, Objects.requireNonNull(right));
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public Optional<A> getLeft() {
        return Optional.ofNullable(left);
    }

    public Optional<B> getRight() {
        return Optional.ofNullable(right);
    }

    public Optional<B> toOptional() {
        return getRight();
    }

    <C> Either<A, C> map(Function<B, C> function) {
        if (isLeft()) {
            return left(this.left);
        } else {
            return right(function.apply(this.right));
        }
    }

    <C> Either<A, C> flatMap(Function<B, Either<A, C>> function) {
        if (isLeft()) {
            return left(this.left);
        } else {
            return function.apply(this.right);
        }
    }

    Either<B, A> swap() {
        if (isLeft()) {
            return right(this.left);
        } else {
            return left(this.right);
        }
    }

}
