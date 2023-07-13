
package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;



public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement error = $("[data-test-id=error-notification]");

    public ru.netology.page.VerifyPage validLogin(DataHelper.AuthUser user) {
        login.setValue(user.getLogin());
        password.setValue(user.getPassword());
        loginButton.click();
        return new ru.netology.page.VerifyPage();
    }

    public void clean() {
        login.doubleClick().sendKeys(Keys.BACK_SPACE);
        password.doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    public void getError() {
        error.shouldHave(text("Ошибка! " + "Неверно указан логин или пароль"));
        error.shouldBe(Condition.visible);
    }

    public void getBlock() {
        error.shouldHave(text("Ошибка! " + "Пользователь заблокирован"));
        error.shouldBe(Condition.visible);
    }


}
