package crawling;

import crawling.dto.FinanceType;
import crawling.dto.NaverWorldFinance;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
public class CrawlingApp {

    private final ChromiumDriver driver;

    public CrawlingApp(ChromiumDriver driver) {
        this.driver = driver;
    }

    public static void main(String[] args) {
        ChromiumDriver realDriver = new ChromiumDriver();
        CrawlingApp app = new CrawlingApp(realDriver);
        app.execute(); // 실행
    }

    public void execute() {
        driver.open("https://finance.naver.com/world/");
        WebElement americaIndex = driver.get("#americaIndex");
        List<NaverWorldFinance> collect = americaIndex.findElements(By.cssSelector("thead > tr")).stream()
            .skip(1)
            .filter(el -> FinanceType.isExist(el.findElement(By.cssSelector(".tb_td2")).getText()))
            .map(el -> NaverWorldFinance.builder()
                .title(el.findElement(By.cssSelector(".tb_td2")).getText())
                .price(el.findElement(By.cssSelector(".tb_td3")).getText())
                .rate(el.findElement(By.cssSelector(".tb_td5")).getText())
                .build())
            .sorted(Comparator.comparingInt(NaverWorldFinance::getPriority))
            .collect(Collectors.toList());

        String xPath = "//tr[contains(@class, 'PriceList_tr')][1]";
        crawling(FinanceType.WTI, collect, xPath); //WTI
        crawling(FinanceType.DXY, collect, xPath); //달러인덱스
        crawling(FinanceType.GOLD, collect, xPath); //국제 금
        crawlingVix(collect); //VIX
        crawlingBitcoin(collect); //비트코인

        log.info("result: {}", collect);
        driver.close();
        driver.quit();
    }

    public void crawling(FinanceType type, List<NaverWorldFinance> collect, String xPath) {
        driver.open(type.getUrl());
        driver.wait(1);
//        validModal();
        collect.add(driver.getListXpath(xPath).stream()
            .map(el -> NaverWorldFinance.builder()
                .title(type.getTitle())
                .price(el.findElement(By.xpath(".//td[2]")).getText())
                .rate(el.findElement(By.xpath(".//td[4]")).getText())
                .build())
            .findFirst().orElse(null));
    }

    public void crawlingVix(List<NaverWorldFinance> collect) {
        driver.open(FinanceType.VIX.getUrl());
        driver.wait(1);
        collect.add(NaverWorldFinance.builder()
            .title(FinanceType.VIX.getTitle())
            .price(driver.getXpath("//*[@id=\"stockContentWrapper\"]/div[3]/div[1]/div[1]/strong").getText())
            .rate(driver.getXpath("//*[@id=\"stockContentWrapper\"]/div[3]/div[1]/div[1]/div/span[2]").getText())
            .build());
    }

    public void crawlingBitcoin(List<NaverWorldFinance> collect) {
        driver.open(FinanceType.BTC.getUrl());
        driver.wait(1);
        collect.add(NaverWorldFinance.builder()
            .title(FinanceType.BTC.getTitle())
            .price(driver.getXpath("//*[@id=\"content\"]/div[2]/div[1]/div").getText())
            .build());
    }

    public void validModal() {
        Optional.ofNullable(driver.getXpath("//div[contains(@class, 'BottomModalNoticeWrapper-module_inner')]"))
            .ifPresent(el -> {
                log.info("modal 존재함");
                driver.getXpath("//button[contains(@class, 'BottomModalNoticeWrapper-module_button-close')]").click();
            });
    }
}
