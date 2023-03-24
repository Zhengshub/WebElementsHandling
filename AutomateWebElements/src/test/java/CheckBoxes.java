import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckBoxes {

	public static Logger log = LoggerFactory.getLogger(CheckBoxes.class);

	private WebDriver driver;
	public String baseUrl = "https://jqueryui.com/checkboxradio/#product-selector";

	@BeforeClass(alwaysRun = true)

	public void setUp() throws InterruptedException {
		log.info("Automation Test suite started ...");

		// Starts Edge Chromium Browser
		System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
		driver = new EdgeDriver();
				
        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

	}

	@Test
	public void handleCheckBoxes() {
		driver.get(baseUrl);
		driver.switchTo().frame(0);
		List <WebElement> Boxes = driver.findElements(By.xpath("/html/body/div/div[3]/div/label/span[1]"));
		for (WebElement box : Boxes) {
			if(box.isSelected()) {
				//do nothing
			}else {
				box.click();
			}
		}
		
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@AfterClass(alwaysRun = true)
	public void cleanAfterAllTestMethods() {
		if (driver != null) {
			driver.quit();
		}

		log.info("Automated Test suite ended ...");
	}
}
