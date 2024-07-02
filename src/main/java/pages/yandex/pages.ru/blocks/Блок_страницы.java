package pages.yandex.pages.ru.blocks;

import io.qameta.atlas.webdriver.extension.FindBy;
import pages.html_elements.Button;
import pages.html_elements.Link;
import pages.html_elements.Text;
import ru.lanit.at.pages.block.AbstractBlockElement;

public interface Блок_страницы extends AbstractBlockElement,
        Link.WithLink, Text.WithText, Button.WithButton {


    interface Главный_блок extends AbstractBlockElement {
        @FindBy("(//td[@class=\"tbl-mn_news-padding-right\"])[3]")
        Блок_страницы findLink();
    }
    interface Кнопка_обсудить extends AbstractBlockElement {
        @FindBy("//input[@value=\"Обсудить на форуме\"]")
        Блок_страницы buttonForum();
    }

    interface Блок_новости extends AbstractBlockElement {
        @FindBy("//td[@valign='top' and @style='padding: 4px;']")
        Блок_страницы block();
    }

}
