package study;

import org.junit.jupiter.api.Test;

class TempTest {

  @Test
  void forTryCatchTest() {
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      try {
        if (i == 5) {
          throw new Exception();
        }
      } catch (Exception e) {
        System.out.println("catch");
      }
    }
  }
}
