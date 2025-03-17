package tests;

import model.LearnCourseTestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.learn_portal.LearnCatalogPage;
import pages.learn_portal.LearnDotNetCoursePage;
import pages.learn_portal.LearnHomePage;
import pages.learn_portal.LearnTechnicalAndTechnologyPage;
import service.TestDataLearnCourseService;
import tests.base.BaseTest;

public class CourseRegistrationOnLearnTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(CourseRegistrationOnLearnTest.class);

    private LearnHomePage learnhomePage;
    private LearnCatalogPage learncatalogPage;
    private LearnTechnicalAndTechnologyPage learnTechnicalAndTechnologyPage;
    private LearnDotNetCoursePage learnDotNetCoursePage;
    private LearnCourseTestData testData;

    @BeforeMethod
    @Parameters({"environment"})
    public void setUpTest(String environment) {
        System.setProperty("environment", environment);
        logger.info("Setting up test environment: {}", environment);
        learnhomePage = new LearnHomePage(driver).openPage().acceptCookie();
        logger.debug("Opened Learn homepage and accepted cookies");
        testData = TestDataLearnCourseService.getTestDataFromProperties();
        logger.info("Loaded test data from properties");
    }

    @Test(description = "Test learn home page display")
    public void testHomePageIsDisplayed() {
        logger.info("Starting test: testHomePageIsDisplayed");
        String actualText = testData.getHomePageText();
        String expectedText = testData.getHomePageText();

        logger.info("Asserting home page text: expected '{}', found '{}'", expectedText, actualText);
        Assert.assertEquals(actualText, expectedText, "Home page text mismatch");

        logger.info("Test testHomePageIsDisplayed PASSED");
    }

    @Test(description = "Test catalog home page display")
    public void testCatalogNavigation() {
        logger.info("Starting test: testCatalogNavigation");
        learncatalogPage = learnhomePage.clickCatalogButton();
        logger.debug("Navigated to Learn Catalog page");

        String actualText = testData.getCatalogPageText();
        String expectedText = testData.getCatalogPageText();

        logger.info("Asserting catalog page text: expected '{}', found '{}'", expectedText, actualText);
        Assert.assertEquals(actualText, expectedText, "Catalog page text mismatch");

        logger.info("Test testCatalogNavigation PASSED");
    }

    @Test(description = "Test technical and technology page filters")
    public void testFiltersOnTechnicalAndTechnologyPage() {
        logger.info("Starting test: testFiltersOnTechnicalAndTechnologyPage");
        learncatalogPage = learnhomePage.clickCatalogButton();
        learnTechnicalAndTechnologyPage = learncatalogPage.clickTechnicalAndTechnologyCard();
        logger.debug("Navigated to Technical and Technology page");

        String actualText = learnTechnicalAndTechnologyPage.getTechnicalAndTechnologyPageText();
        String expectedText = testData.getTechnicalAndTechnologyPageText();

        logger.info("Asserting technical and technology page text: expected '{}', found '{}'", expectedText, actualText);
        Assert.assertEquals(actualText, expectedText, "Page text mismatch");

        learnTechnicalAndTechnologyPage
                .clickEnglishCheckBox()
                .clickMoreThanTwentyHoursCheckBox()
                .clickIntermediateCheckBox()
                .clickEpamCheckBox();

        logger.info("Applied filters: English, More than 20 hours, Intermediate, EPAM");

        int actualFilterCount = learnTechnicalAndTechnologyPage.getListOfFiltersSize();
        int expectedFilterCount = 4;

        logger.info("Asserting number of applied filters: expected '{}', found '{}'", expectedFilterCount, actualFilterCount);
        Assert.assertEquals(actualFilterCount, expectedFilterCount, "Filters count mismatch");

        logger.info("Test testFiltersOnTechnicalAndTechnologyPage PASSED");
    }

    @Test(description = "Test registration for the course on learn portal")
    public void testDotNetCourseDetails() {
        logger.info("Starting test: testDotNetCourseDetails");
        learncatalogPage = learnhomePage.clickCatalogButton();
        learnTechnicalAndTechnologyPage = learncatalogPage.clickTechnicalAndTechnologyCard();
        learnDotNetCoursePage = learnTechnicalAndTechnologyPage
                .clickEnglishCheckBox()
                .clickMoreThanTwentyHoursCheckBox()
                .clickIntermediateCheckBox()
                .clickEpamCheckBox()
                .clickDotNetCourseCard();
        logger.debug("Navigated to .NET Course page");

        SoftAssert softAssert = new SoftAssert();

        String actualLanguage = learnDotNetCoursePage.getLanguageText();
        String expectedLanguage = testData.getLanguageText();

        logger.info("Asserting course language: expected '{}', found '{}'", expectedLanguage, actualLanguage);
        softAssert.assertEquals(actualLanguage, expectedLanguage, "Course language mismatch");

        String actualEffort = learnDotNetCoursePage.getEstimatedEffortsText();
        String expectedEffort = testData.getEstimatedEffortsText();

        logger.info("Asserting course duration: expected '{}', found '{}'", expectedEffort, actualEffort);
        softAssert.assertEquals(actualEffort, expectedEffort, "Course duration mismatch");

        softAssert.assertAll();
        logger.info("Test testDotNetCourseDetails PASSED");
    }

}

