package cuncurrency;

import org.junit.jupiter.api.Test;

class SynchronizedTest {

    /**
     * 동기화 기법 사용 안한 케이스
     * 결과: 실패
     */
    private static int count;
    void addCount() {
        count++;
    }

    @Test
    void add_count() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                addCount();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                addCount();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count : " + count);
    }

    /**
     * volatile 키워드 사용 케이스
     * 결과: 실패
     */
    private static volatile int volatileCount;
    void volatileAddCount() {
        volatileCount += 1;
    }

    @Test
    void volatile_add_count() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                volatileAddCount();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                volatileAddCount();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count : " + volatileCount);
    }

    /**
     * synchronized 키워드 사용
     * 결과: 성공
     */
    synchronized void synchronizedAddCount() {
        count++;
    }

    @Test
    void synchronized_add_count() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronizedAddCount();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronizedAddCount();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count : " + count);
    }





}
