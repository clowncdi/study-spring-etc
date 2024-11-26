package concurrency.cas;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicIntegerGetAndUpdateExample {

    private static final AtomicInteger account = new AtomicInteger(10000);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int withdrawalAmount = 3000;
                int updatedBalance = account.getAndUpdate(balance -> {
                    if (balance >= withdrawalAmount) {
                        return balance - withdrawalAmount;
                    } else {
                        return balance; // 출금 실패
                    }
                });
                if (updatedBalance >= withdrawalAmount) {
                    log.info("{} 잔고 {} 원에서 {} 원 출금 성공 🅾️", Thread.currentThread().getName(), updatedBalance, withdrawalAmount);
                } else {
                    log.info("{} 잔고 {} 원으로 {} 원 출금 실패 ❎", Thread.currentThread().getName(), updatedBalance, withdrawalAmount);
                }
            }).start();
        }
    }
}
