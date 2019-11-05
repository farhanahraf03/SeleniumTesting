
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.io.*;

class Test
{
	
	
	public static String testTitle(WebDriver driver) throws InterruptedException
	{
		String baseurl = "http://127.0.0.1/LoanApprovalSystem/Loan Approval System/zero.html";
		String expectedTitle = "Home Page";
		String actualTitle = "";
		
		driver.get(baseurl);
		
		actualTitle = driver.getTitle();
		
		
		String res = actualTitle.contentEquals(expectedTitle)?"Title is correct - TEST SUCCESSFUL":"Title is incorrect - TEST FAILED";
		System.out.println(res);
		
		Thread.sleep(3000);
		
		return res;
		
	}
	
	public static String checkRedirect(WebDriver driver) throws InterruptedException
	{
		String baseurl = "http://127.0.0.1/LoanApprovalSystem/Loan Approval System/zero.html";
		String expectedTitle = "Admin Login";
		String actualTitle = "";
		
		driver.get(baseurl);
		Thread.sleep(3000);
		
		driver.findElement(By.id("admin")).click();
		
		actualTitle = driver.getTitle();
		
		String res = actualTitle.contentEquals(expectedTitle)?"\t\tCLICK TEST SUCCESSFUL\n":"\t\tCLICK TEST FAILED\n";
		
		Thread.sleep(3000);
		
		return res;
		
	}
	
	public static String login(WebDriver driver,String u, String p, int count) throws InterruptedException
	{
		String temp = "";
		WebElement username = driver.findElement(By.id("user"));
		WebElement password = driver.findElement(By.id("pwd"));
		WebElement login = driver.findElement(By.xpath("//input[@type='submit']"));
		
		username.clear();
		password.clear();
		
		for(int i=0; i<u.length(); i++)
		{
			temp = Character.toString(u.charAt(i));
			username.sendKeys(temp);
			Thread.sleep(500);
		}

		temp = "";
		for(int i=0; i<p.length(); i++)
		{
			temp = Character.toString(p.charAt(i));
			password.sendKeys(temp);
			Thread.sleep(500);
		}
		
		login.click();
		Thread.sleep(3000);	
		
		String res = driver.getTitle().contentEquals("Admin Home")?"LOGIN TEST "+count+" SUCCESSFUL":"LOGIN TEST "+count+" FAILED";
		System.out.println(res);
		
		return res;
		
	}
	
	public static void checkLogin(WebDriver driver) throws InterruptedException, IOException
	{
		String baseurl = "http://127.0.0.1/LoanApprovalSystem/Loan Approval System/adminlogin.html";
		String res="";
		
		File f = new File("/home/hughjass/eclipse-workspace/SeleniumTest/src/output.txt");
		FileWriter fw = new FileWriter(f,true);
		
		driver.get(baseurl);
		
		fw.write("\n===========LOGIN TEST============\n");
		fw.flush();
		
		String u = "";
		String p = "";
		res = login(driver,u,p,1);
		fw.write("\t"+res+"\n");
		fw.flush();
		

		u = "admin";
		p = "";
		res = login(driver,u,p,2);
		fw.write("\t"+res+"\n");
		fw.flush();
		
		u = "admin";
		p = "admin";
		res = login(driver,u,p,3);
		fw.write("\t"+res+"\n");
		fw.flush();
		
		u = "admin";
		p = "admin123";
		res = login(driver,u,p,4);
		fw.write("\t"+res+"\n");
		fw.flush();
		
		fw.write("\n=================================\n");
		fw.flush();

		fw.close();
		driver.close();
		
		
	}
	

	
	
	public static void main(String[] args) throws InterruptedException, IOException
	{
		System.setProperty("webdriver.gecko.driver", "/home/hughjass/Downloads/geckodriver");
		WebDriver driver = new FirefoxDriver();

		File f = new File("/home/hughjass/eclipse-workspace/SeleniumTest/src/output.txt");
		FileWriter fw = new FileWriter(f);
		fw.write("\n===========TITLE TEST============\n");
		fw.flush();
		
		String res = Test.testTitle(driver);
		fw.write(res);
		fw.flush();
		
		fw.write("\n=================================\n");
		fw.flush();
		
		fw.write("\n==========REDIRECT TEST==========\n");
		fw.flush();
		
		res = Test.checkRedirect(driver);
		fw.write(res);
		fw.flush();
		
		fw.write("=================================\n");
		fw.flush();
		
		fw.close();
		
		
		Test.checkLogin(driver); //Login Test
		
		
	}
}
