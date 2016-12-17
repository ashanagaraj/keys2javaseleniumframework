package in.keys2javaselenium.newtour1.utility;

import java.io.IOException;

import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.IClass;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class NewToursListener extends TestListenerAdapter implements ISuiteListener{

	static int passcount =0;
	static int failcount = 0;
	static int skipcount = 0;
	
	@Override
	  public void onTestStart(ITestResult tr) {
	    System.out.println(tr.getMethod()+"...started");
	  }
	
	@Override
	  public void onTestSuccess(ITestResult tr) {
		log("Test '" +tr.getName()+"' PASSED");
		log(tr.getTestClass());
		System.out.println(".......");
		
	    passcount++;
	  }

	  @Override
	  public void onTestFailure(ITestResult tr) {
		  log("Test '" +tr.getName()+"' FAILED");
			log(tr.getTestClass());
			System.out.println(".......");
			//if the test is failed, then we are taking screenshot using try catch block. because onTestFailure() does not accept exceptions.
		  try{
			  BrowserSelection.getScreenshot();
		  }catch(IOException e){
			  e.printStackTrace();
		  }
		  failcount++;
	  }

	  @Override
	  public void onTestSkipped(ITestResult tr) {
		  skipcount++;
	  }
	
	  private void log(String methodName){
		  System.out.println(methodName);
	  }
	  private void log(IClass className){
		  System.out.println(className);
	  }

	public void onStart(ISuite suite) {
		System.out.println("TestScripts EWxceution is started ....");
		
	}

	public void onFinish(ISuite suite) {
		System.out.println("Total Tests Passed :"+passcount);
		System.out.println("Total Tests failed :"+failcount);
		System.out.println("Total Tests skipped :"+skipcount);
		System.out.println("Total test cases are :"+(passcount+failcount+skipcount));
		
	}
}
