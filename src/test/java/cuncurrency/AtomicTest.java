package cuncurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import org.junit.jupiter.api.Test;

public class AtomicTest {

    private static int value = 0;
    private static final int THREAD_COUNT = 3;

    @Test
    void non_atomic_test() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {

            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    int expectedValue = value;
                    int newValue = expectedValue + 1;
                    value = newValue;
                    System.out.println(Thread.currentThread().getName() + ": " + expectedValue + " / " + newValue);
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("최종 value = " + value);
    }

    private static final AtomicInteger count = new AtomicInteger(0);

    @Test
    void atomic_test() throws InterruptedException {

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {

            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    int incremented = count.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + ": " + incremented);
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("최종 value = " + count.get());
    }

    @Test
    void atomic_api_test() {
        AtomicInteger atomicInteger = new AtomicInteger(3);
        System.out.println("atomicInteger: " + atomicInteger.get());    // atomicInteger: 3

        atomicInteger.set(10);
        System.out.println("set(10): " + atomicInteger.get());  // set(10): 10

        int andSet = atomicInteger.getAndSet(20);
        System.out.println("getAndSet(20): " + andSet); // getAndSet(20): 10

        System.out.println("incrementAndGet: " + atomicInteger.incrementAndGet());  // incrementAndGet: 21

        boolean isSuccess = atomicInteger.compareAndSet(21, 30);
        System.out.println("compareAndSet successful? " + isSuccess + ", result: " + atomicInteger.get());  // compareAndSet successful? true, result: 30

        IntUnaryOperator addTen = value -> value + 10;
        int beforeAdd = atomicInteger.getAndUpdate(addTen);
        int afterAdd = atomicInteger.get();
        System.out.println("getAndUpdate before: " + beforeAdd + ", afterAdd: " + afterAdd);    // getAndUpdate before: 30, afterAdd: 40
    }

}
