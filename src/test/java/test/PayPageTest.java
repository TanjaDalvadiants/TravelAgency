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
    public static String url = System.getProperty( "sut.url" );
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeEach
    public void openPage() {
        open( url );
    }

//    @BeforeEach
//    public void cleanBase() {
//        SQLHelper.cleanDatabase();
//    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }




    @Test
    @DisplayName("Успешная операция с валидными значениями")
    void shouldFillValidValuesWithApprovedResult() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.approvedOperationMessage();

        var expected = "APPROVED";
        var actual = SQLHelper.getStatusPay();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Отклоненная операция с валидными значениями")
    void shouldFillValidValuesWithDeclinedResult() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(declinedCard(), currentMonth(), currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.declinedOperationMessage();

        var expected = "DECLINED";
        var actual = SQLHelper.getStatusPay();
        assertEquals(expected, actual);
    }

    @DisplayName("Незаполненные поля")
    @Test
    void shouldFillEmptyCardNumber() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card("", currentMonth(), currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.invalidFormatMessage();

    }

    @Test
    void shouldFillEmptyMonthNumber() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), "", currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.invalidFormatMessage();

    }

    @Test
    void shouldFillEmptyYearNumber() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), "", name(), codeCVC());
        payPage.data(card);
        payPage.invalidFormatMessage();

    }

    @Test
    void shouldFillEmptyCardHolder() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), currentYear(), "", codeCVC());
        payPage.data(card);
        payPage.emptyFieldMessage();

    }

    @Test
    void shouldFillEmptyCVC() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), currentYear(), name(), "");
        payPage.data(card);
        payPage.invalidFormatMessage();

    }
    @DisplayName("Невалидные значения полей")
    @Test
    void shouldFillInvalidToShortCardNumber() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(invalidToShortCardNumber(), currentMonth(), currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.invalidFormatMessage();

    }

    @Test
    void shouldFillToLongCardNumber() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(invalidToLongCardNumber(), currentMonth(), currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.invalidFormatMessage();

    }

    @Test
    void shouldFillCardNumberWithSymbols() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(invalidCardNumberWithSymbols(), currentMonth(), currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.invalidFormatMessage();

    }

    @Test
    void shouldFillInvalidCardNumberWithAnotherNumbers() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(invalidCardNumberWithAnotherNumbers(), currentMonth(), currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.errorMessage();

    }

    @Test
    void shouldFillUnrealMonth() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), "13", currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.invalidCardDurationMessage();

    }

    @Test
    void shouldFillPastMonth() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), pastMonth(), currentYear(), name(), codeCVC());
        payPage.data(card);
        payPage.invalidCardDurationMessage();

    }

    @Test
    void shouldFillPastYear() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), pastYear(), name(), codeCVC());
        payPage.data(card);
        payPage.passedCardMessage();

    }

    @Test
    void shouldFillYearPlusSix() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), invalidYear(), name(), codeCVC());
        payPage.data(card);
        payPage.invalidCardDurationMessage();

    }

    @Test
    void shouldFillNameWithRussianLetters() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), nameWithRussianLetters(), codeCVC());
        payPage.data(card);
        payPage.emptyFieldMessage();

    }

    @Test
    void shouldFillNameWithSymbols() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), nameWithSymbols(), codeCVC());
        payPage.data(card);
        payPage.emptyFieldMessage();

    }

    @Test
    void shouldFillToShortName() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toShortName(), codeCVC());
        payPage.data(card);
        payPage.emptyFieldMessage();

    }

    @Test
    void shouldFillToLongName() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toLongName(), codeCVC());
        payPage.data(card);
        payPage.emptyFieldMessage();

    }

    @Test
    void shouldFillToShortCVC() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toLongName(), toShortCodeCVC());
        payPage.data(card);
        payPage.invalidFormatMessage();

    }

    @Test
    void shouldFillToLongCVC() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toLongName(), toLongCodeCVC());
        payPage.data(card);
        payPage.invalidFormatMessage();

    }

    @Test
    void shouFillCodeCVCWithSymbols() {
        var tourPage = new TourPage();
        var payPage = tourPage.payPageOpen();
        Card card = new Card(approvedCard(), currentMonth(), validYear(), toLongName(), codeCVCWithSymbols());
        payPage.data(card);
        payPage.invalidFormatMessage();

    }
}
