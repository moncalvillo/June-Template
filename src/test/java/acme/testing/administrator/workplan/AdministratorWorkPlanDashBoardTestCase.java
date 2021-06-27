package acme.testing.administrator.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class AdministratorWorkPlanDashBoardTestCase extends AcmePlannerTest{
	
/*En el siguiente test se provara que los valores numericos devueltos por el servicio sean correctos*/
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/workplan/workplan-dashboard.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll( 
	final String publicTasks,
	final String privateTasks,
	final String finishedTasks,
	final String nonFinishedTasks,
	final String averageWorkFlow,
	final String deviationWorkFlow,
	final String maxWorkFlow,
	final String minWorkFlow,
	final String averageExecutionPeriod,
	final String deviationExecutionPeriod,
	final String maxExecutionPeriod,
	final String minExecutionPeriod) {
		
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard Work Plans");
		
		super.checkDashBoard(publicTasks, privateTasks, finishedTasks, nonFinishedTasks, averageWorkFlow, deviationWorkFlow, maxWorkFlow, minWorkFlow, averageExecutionPeriod, deviationExecutionPeriod, maxExecutionPeriod, minExecutionPeriod);;
		
		this.signOut();
	}
	
	/*En el siguiente test se provara la no posibilidad de acceder al dashboard por parte de un usuario no autorizado*/
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/workplan/users.csv", encoding="utf-8", numLinesToSkip=1)
	@Order(20)
	public void listNegative(final String username, final String password) {
		if(username!=null) this.signIn(username, password);
			super.driver.get("http://localhost:8080/June-Template/administrator/dashboardwp/list");
			super.checkErrorsExist();
			if(username!=null) super.signOut();;
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/shoutDashboard.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void shoutDashboard( 
	final String totalShouts, final String ratioTrue, final String ratioEUR, final String EURavg,final String USDavg, final String EURdev, final String USDdev) {
		
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard Work Plans");
		

		final By locatorTotalShouts = By.xpath("/html/body/div[2]/div/table[1]/tbody/tr/td[2]");
		final By locatorRatioTrue = By.xpath("/html/body/div[2]/div/table[1]/tbody/tr/td[3]");
		final By locatorRatioEUR = By.xpath("/html/body/div[2]/div/table[1]/tbody/tr/td[4]");
		final By locatorEURavg = By.xpath("/html/body/div[2]/div/table[1]/tbody/tr/td[5]");
		final By locatorUSDavg = By.xpath("/html/body/div[2]/div/table[1]/tbody/tr/td[6]");
		final By locatorEURdev = By.xpath("/html/body/div[2]/div/table[1]/tbody/tr/td[7]");
		final By locatorUSDdev = By.xpath("/html/body/div[2]/div/table[1]/tbody/tr/td[8]");
		
		assert super.driver.findElement(locatorTotalShouts).getText().equals(totalShouts) ;
		assert super.driver.findElement(locatorRatioTrue).getText().equals(ratioTrue);
		assert super.driver.findElement(locatorRatioEUR).getText().equals(ratioEUR);
		assert super.driver.findElement(locatorEURavg).getText().equals(EURavg);
		assert super.driver.findElement(locatorUSDavg).getText().equals(USDavg);
		assert super.driver.findElement(locatorEURdev).getText().equals(EURdev);
		assert super.driver.findElement(locatorUSDdev).getText().equals(USDdev);
		
		
		this.signOut();
	}
}
