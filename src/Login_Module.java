import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
public class Login_Module{	
	public void login(String username, String password) {
		NewLine_Tests.driver.findElement(By.cssSelector(".ml-auto > .nav-item:nth-child(2) > .nav-link")).click();
		  NewLine_Tests.driver.findElement(By.name("username")).sendKeys(username);
		  NewLine_Tests.driver.findElement(By.name("password")).sendKeys(password);
		  NewLine_Tests.driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
	}
}