import org.testng.Assert;
public class Comparison_Module{
	public void compare_title(String expected_title, String failed_message) {
	    String actual_title=NewLine_Tests.driver.getTitle();
	    Assert.assertEquals(actual_title,expected_title, failed_message);
	}
}