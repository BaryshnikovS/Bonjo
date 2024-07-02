package steps;

import com.codeborne.selenide.Selenide;
import cucumber.api.java.en.And;
import cucumber.api.java.ru.Пусть;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.bg.И;
import io.cucumber.java.ja.但し;
import io.cucumber.java.ru.Затем;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.lanit.at.Config;
import ru.lanit.at.exceptions.FrameworkRuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class NewSampleSteps extends BaseSteps {
    public WebDriver driver;
    @Пусть("^открываем тестируемое приложение$")
    public void openApp() {
        String url = System.getProperty("site.url", Config.loadProperty("site.url"));
        if (url == null || url.isEmpty())
            throw new FrameworkRuntimeException("Не указан стенд, установите урл для стенда выполнения");
        getDriver().get(url);
    }
    @И("^открываем сайт$")
    public void открытьСайт() {
        String url = System.getProperty("site.url", Config.loadProperty("site.url"));
        if (url == null || url.isEmpty())
            throw new FrameworkRuntimeException("Не указан стенд, установите урл для стенда выполнения");
        getDriver().get(url);
    }
    @Затем("Открыть {element} под номером {int}")
    public void открытьНомерПоСпискуНовость(WebElement element, int numberNews) {
        List<WebElement> newsItems = element.findElements(By.xpath("//b[@class=\"tbl-mn_new-header\"]"));

        if(numberNews < 1 || numberNews > newsItems.size()) {
            throw new IllegalArgumentException("Номер новости вне диапазона: " + numberNews);
        }
        WebElement targetElement = newsItems.get(numberNews - 1);
        targetElement.click();
    }

    @И("проверить, что в блоке отображаются списки:")
    public void проверитьЧтоОтображаютсяСписки(DataTable dataTable) {
        driver = new ChromeDriver();
        List<String> expectedTopics = dataTable.asList(String.class);

        List<WebElement> menuItems = driver.findElements(By.xpath("//div[@class='menuitem']/a"));
        List<String> actualTopics = new ArrayList<>();
        for (WebElement menuItem : menuItems) {
            String text = menuItem.getText().trim();
            actualTopics.add(text);
        }

        System.out.println("Ожидаемые темы:");
        expectedTopics.forEach(System.out::println);

        System.out.println("Фактические темы:");
        actualTopics.forEach(System.out::println);

        // Проверяем, что количество ожидаемых тем совпадает с фактическими
        Assert.assertEquals(expectedTopics.size(), actualTopics.size());

        // Проверяем, что каждая ожидаемая тема содержится в фактических темах
        for (String expectedTopic : expectedTopics) {
            Assert.assertTrue(actualTopics.contains(expectedTopic), "Тема \"" + expectedTopic + "\" не найдена в фактических темах.");
        }
    }

    @И("нажать на элемент с атрибутом {string}")
    public void findAndClickElementByValue(String value) {
        driver = new ChromeDriver();
        try {
            WebElement element = driver.findElement(By.xpath("//input[@value='" + value + "']"));
            element.click();
        } catch (NoSuchElementException | NullPointerException e) {
            System.out.println("Не удалось найти элемент с атрибутом value = '" + value + "'");
            e.printStackTrace();
        }
    }

    @И("ожидать {int} секунд")
    public void ожидатьСекунд(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Ошибка во время ожидания: " + e.getMessage());
        }
    }

    @И("переключиться на вкладку номер {int}")
    public void переключитьсяНаВкладкуНомер(int index) {
        driver = new ChromeDriver();

        Set<String> windowHandles = driver.getWindowHandles();

        List<String> windowHandlesList = new ArrayList<>(windowHandles);

        if (index < 0 || index >= windowHandlesList.size()) {
            throw new IllegalArgumentException("Недопустимый индекс вкладки: " + index + " - Количество окон " + windowHandles.size());
        }

        driver.switchTo().window(windowHandlesList.get(index));

    }

    @И("переключиться на вкладму с именем {string}")
    public void переключитьсяНаВкладмуСИменем(String name) {
        driver = new ChromeDriver();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(name)) {
                driver.switchTo().window(handle);
            }
        }
            throw new RuntimeException("Вкладка с именем '" + name + "' не найдена" + "  Открытые вкладки: " + windowHandles.size());
    }
    @И(value = "перейти на вкладку браузера под номером {int}")
    public void switchToBrowserTab(int index) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
        boolean isChildWindowOpen = wait.until(ExpectedConditions.numberOfWindowsToBe(index));
        if (isChildWindowOpen)
            Selenide.switchTo().window(index - 1);
    }
}
