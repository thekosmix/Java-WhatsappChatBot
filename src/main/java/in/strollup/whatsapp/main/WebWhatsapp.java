package in.strollup.whatsapp.main;

import in.strollup.whatsapp.utils.Utils;

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
	 * indicator of if a chat has been started or not
	 */
	private boolean isStarted = false;

	/**
	 * time in millis to wait for checking new incoming message;
	 */
	private long sleepTime = 3000;

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
		 * keep checking for unread count every sleepTime milli secs.
		 * If some elementFound, then click it and set isStarted to true
		 * The while will check for this variable, and then will reply on that element.
		 */
		while (true) {
			try {
				if (isStarted) {
					/**
					 *  once the new messages are found
					 *  get the last message and reply to the user according to it:	
					 */
					WebElement selectedWindow = browser.findElement(By
							.xpath("//div[contains(@class, 'message-list')]"));
					List<WebElement> msgList = selectedWindow.findElements(By
							.xpath("//div[contains(@class,'msg')]"));
					WebElement lastMsgDiv = msgList.get(msgList.size() - 1);
					WebElement lastMsgSpan = lastMsgDiv
							.findElement(By
									.xpath("//span[contains(@class, 'selectable-text')]"));
					String msg = lastMsgSpan.getText();
					reply("I was already chatting with you: " + msg);
					isStarted = false;
				}

				/**
				 *  get the user who just pinged, whose 'unread-count' will be 1 or more
				 */
				List<WebElement> nonSelectedWindows = browser.findElements(By
						.xpath("//span[contains(@class,'unread-count')]"));
				if (!Utils.isEmptyOrNull(nonSelectedWindows)) {
					isStarted = true;
					responseNonSelectedWindow(nonSelectedWindows);
				} else {
					Utils.log("no new msg yet");
					Thread.sleep(sleepTime);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}

		browser.quit();
	}

	/**
	 * Select the user with unread-count and click on it to start chatting with him/her
	 * @param elems
	 */
	private void responseNonSelectedWindow(List<WebElement> elems) {
		Utils.log("new msgs found");
		for (WebElement elem : elems) {
			elem.click();
			reply("your chat was not selected. Now it is.");
		}
	}

	private void reply(String string) {
		List<WebElement> elem1 = browser.findElements(By.className("input"));
		for (int i = 0; i < 1; i++) {
			elem1.get(1).sendKeys(string);//
			browser.findElement(By.className("send-container")).click();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		WebWhatsapp web = new WebWhatsapp();
		web.openWhatsapp();
	}

}
