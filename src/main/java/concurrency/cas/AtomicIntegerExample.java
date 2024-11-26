package concurrency.cas;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicIntegerExample {

    private static final AtomicInteger count = new AtomicInteger(0);
    private static final int THREAD_NUM = 5;
    private static final int REPEAT_NUM = 1_000_000;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < REPEAT_NUM; j++) {
                    count.incrementAndGet();
                    log.info("count: {}", count.get());
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
    }
}
