package data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {

    }


    public static String approvedCard() {
        return "1111 2222 3333 4444";
    }

    public static String declinedCard() {
        return "5555 6666 7777 8888";
    }


    public static String invalidToShortCardNumber() {
        return "5555 6666 7777 888";
    }
    public static String invalidToLongCardNumber() {
        return "1111 2222 3333 4444 5";
    }
    public static String invalidCardNumberWithSymbols() {
        return "1&^^*(";
    }
    public static String invalidCardNumberWithAnotherNumbers() {
        return "1234 1234 1234 1234";
    }
    public static String currentMonth(){
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        return String.format("%02d", month);
    }

    public static String pastMonth() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue() - 1;
        return String.format("%02d", month);
    }

    public static String anyMonth() {
        Random random = new Random();
        int month = random.nextInt(12) + 1;
        return String.format("%02d", month);
    }
    public static String currentYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear()-2000;
        return String.format("%02d", year);
    }

    public static String yearPlusFive() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear()-2000+5;
        return String.format("%02d", year);
    }

    public static String pastYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear() - 1;
        return String.format("%02d", year);
    }

    public static String validYear() {
        Random random = new Random();
        int y = random.nextInt(5) + 1;
        LocalDate futureYear = LocalDate.now().plusYears(y);
        int year = futureYear.getYear() - 2000;
        return String.format("%02d", year);
    }

    public static String invalidYear() {
        Random random = new Random();
        int y = random.nextInt(25) + 6;
        LocalDate futureYear = LocalDate.now().plusYears(y);
        int year = futureYear.getYear() - 2000;
        return String.format("%02d", year);
    }

    public static String codeCVC() {
        FakeValuesService fake = new FakeValuesService(
                new Locale("en"), new RandomService());
        return fake.regexify("[1-999]{3}");
    }
    public static String toShortCodeCVC() {
        return "1";
    }
    public static String toLongCodeCVC() {
        return "1234";
    }
    public static String codeCVCWithSymbols() {
        return "^%*Sa";
    }
    public static String name() {
        return faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
    }
    public static String nameWithRussianLetters() {
        return "АННА";
    }
    public static String nameWithSymbols() {
        return "^%&%&";
    }
    public static String toShortName() {
        return "o";
    }

    public static String toLongName() {
        FakeValuesService fake = new FakeValuesService(
                new Locale("en"), new RandomService());
        return fake.regexify("[a-z]{65}");
    }


}



