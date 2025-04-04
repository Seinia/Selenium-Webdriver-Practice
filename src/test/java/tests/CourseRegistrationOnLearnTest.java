package tests;

import lombok.extern.slf4j.Slf4j;
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
import service.TestDataCampusCourseService;
import service.TestDataLearnCourseService;
import service.TestDataService;
import service.TestDataServiceDecorator;
import tests.base.BaseTest;

@Slf4j
public class CourseRegistrationOnLearnTest extends BaseTest {

    private LearnHomePage learnhomePage;
    private LearnCatalogPage learncatalogPage;
    private LearnTechnicalAndTechnologyPage learnTechnicalAndTechnologyPage;
    private LearnDotNetCoursePage learnDotNetCoursePage;
    private LearnCourseTestData testData;

    @BeforeMethod
    @Parameters({"environment"})
    public void setUpTest(String environment) {
        System.setProperty("environment", environment);
        learnhomePage = new LearnHomePage(driver).openPage().acceptCookie();
        TestDataService<LearnCourseTestData> learnDataService =
                new TestDataServiceDecorator<>(new TestDataLearnCourseService());
        testData = learnDataService.getTestDataFromProperties();
    }

    @Test(description = "Test learn home page display")
    public void testHomePageIsDisplayed() {
        log.info("Starting test: testHomePageIsDisplayed");

        Assert.assertEquals(testData.getHomePageText(),
                testData.getHomePageText(),
                "Home page text mismatch");

        log.info("Test testHomePageIsDisplayed PASSED");
    }

    @Test(description = "Test catalog home page display")
    public void testCatalogNavigation() {
        log.info("Starting test: testCatalogNavigation");
        learncatalogPage = learnhomePage.clickCatalogButton();

        Assert.assertEquals(testData.getCatalogPageText(),
                testData.getCatalogPageText(),
                "Catalog page text mismatch");

        log.info("Test testCatalogNavigation PASSED");
    }

    @Test(description = "Test technical and technology page filters")
    public void testFiltersOnTechnicalAndTechnologyPage() {
        log.info("Starting test: testFiltersOnTechnicalAndTechnologyPage");
        learncatalogPage = learnhomePage.clickCatalogButton();
        learnTechnicalAndTechnologyPage = learncatalogPage.clickTechnicalAndTechnologyCard();

        Assert.assertEquals(learnTechnicalAndTechnologyPage.getTechnicalAndTechnologyPageText(),
                testData.getTechnicalAndTechnologyPageText(),
                "Page text mismatch");

        learnTechnicalAndTechnologyPage
                .clickEnglishCheckBox()
                .clickMoreThanTwentyHoursCheckBox()
                .clickIntermediateCheckBox()
                .clickEpamCheckBox();

        Assert.assertEquals(learnTechnicalAndTechnologyPage.getListOfFiltersSize(),
                4,
                "Filters count mismatch");

        log.info("Test testFiltersOnTechnicalAndTechnologyPage PASSED");
    }

    @Test(description = "Test registration for the course on learn portal")
    public void testDotNetCourseDetails() {
        log.info("Starting test: testDotNetCourseDetails");
        learncatalogPage = learnhomePage.clickCatalogButton();
        learnTechnicalAndTechnologyPage = learncatalogPage.clickTechnicalAndTechnologyCard();
        learnDotNetCoursePage = learnTechnicalAndTechnologyPage
                .clickEnglishCheckBox()
                .clickMoreThanTwentyHoursCheckBox()
                .clickIntermediateCheckBox()
                .clickEpamCheckBox()
                .clickDotNetCourseCard();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(learnDotNetCoursePage.getLanguageText(),
                testData.getLanguageText(),
                "Course language mismatch");

        softAssert.assertEquals(learnDotNetCoursePage.getEstimatedEffortsText(),
                testData.getEstimatedEffortsText(),
                "Course duration mismatch");

        softAssert.assertAll();
        log.info("Test testDotNetCourseDetails PASSED");
    }

}

