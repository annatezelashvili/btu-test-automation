import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleTests {

    @Test
    public void firstTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        WebElement button=driver.findElement(By.xpath("//ul/li/a"));
        button.click();
        WebElement text = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));
        Assert.assertNotEquals(text.getText(),"No Test");
        // 1. Navigate back to the main page
        driver.navigate().back();
        // 2. Click on DropDown
        WebElement dropdown= driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[11]/a"));
        dropdown.click();
        // 3. Select 'Option 1'
        WebElement dropdownElement =  driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdownElement);
        dropdownSelect.selectByVisibleText("Option 1");

        driver.close();

    }
}
