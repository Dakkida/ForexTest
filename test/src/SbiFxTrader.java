import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SbiFxTrader extends Thread {
    private WebDriver driver;
    float buy;
    float sell;

    public SbiFxTrader(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void run() {
        System.out.println("LoginSbiFxTrader of Thread number is " + getName());
        driver.get("https://www.sbisec.co.jp/ETGate");
        System.out.println(driver.getTitle());
        driver.findElement(By.name("user_id")).sendKeys("");//ここにSBI証券のログインIDを入力してください
        driver.findElement(By.name("user_password")).sendKeys("");//ここにSBI証券のログインパスワードを入力してください
        driver.findElement(By.name("ACT_login")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//*[@id=\"navi01P\"]/ul/li[7]/a/img")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"SUBAREA01\"]/div[4]/div/div/ul/li[7]/a")).click();

        // 指定した要素が表示されるまで待機する
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        System.out.println("取引サイトまではログインOK");
        // fetch handles of all windows, there will be two, [0]- default, [1] - new
        // window
        Object[] windowHandles = driver.getWindowHandles().toArray();
        System.out.println(windowHandles);
        driver.switchTo().window((String) windowHandles[1]);
        // assert on title of new window
        WebElement iframe = driver.findElement(By.id("frame_price"));
        driver.switchTo().frame(iframe);

        while (true) {
            buy = Float.parseFloat(driver
                    .findElement(By.xpath("//*[@id=\"list_0\"]/div/div[2]/span[1]")).getText());
            System.out.println("--------------------");
            System.out.println("SBI証券FX買いRATE:" + buy);

            sell = Float.parseFloat(driver
                    .findElement(By.xpath(
                            "//*[@id=\"list_0\"]/div/div[3]/span[1]"))
                    .getText());
            System.out.println("SBI証券FX売りRATE:" + sell);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("--------------------");
        }

    }
}