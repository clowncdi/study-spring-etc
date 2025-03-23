package crawling;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public class HomepageCrawlingApp {

    private final ChromiumDriver driver;
    private final WebClient webClient;
    private static final Set<String> VISITED_URLS = new HashSet<>();

    public HomepageCrawlingApp(ChromiumDriver driver, WebClient webClient) {
        this.driver = driver;
        this.webClient = webClient;
    }

    public static void main(String[] args) {
        ChromiumDriver realDriver = new ChromiumDriver();
        HomepageCrawlingApp app = new HomepageCrawlingApp(realDriver, WebClient.create());
        String sampleUrl = "https://antock.com/";
        if (isDynamicWebPage(sampleUrl)) {
            log.info("dynamic web page 수집");
        } else {
            log.info("static web page 수집");
            app.scrapeWebsite(sampleUrl);
        }
        realDriver.close();
        realDriver.quit();
    }

    private static boolean isDynamicWebPage(String sampleUrl) {
        return false;
    }

    private void scrapeWebsite(String url) {

        if (VISITED_URLS.contains(url)) {
            return;
        }
        VISITED_URLS.add(url);

        try {
            // HTML 가져오기
            String html = webClient.get()
                .uri(URI.create(url))
                .retrieve()
                .bodyToMono(String.class)
                .block();

            if (html == null) {
                return;
            }

            // Jsoup 으로 HTML 파싱
            Document document = Jsoup.parse(html);
            String text = document.body().text();
            log.info("Body text: {}", text);

            // Canonical URL 확인
            Element canonicalTag = document.selectFirst("link[rel=canonical]");
            if (canonicalTag != null) {
                String canonicalUrl = canonicalTag.attr("href");
                log.info("Canonical URL: {}", canonicalUrl);
                if (!canonicalUrl.equals(url) && !VISITED_URLS.contains(canonicalUrl)) {
                    scrapeWebsite(canonicalUrl);
                    return;
                }
            }

            // 앵커 태그 크롤링 (재귀)
            for (Element link : document.select("a[href]")) {
                String nextUrl = link.absUrl("href");
                if (isValidUrl(nextUrl, url)) {
                    scrapeWebsite(nextUrl);
                }
            }
        } catch (Exception e) {
            log.error("홈페이지 스크래핑 실패. url: {}", url, e);
        }
    }

    private boolean isValidUrl(String nextUrl, String baseUrl) {
        return nextUrl.startsWith(baseUrl) && !VISITED_URLS.contains(nextUrl);
    }

}
