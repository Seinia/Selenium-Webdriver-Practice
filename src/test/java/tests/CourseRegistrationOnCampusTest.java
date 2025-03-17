package tests;

import model.CampusCourseTestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.campus_portal.CampusHomePage;
import pages.campus_portal.CampusLoginPage;
import pages.campus_portal.CampusTrainingPage;
import pages.learn_portal.LearnHomePage;
import service.TestDataCampusCourseService;
import tests.base.BaseTest;

public class CourseRegistrationOnCampusTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(CourseRegistrationOnCampusTest.class);

    private LearnHomePage learnHomePage;
    private CampusHomePage campusHomePage;
    private CampusTrainingPage campusTrainingPage;
    private CampusLoginPage campusLoginPage;
    private CampusCourseTestData testData;

    @BeforeMethod
    @Parameters({"environment"})
    public void setUpTest(String environment) {
        System.setProperty("environment", environment);
        logger.info("Setting up test environment: {}", environment);
        testData = TestDataCampusCourseService.getTestDataFromProperties();
        logger.info("Loaded test data from properties");
        learnHomePage = new LearnHomePage(driver).openPage().acceptCookie();
        logger.debug("Opened Learn homepage and accepted cookies");
    }

    @Test(description = "Verify 'Learn' homepage elements are displayed")
    public void testLearnHomePageIsDisplayed() {
        logger.info("Starting test: testLearnHomePageIsDisplayed");

        String actualText = learnHomePage.getHomePageText();
        String expectedText = testData.getLearnHomePageText();

        logger.info("Asserting Learn home page text: expected '{}', found '{}'", expectedText, actualText);
        Assert.assertEquals(actualText, expectedText, "Learn home page text mismatch");

        logger.info("Test testLearnHomePageIsDisplayed PASSED");
    }

    @Test(description = "Verify campus home page elements are displayed")
    public void testCampusHomePageIsDisplayed() {
        logger.info("Starting test: testCampusHomePageIsDisplayed");

        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        logger.debug("Navigated to Campus homepage");

        String actualText = campusHomePage.getHomePageText();
        String expectedText = testData.getCampusHomePageText();

        logger.info("Asserting Campus home page text: expected '{}', found '{}'", expectedText, actualText);
        Assert.assertEquals(actualText, expectedText, "Campus page text mismatch");

        logger.info("Test testCampusHomePageIsDisplayed PASSED");
    }

    @Test(description = "Verify navigation on training page")
    public void testTrainingPageNavigation() {
        logger.info("Starting test: testTrainingPageNavigation");

        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        campusTrainingPage = campusHomePage.clickFindAProgramButton();
        logger.debug("Navigated to Campus training page");

        String actualText = campusTrainingPage.getTrainingPageText();
        String expectedText = testData.getTrainingPageText();

        logger.info("Asserting Campus training page text: expected '{}', found '{}'", expectedText, actualText);
        Assert.assertEquals(actualText, expectedText, "Campus training page text mismatch");

        logger.info("Test testTrainingPageNavigation PASSED");
    }

    @Test(description = "Verify filters on campus training page")
    public void testCampusTrainingPageFilters() {
        logger.info("Starting test: testCampusTrainingPageFilters");

        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        campusTrainingPage = campusHomePage
                .acceptCookie()
                .clickFindAProgramButton()
                .clickLocationsDropDown()
                .inputSearchField(testData.getCourseLocation())
                .clickDropDownCheckBox()
                .clickSkillsDropDown()
                .inputSearchField(testData.getCourseSkill())
                .clickDropDownCheckBox();
        logger.info("Applied filters: Location '{}', Skill '{}'", testData.getCourseLocation(), testData.getCourseSkill());

        String actualText = campusTrainingPage.getCourseCardText();
        String expectedText = testData.getCourseName();

        logger.info("Asserting course card text: expected '{}', found '{}'", expectedText, actualText);
        Assert.assertEquals(actualText, expectedText, "Course card text mismatch");

        logger.info("Test testCampusTrainingPageFilters PASSED");
    }

    @Test(description = "Verify JavaScript course registration process")
    public void testJavaScriptCourseRegistration() {
        logger.info("Starting test: testJavaScriptCourseRegistration");

        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        campusLoginPage = campusHomePage
                .acceptCookie()
                .clickFindAProgramButton()
                .clickLocationsDropDown()
                .inputSearchField(testData.getCourseLocation())
                .clickDropDownCheckBox()
                .clickSkillsDropDown()
                .inputSearchField(testData.getCourseSkill())
                .clickDropDownCheckBox()
                .clickCourseCard()
                .clickRegisterButton();
        logger.debug("Navigated to course registration page");

        String actualText = campusLoginPage.getLoginPageText();
        String expectedText = testData.getLoginPageText();

        logger.info("Asserting login page text: expected '{}', found '{}'", expectedText, actualText);
        Assert.assertEquals(actualText, expectedText, "Login page text mismatch");

        logger.info("Test testJavaScriptCourseRegistration PASSED");
    }

}
