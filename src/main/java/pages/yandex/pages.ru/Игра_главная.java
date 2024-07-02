package pages.yandex.pages.ru;

import pages.html_elements.Button;
import pages.html_elements.Link;
import pages.html_elements.Text;
import pages.yandex.pages.ru.blocks.Блок_страницы;
import ru.lanit.at.pages.AbstractPage;


public interface Игра_главная extends AbstractPage,
        Блок_страницы.Главный_блок,  Text.WithText, Button.WithButton, Link.WithLink {

}
