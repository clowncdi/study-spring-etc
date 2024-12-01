package cuncurrency;

import org.junit.jupiter.api.Test;

public class VolatileTest {

    volatile boolean flag = true;

    @Test
    void volatile_test() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            int count = 0;
            while (flag) {
                count++;
            }
            System.out.println("thread1 count = " + count);
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("thread2 종료");
            flag = false;
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("테스트 종료");
    }

}
