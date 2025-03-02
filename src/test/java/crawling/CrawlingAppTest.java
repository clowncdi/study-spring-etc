package crawling;

import crawling.dto.FinanceType;
import crawling.dto.NaverWorldFinance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

class CrawlingAppTest {

    private ChromiumDriver mockDriver; // 크롬 드라이버 모의 객체
    private WebElement mockAmericaIndex; // 미국 지수 섹션 모의 객체
    private WebElement mockWebElement; // 일반 웹 요소 모의 객체
    private WebElement mockModalButton; // 모달 닫기 버튼 모의 객체
    private WebElement mockVixPrice; // VIX 가격 모의 객체
    private WebElement mockVixRate; // VIX 변동률 모의 객체
    private WebElement mockBitcoinPrice; // 비트코인 가격 모의 객체
    private CrawlingApp crawlingApp;

    @BeforeEach
    void setUp() {
        // 각 테스트 메서드 실행 전에 모의 객체 초기화
        mockDriver = mock(ChromiumDriver.class);
        mockAmericaIndex = mock(WebElement.class);
        mockWebElement = mock(WebElement.class);
        mockModalButton = mock(WebElement.class);
        mockVixPrice = mock(WebElement.class);
        mockVixRate = mock(WebElement.class);
        mockBitcoinPrice = mock(WebElement.class);
        crawlingApp = new CrawlingApp(mockDriver);
    }

    @Test
    @DisplayName("미국지수 크롤링")
    void main_crawling_americaIndex() {
        //given
        when(mockDriver.get(anyString())).thenReturn(mockAmericaIndex);
        when(mockDriver.get("#americaIndex")).thenReturn(mockAmericaIndex);
        when(mockDriver.getXpath(anyString())).thenReturn(mockWebElement);
        when(mockDriver.getListXpath(anyString())).thenReturn(Collections.singletonList(mockWebElement));
        when(mockDriver.getXpath("//button[contains(@class, 'BottomModalNoticeWrapper-module_button-close')]")).thenReturn(mockModalButton);
        when(mockDriver.getXpath("//*[@id=\"stockContentWrapper\"]/div[3]/div[1]/div[1]/strong")).thenReturn(mockVixPrice);
        when(mockDriver.getXpath("//*[@id=\"stockContentWrapper\"]/div[3]/div[1]/div[1]/div/span[2]")).thenReturn(mockVixRate);
        when(mockDriver.getXpath("//*[@id=\"content\"]/div[2]/div[1]/div")).thenReturn(mockBitcoinPrice);

        when(mockVixPrice.getText()).thenReturn("21.13");
        when(mockVixRate.getText()).thenReturn("10.23%");
        when(mockBitcoinPrice.getText()).thenReturn("125,000,000원");

        List<WebElement> tableRows = Arrays.asList(
            createMockTableRow("skip line", "0", "0%"),
            createMockTableRow("S&P 500", "4,500", "+1.00%"),
            createMockTableRow("다우 산업", "34,000", "+0.75%"),
            createMockTableRow("나스닥 종합", "14,000", "+0.50%")
        );
        when(mockAmericaIndex.findElements(By.cssSelector("thead > tr"))).thenReturn(tableRows);

        when(mockWebElement.findElement(By.xpath(".//td[2]"))).thenReturn(mockWebElement);
        when(mockWebElement.findElement(By.xpath(".//td[4]"))).thenReturn(mockWebElement);
        when(mockWebElement.getText()).thenReturn("1234").thenReturn("+12%");

        //when
        crawlingApp.execute();

        //then
        verify(mockDriver, times(1)).open("https://finance.naver.com/world/");
        verify(mockDriver, times(1)).get("#americaIndex");
        verify(mockAmericaIndex, times(1)).findElements(By.cssSelector("thead > tr"));
        verify(mockDriver, times(1)).open(FinanceType.WTI.getUrl());
        verify(mockDriver, times(1)).open(FinanceType.DXY.getUrl());
        verify(mockDriver, times(1)).open(FinanceType.GOLD.getUrl());
        verify(mockDriver, times(1)).open(FinanceType.VIX.getUrl());
        verify(mockDriver, times(1)).open(FinanceType.BTC.getUrl());
        verify(mockDriver, atLeastOnce()).close();
        verify(mockDriver, atLeastOnce()).quit();
    }

    @Test
    @DisplayName("crawling 메소드 테스트 - WTI")
    void craling_wti() {
        //given
        List<NaverWorldFinance> collect = new ArrayList<>();
        String xPath = "//tr[contains(@class, 'PriceList_tr')][1]";
        when(mockDriver.getListXpath(xPath)).thenReturn(List.of(mockWebElement));
        when(mockDriver.getXpath(anyString())).thenReturn(mockWebElement);
        when(mockWebElement.findElement(By.xpath(".//td[2]"))).thenReturn(mockWebElement);
        when(mockWebElement.findElement(By.xpath(".//td[4]"))).thenReturn(mockWebElement);
        when(mockWebElement.getText()).thenReturn("1234").thenReturn("+12%");

        //when
        crawlingApp.crawling(FinanceType.WTI, collect, xPath);

        //then
        assertThat(collect).hasSize(1);
        NaverWorldFinance result = collect.getFirst();
        assertThat(result.getTitle()).isEqualTo("WTI");
        assertThat(result.getPrice()).isEqualTo("1234");
        assertThat(result.getRate()).isEqualTo("+12%");
        verify(mockDriver, times(1)).open(FinanceType.WTI.getUrl());
    }

    @Test
    @DisplayName("cralingVix 메소드 테스트")
    void craling_vix() {
        //given
        List<NaverWorldFinance> collect = new ArrayList<>();
        when(mockDriver.getXpath(
            "//*[@id=\"stockContentWrapper\"]/div[3]/div[1]/div[1]/strong")).thenReturn(
            mockVixPrice);
        when(mockDriver.getXpath("//*[@id=\"stockContentWrapper\"]/div[3]/div[1]/div[1]/div/span[2]")).thenReturn(mockVixRate);
        when(mockVixPrice.getText()).thenReturn("21.13");
        when(mockVixRate.getText()).thenReturn("10.23%");

        //when
        crawlingApp.crawlingVix(collect);

        //then
        assertThat(collect).hasSize(1);
        NaverWorldFinance result = collect.getFirst();
        assertThat(result.getTitle()).isEqualTo("VIX: 변동성 지수");
        assertThat(result.getPrice()).isEqualTo("21.13");
        assertThat(result.getRate()).isEqualTo("10.23%");
        verify(mockDriver, times(1)).open(FinanceType.VIX.getUrl());
    }

    @Test
    @DisplayName("crawlingBitcoin 메소드 테스트")
    void crawling_bitcoin() {
        //given
        List<NaverWorldFinance> collect = new ArrayList<>();
        when(mockDriver.getXpath("//*[@id=\"content\"]/div[2]/div[1]/div")).thenReturn(mockBitcoinPrice);
        when(mockBitcoinPrice.getText()).thenReturn("BITCOIN_PRICE");

        //when
        crawlingApp.crawlingBitcoin(collect);

        //then
        assertEquals(1, collect.size());
        NaverWorldFinance result = collect.getFirst();
        assertEquals("비트코인", result.getTitle());
        assertEquals("BITCOIN_PRICE", result.getPrice());
        assertNull(result.getRate());
        verify(mockDriver, times(1)).open(FinanceType.BTC.getUrl());
    }

    @Test
    @DisplayName("validModal 메소드 테스트 - 모달 존재")
    void valiModal_exists() {
        //given
        when(mockDriver.getXpath("//div[contains(@class, 'BottomModalNoticeWrapper-module_inner')]")).thenReturn(mockWebElement);
        when(mockDriver.getXpath("//button[contains(@class, 'BottomModalNoticeWrapper-module_button-close')]")).thenReturn(mockModalButton);

        //when
        crawlingApp.validModal();

        //then
        verify(mockDriver, times(1)).getXpath("//div[contains(@class, 'BottomModalNoticeWrapper-module_inner')]");
        verify(mockDriver, times(1)).getXpath("//button[contains(@class, 'BottomModalNoticeWrapper-module_button-close')]");
        verify(mockModalButton, times(1)).click();
    }

    @Test
    @DisplayName("validModal 메소드 테스트 - 모달 없음")
    void validModal_notExists() {
        // Given
        when(mockDriver.getXpath("//div[contains(@class, 'BottomModalNoticeWrapper-module_inner')]")).thenReturn(null);

        // When
        crawlingApp.validModal();

        // Then
        verify(mockDriver, times(1)).getXpath("//div[contains(@class, 'BottomModalNoticeWrapper-module_inner')]");
        verify(mockDriver, never()).getXpath("//button[contains(@class, 'BottomModalNoticeWrapper-module_button-close')]");
    }

    private WebElement createMockTableRow(String title, String price, String rate) {
        WebElement row = mock(WebElement.class);
        WebElement titleElement = mock(WebElement.class);
        WebElement priceElement = mock(WebElement.class);
        WebElement rateElement = mock(WebElement.class);

        when(row.findElement(By.cssSelector(".tb_td2"))).thenReturn(titleElement);
        when(row.findElement(By.cssSelector(".tb_td3"))).thenReturn(priceElement);
        when(row.findElement(By.cssSelector(".tb_td5"))).thenReturn(rateElement);

        when(titleElement.getText()).thenReturn(title);
        when(priceElement.getText()).thenReturn(price);
        when(rateElement.getText()).thenReturn(rate);

        return row;
    }

}