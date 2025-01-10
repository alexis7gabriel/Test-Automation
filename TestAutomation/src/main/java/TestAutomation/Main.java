package TestAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //step 1) enter the website
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mercadolibre.com");

        //step 2) Select México as a country
        driver.findElement(By.xpath("//a[@id='MX']")).click();

        //step 3) Search for the term playstation 5
        driver.findElement(By.xpath("//button[normalize-space()='Aceptar cookies']")).click();
        driver.findElement(By.xpath("//button[@data-js='onboarding-cp-close']")).click();
        driver.findElement(By.xpath("//input[@id='cb1-edit']")).sendKeys("playstation5");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //step 4) Filter by condition Nuevos
        driver.findElement(By.xpath("//a[contains(@title, 'Nuevo')]")).click();

        //step 5) Filter by location CDMX
        driver.findElement(By.xpath("//a[contains(@title, 'Distrito Federal')]")).click();

        driver.findElement(By.xpath("//button[@aria-label='Quitar el filtro de Modelo PlayStation 5']")).click();
        driver.findElement(By.xpath("//button[@aria-label='Quitar el filtro de Marca Sony']")).click();

        //step 6) Order by mayor a menor precio
        driver.findElement(By.xpath("//button[@id=':R2m55ee:-trigger']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id=':R2m55ee:-menu-list-option-price_desc']")));
        driver.findElement(By.xpath("//li[@id=':R2m55ee:-menu-list-option-price_desc']")).click();

        //step 7) Obtain the name and the price of the first 5 products and print
        List<WebElement> items = driver.findElements(By.className("ui-search-layout__item"));
        System.out.println("Size of elements: " + items.size());

        for (int i = 0; i < 5; i++) {
            WebElement item = items.get(i);

            // get name of product
            WebElement titleElement = item.findElement(By.className("poly-component__title"));
            String title = titleElement.getText();

            // get price of the product
            WebElement priceElement = item.findElement(By.className("andes-money-amount__fraction"));
            String price = priceElement.getText();

            // print data
            System.out.println("Item " + (i + 1) + ": " + title);
            System.out.println("Price: " + price);
            System.out.println();
        }
    }
}