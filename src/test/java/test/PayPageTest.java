package test;

import data.Card;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;
import page.TourPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayPageTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        SQLHelper.cleanDatabase();
    }


    @Test
    @DisplayName("Успешная операция с валидными значениями")
    void shouldFillValidValuesWithApprovedResult() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.approvedOperationMessage();

        var expected = "APPROVED";
        var actual = SQLHelper.getStatusPay();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Отклоненная операция с валидными значениями")
    void shouldFillValidValuesWithDeclinedResult() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(declinedCard(), currentMonth(), currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.declinedOperationMessage();

        var expected = "DECLINED";
        var actual = SQLHelper.getStatusPay();
        assertEquals(expected, actual);
    }

    @DisplayName("Незаполненные поля")
    @Test
    void shouldFillEmptyCardNumber() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card("", currentMonth(), currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillEmptyMonthNumber() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), "", currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillEmptyYearNumber() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), "", name(), codeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillEmptyCardHolder() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), currentYear(), "", codeCVC());
        PayPage.data(card);
        PayPage.emptyFieldMessage();

    }

    @Test
    void shouldFillEmptyCVC() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), currentYear(), name(), "");
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }
    @DisplayName("Невалидные значения полей")
    @Test
    void shouldFillInvalidToShortCardNumber() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(invalidToShortCardNumber(), currentMonth(), currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillToLongCardNumber() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(invalidToLongCardNumber(), currentMonth(), currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillCardNumberWithSymbols() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(invalidCardNumberWithSymbols(), currentMonth(), currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillInvalidCardNumberWithAnotherNumbers() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(invalidCardNumberWithAnotherNumbers(), currentMonth(), currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.errorMessage();

    }

    @Test
    void shouldFillUnrealMonth() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), "13", currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.invalidCardDurationMessage();

    }

    @Test
    void shouldFillPastMonth() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), pastMonth(), currentYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.invalidCardDurationMessage();

    }

    @Test
    void shouldFillPastYear() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), pastYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.passedCardMessage();

    }

    @Test
    void shouldFillYearPlusSix() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), invalidYear(), name(), codeCVC());
        PayPage.data(card);
        PayPage.invalidCardDurationMessage();

    }

    @Test
    void shouldFillNameWithRussianLetters() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), nameWithRussianLetters(), codeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillNameWithSymbols() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), nameWithSymbols(), codeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillToShortName() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toShortName(), codeCVC());
        PayPage.data(card);
        PayPage.errorMessage();

    }

    @Test
    void shouldFillToLongName() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toLongName(), codeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillToShortCVC() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toLongName(), toShortCodeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouldFillToLongCVC() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toLongName(), toLongCodeCVC());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }

    @Test
    void shouFillCodeCVCWithSymbols() {
        var TourPage = new TourPage();
        var PayPage = TourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toLongName(), codeCVCWithSymbols());
        PayPage.data(card);
        PayPage.invalidFormatMessage();

    }
}
