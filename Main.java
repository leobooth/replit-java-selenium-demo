import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.Scanner; 
import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chromium.*;
import org.openqa.selenium.chromium.ChromiumOptions;

class Main {
  public static void main(String[] args) throws Exception {
    
	  ChromeOptions options = new ChromeOptions();
    // options.addArguments(
    //   "--no-sandbox",
    //   "--disable-dev-shm-usage",
    //   "--start-maximized"
    // );
      options.addArguments(
      "--no-sandbox",
      "--disable-dev-shm-usage",
      "--headless"
    );
	
    ChromeDriver driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    /* point Selenium driver to the webpage at address 
     * "https://seleniumpracticepages.leobooth.repl.co/"
    */
    String webpage = "https://seleniumpracticepages.leobooth.repl.co/";
    driver.get(webpage);
    System.out.println("view practice webpage at: " + webpage);

    // print page title to console
    System.out.println("page title: " + driver.getTitle());

    // print the text in the second 'target' element to the console
    WebElement target2 = driver.findElement(
      By.xpath("//div[contains(text(),'target')][2]")
    );
    System.out.println("second 'target' element: " + target2.getText());

    /* use a single selector to get both the first and second 'target' elements
     * then print the text from both elements to the console
    */
    List<WebElement> targets = driver.findElements(
      By.xpath("//div[contains(text(),'target')][position()<=2]")
    );
    
    int targetCounter = 0;
    for(WebElement target : targets) {
          targetCounter++;
          System.out.println("'target' element " + targetCounter + " text:" +  
          target.getText());
    }

    /* select the "div" elements that contain numbers only
     * then print the text from all the elements to the console
    */
    List<WebElement> divs = driver.findElements(
      By.xpath("//div[not(contains(text(),'target'))]")
    );
    
    int divCounter = 0;
    for(WebElement div : divs) {
          divCounter++;
          System.out.println("'div' element " + divCounter + " text:" +  
          div.getText());
    }
    
    /* use this Scanner section to pause the test
     * until you are ready to close the web browser
    */ 
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter 'done' to close test.");  
    int maxTries = 0;
    String scannerInput = sc.next();
    
    while (
      !scannerInput.equalsIgnoreCase("done") && 
      maxTries < 10) {
      scannerInput = sc.next();
      maxTries++;
    }  

    sc.close();
    
    driver.quit();
  } 
}