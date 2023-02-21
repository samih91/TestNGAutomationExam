package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class TestNGExamPage {

	WebDriver driver;

	public TestNGExamPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.CSS, using = "body > div.controls > a:nth-child(6) > span")
	WebElement FIRST_CATRGORY_ELEMENT;
	@FindBy(how = How.CSS, using = "input[name='categorydata']")
	WebElement ADD_CATEGORY_INPUT_FIELD;
	@FindBy(how = How.CSS, using = "input[value='Add category']")
	WebElement ADD_CATEGORY_BUTTON;
	@FindBy(how = How.CSS, using = "#extra > select:nth-child(3)")
	WebElement DROP_DOWN_ELEMENT;

	public void clickOnAddCategoryButton() {

		ADD_CATEGORY_BUTTON.click();
	}

	public void addCategory(String category) {

		ADD_CATEGORY_INPUT_FIELD.sendKeys(category);
	}

	public ArrayList<WebElement> CategoryListData() {

		ArrayList<WebElement> CategoryArrayListElements = new ArrayList<WebElement>();
		int i = 7;

		try {
			while (FIRST_CATRGORY_ELEMENT.isDisplayed()) {

				CategoryArrayListElements.add(FIRST_CATRGORY_ELEMENT);
				FIRST_CATRGORY_ELEMENT = driver
						.findElement(By.cssSelector("body > div.controls > a:nth-child(" + i + ") > span"));

				i++;
			}
		} catch (Exception e) {
		}
		return CategoryArrayListElements;
	}

	public String GetLastElementText() {
		ArrayList<WebElement> CategoryArrayListElements = new ArrayList<WebElement>();
		CategoryArrayListElements = CategoryListData();
//		System.out.println(fileData.size());
		String LastElementText = CategoryArrayListElements.get(CategoryArrayListElements.size() - 1).getText();
		return LastElementText;
	}

	public void verifyCategoryadded(String category) {

		if (category.equals(GetLastElementText())) {
			System.out.println("Category Added Succesfully");
		} else {
			System.out.println("Category not added");
		}

	}

	public void verifyCategorynotduplicated(String category) {

		if (driver.getCurrentUrl().equals("https://techfios.com/test/101/todo.php")) {

			System.out.println("The category you want to add already exists:<" + category + ">.");
		} else {
			System.out.println("Duplicate operation is suspected");
		}
	}

	public void verifyAllMonthsExistOnTheDropList() {

		Select dropdown = new Select(DROP_DOWN_ELEMENT);
		List<WebElement> options = dropdown.getOptions();
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", };

		int count = 0;
		for (int i = 0; i < options.size(); i++) {

			for (int j = 0; j < months.length; j++)
				if (months[j].equals(options.get(i).getText())) {
					count++;
				}
		}
		if (count == months.length) {
			System.out.println("All the months exist On the dropList");
		} else {
			System.out.println("the drop list missing some months");
		}

	}

}
