package concurrency.cas;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NonAtomicExample {

    private static int value = 0;
    private static final int THREAD_COUNT = 3;

    public static void main(String[] args) {

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {

            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    int expectedValue = value;
                    int newValue = expectedValue + 1;
                    value = newValue;
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

        log.info("최종 value = {}", value);
    }

}
