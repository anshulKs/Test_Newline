import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
public class NewLine_Tests {
	static WebDriver driver;	//As the other modules will use the WebDriver instance of the Main script, it is important to declare it static, so that there is no need to instantiate its object in the other module scripts.
	//Creating objects of modules to access their methods
	Launch_module test_instance=new Launch_module(); Register_Module register=new Register_Module(); Login_Module login=new Login_Module(); Comparison_Module compare=new Comparison_Module();
  
  @BeforeTest
  public void setUp() {
	  //System.setProperty("webdriver.chrome.driver", "PATH_TO_CHROMEDRIVER.EXE");
    driver = new ChromeDriver();
  }
  @AfterTest
  public void tearDown() {
    driver.quit();
  }
  
  //VALID TEST CASES
  
  @Test
  public void tC_001_VALID() {
	  //launching Home page
    test_instance.launch_browser();
    compare.compare_title("NewLine Real Estate | Welcome", "The Home page is not visited/Smoke Test Failed");

    //launching About Page
    driver.findElement(By.cssSelector(".navbar-nav:nth-child(1) > .nav-item:nth-child(2) > .nav-link")).click();
    compare.compare_title("NewLine Real Estate | About Us", "The About page is not visited");
        
    //launching Featured Listings Page
    driver.findElement(By.cssSelector(".nav-item:nth-child(3) > .nav-link")).click();
    compare.compare_title("NewLine Real Estate | Browse Property Listings", "The Featured Listings page is not visited");
    
    //launching Register page
    driver.findElement(By.cssSelector(".ml-auto > .nav-item:nth-child(1) > .nav-link")).click();
    compare.compare_title("NewLine Real Estate | Register Account", "The Register page is not visited");
    
    //launching login page
    driver.findElement(By.cssSelector(".ml-auto > .nav-item:nth-child(2) > .nav-link")).click();
    compare.compare_title("NewLine Real Estate | Account Login", "The Login page is not visited");
  }
  
  @Test
  public void tC_002_VALID() {
	  test_instance.launch_browser();
	    //Navigating to Register
	  register.register("Anshul", "Sharma", "anshul10", "******@gmail.com", "******", "******");
	  compare.compare_title("NewLine Real Estate | Account Login", "The Login page is not visited, Either- registration didn't occur, or the input values already have a registered account associated with them");
	    //Note- if the test is re-run, different test data should be considered, as the same user information is registered.
	  }
  
  @Test
  public void tC_003_VALID() {
	  test_instance.launch_browser();
	  driver.findElement(By.cssSelector(".nav-item:nth-child(3) > .nav-link")).click();
	  driver.findElement(By.cssSelector(".card-img-overlay")).click();
	  driver.findElement(By.linkText("More Info")).click();	//Navigating to the details of a listing (we have to keep in mind if the listing is added through admin panel
	  compare.compare_title("NewLine Real Estate | Listing1", "Error-verify whether listing exists");
  }
  
  @SuppressWarnings("deprecation")
@Test
  public void tC_004_VALID() {
	  test_instance.launch_browser();
	  driver.findElement(By.cssSelector(".nav-item:nth-child(3) > .nav-link")).click();
	  driver.findElement(By.linkText("More Info")).click();
	  driver.findElement(By.cssSelector(".btn-primary")).click();
	  driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);	//Wait for the element to appear
	  driver.findElement(By.name("name")).sendKeys("Anshul");
	  driver.findElement(By.name("email")).sendKeys("abc@mail.com1111110");
	  driver.findElement(By.name("phone")).sendKeys("1111110");
	  driver.findElement(By.name("message")).sendKeys("Inquiry");
	  driver.findElement(By.cssSelector(".btn-secondary")).click();
	  String expected_message="Success";
	  String actual_message=driver.findElement(By.tagName("strong")).getText();
	  Assert.assertEquals(actual_message,expected_message, "Error-verify whether listing exists");
  }
  
  @Test
  public void tC_005_VALID() {
	  test_instance.launch_browser();
	  login.login("anshul10", "******");		//Logged In with registered credentials
	  String actual_url=driver.getCurrentUrl();				
	  String expected_url="http://127.0.0.1:8000/accounts/dashboard";			//If logged In, the user would be directed to dashboard
	  Assert.assertEquals(actual_url,expected_url);
	  driver.findElement(By.xpath("//*[@id=\"navbarNavAltMarkup\"]/ul[2]/li[2]/a")).click();
  }
  
  @Test
  public void tC_006_VALID() {
	  driver.get("http://127.0.0.1:8000/admin");								//Admin panel
	  driver.findElement(By.id("id_username")).sendKeys("anshul");
	  driver.findElement(By.id("id_password")).sendKeys("admin");				//Entering Admin credentials
	  driver.findElement(By.id("id_password")).sendKeys(Keys.ENTER);
	  String actual_title=driver.findElement(By.id("head")).getText();
	  String expected_title="Admin Area";
	  Assert.assertEquals(actual_title,expected_title);
	  driver.findElement(By.id("logout-form")).click();
  }
  
  //INVALID TEST CASES
		  
  @Test
  public void tC_001_INVALID() {
	  test_instance.launch_browser();
	  driver.findElement(By.cssSelector(".fa-user-plus")).click();
	  driver.findElement(By.cssSelector(".btn")).click();
	  Assert.assertTrue(driver.findElement(By.cssSelector("input:required")).isDisplayed());	//Checking whether the "required" attribute exists for the input tags
	  compare.compare_title("NewLine Real Estate | Register Account", "The user is not in the registration page, which means the invalid values were considered valid:");
	  // If the form has invalid values, the user is expected to get redirected to the Registration page
  }
  
  @Test
  public void tC_002_INVALID() {
	  test_instance.launch_browser();
	  register.register("a", "b", "c", "@gmail.com", "******", "******");
	  Assert.assertTrue(driver.findElement(By.cssSelector("input:required")).isDisplayed());	//Checking whether the "required" attribute exists for the input tags
	  compare.compare_title("NewLine Real Estate | Register Account", "The user is not in the registration page, which means the invalid values were considered valid:");
  }
  
  @Test
  public void tC_003_INVALID() {
	  test_instance.launch_browser();
	  register.register("a", "b", "c", "gmail.com", "******", "******");
	  Assert.assertTrue(driver.findElement(By.cssSelector("input:required")).isDisplayed());
	  compare.compare_title("NewLine Real Estate | Register Account", "The user is not in the registration page, which means the invalid values were considered valid:");
  }
  
  @Test
  public void tC_004_INVALID() {
	  test_instance.launch_browser();
	  register.register("a", "b", "c", "abc@gmail.com", "*", "**");
	  String Actual_message=driver.findElement(By.id("message")).getText();
	  String expected_message="Passwords do not match";
	  Assert.assertFalse(Actual_message==expected_message);
  }
  
  @Test
  public void tC_005_INVALID() {
	  test_instance.launch_browser();
	  driver.findElement(By.cssSelector(".ml-auto > .nav-item:nth-child(2) > .nav-link")).click();	//The login page
	  driver.findElement(By.cssSelector(".btn")).click();
	  Assert.assertTrue(driver.findElement(By.cssSelector("input:required")).isDisplayed());
	  compare.compare_title("NewLine Real Estate | Account Login", "The user is not in the login page, which means the invalid values were considered valid:");
  }
  
  @Test
  public void tC_006_INVALID() {
	  test_instance.launch_browser();
	  driver.findElement(By.cssSelector(".fa-sign-in-alt")).click();
	  login.login("abc", "******");
	  String expected_message="Error:";
	  String actual_message=driver.findElement(By.tagName("strong")).getText();
	  Assert.assertEquals(actual_message,expected_message, "Defect found- Error for invalid credentials not found");
  }
  
  @Test
  public void tC_007_INVALID() {
	  test_instance.launch_browser();
	  driver.findElement(By.cssSelector(".ml-auto > .nav-item:nth-child(2) > .nav-link")).click();
	  driver.findElement(By.name("username")).click();
	  login.login("anshul10", "******");
	  driver.get("http://127.0.0.1:8000/admin");					//While the normal user is logged In, the user tries to access the admin panel.
	  String expected_message="You are authenticated as anshul10, but are not authorized to access this page. Would you like to login to a different account?";
	  String actual_message= driver.findElement(By.className("errornote")).getText();
	  Assert.assertEquals(actual_message,expected_message, "Defect: The admin panel is logging In a normal user");
  }
}