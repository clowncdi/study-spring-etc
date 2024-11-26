package concurrency.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicIntegerFieldUpdaterExample {

    public static void main(String[] args) {
        AtomicReferenceFieldUpdater<MyClass, String> nameUpdater = AtomicReferenceFieldUpdater.newUpdater(MyClass.class, String.class, "name");
        AtomicIntegerFieldUpdater<MyClass> ageUpdater = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "age");

        MyClass myClass = new MyClass("jack", 10);

        nameUpdater.set(myClass, "smith");
        log.info("{}", myClass);
        nameUpdater.compareAndSet(myClass, "smith", "sandel");
        log.info("{}", myClass);
        ageUpdater.compareAndSet(myClass, 10, 20);
        log.info("{}", myClass);
        ageUpdater.set(myClass, 30);
        log.info("{}", myClass);
    }

    @ToString
    @Getter
    @AllArgsConstructor
    static class MyClass {
        private volatile String name;
        private volatile int age;
    }

    @ToString
    @Getter
    @AllArgsConstructor
    static class MyClass2 {
        private AtomicReference<String> name = new AtomicReference<>("jack");
        private AtomicInteger age = new AtomicInteger(10);
    }
}
