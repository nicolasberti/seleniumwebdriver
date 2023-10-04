package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainTests {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Transferencia exitosa")
    public static void transferenciaExitosa(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setMonto("50000");
        transferenciaForm.setCuentaDestino("01");
        transferenciaForm.setCodigo("1112");
        transferenciaForm.transferir();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/transferenciacorrecta.html");
        driver.quit();
    }

    @Test(testName = "Transferencia monto insuficiente")
    public static void transferenciaMontoInsuficiente(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setMonto("500000");
        transferenciaForm.setCuentaDestino("01");
        transferenciaForm.setCodigo("1112");
        transferenciaForm.transferir();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/fondosinsuficientes.html");
        driver.quit();
    }

    @Test(testName = "Transferencia codigo invalido de 4 digitos")
    public static void transferenciaCodigoInvalido4Dig(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setMonto("50000");
        transferenciaForm.setCuentaDestino("01");
        transferenciaForm.setCodigo("1111");
        transferenciaForm.transferir();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/codigoincorrecto.html");
        driver.quit();
    }

    @Test(testName = "Transferencia monto vacio")
    public static void transferenciaMontoVacio(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setCuentaDestino("01");
        transferenciaForm.setCodigo("1112");
        transferenciaForm.transferir();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/montoincorrecto.html");
        driver.quit();
    }

    @Test(testName = "Transferencia monto invalido (no es numerico)")
    public static void transferenciaMontoInvalido(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setCuentaDestino("01");
        transferenciaForm.setMonto("martin");
        transferenciaForm.setCodigo("1112");
        transferenciaForm.transferir();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/montoincorrecto.html");
        driver.quit();
    }

    @Test(testName = "Transferencia monto negativo")
    public static void transferenciaMontoNegativo(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setCuentaDestino("01");
        transferenciaForm.setMonto("-50000");
        transferenciaForm.setCodigo("1112");
        transferenciaForm.transferir();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/montoincorrecto.html");
        driver.quit();
    }

    @Test(testName = "Transferencia cuenta destino vacia")
    public static void transferenciaCuentaDestinoVacia(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setCuentaDestino("00");
        transferenciaForm.setMonto("50000");
        transferenciaForm.setCodigo("1112");
        transferenciaForm.transferir();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/destinoincorrecto.html");
        driver.quit();
    }

    @Test(testName = "Transferencia codigo invalido (menor a 4 digitos)")
    public static void transferenciaCodigo3Dig(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setCuentaDestino("01");
        transferenciaForm.setMonto("50000");
        transferenciaForm.setCodigo("111");
        transferenciaForm.transferir();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/codigoincorrecto.html");
        driver.quit();
    }

    @Test(testName = "Transferencia codigo vacio")
    public static void transferenciaCodigoVacio(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.setCuentaOrigen("CA 01-4587796-9");
        transferenciaForm.setCuentaDestino("01");
        transferenciaForm.setMonto("50000");
        transferenciaForm.setCodigo("");
        transferenciaForm.transferir();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://cs.uns.edu.ar/~mll/temp/testing/codigo.html");
        driver.quit();
    }

}
