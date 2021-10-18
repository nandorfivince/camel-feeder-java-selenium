import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class CamelFeederApp {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        System.out.println("Chrome driver initialized");
        driver.get("https://www.teveclub.hu/");
        driver.findElement(By.name("tevenev")).sendKeys("");
        driver.findElement(By.name("pass")).sendKeys("");
        driver.findElement(By.cssSelector("input[type=image]:nth-child(1)")).click();
        System.out.println("Camel logged in");
        try {
            driver.findElement(By.name("kaja")).click();
            List<WebElement> days = driver.findElements(By.xpath("(//*[contains(@name,'kaja')])[2]/option"));
            if (days.size() <= 7) {
                String xpathSelector = "((//*[contains(@name,'kaja')])[2]/option)[%s]";
                System.out.println("Remained numbers of food days: " + days.size());
                String numberOfDays = String.valueOf(days.size());
                String fullXpath = String.format(xpathSelector, numberOfDays);
                driver.findElement(By.xpath(fullXpath)).click();
                driver.findElement(By.xpath("(//*[contains(@name,'etet')])[2]")).click();
                System.out.println("Camel ate today");
            }
        } catch (Exception e) {e.printStackTrace();}
        try {
            driver.findElement(By.name("pia")).click();
            List<WebElement> days = driver.findElements(By.xpath("(//*[contains(@name,'pia')])[2]/option"));
            if (days.size() <= 7) {
                String xpathSelector = "((//*[contains(@name,'pia')])[2]/option)[%s]";
                System.out.println("Remained numbers of drink days: " + days.size());
                String numberOfDays = String.valueOf(days.size());
                String fullXpath = String.format(xpathSelector, numberOfDays);
                driver.findElement(By.xpath(fullXpath)).click();
                driver.findElement(By.xpath("(//*[contains(@name,'etet')])[2]")).click();
                System.out.println("Camel drank today");
            }
        } catch (Exception e) {e.printStackTrace();}
        try {
            driver.findElement(By.xpath("//img[@src='/images/farm/tanit_0.gif']")).click();
            driver.findElement(By.name("learn")).click();
            System.out.println("Camel learned today");
        } catch (Exception e) {e.printStackTrace();}
        driver.close();
        driver.quit();
        System.out.println("Chrome driver closed");
    }

}
