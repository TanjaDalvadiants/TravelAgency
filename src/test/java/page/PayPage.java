package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.Card;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayPage {
    private SelenideElement payPageHead = $$(".heading ").find(exactText("Оплата по карте"));
    private SelenideElement сardNumberInput = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonthInput = $("[placeholder='08']");
    private SelenideElement cardYearInput = $("[placeholder='22']");
    private SelenideElement cardHolderInput = $(By.xpath("//div//fieldset/div[3]//span[1]/span/span/span[2]/input"));
    private SelenideElement cardCVCInput = $("[placeholder='999']");
    private SelenideElement continueButton = $$(".button__text").find(exactText("Продолжить"));
    private SelenideElement approvedMessage = $(".notification_status_ok");
    private SelenideElement declinedMessage = $(".notification_status_declined");
    private SelenideElement errorMessage = $(".notification_status_error");
    private SelenideElement invalidFormat = $$(".input__sub").find(exactText("Неверный формат"));
    private SelenideElement emptyField = $$(".input__sub").find(exactText("Поле обязательно для заполнения"));
    private SelenideElement passedCard = $$(".input__sub").find(exactText("Истёк срок действия карты"));
    private SelenideElement invalidCardDuration = $$(".input__sub").find(exactText("Неверно указан срок действия карты"));


    public PayPage() {
        payPageHead.shouldBe(Condition.visible);
    }

    public void approvedOperationMessage() {
        approvedMessage.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void declinedOperationMessage() {
        declinedMessage.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    public void errorMessage() {
        errorMessage.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void invalidFormatMessage() {
        invalidFormat.shouldBe(Condition.visible);
    }

    public void emptyFieldMessage() {
        emptyField.shouldBe(Condition.visible);
    }

    public void passedCardMessage() {
        passedCard.shouldBe(Condition.visible);
    }

    public void invalidCardDurationMessage() {
        invalidCardDuration.shouldBe(Condition.visible);
    }


    public void data(Card card) {
        сardNumberInput.setValue(card.getNumber());
        cardMonthInput.setValue(card.getMonth());
        cardYearInput.setValue(card.getYear());
        cardHolderInput.setValue(card.getHolder());
        cardCVCInput.setValue(card.getCVC());
        continueButton.click();

    }

}