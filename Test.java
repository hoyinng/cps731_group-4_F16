
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Test {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(UnitTest.class);

	  	System.out.println("Number of Test Case Runned "+result.getRunCount());
	  	System.out.println("Completed in "+result.getRunTime()+" milliseconds");
	    System.out.println("Was Test Cases sucessfully finished "+result.wasSuccessful());
	  
	    for (Failure failure : result.getFailures()) {
		        System.out.println(failure.toString());
		}
	}
	
}
