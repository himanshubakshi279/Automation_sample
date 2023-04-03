package com.Automation.framework.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebUtils {
	private WebDriver driver = null;

	public WebUtils(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * It will refresh the current browser tab.
	 */
	public void refreshBrowser() {
		driver.navigate().refresh();
	}

	/**
	 * It will switch to the frame element.
	 * 
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	public void gotop() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.HOME).build().perform();
	}

	/**
	 * It will refresh the current browser tab by getting its current URL.
	 */
	public void refreshWithCurrentUrl() {
		driver.get(driver.getCurrentUrl());
	}

	/**
	 * It will refresh the current browser by hitting F5 key.
	 */
	public void refreshWithF5Key() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
	}

	/**
	 * It will open a new blank tab in browser.
	 * 
	 */
	public void openNewTab() {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
	
	public void scrollpage() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-800)");


	}

	/**
	 * It will close all other tabs except the provided one.
	 * 
	 * @param originalHandle you can get it by using driver.getWindowHandle();
	 */
	public void closeOtherTabs(String originalHandle) {
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
	}

	/**
	 * It will close the next tab opened other then original tab.
	 */
	public void closeNextTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}

	public void closeNext2Tabs() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
		driver.close();
		driver.switchTo().window(tabs2.get(1));
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}
	
	public void close3rdTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
		driver.close();
		driver.switchTo().window(tabs2.get(1));
	}

	public void Windowhandler() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));

	}

	/**
	 * It will return the current URL of the page.
	 * 
	 * @return Current URL
	 */
	public String getCurrentURL() {

		return driver.getCurrentUrl();
	}

	/**
	 * It will switch to the 2nd tab present.
	 */
	public void switchToNextTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
	}

	public void switchToNexttonextTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
	}

	/**
	 * It will switch to 1st tab.
	 */
	public void switchToPreviousTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(0));
	}

	/**
	 * It will get element.
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(String locator) {
		WebElement element = null;
		try {
			element = driver.findElement(getByObject(locator));
		} catch (Exception e) {
			System.out.println("!!!!!! Exception Occurred : {}: ");
		}

		return element;
	}

	/**
	 * It will get list of elements of a specific locator.
	 * 
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> getElements(String locator) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(getByObject(locator));
		} catch (Exception e) {
			System.out.println("!!!!!! Exception Occurred : {}: ");
		}

		return elements;
	}

	/**
	 * It will return the element using its parent element.
	 * 
	 * @param parentElement
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(WebElement parentElement, String locator) {
		WebElement element = null;
		try {
			element = parentElement.findElement(getByObject(locator));
		} catch (Exception e) {
			System.out.println("!!!!!! Exception Occurred : {}: ");
		}

		return element;
	}

	/**
	 * It will return list of elements using parent element.
	 * 
	 * @param parentElement
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> getElements(WebElement parentElement, String locator) {
		List<WebElement> elements = null;
		try {
			elements = parentElement.findElements(getByObject(locator));
		} catch (Exception e) {
			System.out.println("!!!!!! Exception Occurred : {}: ");
		}

		return elements;
	}

	/**
	 * For WebElement : It will return only visible element (neglecting hidden
	 * elements of same locator).
	 * 
	 * @param locator
	 * @param timeOut
	 * @return WebElement
	 */
	public WebElement getDisplayedWebElement(String locator, long timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(org.openqa.selenium.NoSuchElementException.class)
				.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				List<WebElement> elements = driver.findElements(getByObject(locator));
				return elements.stream().filter(element -> element.isDisplayed()).findFirst().get();
			}
		});

		return element;
	}

	/**
	 * For WebElement : It will return only visible elements (neglecting hidden
	 * elements of same locator)
	 * 
	 * @param locator
	 * @param timeOut
	 * @return List<WebElement>
	 */
	public List<WebElement> getDisplayedWebElements(String locator, long timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(org.openqa.selenium.NoSuchElementException.class)
				.ignoring(NoSuchElementException.class);
		List<WebElement> elements = (List<WebElement>) wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				List<WebElement> elements = driver.findElements(getByObject(locator));
				return elements.stream().filter(element -> element.isDisplayed()).collect(Collectors.toList());
			}

		});

		return elements;
	};

	

	/**
	 * It will give the object of "By"
	 * 
	 * @param locator e.g : //div[@class='demo']_xpath
	 * @return By object
	 */
	public By getByObject(String locator) {
		locator = locator.trim();
		By byObj = null;
		if (locator.endsWith("_xpath")) {
			byObj = By.xpath(locator.replaceAll("_xpath", ""));
		} else if (locator.endsWith("_css")) {
			byObj = By.cssSelector(locator.replaceAll("_css", ""));
		} else if (locator.endsWith("_id")) {
			byObj = By.id(locator.replaceAll("_id", ""));
		} else if (locator.endsWith("_linkText")) {
			byObj = By.linkText(locator.replaceAll("__linkText", ""));
		} else if (locator.endsWith("_name")) {
			byObj = By.name(locator.replaceAll("_name", ""));
		}

		return byObj;

	}

	/**
	 * It just check that an element is present on the DOM of a page.
	 * 
	 * @param locator
	 * @param seconds
	 */
	public void waitForElementPresence(String locator, long seconds) {

		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(getByObject(locator)));
	}

	/**
	 * It just check that an element is present on the DOM of a page.
	 * 
	 * @param locator
	 * @throws IOException
	 */
	public void waitForElementPresence(String locator) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(waitime));
		wait.until(ExpectedConditions.presenceOfElementLocated(getByObject(locator)));
	}

	/**
	 * It will check that an element is present on the DOM of a page and visible.
	 * 
	 * @param locator
	 * @param seconds
	 * @throws IOException
	 */
	public void waitForlocatorVisibility(String locator) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait wait = new WebDriverWait(driver, waitime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByObject(locator)));

	}

	/**
	 * It will check that an element is present on the DOM of a page and visible.
	 * 
	 * @param locator
	 * @param seconds
	 * @throws IOException
	 */
	public void waitForElementVisibility(WebElement webElement) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait wait = new WebDriverWait(driver, waitime);
		wait.until(ExpectedConditions.visibilityOf(webElement));

	}

	public void waitForElementInVisibility(WebElement webElement) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait wait = new WebDriverWait(driver, waitime);
		wait.until(ExpectedConditions.invisibilityOf(webElement));

	}

	/**
	 * It will check that an element is present on the DOM of a page and visible.
	 * 
	 * @param locator
	 * @throws IOException
	 */
	public void waitForElementVisibilityWithMinTimeOut(String locator) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(waitime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByObject(locator)));
	}

	public void WaituntilElementclickable(WebElement element) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(waitime));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * It will check that an element is present on the DOM of a page and visible.
	 * 
	 * @param locator
	 * @throws IOException
	 */
	public void waitForElementVisibilityWithMaxTimeOut(String locator) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(waitime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByObject(locator)));
	}

	/**
	 * It will hard wait for the given seconds.
	 * 
	 * @param seconds
	 * @throws IOException
	 */
	public void sleepforDropdown() throws IOException {
		String time = Utility.fetchPropertyValue("dropdown").toString();
		int waitime = Integer.parseInt(time);
		try {
			Thread.sleep(waitime * 1000);
		} catch (InterruptedException e) {
			System.out.println("Exception occurred while waiting for {} seconds ");
		}

	}

	public void sleepforloader() throws IOException {
		String time = Utility.fetchPropertyValue("sleepforloader").toString();
		int waitime = Integer.parseInt(time);
		try {
			Thread.sleep(waitime * 1000);
		} catch (InterruptedException e) {
			System.out.println("Exception occurred while waiting for {} seconds ");
		}

	}

	public void sleepenteringdataintextbox() throws IOException {
		String time = Utility.fetchPropertyValue("textfield").toString();
		int waitime = Integer.parseInt(time);
		try {
			Thread.sleep(waitime * 1000);
		} catch (InterruptedException e) {
			System.out.println("Exception occurred while waiting for {} seconds ");
		}

	}

	public void sleepforremoveToastMessage() throws IOException {
		String time = Utility.fetchPropertyValue("waittillremoveToastMessage").toString();
		int waitime = Integer.parseInt(time);
		try {
			Thread.sleep(waitime * 1000);
		} catch (InterruptedException e) {
			System.out.println("Exception occurred while waiting for {} seconds ");
		}

	}

	public void sleepforPageload() throws IOException {
		String time = Utility.fetchPropertyValue("Pageloadtime").toString();
		int waitime = Integer.parseInt(time);
		try {
			Thread.sleep(waitime * 1000);
		} catch (InterruptedException e) {
			System.out.println("Exception occurred while waiting for {} seconds ");
		}

	}

	public void sleepafterclick() throws IOException {
		String time = Utility.fetchPropertyValue("waitafterclick").toString();
		int waitime = Integer.parseInt(time);
		try {
			Thread.sleep(waitime * 1000);
		} catch (InterruptedException e) {
			System.out.println("Exception occurred while waiting for {} seconds ");
		}

	}

	/**
	 * It will click on a given locator.
	 * 
	 * @param locator
	 */
	public void click(WebElement element) {
		try {
			// highlightWebElement(element);
			element.click();
		} catch (Exception e) {
			System.out.println("Exception occurred while clicking : [{}]");
		}
	}

	/**
	 * It will navigate to the specified URL.
	 * 
	 * @param URL
	 */
	public void navigateToURL(String URL) {
		driver.navigate().to(URL);

	}

	/**
	 * It will give the page title.
	 * 
	 * @return
	 */
	public String getPageTitle() {
		String pageTitle = driver.getTitle();
		// System.out.println("Page Title is : [{}]");

		return pageTitle;

	}

	/**
	 * It will give the text present in element.
	 * 
	 * @return element's text value
	 * @throws IOException
	 */
	public String getText(WebElement element) throws IOException {
		String time = Utility.fetchPropertyValue("waitforGetText").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait driverWait = new WebDriverWait(driver, Long.valueOf(waitime));
		driverWait.until(ExpectedConditions.visibilityOf(element));
		// highlightWebElement(element);
		String elementText = element.getText();

		return elementText;

	}

	/**
	 * It will get an element's text using any attribute. For e.g attribute can be :
	 * innerText,textContent,title etc as defined in the element html code
	 * 
	 * @param locator
	 * @param attribute
	 * @return
	 * @throws IOException
	 */
	public String getTextUsingAttribute(WebElement element, String attribute) throws IOException {
		String time = Utility.fetchPropertyValue("waitforGetText").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait driverWait = new WebDriverWait(driver, Long.valueOf(waitime));
		driverWait.until(ExpectedConditions.visibilityOf(element));
		// highlightWebElement(element);
		String elementText = element.getAttribute(attribute);

		return elementText;

	}

	/**
	 * It will get InnerText of element using JavaScript.
	 * 
	 * @param element
	 * @return innerText of element.
	 */
	public String getInnerText(WebElement element) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", element);
	}

	/**
	 * It will get textContent of element using JavaScript.
	 * 
	 * @param element
	 * @return textContent of element.
	 */
	public String getTextContent(WebElement element) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", element);
	}

	public String getAttributeUsingJs(WebElement element, String attribute) {
		return (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].getAttribute('" + attribute + "');", element);
	}

	/**
	 * It enters the value in text box.
	 * 
	 * @param locator
	 * @param text
	 * @throws IOException
	 */
	public void enterText(WebElement element, String text) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait driverWait = new WebDriverWait(driver, Long.valueOf(waitime));
		driverWait.until(ExpectedConditions.elementToBeClickable(element));
		// highlightWebElement(element);
		element.clear(); // clearing if any text is present in text box.
		element.sendKeys(text);

	}

	public void enterTextwithoutclearing(WebElement element, String text) {
		// highlightWebElement(element);
		element.sendKeys(text);

	}

	public void clkEnterafterenterText(WebElement element, String text) {
		// highlightWebElement(element);
		element.sendKeys(text + Keys.ENTER);

	}

	public void Clickarrowup(WebElement element) {
		// highlightWebElement(element);
		element.sendKeys(Keys.ARROW_UP, Keys.ENTER);

	}

	public void ClickarrowDown(WebElement element) {
		// highlightWebElement(element);
		element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

	}

	/**
	 * It will clear the text present in text box.
	 * 
	 * @param locator
	 */
	public void clearTextBox(WebElement element) {
		// highlightWebElement(element);
		element.clear();

	}

	/**
	 * It enters the value in text box.
	 * 
	 * @param locator
	 * @param text
	 */
	public void enterTextUsingActions(WebElement element, String text) {
		// highlightWebElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(text);

		actions.build().perform();
	}

	public void ClickUsingActions(WebElement element) {
		// highlightWebElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.build().perform();
	}

	/**
	 * It focuses on given web element.
	 * 
	 * @param driver
	 * @param element
	 */
	public void jsFocus(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].focus();", element);
	}

	/**
	 * It clicks on given web element using javascript.
	 * 
	 * @param element
	 */
	public void jsClick(WebElement element) {
		// highlightWebElement(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * It scrolls to the given WebElement.
	 * 
	 * @param driver
	 * @param element
	 * @return WebElement
	 */
	public WebElement scrollingToElementofAPage(WebElement element) {
		// highlightWebElement(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

		return element;
	}

	/**
	 * It scrolls to the given WebElement and click on it using javascript executor.
	 */
	public void scrollToElementAndClickUsingJS(WebElement element) {
		// highlightWebElement(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		jsClick(element);
	}

	public void scrollTillEndOfPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * It will highlight the web element
	 * 
	 * @param element
	 */
	public void highlightWebElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'background:#ffffb3; border:3px solid green;');", element);
	}

	/**
	 * This method copies the content to system Clipboard and than paste it. i.e it
	 * just performs copy paste operation like (Ctrl C + Ctrl V)
	 * 
	 * @param element
	 * @param stringToBePasted
	 */
	public void copyPaste(WebElement element, String stringToBePasted) {
		element.clear();
		element.click();
		StringSelection stringSelection = new StringSelection(stringToBePasted);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			System.out.println("!!!!!!!! Exception occured while copy pasting the given content....: {}");
		}

	}

	/**
	 * It will click on element using Actions class.
	 * 
	 * @param element WebElement
	 * @throws IOException
	 */
	public void actionClick(WebElement element) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		// highlightWebElement(element);
		WebDriverWait driverWait = new WebDriverWait(driver, Long.valueOf(waitime));
		driverWait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
	}

	public void DoubleClick(WebElement element) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait driverWait = new WebDriverWait(driver, Long.valueOf(waitime));
		driverWait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	public void waitforelementClick(WebElement element) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		WebDriverWait driverWait = new WebDriverWait(driver, Long.valueOf(waitime));
		driverWait.until(ExpectedConditions.elementToBeClickable(element));

	}

	/**
	 * It will click on element without waiting for it to be clickable using Actions
	 * class.
	 * 
	 * @param element WebElement
	 */
	public void actionPress(WebElement element) {
		// highlightWebElement(element);
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
	}

	/**
	 * It will select the value from dropdown using value attribute present in HTML
	 * tag.
	 * 
	 * @param element
	 * @param value
	 */
	public void selectDropDownByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * It will select the dropdown value using its index.
	 * 
	 * @param element
	 * @param value
	 */
	public void selectDropDownByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * It will select the value using current text visible on dropdown.
	 * 
	 * @param element
	 * @param value
	 */
	public void selectDropDownByVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	/**
	 * It will check if element is enabled or not.
	 * 
	 * @param element
	 * @return enabled status
	 */
	public boolean isElementEnabled(WebElement element) {

		return element.isEnabled();
	}

	/**
	 * It will check if element is selected or not.
	 * 
	 * @param element
	 * @return enabled status
	 */
	public boolean isElementSelected(WebElement element) {

		return element.isSelected();
	}

	/**
	 * It will check if element is selected or not.
	 * 
	 * @param element
	 * @return enabled status
	 */
	public boolean isElementDisplayed(WebElement element) {

		return element.isDisplayed();
	}

	/**
	 * It will perform mouse hover operation on the WebElement.
	 * 
	 * @param elementToHover
	 */
	public void mouseHover(WebElement elementToHover) {

		Actions hover = new Actions(driver);
		hover.moveToElement(elementToHover).perform();

	}

	/**
	 * It will perform mouse hover operation on the WebElement using javascript.
	 * 
	 * @param elementToHover
	 */
	public void mouseHoverUsingJs(WebElement elementToHover) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);}else if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) driver).executeScript(mouseOverScript, elementToHover);
	}

	/**
	 * It will return the text of only parent element excluding the text present in
	 * child elements. e.g : Sometimes we have to get the text content of a parent
	 * element like <div> and exclude all other tags and their contents which may be
	 * present in it. like <div>Hello
	 * <h1>ChildText</h1></div> , so this method will return "Hello" only.
	 * 
	 * @param element
	 * @return parent element text content.
	 * @throws IOException
	 */
	public String getOnlyParentElementText(WebElement element) throws IOException {

		String text = element.getText();
		for (WebElement child : element.findElements(By.xpath("./*"))) {
			text = text.replaceFirst(child.getText(), "");
		}

		return text;
	}

	/**
	 * It will generate random number based on given range. e.g : if range is 10,
	 * then it will return values from 0-9 inclusive
	 * 
	 * @param range
	 * @return
	 */
	public int generateRandomNumber(int range) {
		Random rand = new Random();
		return rand.nextInt(range);
	}

	/**
	 * It will wait until javascript is fully loaded inpage.
	 * 
	 */
	public void waitForJavascriptLoading(long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * It will get the first occurrence of the particular text and click it e.g.
	 * under any locator - get the value of the attribute and find its first
	 * occurrence and click it.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param attributeName
	 * @param text
	 * 
	 */
	public void clickFirstDisplayedElement(String locator, long timeOut, String attributeName, String text) {
		List<WebElement> options = this.getDisplayedWebElements(locator, timeOut);
		WebElement OptionToSelect = options.stream().filter(option -> option.getAttribute(attributeName).contains(text))
				.findFirst().get();
		this.click(OptionToSelect);
	}

	/**
	 * It will get selected value from dropdown
	 * 
	 * @param element
	 */
	public String getSelectedValueInDropDown(WebElement element) {
		Select select = new Select(element);
		String selectedValueFromDropdown = select.getFirstSelectedOption().getText();
		return selectedValueFromDropdown;
	}

	/**
	 * It will re attempt the click if StaleElementReferenceException exception
	 * occurs.
	 * 
	 * @param WebElement
	 * @return
	 * @throws IOException
	 */
	public boolean retryingClick(WebElement element) throws IOException {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				actionClick(element);
				result = true;
				break;
			} catch (StaleElementReferenceException | ElementNotInteractableException e) {
			}
			attempts++;
		}
		return result;
	}
	
	public void copydata(WebElement element) {
		
		// Create Actions object and simulate key presses
		Actions actions = new Actions(driver);
		actions.moveToElement(element)
		       .keyDown(Keys.CONTROL)
		       .sendKeys("a")
		       .sendKeys("c")
		       .keyUp(Keys.CONTROL)
		       .build()
		       .perform();
	}
	
	public void pastedata() {
		// Get the system clipboard and retrieve the pasted data
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = clipboard.getContents(null);
		String pastedData = "";

		// If the clipboard content is text, retrieve it as a string
		if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
		    try {
		        pastedData = (String) transferable.getTransferData(DataFlavor.stringFlavor);
		    } catch (UnsupportedFlavorException | IOException ex) {
		        // Handle exceptions
		    }
		}

		// Simulate key presses to paste the clipboard content
		try {
		    Robot robot = new Robot();
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException ex) {
		    // Handle exceptions
		}
	}
	


	public boolean clickUntilElementIsInvisible(List<WebElement> elements) throws IOException {
		boolean result = false;
		int attempts = 0;
		while (elements.size() > 0 && attempts <= 4) {
			try {
				actionClick(elements.get(0));
				result = true;
				break;
			} catch (StaleElementReferenceException | ElementNotInteractableException e) {
			}
			attempts++;
		}
		return result;
	}

	/**
	 * It will perform drag drop operation using Action class.
	 * 
	 * @param driver
	 * @param sourceElement
	 * @param targetElement
	 */
	public void dragDropUsingActions(WebDriver driver, WebElement sourceElement, WebElement targetElement) {
		// highlightWebElement(sourceElement);
		// highlightWebElement(targetElement);
		(new Actions(driver)).dragAndDrop(sourceElement, targetElement).perform();
	}

	/**
	 * It will drag and drop the element by first holding the source element for the
	 * given time and then will drag to target element.
	 * 
	 * @param driver
	 * @param sourceElement
	 * @param targetElement
	 * @param timeToHoldInSeconds
	 */
	public void dragDropUsingClickAndHold(WebDriver driver, WebElement sourceElement, WebElement targetElement,
			long timeToHoldInSeconds) {
		// highlightWebElement(sourceElement);
		// highlightWebElement(targetElement);
		Actions actions = new Actions(driver);
		actions.clickAndHold(sourceElement).pause(Duration.ofSeconds(timeToHoldInSeconds)).moveToElement(targetElement)
				.release().build().perform();
	}

	public void pressEnter() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(200);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public void setValueAttribute(WebElement element, String attValue) {
		String script = "arguments[0].value=" + "'" + attValue + "'";
		System.out.println("YES IT IS  :" + script);
		((JavascriptExecutor) driver).executeScript("arguments[0].value=" + "'" + attValue + "'", element);
	}

	/**
	 * It wil accept the alert by first waiting for it
	 * 
	 * @param wait in seconds
	 * @return Alert object
	 */
	public Alert acceptAlert(long wait) {
		WebDriverWait waitTime = new WebDriverWait(driver, wait);
		try {
			synchronized (waitTime) {
				Alert alert = driver.switchTo().alert();
				alert.accept();

				return alert;
			}

		} catch (NoAlertPresentException ex) {
			return null;
		}

	}

	public void waitForPageLoad(WebDriver driver) throws IOException {
		String time = Utility.fetchPropertyValue("Waituntil").toString();
		int waitime = Integer.parseInt(time);
		Wait<WebDriver> wait = new WebDriverWait(driver, waitime);
		wait.until(new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});

	}
}
