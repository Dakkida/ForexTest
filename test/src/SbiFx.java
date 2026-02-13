
//SBI FXトレードのwebページを開き、ログインします。
import org.openqa.selenium.*;

public class SbiFx extends Thread {
    private WebDriver driver;
    private float buy;
    private float sell;

    public SbiFx(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void run(){
        // URLを開きます
        System.out.println("LoginSbiFx of Thread number is " + getName());
        driver.get("https://www.sbifxt.co.jp/");
        System.out.println(driver.getTitle());
        driver.findElement(By.id("loginid")).sendKeys("");//ここにSBI FXのログインIDを入力してください
        driver.findElement(By.name("PASS")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"ClientLogin\"]/div[3]/p/a")).click();
        driver.findElement(By.xpath("//*[@id=\"home_depositGraph\"]/section[1]/div/div/ul/li[1]/p/button[1]/span"))
                .click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // 為替レートを取得
        while (true) {
            buy = Float.parseFloat(driver
                    .findElement(By.xpath("//*[@id=\"ratePanel\"]/section/div[2]/table/tbody/tr[1]/td[2]")).getText());
            System.out.println("--------------------");
            System.out.println("SBIFX買いRATE:" + buy);

            sell = Float.parseFloat(driver
                    .findElement(By.xpath("//*[@id=\"ratePanel\"]/section/div[2]/table/tbody/tr[1]/td[4]")).getText());
            System.out.println("SBIFX売りRATE:" + sell);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("--------------------");
        }
    }

    public float getBuy() {
        return this.buy;
    }

    public float getSell() {
        return this.sell;
    }
}