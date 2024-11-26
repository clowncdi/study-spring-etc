package concurrency.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicIntegerApiExample {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(3);
        log.info("currentValue: {}", atomicInteger.get());

        atomicInteger.set(10);
        log.info("set(10): {}", atomicInteger.get());

        int andSet = atomicInteger.getAndSet(20);
        log.info("getAndSet(20): {}", andSet);

        log.info("incrementAndGet: {}", atomicInteger.incrementAndGet());

        boolean isSuccess = atomicInteger.compareAndSet(21, 30);
        log.info("compareAndSet successful? {}, result: {}", isSuccess, atomicInteger.get());

        IntUnaryOperator addTen = value -> value + 10;
        int beforeAdd = atomicInteger.getAndUpdate(addTen);
        int afterAdd = atomicInteger.get();
        log.info("getAndUpdate before: {}, after: {}", beforeAdd, afterAdd);


    }
}
