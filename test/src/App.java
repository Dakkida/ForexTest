import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
	public static void main(String[] args) throws Exception {
		System.out.println("Start run");
		System.setProperty("webdriver.chrome.driver", "/Users/daiki/Private/個人開発テスト/test/src/drivers/chromedriver");
		WebDriver driverone = new ChromeDriver();
		WebDriver drivertwo = new ChromeDriver();

		SbiFx loginone = new SbiFx(driverone);
		loginone.start();;
		Thread.sleep(1000);
		
		SbiFxTrader logintwo = new SbiFxTrader(drivertwo);
		logintwo.start();
		Thread.sleep(1000);

		//driverone.quit();
		//test github 

		//merge test pull request
	}
}