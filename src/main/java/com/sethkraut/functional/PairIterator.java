package com.sethkraut.functional;

import java.util.Iterator;

/**
 *
 */
public class PairIterator<A,B> implements Iterator<Pair<A,B>> {
    private final Iterator<A> aIterator;
    private final Iterator<B> bIterator;

    public PairIterator(Iterator<A> aIterator, Iterator<B> bIterator) {
        this.aIterator = aIterator;
        this.bIterator = bIterator;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Pair<A, B> next() {
        return null;
    }
}
