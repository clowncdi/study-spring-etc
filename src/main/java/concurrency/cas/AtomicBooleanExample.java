package concurrency.cas;

import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicBooleanExample {

    private static final AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    log.info("{} busy waiting...", Thread.currentThread().getName());
                }
                log.info("{} critical section...", Thread.currentThread().getName());
                flag.set(false);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    log.info("{} busy waiting...", Thread.currentThread().getName());
                }
                log.info("{} critical section...", Thread.currentThread().getName());
                flag.set(false);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        thread1.start();
        thread2.start();
    }
}
