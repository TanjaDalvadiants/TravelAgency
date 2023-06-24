package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class TourPage {
    private SelenideElement tourPageHead = $$(".heading ").find(exactText("Путешествие дня"));
    private SelenideElement payButton = $$(".button").find(exactText("Купить"));

    //private SelenideElement creditPayButton = $$(".button").find(exactText("Купить в кредит"));
    public TourPage() {
        tourPageHead.shouldBe(Condition.visible);
    }


    public PayPage payPageOpen() {
        payButton.click();
        return new PayPage();

    }


}
