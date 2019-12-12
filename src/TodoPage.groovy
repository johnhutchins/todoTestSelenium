import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

import java.awt.event.MouseEvent
import java.lang.reflect.Array

class TodoPage {

    private static final String BASE_URL = 'https://react-redux-todomvc.stackblitz.io/'
    private static final String HEADER_H1 = "div > header > h1"
    private static final String INPUT = ".header > .new-todo"
    private static final String TODO_LIST = "//ul[@class='todo-list']//div[@class='view']//label"
    private static final String TODO_LIST_INPUTS = ".todo-list > li"
    private static final String TODO_LIST_DEFAULT_INPUT = ".todo-list > li input"
    private static final String DEFAULT_ITEM_DELETE = '.destroy'
    private static final String ENTIRE_INPUT = '#root > div > section > ul > li'

    private static final String TODO_LIST_COMPLETED = ".todo-list > .completed"
    private static final String TODO_COUNT = ".todo-count"

    private static final String LIST_ITEM = "//ul[@class='todo-list']//li"
    private static final String firstItemToDestroy = "//button"
    private static final String allListLink = "#root > div > section > footer > ul > li:nth-child(1)"
    private static final String activeListLink = "#root > div > section > footer > ul > li:nth-child(2)"
    private static final String completedListLink = "#root > div > section > footer > ul > li:nth-child(3)"

    private static final String todoItem = 'Mow the Carpet'

    private final WebDriver driver

    public TodoPage(WebDriver driver){
        driver.get(BASE_URL)
        this.driver = driver
    }

    public String getPageHeader(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement s =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(HEADER_H1)))
        return s.getText()
    }

    public String isPageTodoListPage(){
        return BASE_URL
    }

    public String addTodoItem(String item){
        WebElement input = driver.findElement(By.cssSelector(INPUT))
        input.sendKeys(item)
        input.sendKeys(Keys.ENTER)
        //length should be 2, could return that instead of text?
        return driver.findElements(By.cssSelector(TODO_LIST_INPUTS))[0].getText()
    }

    public Integer deleteTodoItem(){
        WebDriverWait wait = new WebDriverWait(driver, 5)
        Actions action = new Actions(driver)
        WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ENTIRE_INPUT)))
        action.moveToElement(we).build().perform()
        action.moveToElement(driver.findElement(By.cssSelector(DEFAULT_ITEM_DELETE))).click().build().perform()

        List<WebElement> second = driver.findElements(By.cssSelector(TODO_LIST_INPUTS))
        return second.size()
    }

    public Integer clearCompletedItems(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement input =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div > header > input")))
        input.sendKeys("item");
        input.sendKeys(Keys.ENTER);
        WebElement todoListInput = driver.findElement(By.cssSelector("#root > div > section > ul > li:nth-child(1) > div > input"))
        todoListInput.click()
        WebElement activeListLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector((completedListLink))))
        activeListLink.click()
        WebElement completedItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".todo-list > .completed")))
        //make sure that there is a completed item
        if(completedItem.getText() == "item"){
            driver.findElement(By.cssSelector("#root > div > section > footer > button")).click()
        }

        List<WebElement> todoListItems = driver.findElements(By.cssSelector(TODO_LIST_INPUTS))
        return todoListItems.size()
    }

    public Integer markItemAsComplete(String item){
        WebElement input = driver.findElement(By.cssSelector(INPUT))
        input.sendKeys(item)
        input.sendKeys(Keys.ENTER)

        driver.findElement(By.cssSelector("#root > div > section > ul > li:nth-child(1) > div > input")).click()

        driver.findElement(By.cssSelector(completedListLink)).click()
        return driver.findElements(By.cssSelector(TODO_LIST_COMPLETED)).size()

    }


}

