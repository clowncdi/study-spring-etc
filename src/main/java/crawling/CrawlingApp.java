package crawling;

import crawling.dto.FinanceType;
import crawling.dto.NaverWorldFinance;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
public class CrawlingApp {

    private final static ChromiumDriver driver = new ChromiumDriver();

    public static void main(String[] args) {


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
        String xPathVix = "//*[@id=\"content\"]/div[10]/div[2]/div[3]/table/tbody/tr[1]";
        crawling(FinanceType.WTI, collect, xPath); //WTI
        crawling(FinanceType.DXY, collect, xPath); //달러인덱스
        crawling(FinanceType.VIX, collect, xPathVix); //VIX
        crawling(FinanceType.GOLD, collect, xPath); //국제 금
        crawlingBitcoin(FinanceType.BTC, collect); //비트코인

        log.info("result = " + collect);
        driver.close();
        driver.quit();
    }

    private static void crawling(FinanceType type, List<NaverWorldFinance> collect, String xPath) {
        driver.open(type.getUrl());
        driver.wait(1);
        validModal();
        collect.add(driver.getListXpath(xPath).stream()
            .map(el -> NaverWorldFinance.builder()
                .title(type.getTitle())
                .price(el.findElement(By.xpath(".//td[2]")).getText())
                .rate(el.findElement(By.xpath(".//td[4]")).getText())
                .build())
            .findFirst().get());
    }

    private static void validModal() {
        WebElement xpath = driver.getXpath("//div[contains(@class, 'BottomModalNoticeWrapper-module_inner')]");
        if (xpath != null) {
            log.info("modal 존재함");
            driver.getXpath("//button[contains(@class, 'BottomModalNoticeWrapper-module_button-close')]").click();
        }
    }

    private static void crawlingBitcoin(FinanceType type, List<NaverWorldFinance> collect) {
        driver.open(type.getUrl());
        driver.wait(1);
        collect.add(NaverWorldFinance.builder()
            .title(type.getTitle())
            .price(driver.getXpath("//*[@id=\"content\"]/div[2]/div[1]/div").getText())
            .build());
    }
}
