package callback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleCallbackFactory implements MyCallbackCreator {

    @Override
    public String getSampleData(String key, MyCallback<String> callback) {
        log.info("SampleCallbackFactory 호출");

        if (key == null) {
            callback.onError(new IllegalArgumentException("key is null"));
            return null;
        }

        String data = switch (key) {
            case "a" -> "'a' 호출";
            case "b" -> "'b' callback 호출";
            case "c" -> "'c' callback 호출";
            default -> "'default' callback 호출";
        };

        callback.onReceived(data);
        callback.onComplete();

        return key + ":" + data + " => 호출 완료";
    }
}
