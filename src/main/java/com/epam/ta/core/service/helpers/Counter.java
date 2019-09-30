package com.epam.ta.core.service.helpers;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private static final int INITIAL_VALUE = 0;

    private static volatile Counter instance;

    private Counter() {
    }

    private AtomicInteger atomicInt = new AtomicInteger(INITIAL_VALUE);

    public static Counter getInstance() {
        if (instance == null) {
            synchronized (Counter.class) {
                if (instance == null) {
                    instance = new Counter();
                }
            }
        }
        return instance;
    }

    public int increment() {
        return atomicInt.incrementAndGet();
    }

}