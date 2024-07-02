package pages.yandex.pages.ru;

import pages.html_elements.Button;
import pages.html_elements.Link;
import pages.html_elements.Text;
import pages.yandex.pages.ru.blocks.Блок_новостей;
import ru.lanit.at.pages.AbstractPage;
import ru.lanit.at.pages.annotations.Title;


public interface Игра_новость extends AbstractPage,
        Блок_новостей.Новостной_блок, Text.WithText, Link.WithLink, Button.WithButton {

}
