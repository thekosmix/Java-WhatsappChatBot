package in.strollup.whatsapp.main;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * This class will open a chrome browser and web.whatsapp.com in it. Then it
 * will check for new incoming messages and reply them
 * 
 * @author siddharth
 *
 */
public class WebWhatsapp {

	/**
	 * Webdriver is chrome, this can be changed to firefox as well.
	 */
	private WebDriver browser = new ChromeDriver();
	/**
	 * user to whom you want to send the message
	 */
	private String user = "";

	WebWhatsapp() {
		/**
		 * path to chrome driver, it has to be installed before using this
		 * program Please check HOWTO.md for installation instruction
		 */
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		/**
		 * open web.whatsapp.com and you'll have to add Whatsapp web in your
		 * whatsapp mobile application by scanning the QR Code
		 */
		browser.get("https://web.whatsapp.com");
	}

	private void openWhatsapp() {
		/**
		 * wait till whatsapp is loaded after scanning the QR code then type
		 * start in console to start sending messages
		 */
		Scanner sc = new Scanner(System.in);
		String command = sc.next();
		if (!command.equalsIgnoreCase("start")) {
			browser.quit();
			System.exit(1);
		}
		sc.close();

		/**
		 * get the user's span and click it to select the conversation with user
		 */
		WebElement selectedWindow = browser.findElement(By
				.xpath("//span[contains(text(), '" + user + "')]"));
		selectedWindow.click();

		/**
		 * After conversation is selected find the input box and enter a message
		 * ancd click the send button
		 */
		List<WebElement> elem1 = browser.findElements(By.className("input"));
		elem1.get(1).sendKeys("Hello World");
		browser.findElement(By.className("send-container")).click();

	}

	public static void main(String[] args) throws InterruptedException {
		WebWhatsapp web = new WebWhatsapp();
		web.openWhatsapp();
	}

}
