import org.openqa.selenium.By;

public class Register_Module{
	public void register(String firstname, String lastname, String username, String email, String password, String confirm_password) {
		NewLine_Tests.driver.findElement(By.cssSelector(".ml-auto > .nav-item:nth-child(1) > .nav-link")).click();
		  NewLine_Tests.driver.findElement(By.name("first_name")).sendKeys(firstname);
		  NewLine_Tests.driver.findElement(By.name("last_name")).sendKeys(lastname);
		  NewLine_Tests.driver.findElement(By.name("username")).sendKeys(username);
		  NewLine_Tests.driver.findElement(By.name("email")).sendKeys(email);
		  NewLine_Tests.driver.findElement(By.name("password")).sendKeys(password);
		  NewLine_Tests.driver.findElement(By.name("password2")).sendKeys(confirm_password);
		  NewLine_Tests.driver.findElement(By.cssSelector(".btn")).click();
	}
}