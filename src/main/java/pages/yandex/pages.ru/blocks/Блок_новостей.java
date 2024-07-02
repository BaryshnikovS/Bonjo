package pages.yandex.pages.ru.blocks;


import io.qameta.atlas.webdriver.extension.FindBy;
import pages.html_elements.Button;
import pages.html_elements.Link;
import pages.html_elements.Text;
import ru.lanit.at.pages.block.AbstractBlockElement;

public interface Блок_новостей extends AbstractBlockElement,
        Button.WithButton, Text.WithText, Link.WithLink {

    interface Новостной_блок extends AbstractBlockElement {
        @FindBy("")
        Блок_новостей buttonForum();
    }
}
