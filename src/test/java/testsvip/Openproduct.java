package testsvip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class Openproduct {
    ChromeDriver wd;
    Random random = new Random();
    String[] userFemaleFirstName = {"Иванова","Смирнова","Кузнецова","Попова","Васильева","Петрова"};
    String[] userFemaleName = {"Мария","Анна", "Анастасия", "Виктория", "Елизавета", "Полина"};
    String[] userFemaleFatherName = {" Александровна"," Михаиловна"," Иванова"," Дмитриевна"," Кирилловна"," Андреевнв"};


    @Before
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.manage().window().maximize();
    }

    @Test
    public void Openproduct() throws InterruptedException {
        wd.get("https://www.kupivip.ru");
        wd.findElement(By.xpath("//form[@id='search-form']/button")).click();
        wd.findElement(By.xpath("//*[contains(@class, 'list-products') and contains(@data-position, '1')]")).click();
        wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        wd.findElement(By.xpath("//div[@class='product-cart']//button[.='Добавить в корзину']")).click();
        Thread.sleep(2000);
        wd.findElement(By.linkText("Оформить мои покупки")).click();
        wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        wd.findElement(By.id("username")).click();
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys(userFemaleFirstName[random.nextInt(6)]+" "+userFemaleName[random.nextInt(6)]+userFemaleFatherName[random.nextInt(6)]);
        wd.findElement(By.id("emailAddressField")).click();
        wd.findElement(By.id("emailAddressField")).clear();
        wd.findElement(By.id("emailAddressField")).sendKeys(General.CreateEmailName());
        wd.findElement(By.id("phone")).click();
        wd.findElement(By.id("phone")).clear();
        wd.findElement(By.id("phone")).sendKeys("+7 (999) 309-99-99");
        wd.findElement(By.id("address")).click();
        wd.findElement(By.id("address")).clear();
        wd.findElement(By.id("address")).sendKeys("Смоленск Ленина 10");
        wd.findElement(By.cssSelector("label.control-label.dotted")).click();
        wd.findElement(By.id("comment")).click();
        wd.findElement(By.id("comment")).clear();
        wd.findElement(By.id("comment")).sendKeys("Тест");
        Thread.sleep(1000);
        wd.findElement(By.id("buy")).click();
        wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        wd.findElement(By.linkText("Личный кабинет")).click();
        wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        wd.findElement(By.xpath("//td[@class='orders-table__number']")).click();
        wd.findElement(By.linkText("Отменить заказ")).click();
        if (!wd.findElement(By.xpath("//select[@id='cancelReason']//option[3]")).isSelected()) {
            wd.findElement(By.xpath("//select[@id='cancelReason']//option[3]")).click();
        }
        wd.findElement(By.name("submitForm")).click();
    }



    @After
    public void tearDown() {
       // wd.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
