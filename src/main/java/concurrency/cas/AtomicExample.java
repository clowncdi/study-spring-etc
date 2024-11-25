package concurrency.cas;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicExample {

    private static final AtomicInteger value = new AtomicInteger(0);
    private static final int THREAD_COUNT = 3;

    public static void main(String[] args) {

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {

            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    int expectedValue;
                    int newValue;

                    do {
                        expectedValue = value.get();
                        newValue = expectedValue + 1;
                    } while (!value.compareAndSet(expectedValue, newValue));
                    log.info("{}: {} / {}", Thread.currentThread().getName(), expectedValue, newValue);
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        log.info("최종 value = {}", value.get());
    }

}
