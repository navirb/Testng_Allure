package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() {

		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");

		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");

		try {
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

			//ChromeOptions options = new ChromeOptions();
			System.out.println("==================================");
			System.out.println(System.getProperty("user.dir"));
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			System.out.println("==================================");
			//options.setBinary("drivers/chromedriver.exe");

			//options.addArguments("--remote-allow-origins=*");
			// WebDriver driver = new ChromeDriver(options);
			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(options);

		} else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webrdiver.gecko.driver", "C:\\geckodriver-v0.33.0-win32\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));

		return driver;

	}

}
