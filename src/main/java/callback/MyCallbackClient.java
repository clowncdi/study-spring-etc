package callback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyCallbackClient {

    public static void main(String[] args) {
        log.info("===== 작업 시작 =====");
        MyCallbackCreator creator = new SampleCallbackFactory();
        String key = "a";

        String result = creator.getSampleData(key, new MyCallback<>() {
            @Override
            public void onReceived(String data) {
                log.info("onReceived = {}", data);
            }

            @Override
            public void onError(Throwable error) {
                log.error("error 발생!", error);
            }

            @Override
            public void onComplete() {
                log.info("onComplete = '{}' 호출 완료", key);
            }
        });

        log.info("최종 결과 => {}", result);
    }
}
