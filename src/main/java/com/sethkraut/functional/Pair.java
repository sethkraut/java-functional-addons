package com.sethkraut.functional;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Provides support for parameterized binary groupings with lots of functional goodies.
 */
public class Pair<A,B> {

    public static<A, B> Stream<Pair<A,B>> zip(Stream<A> a, Stream<B> b) {
        return FunUtil.zip(a, b, Pair::new);
    }

    /**
     *
     * @param fu
     * @param <E>
     * @param <F>
     * @return
     */
    public static <E,F> Function<E, Pair<E,F>> adding(Function<E,F> fu) {
        return e -> new Pair<>(e, fu.apply(e));
    }

    /**
     * Uses both values in the pair to calculate a new value for the second element in the pair.
     * @param fu
     * @param <C>
     * @param <D>
     * @param <E>
     * @return
     */
    public static <C,D,E> Function<Pair<C,D>, Pair<C,E>> replacingSecond(BiFunction<C,D,E> fu) {
        return p -> new Pair<>(p.getFirst(), fu.apply(p.getFirst(), p.getSecond()));
    }

    /**
     * Uses both values in the pair to calculate a new value for the first element in the pair.
     * @param function BiFunction that calculate a value from the pair.
     * @param <C> The type of the first element initially.
     * @param <D> The type of the second element before and after
     * @param <E> The type of the first element after the function
     * @return
     */
    public static <C,D,E> Function<Pair<C,D>, Pair<E,D>> replacingFirst(BiFunction<C,D,E> function) {
        return p -> new Pair<>(function.apply(p.getFirst(), p.getSecond()), p.getSecond());
    }

    /**
     * Tests the pair by using the predicate to test the first element.
     * @param first
     * @param <C>
     * @param <D>
     * @return
     */
    public static <C,D> Predicate<Pair<C,D>> testingFirst(Predicate<C> first) {
        return a -> first.test(a.getFirst());
    }

    /**
     *
     * @param second
     * @param <C>
     * @param <D>
     * @return
     */
    public static <C,D> Predicate<Pair<C,D>> testingSecond(Predicate<D> second) {
        return a -> second.test(a.getSecond());
    }

    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
