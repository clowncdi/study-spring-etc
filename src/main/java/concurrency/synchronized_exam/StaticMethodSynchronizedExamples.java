package concurrency.synchronized_exam;

public class StaticMethodSynchronizedExamples {

  private static int count = 0;

  public static synchronized void increment() {
    count++;
    System.out.println(Thread.currentThread().getName() + " 가 증가 시켰습니다. 현재 값: " + count);
  }

  public static synchronized void decrement() {
    count--;
    System.out.println(Thread.currentThread().getName() + " 가 감소 시켰습니다. 현재 값: " + count);
  }

  public static int getCount() {
    return count;
  }

  public static void main(String[] args) {

    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        StaticMethodSynchronizedExamples.increment();
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        StaticMethodSynchronizedExamples.decrement();
      }
    });

    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println("최종 값: " + StaticMethodSynchronizedExamples.getCount());

  }

}
