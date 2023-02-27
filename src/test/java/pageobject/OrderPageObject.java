package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPageObject {

    private final WebDriver driver;

    //Кнопка "Далее" в форме оформления заказа
    private final By nextButton = By.className("Button_Middle__1CSJM");
    //Кнопка "Заказать" в форме оформления заказа
    private final By orderButton = By.className("Button_Middle__1CSJM");
    //Кнопка "Заказать" на главной странице сверху
    private final By topOrderButton = By.className("Button_Button__ra12g");
    //Drop-down меню (Станция метро) для выбора станции метро (в свёрнутом состоянии)
    private final By metroStationDropDown = By.className("Order_Text__2broi");
    //Кнопка "Заказать" на главной странице снизу
    private final By bottomOrderButton = By.className("Button_Middle__1CSJM");
    //Кнопка "Да" в окне подтверждения
    private final By confirmationButton = By.className("Button_Middle__1CSJM");
    //Drop-down меню (Срок аренды) для выбора срока аренды (в свёрнутом состоянии)
    private final By rentalPeriodDropDownMenu = By.className("Dropdown-option");
    //Календарь (Когда привезти самокат) для выбора даты доставки самоката (в свёрнутом состоянии)
    private final By deliveryDatePicker = By.className("react-datepicker__day");
    //Элемент drop-down меню (Срок аренды) для выбора срока аренды (в развёрнутом состоянии)
    private final By rentalPeriodDropDownField = By.className("Dropdown-placeholder");
    //Header окна, появляющегося, после успешного оформления заказа
    private final By successfulWindowHeader = By.className("Order_ModalHeader__3FDaJ");
    //Поле (Имя) для заполнение имени
    private final By nameTextField = By.xpath(".//input[@placeholder = '* Имя']");
    //Поле (Фамилия) для заполнение фамилии
    private final By surnameTextField = By.xpath(".//input[@placeholder = '* Фамилия']");
    //Поле (Комментарий для курьера) для заполнение коментария
    private final By commentTextField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    //Элемент drop-down меню (Станция метро) для выбора станции метро (в развёрнутом состоянии)
    private final By metroStationDropDownField = By.xpath(".//input[@placeholder = '* Станция метро']");
    //Check box для выбора цвета
    private final By colorCheckBox = By.xpath(".//input[@class = 'Checkbox_Input__14A2w']/parent::label");
    //Поле (Адрес: куда привезти самокат) для заполнение адреса
    private final By addressTextField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Дата в календаре (Когда привезти самокат) для выбора даты доставки самоката (в развёрнутом состоянии)
    private final By deliveryDateCalendarField = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    //Поле (Телефон: на него позвонит курьер) для заполнение номера телефона
    private final By phoneTextField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");

    public OrderPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSuccessfulOrderHeader() {
        return driver.findElement(successfulWindowHeader);
    }

    public void scrollToOrderButtonBottom() {
        WebElement element = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }

    public void clickDeliveryDatePicker() {
        driver.findElement(deliveryDateCalendarField).click();
    }

    public void clickRentalPeriodDropDown() {
        driver.findElement(rentalPeriodDropDownField).click();
    }

    public void clickMetroStationDropDown() {
        driver.findElement(metroStationDropDownField).click();
    }

    public void clickColorCheckBox(String color) {
        clickElementInList(color, colorCheckBox);
    }

    public void clickConfirmationButton() {
        clickElementInList("Да", confirmationButton);
    }

    public void clickMetroStationDropDown(String metroStation) {
        clickElementInList(metroStation, metroStationDropDown);
    }

    public void clickDeliveryDatePicker(String day) {
        clickElementInList(day, deliveryDatePicker);
    }

    public void clickRentalPeriodDropDown(String rentalPeriod) {
        clickElementInList(rentalPeriod, rentalPeriodDropDownMenu);
    }

    public void clickOrderButton() {
        clickElementInList("Заказать", orderButton);
    }

    private void clickElementInList(String value, By by) {
        List<WebElement> elements = driver.findElements(by);
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            if (value.equals(element.getText())) {
                elements.get(i).click();
                return;
            }
        }
    }

    public void setName(String name) {
        setTextValue(name, nameTextField);
    }

    public void setSurname(String surname) {
        setTextValue(surname, surnameTextField);
    }

    public void setAddress(String address) {
        setTextValue(address, addressTextField);
    }

    public void setPhone(String phone) {
        setTextValue(phone, phoneTextField);
    }

    public void setComment(String comment) {
        setTextValue(comment, commentTextField);
    }

    private void setTextValue(String value, By by) {
        WebElement element = driver.findElement(by);
        element.sendKeys(value);
    }

    public void waitOrderForm() {
        waitElement(nameTextField);
    }

    public void waitAfterClickOrderButton() {
        waitElement(confirmationButton);
    }

    public void waitAfterClickNextButton() {
        waitElement(deliveryDateCalendarField);
    }

    public void waitAfterClickConfirmationButton() {
        waitElement(successfulWindowHeader);
    }

    private void waitElement(By by) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
