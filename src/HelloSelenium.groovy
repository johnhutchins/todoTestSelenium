
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HelloSelenium {

    public static class Holiday{
        private String holidayDate;
        private String holidayName;
        private String description;
    }

    public void main(String[] args) {
        getCurrentHoliday();
        getFutureHolidaysAndDescriptions();
    }

    @Test
    public void getCurrentHoliday(){
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("https://nationaltoday.com/what-is-today/");
            WebElement firstResult = driver.findElement(By.xpath("//div[@class='holiday-title']//h2[@class='entry-title']"));
            System.out.println(firstResult.getAttribute("textContent"));
        } catch(Exception e){
            System.out.println(e);
        }
        finally {
            driver.quit();
        }
    }

    @Test
    public void getFutureHolidaysAndDescriptions(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://nationaltoday.com/what-is-today/");
        List<WebElement> el = driver.findElements(By.xpath("//div[@id='secondary']//div[@class='daycard upcoming-day']//a"));
        List<String> urls = new ArrayList<>();
        for(Integer j=0;j<el.size();j++){
            urls.add(el.get(j).getAttribute("href"));
        }
        List<Holiday> holidayList = new ArrayList<>();

        for(Integer i=0;i<el.size();i++){
            Holiday holiday = new Holiday();
            driver.navigate().to(urls.get(i));

            WebElement dateElem = driver.findElement(By.xpath("//div[@class='holiday-date-box']//span[@class='ntdb-holiday-date']"));
            System.out.println(dateElem.getText());
            holiday.holidayDate = dateElem.getText();

            WebElement descriptionElem = driver.findElement(By.xpath("//div[@class='page-content-wrap']//div[@class='entry-content-inner']//div"));
            System.out.println(descriptionElem.getText());
            holiday.description = descriptionElem.getText();

            WebElement nameElem = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/header/div[1]/div/div[2]/h1"));
            System.out.println(nameElem.getText());
            holiday.holidayName = nameElem.getText();

            holidayList.add(holiday);
        }

        driver.quit();
    }
}