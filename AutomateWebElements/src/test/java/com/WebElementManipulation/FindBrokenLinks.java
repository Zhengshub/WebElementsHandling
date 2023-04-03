package com.WebElementManipulation;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class FindBrokenLinks {

	public static void main(String[] args) {
		// Set ChromeDriver path
		System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");

		// Create a new instance of ChromeDriver
		WebDriver driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

		// Navigate to the webpage you want to test
		driver.get("https://jqueryui.com/checkboxradio/#product-selector");

		// Get all links on the webpage
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int linkNum = links.size();
		System.out.println("Total Links: " + linkNum);

		// Loop through each link and validate it
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			try {
				// Create an HTTP connection to the URL
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

				// Set request method to HEAD (to check only the headers, not the entire
				// response body)
				connection.setRequestMethod("HEAD");

				// Get the response code
				int responseCode = connection.getResponseCode();

				// Check if the response code is 404 or 500 (broken link)
				if (responseCode == HttpURLConnection.HTTP_NOT_FOUND
						|| responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
					System.out.println("Broken link found: " + url);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Close the browser
		driver.quit();
	}

}
