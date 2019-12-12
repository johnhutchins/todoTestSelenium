import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

class TodoPage {

    private static final String BASE_URL = 'https://react-redux-todomvc.stackblitz.io/'
    private static final String HEADER_H1 = "div > header > h1"
    private static final String INPUT = ".header > .new-todo"
    private static final String TODO_LIST_INPUTS = ".todo-list > li"
    private static final String DEFAULT_ITEM_DELETE = '.destroy'
    private static final String ENTIRE_INPUT = '#root > div > section > ul > li'
    private static final String ADDED_ITEM_COMPLETE_CHECKBOX = "#root > div > section > ul > li:nth-child(1) > div > input"
    private static final String TODO_LIST_COMPLETED = ".todo-list > .completed"
    private static final String completedListLink = "#root > div > section > footer > ul > li:nth-child(3)"
    private static final String clearCompletedButton = ".clear-completed"
    private static final String completedItem = ".todo-list > .completed"
    private static final String completeItemInput = "#root > div > section > ul > li:nth-child(1) > div > input"
    private final WebDriver driver

    public TodoPage(WebDriver driver){
        driver.get(BASE_URL)
        this.driver = driver
    }

    public String getPageHeader(){
        WebDriverWait wait = new WebDriverWait(driver, 5)
        WebElement s =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(HEADER_H1)))
        return s.getText()
    }

    public String isPageTodoListPage(){
        return BASE_URL
    }

    public String addTodoItem(String item){
        WebDriverWait wait = new WebDriverWait(driver, 5)
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(INPUT)))
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
        WebDriverWait wait = new WebDriverWait(driver, 5)
        WebElement input =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(INPUT)))
        input.sendKeys("item")
        input.sendKeys(Keys.ENTER)
        WebElement todoListInput = driver.findElement(By.cssSelector(completeItemInput))
        todoListInput.click()
        WebElement completedListLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector((completedListLink))))
        completedListLink.click()
        WebElement completedItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(completedItem)))
        //make sure that there is a completed item
        if(completedItem.getText() == "item"){
            //clear-completed
            driver.findElement(By.cssSelector(clearCompletedButton)).click()
        }

        List<WebElement> todoListItems = driver.findElements(By.cssSelector(TODO_LIST_INPUTS))
        return todoListItems.size()
    }

    public Integer markItemAsComplete(String item){
        WebDriverWait wait = new WebDriverWait(driver, 5)
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(INPUT)))
        input.sendKeys(item)
        input.sendKeys(Keys.ENTER)
        WebElement itemAddedCheckBox = driver.findElement(By.cssSelector(ADDED_ITEM_COMPLETE_CHECKBOX))
        itemAddedCheckBox.click()
        driver.findElement(By.cssSelector(completedListLink)).click()
        return driver.findElements(By.cssSelector(TODO_LIST_COMPLETED)).size()
    }
}

