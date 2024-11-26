package concurrency.cas;

import java.util.concurrent.atomic.AtomicReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicReferenceExample {

    public static void main(String[] args) {
        User a = new User("a", 10);
        User b = new User("b", 20);
        User c = new User("c", 30);

        AtomicReference<User> user = new AtomicReference<>(a);

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            while (!user.compareAndSet(a, b)) {
                log.info("{} {} 유저로 변경 실패 ❎", Thread.currentThread().getName(), b.getName());
            }
            log.info("{} {} 유저로 변경 성공 🅾️", Thread.currentThread().getName(), b.getName());
        });

        Thread thread2 = new Thread(() -> {
            while (!user.compareAndSet(b, c)) {
                log.info("{} {} 유저로 변경 실패 ❎", Thread.currentThread().getName(), c.getName());
            }
            log.info("{} {} 유저로 변경 성공 🅾️", Thread.currentThread().getName(), c.getName());
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("result user: {}", user.get());
    }

    @ToString
    @Getter
    @AllArgsConstructor
    static class User {
        private String name;
        private int age;
    }
}
