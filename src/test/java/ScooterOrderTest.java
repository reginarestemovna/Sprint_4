import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.OrderPageObject;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ScooterOrderTest {

    private static final String EXPECTED = "Заказ оформлен";

    private WebDriver driver;

    private final String day;
    private final String name;
    private final String phone;
    private final String color;
    private final String surname;
    private final String address;
    private final String comment;
    private final String metroStation;
    private final String rentalPeriod;

    public ScooterOrderTest(String name,
                            String phone,
                            String surname,
                            String address,
                            String color,
                            String comment,
                            String metroStation,
                            String rentalPeriod,
                            String day) {
        this.name = name;
        this.phone = phone;
        this.surname = surname;
        this.address = address;
        this.color = color;
        this.comment = comment;
        this.metroStation = metroStation;
        this.rentalPeriod = rentalPeriod;
        this.day = day;
    }

    @Parameterized.Parameters
    public static Object[][] getLocatorsAndText() {
        return new Object[][]{
                {"Иван", "79999999999", "Иванов", "г. Москва, дом 1", "чёрный жемчуг", "", "Бульвар Рокоссовского", "сутки", "1"},
                {"Сидор", "78888888888", "Сидоров", "г. Москва, дом 2", "серая безысходность", "Комментарий1", "Черкизовская", "двое суток", "9"},
                {"Петр", "77777777777", "Петров", "г. Москва, дом 3", "чёрный жемчуг", "", "Преображенская площадь", "трое суток", "11"},
                {"Владимир", "76666666666", "Владимиров", "г. Москва, дом 4", "серая безысходность", "Комментарий2", "Сокольники", "четверо суток", "15"},
                {"Александр", "75555555555", "Александров", "г. Москва, дом 5", "чёрный жемчуг", "", "Красносельская", "пятеро суток", "23"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void testTopButtonCreateOrder() {
        driver = new ChromeDriver();
        //Ссылка на страницу Яндекс Самокат
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderPageObject orderPageObject = new OrderPageObject(driver);

        //Нажатие кнопки "Заказать" вверху страницы
        orderPageObject.clickTopOrderButton();

        createOrder(orderPageObject);
    }

    @Test
    public void testBottomButtonCreateOrder() {
        driver = new ChromeDriver();
        //Ссылка на страницу Яндекс Самокат
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderPageObject orderPageObject = new OrderPageObject(driver);

        //Прокрутка страницы до нижней кнопки "Заказать"
        orderPageObject.scrollToOrderButtonBottom();
        //Нажатие кнопки "Заказать" внизу страницы
        orderPageObject.clickBottomOrderButton();

        createOrder(orderPageObject);
    }

    private void createOrder(OrderPageObject orderPageObject) {
        //Ожидание открытия окна офрмления заказа
        orderPageObject.waitOrderForm();
        //Установка значения в поле "Имя"
        orderPageObject.setName(name);
        //Установка значения в поле "Фамилия"
        orderPageObject.setSurname(surname);
        //Установка значения в поле "Адрес"
        orderPageObject.setAddress(address);
        //Установка значения в поле "Телефон"
        orderPageObject.setPhone(phone);
        //Раскрытие списка станций метро
        orderPageObject.clickMetroStationDropDown();
        //Выбор элемента из списка станций метро
        orderPageObject.clickMetroStationDropDown(metroStation);
        //Нажатие кнопки "Далее"
        orderPageObject.clickNextButton();
        //Ожидание перехода на следующую страницу заполнения заказа
        orderPageObject.waitAfterClickNextButton();
        //Нажатие на поле "Дата" с выбором даты доставки самоката
        orderPageObject.clickDeliveryDatePicker();
        //Выбор даты в календаре
        orderPageObject.clickDeliveryDatePicker(day);
        //Раскрытие списка периодов аренды
        orderPageObject.clickRentalPeriodDropDown();
        //Выбор периода аренды из списка периодов аренды
        orderPageObject.clickRentalPeriodDropDown(rentalPeriod);
        //Выбор цвета
        orderPageObject.clickColorCheckBox(color);
        //Установка значения в поле "Комментарий"
        orderPageObject.setComment(comment);
        //Нажатие кнопки "Заказать" в окне заполнения заказа
        orderPageObject.clickOrderButton();
        //Ожидание открытия окна подтверждения
        orderPageObject.waitAfterClickOrderButton();
        //Нажатие кнопки подтверждения в окне подтверждения
        orderPageObject.clickConfirmationButton();
        //Ожидание открытия окна с номером заказа
        orderPageObject.waitAfterClickConfirmationButton();
        //Получение header окна с номером заказа
        WebElement element = orderPageObject.getSuccessfulOrderHeader();
        //Сравнение ожидаемого текста ответа и полученного
        assertEquals(EXPECTED, element.getText());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
