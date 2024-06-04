package org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{
private By btnMyAccount = By.cssSelector("a[title='My Account']");
private By btnRegister = By.xpath("//a[normalize-space()='Register']");
private By firstName = By.id("input-firstname");
private By lastName = By.id("input-lastname");
private By email = By.id("input-email");
private By telephone = By.id("input-telephone");
private By password = By.id("input-password");
private By confirmPassword = By.id("input-confirm");
private By btnprivacyPolicy = By.xpath("//input[@name='agree']");
private By btnNoNewsletter = By.cssSelector("input[value='0'][name='newsletter']");
private By btnContinue = By.cssSelector("input[value='Continue'][type='submit']");
private By resultContent = By.id("content");

    /**Constructor de la clase RegisterPage
     * @param driver la instancia de WebDriver utilizada para interactuar con la página web
     */
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /** Hace click en el botón "My Account".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickMyAccount() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(btnMyAccount));
        this.click(btnMyAccount);
    }

    /** Hace click en el botón "Register".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickRegister() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(btnRegister));
        this.click(btnRegister);
    }

    /** Ingresa el nombre proporcionado en el campo de nombre.
     * @param name el nombre a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeFirstName(String name) throws InterruptedException {
        this.sendText(name, firstName);
    }

    /** Ingresa el apellido proporcionado en el campo de apellido.
     * @param name el apellido a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeLastName(String name) throws InterruptedException {
        this.sendText(name, lastName);
    }

    /** Ingresa el correo electrónico proporcionado en el campo de correo electrónico.
     * @param mail el correo electrónico a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeEmail(String mail) throws InterruptedException {
        this.sendText(mail, email);
    }

    /** Ingresa el teléfono proporcionado en el campo de teléfono.
     * @param phone el teléfono a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeTelephone(String phone) throws InterruptedException {
        this.sendText(phone, telephone);
    }

    /** Ingresa la contraseña proporcionada en el campo de contraseña.
     * @param pass la contraseña a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writePassword(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    /** Reingresa la contraseña proporcionada en el campo de confirmación de contraseña.
     * @param pass la contraseña a reingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void confirmPassword(String pass) throws InterruptedException {
        this.sendText(pass, confirmPassword);
    }

    /** Hace click en el botón "Privacy Policy".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickPrivacyPolicy() throws InterruptedException {
        this.click(btnprivacyPolicy);
    }

    /** Hace click en el botón "No Newsletter".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickNoNewsletter() throws InterruptedException {
        this.click(btnNoNewsletter);
    }

    /** Hace click en el botón "Continue".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickContinue() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
        this.click(btnContinue);
    }

    /** Obtiene el mensaje de resultado de la operación.
     * @return el texto del mensaje de resultado
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public String getResultContent() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(resultContent));
        return this.getText(resultContent);
    }

}
