package pages.Game;

import io.qameta.atlas.webdriver.extension.FindBy;
import pages.html_elements.Button;
import pages.html_elements.Link;
import ru.lanit.at.pages.AbstractPage;
import ru.lanit.at.pages.annotations.Title;
import ru.lanit.at.pages.annotations.WithName;


@Title("Главная страница игры")
public interface Страница_игры extends AbstractPage {


    @WithName("Основная ссылка")
    @FindBy("")
    Link link();

    @WithName("Кнопка Обсудить на форуме")
    @FindBy("//input[@value='Обсудить на форуме']")
    Button button();



}
