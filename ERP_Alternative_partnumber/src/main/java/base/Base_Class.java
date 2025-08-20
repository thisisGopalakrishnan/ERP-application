package base;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Class {
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();;
	
	public static void setDriver(WebDriver driverr) {
		driver.set(driverr);
	}
	public static WebDriver getdriver() {
		return driver.get();		
	}
	public void launch_browser(String browsername) {
		WebDriver driverr = null;
		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driverr= new ChromeDriver();	
		} else if (browsername.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Users\\GopalakrishnanG\\edgedriver_win64 (2)\\msedgedriver.exe");
			 driverr = new EdgeDriver();
//			WebDriverManager.edgedriver().setup();
//			driverr = new EdgeDriver();
		}else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driverr = new FirefoxDriver();
		}
		driverr.manage().window().maximize();
		driverr.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		setDriver(driverr);
	}
	public static void url(String url) {
		getdriver().get(url);
	}

	public static void click(WebElement click) {
		((JavascriptExecutor) getdriver()).executeScript("arguments[0].click;", click);
	}

	public static void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) getdriver();
		js.executeScript("window.scrollby(0,1000)", "");
	}
	
	public static void scroll_down() throws InterruptedException {
		((JavascriptExecutor) getdriver()).executeScript("window.scrollTo(0, 0);");
		JavascriptExecutor js = (JavascriptExecutor) getdriver();
//		 Scroll to top
		js.executeScript("window.scrollTo(0, 0);");
		Thread.sleep(2000); // allow time for page to settle

	}

	public static void robotclass() throws AWTException {
		Robot robot = new Robot();
	}

	private void selectbyindex(WebElement element, WebDriver getDriver, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public static void Screenshot() throws IOException, WebDriverException {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
		File s = ((TakesScreenshot) getdriver()).getScreenshotAs(OutputType.FILE);
		Files.copy(s, new File("\\D:\\Screenshots\\" + timestamp + ".jpeg"));
	}
	public static void elementwaitclicable(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(Element));
	}
	public static void elementwaitvisible(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Element));
	}
	public static void timeStampp() {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
	}
	

}
