package org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {
    private By searchBox = By.xpath("//*[@id=\"search\"]/input");
    private By searchButton = By.xpath("//*[@id=\"search\"]/span/button");
    private By addToCartButton = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]");
    private By resultContent = By.xpath("//div[@class='alert alert-success alert-dismissible']");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /**Completa la busqueda con la palabra "Iphone".
    * @param product La palabra a buscar.
    * @throws InterruptedException Si ocurre un error durante la espera.
    */
    public void search(String product) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBox));
        this.sendText(product, searchBox);
        this.click(searchButton);
    }

    /** Click en el boton buscar.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void clickSearch() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        this.click(searchButton);
    }

    /** Click en el boton agregar al carrito.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void clickAddToCart() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        this.click(addToCartButton);
    }

    /** Obtiene el resultado de la accion agregar al carrito.
     * @return El texto del resultado de la accion.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public String resultContent() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(resultContent));
        System.out.println("RESULTADO DE LA ACCION: " + this.getText(resultContent));
        return this.getText(resultContent);
    }



}
