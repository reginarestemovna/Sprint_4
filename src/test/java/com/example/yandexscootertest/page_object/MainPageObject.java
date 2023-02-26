package com.example.yandexscootertest.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageObject {

    private final WebDriver driver;

    //Список выпадающих элементов (вопросов)
    private final By[] dropDownElements = new By[]{
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")};

    //Список выпадающих элементов (ответов)
    private final By[] dropDownElementTexts = new By[]{
            By.xpath(".//div[@id='accordion__panel-0']//p"),
            By.xpath(".//div[@id='accordion__panel-1']//p"),
            By.xpath(".//div[@id='accordion__panel-2']//p"),
            By.xpath(".//div[@id='accordion__panel-3']//p"),
            By.xpath(".//div[@id='accordion__panel-4']//p"),
            By.xpath(".//div[@id='accordion__panel-5']//p"),
            By.xpath(".//div[@id='accordion__panel-6']//p"),
            By.xpath(".//div[@id='accordion__panel-7']//p")
    };

    public MainPageObject(WebDriver driver) {
        this.driver = driver;
    }


    private WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public void scrollToQuestion(int index) {
        WebElement element = findElement(dropDownElements[index]);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickToQuestion(int index) {
        WebElement element = findElement(dropDownElements[index]);
        element.click();
    }

    public void waitAfterClickToQuestion(int index) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(dropDownElementTexts[index]));
    }

    public String getAnswerText(int index) {
        WebElement element = findElement(dropDownElementTexts[index]);
        return element.getText();
    }
}
