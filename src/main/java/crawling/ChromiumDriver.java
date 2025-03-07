package crawling;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class ChromiumDriver extends BrowserDriver<ChromeDriver> {
    private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    //win
//    private final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";
//    private final String DOWNLOAD_PATH = "C:\\chromedriver\\downloads";
    //mac
    private final String WEB_DRIVER_PATH = "/Users/yd/bin/chromedriver/chromedriver";
    private final String DOWNLOAD_PATH = "/Users/yd/Downloads";
    private final String[] userAgents = {
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246", //Windows 10-based PC using Edge browser
        "Mozilla/5.0 (X11; CrOS x86_64 8172.45.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.64 Safari/537.36", //Chrome OS-based laptop using Chrome browser (Chromebook)
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9", //Mac OS X-based computer using a Safari browser
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36", //Windows 7-based PC using a Chrome browser
        "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:15.0) Gecko/20100101 Firefox/15.0.1" //Linux-based PC using a Firefox browser
    };


    public ChromiumDriver() {
        setByOs();
        setHeadless();
        setCustomOption();
        chromeDriverLogging();
        this.driver = new ChromeDriver(options);
        this.driverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
    }

    /**
     * headless(백그라운드 동작) 옵션 설정
     */
    private void setHeadless() {
        options.addArguments("--headless");
//        options.addArguments("--no-sandbox"); //Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
//        options.addArguments("--disable-gpu"); //GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
    }

    /**
     * 사람처럼 보이게 하는 옵션들
     */
    private void setCustomOption() {
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            secureRandom = new SecureRandom();
        }
        options.addArguments("user-agent="+userAgents[secureRandom.nextInt(userAgents.length)]); // 사용자 에이전트 랜덤 설정
        options.addArguments("lang=ko_KR");
        options.addArguments("--disable-notifications"); // 알림 비활성
        options.addArguments("--disable-extensions"); // 확장 프로그램 비활성
        options.addArguments("--disable-setuid-sandbox"); // root 권한 무시
        options.addArguments("--disable-dev-shm-usage"); // 공유 메모리 사용을 비활성화
        options.addArguments("--single-process");
        options.addArguments("--remote-allow-origins=*"); // 크로스 도메인 허용//
        options.addArguments("--disable-popup-blocking"); //팝업 무시
        options.addArguments("--disable-default-apps"); //기본앱 사용안함

    }

    private void setByOs() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH); //시스템에 웹 드라이버 등록

        // 웹 브라우저 프로필 설정
        options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0); // 팝업차단
        prefs.put("download.default_directory", DOWNLOAD_PATH); // 다운로드 경로 설정
        prefs.put("download.prompt_for_download", false); // 다운로드 경로 묻지 않기
        options.setExperimentalOption("prefs", prefs);
    }

    private void chromeDriverLogging() {
        // 성능 로그 설정
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
    }
}
