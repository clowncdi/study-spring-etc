package crawling;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

@Slf4j
public class CrawlingApp {
    public static void main(String[] args) throws IOException {

        String url = "https://finance.naver.com/world/";
        ChromiumDriver driver = new ChromiumDriver();
        driver.init();
        driver.open(url);
        driver.wait(1);

        WebElement americaIndex = driver.get("#americaIndex");
        List<WebElement> list = americaIndex.findElements(By.className("point_dn"));
        for (WebElement webElement : list) {
            System.out.println("title = " + webElement.findElement(By.cssSelector(".tb_td2")).getText());
            System.out.println("price = " + webElement.findElement(By.cssSelector(".tb_td3")).getText());
            System.out.println("rate = " + webElement.findElement(By.cssSelector(".tb_td5")).getText());
            System.out.println();
        }

    }
}
