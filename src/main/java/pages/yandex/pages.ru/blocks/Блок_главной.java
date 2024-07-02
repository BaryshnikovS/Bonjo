package pages.yandex.pages.ru.blocks;

import io.qameta.atlas.webdriver.extension.FindBy;
import org.openqa.selenium.By;
import pages.html_elements.Button;
import pages.html_elements.Link;
import pages.html_elements.Text;
import ru.lanit.at.pages.block.AbstractBlockElement;

public interface Блок_главной extends AbstractBlockElement,
        Link.WithLink, Text.WithText, Button.WithButton {

    default void запомнить_ссылку(String link) {
        findElement(By.xpath(""));
    }
    default void нажать_на_форум() {
        Button button = button("Обсудить на форуме");
        button.click();
    }


    interface Ссылка_блок extends AbstractBlockElement {
        @FindBy("//b[@class=\"tbl-mn_new-header\"]")
        Блок_главной findLink();
    }
}
