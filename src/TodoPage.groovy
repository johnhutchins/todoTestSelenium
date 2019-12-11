import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait

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
        String s = driver.findElement(By.cssSelector(HEADER_H1)).getText()
        return s
    }

    public String isPageTodoListPage(){
        return BASE_URL
    }

    public Integer addTodoItem(){
        WebElement input = driver.findElement(By.cssSelector(INPUT))
        input.sendKeys(todoItem)
        input.sendKeys(Keys.ENTER)
        return driver.findElements(By.cssSelector(TODO_LIST_INPUTS)).size()
    }

    public Integer deleteTodoItem(){
        sleep(5000)
        List<WebElement> fir = driver.findElements(By.cssSelector(".todo-list >li"))
        //System.out.println(fir.size())
        Actions action = new Actions(driver)
        WebElement we = driver.findElement(By.cssSelector(ENTIRE_INPUT))
        sleep(2000)
        action.moveToElement(we).build().perform()
        sleep(2000)
        action.moveToElement(driver.findElement(By.cssSelector(DEFAULT_ITEM_DELETE))).click().build().perform()
        //make sure the item is not in the list
        sleep(2000)

        List<WebElement> second = driver.findElements(By.cssSelector(".todo-list > li"))
        sleep(2000)
        System.out.println(second.size())
        return second.size()
    }

    public Integer clearCompletedItems(){
        //Add new item
        //complete the item
        //go to the completed list, make sure the length is 1
        //clear completed list
        //return the number of li elements within the completed list

//        WebElement input = ff.findElement(By.xpath("//input[@class='new-todo']"));
//
//        input.sendKeys("item");
//        input.sendKeys(Keys.ENTER);
//
//        List<WebElement> todoListInputs = ff.findElementsByXPath("//ul[@class='todo-list']//div[@class='view']//input");
//        todoListInputs[0].click();
//
//        WebElement activeListLink = ff.findElement(By.xpath(("//footer/ul/li[2]/a")))
//        activeListLink.click()
//
//        List<WebElement> listItems = ff.findElementsByXPath("//ul[@class='todo-list']//li")
    }

    public Integer markItemAsComplete(){
        //click the default input
        //check the size of the completed list first....
        sleep(3000)
        driver.findElement(By.cssSelector(completedListLink)).click()
        //get size of completed list
        sleep(3000)
        //System.out.println(driver.findElements(By.cssSelector(TODO_LIST_INPUTS)).size())
        Integer initialCompletedSize = driver.findElements(By.cssSelector(TODO_LIST_INPUTS)).size()

        if(initialCompletedSize == 0){
            //go back and click the default, then check
            driver.findElement(By.cssSelector(allListLink)).click()
            sleep(500)

            WebElement defaultInput = driver.findElement(By.cssSelector(TODO_LIST_DEFAULT_INPUT))
            sleep(1000)
            defaultInput.click()

            //go back to completed list.
            driver.findElement(By.cssSelector(completedListLink)).click()
            return driver.findElements(By.cssSelector(TODO_LIST_INPUTS)).size()
        } else {
            System.out.println('Initial size is not 0. Failure, exiting test.')
            return -1500
        }
/*        sleep(1000)
        WebElement defaultInput = driver.findElement(By.cssSelector(TODO_LIST_DEFAULT_INPUT))
        sleep(1000)
        defaultInput.click()
        sleep(1000)

        //now need to click on the completed link
        driver.findElement(By.cssSelector(completedListLink)).click()
        sleep(1000)

        System.out.println(driver.findElement(By.cssSelector(TODO_LIST_COMPLETED)).getText())
        if(driver.findElement(By.cssSelector(TODO_LIST_COMPLETED)).getText() == 'Use Redux'){
            System.out.println('completed item')
        }*/
    }


}

