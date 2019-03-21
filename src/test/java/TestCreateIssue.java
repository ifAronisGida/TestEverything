import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/*
*
* */
class TestCreateIssue {

    void cycleIssueTypes() throws InterruptedException {
        /** Cycles through the issue types Task, Bug and Story **/

        driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]")).sendKeys("Task", Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.id("//*[@id=\"task-136\"]")).isSelected();
        driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]")).sendKeys("Bug", Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]")).sendKeys("Story", Keys.ENTER);
        Thread.sleep(1000);
    }

    By createLinkPath = By.id("create_link");

    private static WebDriver driver = new Driver().getDriver();

    @BeforeAll
    static void login() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user14");
        driver.findElement(By.id("login-form-password")).sendKeys("CCPass123");
        driver.findElement(By.id("login")).click();

    }

    @Test
    void createIssueWithReqFieldsFilled() throws InterruptedException {
        utils.waitForElement(driver,"create_link" );
        WebElement createLink = driver.findElement(createLinkPath);
        createLink.click();

        /** Modal pops up **/

        /** We select coala project **/

        utils.waitForElement(driver, "project-single-select");
        driver.findElement(By.xpath("//*[@id=\"project-field\"]")).sendKeys("CO", Keys.ENTER);

        /** We select Task issue type **/

        Thread.sleep(2000);
//        utils.waitForElement(driver, "project-single-select");
        driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]")).sendKeys("Ta", Keys.ENTER);

        /** We fill the summary field **/

        Thread.sleep(2000);
//        utils.waitForElement(driver, "project-single-select");
        driver.findElement(By.id("summary")).sendKeys("This is an automatated test.");
        driver.findElement(By.name("jiraform")).submit();
        Thread.sleep(2000);
    }

    @Test
    void createIssueWithReqFieldsNotFilled() throws InterruptedException {
        utils.waitForElement(driver,"create_link" );
        WebElement createLink = driver.findElement(createLinkPath);
        createLink.click();

        /** Modal pops up **/

        /** Click on submit without filling all req. fields **/
        Thread.sleep(2000);
        driver.findElement(By.id("create-issue-submit")).click();
        Thread.sleep(2000);

        /** Check if error message is displayed **/

        Assertions.assertNotNull(driver.findElement(By.className("error")).getText());

        /** Close the modal, and deal with the alert box **/
        driver.findElement(By.cssSelector("a[title*=\"Press undefined+` to cancel\"]")).click();
        driver.switchTo().alert().accept();
    }


    /** couldn't find a way to check if selected element is what it should be **/


//    @Test
//    void expectedIssueAndProjectTypesExist() throws InterruptedException {
//        utils.waitForElement(driver,"create_link" );
//        WebElement createLink = driver.findElement(createLinkPath);
//        createLink.click();
//
//        /** Modal pops up **/
//
//        /** We select coala project **/
//
//        utils.waitForElement(driver, "project-single-select");
//        driver.findElement(By.xpath("//*[@id=\"project-field\"]")).sendKeys("CO", Keys.ENTER);
//
//        /** We select Task issue types **/
//
//        Thread.sleep(1000);
//        cycleIssueTypes();
//
//        /** Select JETI project **/
//        driver.findElement(By.xpath("//*[@id=\"project-field\"]")).sendKeys("JE", Keys.ENTER);
//        Thread.sleep(1000);
//        cycleIssueTypes();
//
//        /** Select TOUCAN project **/
//        driver.findElement(By.xpath("//*[@id=\"project-field\"]")).sendKeys("TOUCAN", Keys.ENTER);
//        Thread.sleep(1000);
//        cycleIssueTypes();
//
//        driver.findElement(By.cssSelector("a[title*=\"Press undefined+` to cancel\"]")).click();
//        driver.switchTo().alert().accept();
//
//
//    }
//
//    @Test
//    void createJetiSubtask(){
//
//    }
//
//    @Test
//    void createToucanSubtask(){
//    }
//
//    @Test
//    void createCoalaSubtask(){
//    }

    @AfterAll
    static void cleanUp() throws InterruptedException {

        /** Navigate to the projects **/
        driver.get("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");

        /** Find and delete the issue we created in the test **/
        driver.findElement(By.cssSelector("#projects > div > table > tbody > tr:nth-child(1) > td.cell-type-name > a")).click();
        driver.findElement(By.cssSelector("li[title*=\"This is an automatated test.\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("opsbar-operations_more")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#delete-issue > a > span")).click();
        utils.waitForElement(driver, "delete-issue-dialog");
        driver.findElement(By.id("delete-issue-submit")).click();

        driver.close();
    }
}