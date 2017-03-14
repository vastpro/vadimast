package testsvip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class General {
    ChromeDriver wd;

    @Before
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.manage().window().maximize();
    }

    @Test
    public void General() {
        wd.get("https://www.kupivip.ru/");
        registration();
        logOut();
        logIn();
        logOut();
    }

    public void logOut() { //Разлогинивание
        wd.findElement(By.linkText("Личный кабинет")).click();
        wd.findElement(By.linkText("ВЫХОД")).click();
    }

    public static String passGen() { //Генератор паролей
        String pass = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        String passGen = "";
        Random r = new Random();
        char c ;
        for(int i=0; i<6; i++){
            c = pass.charAt(r.nextInt(pass.length()));
            passGen = passGen + c;
            }
        return passGen;
    }


    public void logIn() { //Логин на сайте
        wd.findElement(By.linkText("Вход")).click();
        wd.findElement(By.id("user-name")).click();
        wd.findElement(By.id("user-name")).clear();
        wd.findElement(By.id("user-name")).sendKeys("av0x@yandex.ru");
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("vastpro19");
        wd.findElement(By.id("login-submit")).click();
    }

    public static String CreateEmailName() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_hh_mm_ss");
        return sdf.format(new Date(System.currentTimeMillis())) + "_test@yopmail.com";
    }

    public void registration() {
        wd.findElement(By.linkText("Регистрация")).click();
        wd.findElement(By.id("email")).click();
        wd.findElement(By.id("email")).clear();
        wd.findElement(By.id("email")).sendKeys(CreateEmailName());
        wd.findElement(By.id("pw")).click();
        wd.findElement(By.id("pw")).clear();
        wd.findElement(By.id("pw")).sendKeys(passGen());
        wd.findElement(By.id("register")).click();
        wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        wd.findElement(By.xpath("//form[@id='registration2-pane']//button[.='Пропустить']")).click();

    }

    @After
    public void tearDown() {
        wd.quit();
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
