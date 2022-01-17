import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import reusable.readExcel
import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.Select as Select
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.concurrent.TimeUnit

CustomKeywords.'login.log.login'()
nuevaConsulta()

public static def consultar(String Usuario, String Contrasena, String Fecha, String Comentario) {
	
	By txt_User = By.xpath("//input[@id='txt-username']")
	By txt_Pass = By.xpath("//input[@id='txt-password']")
	By btn_Login = By.xpath("//button[@id='btn-login']")
	By lst_Facility = By.xpath("//option[contains(text(), 'Tokyo')]")
	By chk_Apply = By.xpath("//label[@class='checkbox-inline']")
	By rdo_None = By.xpath("//input[@id='radio_program_none']")
	By dat_Calendar = By.xpath("//input[@id='txt_visit_date']")
	By txt_Comment = By.xpath("//textarea[@id='txt_comment']")
	By btn_Home = By.xpath("//button[@id='btn-book-appointment']")
	By lbl_Confirmation = By.xpath("//h2[contains(text(), 'Confirmation')]")
	By btn_GoTo = By.xpath("//a[@class='btn btn-default']")
	
	WebDriver driver = DriverFactory.getWebDriver()
	driver.findElement(txt_User).sendKeys(Usuario)
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
	driver.findElement(txt_Pass).sendKeys(Contrasena)
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
	driver.findElement(btn_Login).click()
	WebUI.takeScreenshot()
	driver.findElement(lst_Facility).click()
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
	driver.findElement(chk_Apply).click()
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
	driver.findElement(rdo_None).click()
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
	driver.findElement(dat_Calendar).sendKeys(Fecha)
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
	driver.findElement(txt_Comment).click()
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
	driver.findElement(txt_Comment).sendKeys(Comentario)
	WebUI.takeScreenshot()
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
	driver.findElement(btn_Home).click()
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)
	WebUI.takeScreenshot()
	driver.findElement(btn_GoTo).click()
	CustomKeywords.'login.log.login'()
}

public static def nuevaConsulta() {
	
			String filePath = "Data Driven\\DataDriven.xlsx"
			String dataSheet = "Data"
			
			List<String> data = CustomKeywords.'reusable.readExcel.excelFile'(filePath, dataSheet)
			int contFilas = data.size()
			
			String Usuario, Contrasena, Fecha, Comentario
			
			for (int i = 0 ; i < contFilas ; i++ ) {
				for(int j = 0 ; j < 4 ; j++) {
					switch(j) {
						case 0:
						Usuario = data[i][j]
							break
						case 1:
						Contrasena = data[i][j]
							break
						case 2:
						Fecha = data[i][j]
							break
						case 3:
						Comentario = data[i][j]
							break
					}
					println data[i][j]
				}
				consultar(Usuario, Contrasena, Fecha, Comentario)
			}
	}