# Selenium Automation Framework
An Automation Framework is a collection of assumptions, concepts, and practices you bring in while developing the automation project, so it helps in constituting a work platform or support for automated testing. It would be great if the framework is application-independent.

If you look into any existing framework, it will be a block of function libraries for reporting, error handling, and driver scripts. So the test automation framework is an execution environment for automated tests. It is the overall system in which our tests will be automated.

## What is a Framework?
 In general, a framework is a real or conceptual structure intended to serve as a support or guide for the building of something that expands the structure into something useful.
 
## Why do we need an Automation framework?
###### Using Framework, we can solve many issues like
*	Writing code once and reusing it. Significant Reduction in Testing Cycle Time	
*	Running the script with a different set of data.
*	Executing the scripts end-to-end without any manual intervention. ( If any error occurs from the tool or application, the 		Script run will stop. If we use a framework, it will skip or fail that test case and run with the next test case.)
*	With the basic knowledge of tools also anyone can run and write the script. (All the script, Keywords have been written by 		experts, we have to know how to use those keywords)
*	Maintenance becomes very easy.
  
#  Data-Driven Framework
A Data Driven Framework in Selenium is a technique of separating the “data set” from the actual “test case” (code). Since the test case is separated from the data set, one can easily modify the test case of a particular functionality without making changes to the code.

For example, if one has to modify the code for login functionality, they can modify just the login functionality instead of having to modify any other feature dependent on the same code.

# Steps to Setup Project on your local:
1.	Download and install Java SDK 16
2.	Download Eclipse and Apache Maven and extract the zip files to a specific folder.
3. 	Install the eclipse and install TestNG in it.
4.	Follow the steps to ensure all the system environment variables are set up correctly.
	https://www.tutorialspoint.com/maven/maven_environment_setup.htm
5.	Download and install Git
6.	Clone the Git repository.
7.	Open a eclipse and import the project in it.
8.	Wait till it completes the process.
9.	Navigate to src\test\java and open any test case file.
10. Open testNG file and right click and Run as TestNG and then see the output.

After execution is complete, you should see the test execution report in the Reports folder(../test-output/TestReport.html).

Open the TestReport.html in the Chrome browser to see the result.

# Note Load Time of Every Page.

1. Go to folder ./TestData and open TestData.xlsx
2. Go to Timeout tab and see the total load time of every Page. This is time is in milliseconds.

