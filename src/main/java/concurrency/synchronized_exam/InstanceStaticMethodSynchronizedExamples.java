package concurrency.synchronized_exam;

public class InstanceStaticMethodSynchronizedExamples {

  private static int staticCount = 0;
  private int instanceCount = 0;

  public synchronized void incrementInstanceCount() {
    instanceCount++;
    System.out.println(Thread.currentThread().getName() + " 가 증가 시켰습니다. 현재 값: " + instanceCount);
  }

  public static synchronized void incrementStaticCount() {
    staticCount++;
    System.out.println(Thread.currentThread().getName() + " 가 감소 시켰습니다. 현재 값: " + staticCount);
  }

  public static void main(String[] args) {

    InstanceStaticMethodSynchronizedExamples examples = new InstanceStaticMethodSynchronizedExamples();

    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        examples.incrementInstanceCount();
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        examples.incrementInstanceCount();
      }
    });

    Thread thread3 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        InstanceStaticMethodSynchronizedExamples.incrementStaticCount();
      }
    });

    Thread thread4 = new Thread(() -> {
      for (int i = 0; i < 1000000; i++) {
        InstanceStaticMethodSynchronizedExamples.incrementStaticCount();
      }
    });

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

    try {
      thread1.join();
      thread2.join();
      thread3.join();
      thread4.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println("최종 값: " + examples.instanceCount);
    System.out.println("최종 값: " + staticCount);

  }

}
