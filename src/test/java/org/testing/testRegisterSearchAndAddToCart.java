package org.testing;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.reports.ReportFactory;

import java.time.Duration;

public class testRegisterSearchAndAddToCart {

    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reports/RegisterSearchAndAddToCart-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZA EL TEST >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.getUrl("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @Test
    public void RegisterSearchAndAddToCart() throws InterruptedException {
        try{
        //Para obtener un usuario aleatorio desde el archivo Json en carpeta Resourses
        User randomUser = User.randomUser();
        /**Realiza el registro de un usuario Random.
         * @param randomUser Usuario aleatorio.
         */
        registerUser(randomUser);
        /**Realiza la busqueda de un producto y lo agrega al carrito.
         * @param "iPhone" Producto a buscar y agregar al carrito.
         */
        searchAndAddToCart("iPhone");
        }catch (Exception error) {
            throw new RuntimeException("RegisterSearchAndAddToCart test failed", error);
        }
    }
    @AfterEach
    public void close() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
        System.out.println("<<< FINALIZA EL TEST >>>");
    }


    public void registerUser(User user) throws InterruptedException {
        ExtentTest test = extent.createTest("successful user registration");
        test.log(Status.INFO, "Start test");
        test.log(Status.PASS, "Registering a new user");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        try {
            registerPage.clickMyAccount();
            registerPage.clickRegister();

            registerPage.writeFirstName(user.getFirstName());
            registerPage.writeLastName(user.getLastName());
            registerPage.writeEmail(user.getEmail());
            registerPage.writeTelephone(user.getTelephone());
            registerPage.writePassword("123456");
            registerPage.confirmPassword("123456");
            registerPage.clickNoNewsletter();
            registerPage.clickPrivacyPolicy();
            test.log(Status.PASS, "User data entered successfully");
            registerPage.clickContinue();
            test.log(Status.INFO, "Checking registration");
            if (registerPage.getResultContent().contains("Congratulations! Your new account has been successfully created!")) {
                System.out.println("Registro exitoso");
                test.log(Status.PASS, "USER REGISTRATION SUCCESSFUL");
                ReportFactory.captureScreenshot(test, "SUCCESSFUL_USER_REGISTRATION", driver);
            } else {
                System.out.println("Registro fallido");
                throw new InterruptedException("Invalid user Data");
            };
        }catch (Exception error) {
            test.log(Status.FAIL, "USER REGISTRATION FAILED" + error.getMessage());
            ReportFactory.captureScreenshot(test, "FAILED_USER_REGISTRATION", driver);
        }
        test.log(Status.INFO, "End test");
    }
    public void searchAndAddToCart(String product) throws InterruptedException {
        ExtentTest test = extent.createTest("Search and add product to cart");
        test.log(Status.INFO, "Start test");
        test.log(Status.PASS, "Searching for a product and adding it to the cart");
        SearchPage searchPage = new SearchPage(driver, wait);
        try{
        searchPage.search(product);
        searchPage.clickSearch();
        test.log(Status.PASS, "Product search completed");
        searchPage.clickAddToCart();
        test.log(Status.INFO, "Checking product addition to cart");

        if(searchPage.resultContent().equals("Success: You have added iPhone to your shopping cart!\n" +
                "×")){
            System.out.println("Producto agregado al carrito");
            test.log(Status.PASS, "PRODUCT ADDED TO CART");
            ReportFactory.captureScreenshot(test, "PRODUCT_ADDED_TO_CART", driver);
        } else {
            System.out.println("Producto no agregado al carrito");
            throw new Exception("Error adding product to cart");
        };
        }catch (Exception error) {
            test.log(Status.FAIL, "PRODUCT ADDITION FAILED" + error.getMessage());
            ReportFactory.captureScreenshot(test, "FAILED_PRODUCT_ADDITION", driver);
        }
        test.log(Status.INFO, "End test");
    }
}
