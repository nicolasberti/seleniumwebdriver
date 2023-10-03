package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestPlan {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Test 1")
    public static void testUno(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setMonto("50000");
        transferenciaForm.setCuentaDestino("01");
        transferenciaForm.setCodigo("1112");
        transferenciaForm.transferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/transferenciacorrecta.html");

    }
}
