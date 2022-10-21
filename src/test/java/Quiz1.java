import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Quiz1 {
    WebDriver driver;
   public Quiz1(){
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
    }
    @Test
    public void QuizSteps()  {
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
        WebElement html = driver.findElement(By.tagName("html"));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step:Login
        WebElement userName = driver.findElement(By.id("userName"));
        WebElement pwd = driver.findElement(By.id("password"));
        WebElement login = driver.findElement(By.id("login"));
        userName.sendKeys("test123");
        pwd.sendKeys("Automation@123");
        login.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"submit\"]"),"Log out"));

        // Step:Go To Book Store

        WebElement bookStore = driver.findElement(By.id("gotoStore"));
        bookStore.click();
        List<WebElement> books = driver.findElements(By.className("action-buttons"));
        Assert.assertEquals(books.size() , 8);

        //Step:Book Details
        
        WebElement gitGuide = driver.findElement(By.id("see-book-Git Pocket Guide"));
        gitGuide.click();
        WebElement title = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/label"));
        Assert.assertEquals(title.getText(), "Git Pocket Guide");

        //Step:Add to your collection
        WebElement addCollection = driver.findElement(By.id("addNewRecordButton"));
        addCollection.click();
        String msg = driver.switchTo().alert().getText();
        Assert.assertEquals(msg,"Book already present in the your collection!");

        //Step:Back To Book Store
        bookStore.click();
        Assert.assertEquals(books.size() , 8);

        //Step:Log out
        WebElement logout = driver.findElement(By.id("submit"));
        logout.click();

        WebElement h2 = driver.findElement(By.xpath("//*[@id=\"userForm\"]/div[1]/h2"));
        Assert.assertEquals(h2.getText(), "Welcome,");
        WebElement h5 = driver.findElement(By.xpath("//*[@id=\"userForm\"]/div[1]/h5"));
        Assert.assertEquals(h5.getText(), "Login in Book Store");

        driver.close();
        driver.quit();
}
}
