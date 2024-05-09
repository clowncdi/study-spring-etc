package concurrency.synchronized_exam;

public class InstanceMethodSynchronizedExamples2 {

  private int count = 0;

  public synchronized void increment() {
    this.count++;
    System.out.println(Thread.currentThread().getName() + " 가 증가 시켰습니다. 현재 값: " + this.count);
  }

  public synchronized void decrement() {
    this.count--;
    System.out.println(Thread.currentThread().getName() + " 가 감소 시켰습니다. 현재 값: " + this.count);
  }

  public int getCount() {
    return this.count;
  }

  public static void main(String[] args) {

    InstanceMethodSynchronizedExamples2 instanceMethod1 = new InstanceMethodSynchronizedExamples2();
    InstanceMethodSynchronizedExamples2 instanceMethod2 = new InstanceMethodSynchronizedExamples2();

    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        instanceMethod1.increment();
        instanceMethod2.decrement();
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        instanceMethod1.decrement();
        instanceMethod2.increment();
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

    System.out.println("최종 값: " + instanceMethod1.getCount());

  }

}
