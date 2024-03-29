package reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ReactorApp {
    public static void main(String[] args) throws InterruptedException {
        final List<String> basket1 = Arrays.asList("kiwi", "orange", "lemon", "orange", "lemon", "kiwi");
        final List<String> basket2 = Arrays.asList(new String[]{"banana", "lemon", "lemon", "kiwi"});
        final List<String> basket3 = Arrays.asList(new String[]{"strawberry", "orange", "lemon", "grape", "strawberry"});
        final List<List<String>> baskets = Arrays.asList(basket1, basket2, basket3);
        final Flux<List<String>> basketFlux = Flux.fromIterable(baskets);

        CountDownLatch countDownLatch = new CountDownLatch(5);

        basketFlux.concatMap(basket -> {
            final Mono<List<String>> distinctFruits = Flux.fromIterable(basket).log().distinct().collectList().subscribeOn(Schedulers.parallel());
            final Mono<Map<String, Long>> countFruitsMono = Flux.fromIterable(basket).log()
                    .groupBy(fruit -> fruit) // 바구니로부터 넘어온 과일 기준으로 group을 묶는다.
                    .concatMap(groupedFlux -> groupedFlux.count()
                            .map(count -> {
                                final Map<String, Long> fruitCount = new LinkedHashMap<>();
                                fruitCount.put(groupedFlux.key(), count);
                                return fruitCount;
                            }) // 각 과일별로 개수를 Map으로 리턴
                    ) // concatMap으로 순서보장
                    .reduce((accumulatedMap, currentMap) -> new LinkedHashMap<String, Long>() {
                        {
                            putAll(accumulatedMap);
                            putAll(currentMap);
                        }
                    }) // 그동안 누적된 accumulatedMap에 현재 넘어오는 currentMap을 합쳐서 새로운 Map을 만든다. map끼리 putAll하여 하나의 Map으로 만든다.
                    .subscribeOn(Schedulers.parallel());
            return Flux.zip(distinctFruits, countFruitsMono, FruitInfo::new);
        }).subscribe(
                System.out::println, // 값이 넘어올 때 호출 됨, onNext(T)
                error -> {
                    System.out.println(error);
                    countDownLatch.countDown();
                }, // 에러 발생시 출력하고 countDown, onError(Throwable)
                () -> {
                    System.out.println("complete");
                    countDownLatch.countDown();
                } // 정상적 종료시 countDown, onComplete()
        );
        countDownLatch.await(2, TimeUnit.SECONDS);

//        basketFlux.concatMap(basket -> {
//            final Mono<List<String>> distinctFruits = Flux.fromIterable(basket).distinct().collectList();
//            final Mono<Map<String, Long>> countFruitsMono = Flux.fromIterable(basket)
//                    .groupBy(fruit -> fruit) // 바구니로부터 넘어온 과일 기준으로 group을 묶는다.
//                    .concatMap(groupedFlux -> groupedFlux.count()
//                            .map(count -> {
//                                final Map<String, Long> fruitCount = new LinkedHashMap<>();
//                                fruitCount.put(groupedFlux.key(), count);
//                                return fruitCount;
//                            }) // 각 과일별로 개수를 Map으로 리턴
//                    ) // concatMap으로 순서보장
//                    .reduce((accumulatedMap, currentMap) -> new LinkedHashMap<String, Long>() {
//                        {
//                            putAll(accumulatedMap);
//                            putAll(currentMap);
//                        }
//                    });// 그동안 누적된 accumulatedMap에 현재 넘어오는 currentMap을 합쳐서 새로운 Map을 만든다. map끼리 putAll하여 하나의 Map으로 만든다.
//            return Flux.zip(distinctFruits, countFruitsMono, FruitInfo::new);
//        }).subscribe(System.out::println);
    }
}
