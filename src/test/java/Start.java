import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

public class Start {

    @Test
    public void openFoundry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "driver//chromedriver");
        WebDriver driver = new ChromeDriver();


        /**
         * Login Page
         */
        driver.get("https://fontplatform.testfp.com/app/#/login");

        Thread.sleep(5000);

        WebElement loginTextBox = driver.findElement(By.id("login-username"));
        loginTextBox.sendKeys("swetambari.sinha@monotype.com");

        WebElement passwordTextBox = driver.findElement(By.id("login-password"));
        passwordTextBox.sendKeys("Welcome@123");

        WebElement submitButton = driver.findElement(By.id("login-submit"));
        submitButton.click();

        Thread.sleep(5000);

        /**
         * Home page
         */

        WebElement foundryDropDown = driver.findElement(By.xpath("//div[@class='fou3p7wkkwR']/a/span"));
        String activeFoundryName = foundryDropDown.getText();
        foundryDropDown.click();

        System.out.println("active foundry is: " + activeFoundryName);
        // TODO - catch all foundries in an array


        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,1800)", "");

        System.out.println("scrolling down..");

        Thread.sleep(5000);

        List<WebElement> familiesList = driver.findElements(By.xpath("//div[@class='out-left']/div[contains(@class, 'mt-sans--nowrap fou--S-AkkL')]"));
        for(int familyListNumber =0;familyListNumber<familiesList.size();familyListNumber++){
            System.out.println("families are: " + familiesList.get(familyListNumber).getText());
        }


        List<WebElement> fontFormatDropdown = driver
                    .findElements(By.xpath("//div[@id='home-released-families-families']//select[contains(@class,'mt-select')]"));

        for (int i = 0; i < fontFormatDropdown.size(); i++) {
            List<WebElement> options =fontFormatDropdown.get(i).findElements(By.tagName("option"));

            for(int j=0;j<options.size();j++)
            {
                //fontFormatDropdown.get(i).click();
                String dropDownText = options.get(j).getText();
                if (dropDownText.contains("No Sku Available"))
                {

                }
                else
                    fontFormatDropdown.get(i).click();

                List<WebElement> familyListForCurrentFoundry = driver.findElements(By.xpath("//div[@id='home-released-families-families']//div[contains(@class,'fou--S-AkkL')]"));

                        for(int familyListLoop =0;familyListLoop<familyListForCurrentFoundry.size();familyListLoop++){
                            //clicking the family to open the page.
                            familyListForCurrentFoundry.get(0).click();
                            //Thread.sleep(10000);
                        }
                // aur kaam karonga

            }
        }




    }
}
