package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TransferenciaForm extends PageObject {
    @FindBy(id = "cuentao")
    private WebElement cuentaOrigen;
    Select selectcuentaOrigen = new Select(cuentaOrigen);

    @FindBy(id = "monto")
    private WebElement monto;

    @FindBy(id = "cuentad")
    private WebElement cuentaDestino;
    Select selectcuentaDestino = new Select(cuentaDestino);

    @FindBy(id = "codigo")
    private WebElement codigo;

    @FindBy(xpath = "//button[@onclick='transferencia()']")
    private WebElement botonTransferir;

    public TransferenciaForm(WebDriver driver) {
        super(driver);
    }

    public void setCuentaOrigen(String value){
        this.selectcuentaOrigen.selectByValue(value);
    }

    public void setMonto(String value){
        this.monto.sendKeys(value);
    }

    public void setCuentaDestino(String value){
        this.selectcuentaDestino.selectByValue(value);
    }

    public void setCodigo(String value){
        this.codigo.sendKeys(value);
    }

    public void transferir(){
        botonTransferir.click();
    }
}
