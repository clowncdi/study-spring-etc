package crawling;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class BrowserDriver <T extends ChromeDriver> {

    protected T driver;
    public WebDriverWait driverWait;
    public ChromeOptions options;

    /**
     * 페이지 열기
     */
    public void open(String url) {
        try {
            log.info("Chrome Open URL : {}", url);
            this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 암묵적 대기 설정: 지정 시간동안 대기하며, 요소가 나타나면 즉시 진행
            this.driver.get(url);
            this.driver.manage().window().maximize();
        } catch (Exception e) {
            log.error("Chrome Open URL Error : {}", e.getMessage());
        }
    }

    /**
     * Selector가 로드 됐을 때 불러오기
     */
    public WebElement get(String selector) {
        WebElement element = null;
        try {
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
        } catch (WebDriverException e) {
            log.error("{} 오브젝트를 불러오는데 실패했습니다.", selector);
        }
        return element;
    }

    /**
     * Xpath가 로드 됐을 때 불러오기
     */
    public WebElement getXpath(String xPath) {
        WebElement element = null;
        try {
            element = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
        } catch (WebDriverException e) {
            log.error("{} 오브젝트를 불러오는데 실패했습니다.", xPath);
        }
        return element;
    }

    /**
     * Xpath가 로드 됐을 때 리스트로 불러오기
     */
    public List<WebElement> getListXpath(String xPath) {
        List<WebElement> element = null;
        try {
            element = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xPath)));
        } catch (WebDriverException e) {
            log.error("{} 오브젝트를 불러오는데 실패했습니다.", xPath);
        }
        return element;
    }

    /**
     * driver close
     */
    public void close() {
        if (driver != null) {
            driver.close();
        }
    }

    /**
     * driver quit
     */
    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * 일정 시간 대기
     */
    public void wait(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

}
