package mainPageFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhpTravels {

	String browser;
	WebDriver driver;
	WebDriverWait wait;
	
	
	
	@FindBy(xpath = "//a[@href='#flights']")
	public static WebElement flightsElement;

	/*
	 * @FindBy() public static WebElement enterCity;
	 */

	@FindBy(xpath = "//div[@class='trip-check']/div[2]")
	public static WebElement roundtripButtonCheck;

	@FindBy(xpath = "//select[@name='cabinclass']")
	public static WebElement cabinClass;

	@FindBy(xpath = ".//*[@id='s2id_location_from']/input")
	public static WebElement from;

	@FindBy(xpath = ".//*[@id='s2id_location_to']/input")
	public static WebElement to;

	@FindBy(xpath = "//div[@class='select2-result-label']")
	public static WebElement dropDownFrom;

	@FindBy(xpath = "(//div[@class='select2-result-label'])[1]")
	public static WebElement dropDownTo;

	@FindBy(name = "departure")
	public static WebElement departureBox;

	@FindBy(name = "arrival")
	public static WebElement arrivalBox;

	@FindBy(name = "totalManualPassenger")
	public static WebElement passengerBox;

	@FindBy(css = ".modal-dialog.modal-sm.wow.flipInY.animated")
	public static WebElement passengerBoxDialog;

	@FindBy(xpath = ".//*[@id='flights']/form/div[6]/button")
	public static WebElement searchButton;

	@FindBy(xpath=".//*[@id='load_data']/tbody/tr[1]/td")
	public static WebElement firstFligtsBox;
	
	@FindBy(name="firstname")
	public static WebElement firstName;

	
	@FindBy(name="lastname")
	public static WebElement lastName;

	@FindBy(name="email")
	public static WebElement email;
	
	@FindBy(name="confirmemail")
	public static WebElement confirmEmail;
	
	@FindBy(name="phone")
	public static WebElement mobile;
	
	@FindBy(name="address")
	public static WebElement address;
	
	@FindBy(name="country")
	public static WebElement country;
	
	@FindBy(name="guest")
	public static WebElement confirmBooking;
	
	@FindBy(xpath=".//*[@id='btn']")
	public static WebElement downloadPDF;
	
	@FindBy(css=".btn.btn-default.arrivalpay")
	public static WebElement arrivalPayButton;
	
	@FindBy(xpath=".//*[@id='invoiceTable']/tbody/tr[1]/td/div/b")
	public static WebElement statusReservedMsg;
	
	InputStream input ;
	Properties prop = new Properties();
	
	
	public void openBrowser() throws IOException {

		InputStream input = new FileInputStream("src/dataFile/data.properties");
		
		prop.load(input);

		browser = prop.getProperty("browser");

		if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:/SeleniumJars/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "C:/SeleniumJars/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
	}

	public void openSite() {
		driver.manage().window().maximize();
		driver.get(prop.getProperty("site"));
		
		wait = new WebDriverWait(driver, 30);
	}

	public void closeBrowser() {
		driver.close();
	}

	public void selectFlightButton() throws InterruptedException {
		flightsElement.click();
		Thread.sleep(3000);
	}

	public void roundtrip() throws InterruptedException {
		roundtripButtonCheck.click();
		Thread.sleep(3000);
	}

	public void cabinClassSelect() throws InterruptedException {
		Select dropdown = new Select(cabinClass);
		
		dropdown.selectByValue(prop.getProperty("class"));
		Thread.sleep(3000);
	}

	public void enterFrom() throws InterruptedException {
		from.sendKeys(prop.getProperty("from"));
		wait.until(ExpectedConditions.elementToBeClickable(dropDownFrom));
		// Thread.sleep(2000)
		dropDownFrom.click();
		Thread.sleep(3000);
	}

	public void enterTo() throws InterruptedException {
		to.sendKeys(prop.getProperty("to"));
		wait.until(ExpectedConditions.elementToBeClickable(dropDownTo));
		dropDownTo.click();
		Thread.sleep(3000);
	}

	public void enterDepartureDate() throws InterruptedException {
		departureBox.click();
		departureBox.sendKeys(prop.getProperty("departure"));
		Thread.sleep(3000);
	}

	public void enterArrivalDate() throws InterruptedException {
		arrivalBox.click();
		arrivalBox.sendKeys(prop.getProperty("arrival"));
		Thread.sleep(3000);
	}

	public void enterManualPassenger() throws InterruptedException {
		passengerBox.click();
		wait.until(ExpectedConditions.visibilityOf(passengerBoxDialog));
		passengerBoxDialog.findElement(By.name("madult")).sendKeys(prop.getProperty("noOfAdult"));
		passengerBoxDialog.findElement(By.name("mchildren")).sendKeys(prop.getProperty("noOfChildren"));
		passengerBoxDialog.findElement(By.cssSelector("#sumManualPassenger")).click();

		Thread.sleep(2000);
	}

	public void clickSearchButton() throws InterruptedException {
		searchButton.click();
		Thread.sleep(3000);
	}
	
	public String getTitle()
	{
		return driver.getTitle();
		
	}
	
	public void clickBookNowButton() throws InterruptedException {
		firstFligtsBox.findElement(By.id("bookbtn")).click();
		Thread.sleep(3000);
	}
	
	public void enterAsAGuest() throws InterruptedException 
	{
		firstName.sendKeys(prop.getProperty("firstname"));
		lastName.sendKeys(prop.getProperty("lastname"));
		email.sendKeys(prop.getProperty("email"));
		confirmEmail.sendKeys(prop.getProperty("email"));
		mobile.sendKeys(prop.getProperty("phone"));
		address.sendKeys(prop.getProperty("address"));
		Select setCountry = new Select(country);
		setCountry.selectByVisibleText(prop.getProperty("country"));

		Thread.sleep(3000);
	}
	public void confirmBooking() throws InterruptedException {
		confirmBooking.click();
		Thread.sleep(5000);
	}
	
	public String getInvoicePageTitle()
	{
		
		return driver.getTitle();
	}
	
	public void downloadPDF() throws InterruptedException
	{
		downloadPDF.click();
		Thread.sleep(3000);
		
	}
	
	public void arrivalPayButtonClick() throws InterruptedException
	{
		arrivalPayButton.click();
		Thread.sleep(1000);
		Alert a = driver.switchTo().alert();
		a.accept();
		Thread.sleep(3000);
	}
	public String getReservedMsg()
	{
		return statusReservedMsg.getText();

	}
}
