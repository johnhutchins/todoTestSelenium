import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test;
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

class Todo {


    //Watch https://vimeo.com/44133409
    //Refactor your code from Challenge #4 to follow the Best Practices discussed, (but in the order below)
    //1. Abstracting code use "Page" Pattern
    //2. Use CSS Selectors
    //3. Get rid of inline "magic strings"
    //4. Behavior Driven Design.  Tutorial: https://www.pluralsight.com/guides/introduction-to-testing-with-bdd-and-the-spock-framework.  See if you can refactor your tests to follow BDD.
    //5. Bonus: Setup Selenium Grid

    private WebDriver ff

    @Before
    public void setup(){
        ff = new FirefoxDriver()
    }

    @After
    public void closeFFContext(){
        ff.quit()
    }

    @Test
    public void getHomepageHeader(){
        TodoPage todoPage = new TodoPage(ff)
        sleep(1000)
        assert todoPage.getPageHeader()== "todos"
    }

    @Test
    public void addTodoItem(){
        TodoPage todoPage = new TodoPage(ff)
        sleep(5000)
        assert todoPage.addTodoItem() == 2
    }

    @Test
    public void markItemAsComplete(){
        TodoPage todoPage = new TodoPage(ff)
        sleep(5000)
        assert todoPage.markItemAsComplete() == 1
    }

    @Test
    public void deleteTodoItem(){
        TodoPage todoPage = new TodoPage(ff)
        todoPage.deleteTodoItem()
        //assert todoPage.deleteTodoItem() == 1
    }

    @Test
    public void clearCompletedItems(){
        sleep(5000);
        WebElement input = ff.findElement(By.xpath("//input[@class='new-todo']"));

        input.sendKeys("item");
        input.sendKeys(Keys.ENTER);

        List<WebElement> todoListInputs = ff.findElementsByXPath("//ul[@class='todo-list']//div[@class='view']//input");
        todoListInputs[0].click();

        WebElement activeListLink = ff.findElement(By.xpath(("//footer/ul/li[2]/a")))
        activeListLink.click()

        List<WebElement> listItems = ff.findElementsByXPath("//ul[@class='todo-list']//li")
        assert listItems.size() == 1
    }
}
