package tests;

import lombok.extern.slf4j.Slf4j;
import model.CampusCourseTestData;
import model.LearnCourseTestData;
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
import service.TestDataLearnCourseService;
import service.TestDataService;
import service.TestDataServiceDecorator;
import tests.base.BaseTest;

@Slf4j
public class CourseRegistrationOnCampusTest extends BaseTest {


    private LearnHomePage learnHomePage;
    private CampusHomePage campusHomePage;
    private CampusTrainingPage campusTrainingPage;
    private CampusLoginPage campusLoginPage;
    private CampusCourseTestData testData;

    @BeforeMethod
    @Parameters({"environment"})
    public void setUpTest(String environment) {
        System.setProperty("environment", environment);
        learnHomePage = new LearnHomePage(driver).openPage().acceptCookie();
        TestDataService<CampusCourseTestData> campusDataService =
                new TestDataServiceDecorator<>(new TestDataCampusCourseService());
        testData = campusDataService.getTestDataFromProperties();
    }

    @Test(description = "Verify 'Learn' homepage elements are displayed")
    public void testLearnHomePageIsDisplayed() {
        log.info("Starting test: testLearnHomePageIsDisplayed");

        Assert.assertEquals(learnHomePage.getHomePageText(),
                testData.getLearnHomePageText(),
                "Learn home page text mismatch");
        log.info("Test testLearnHomePageIsDisplayed PASSED\n");
    }

    @Test(description = "Verify campus home page elements are displayed")
    public void testCampusHomePageIsDisplayed() {
        log.info("Starting test: testCampusHomePageIsDisplayed");

        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        log.debug("Navigated to Campus homepage");

        Assert.assertEquals(campusHomePage.getHomePageText(),
                testData.getCampusHomePageText(),
                "Campus page text mismatch");
        log.info("Test testCampusHomePageIsDisplayed PASSED\n");
    }

    @Test(description = "Verify navigation on training page")
    public void testTrainingPageNavigation() {
        log.info("Starting test: testTrainingPageNavigation");

        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        campusTrainingPage = campusHomePage.clickFindAProgramButton();

        Assert.assertEquals(campusTrainingPage.getTrainingPageText(),
                testData.getTrainingPageText(),
                "Campus training page text mismatch");
        log.info("Test testTrainingPageNavigation PASSED\n");
    }

//    @Test(description = "Verify filters on campus training page")
//    public void testCampusTrainingPageFilters() {
//        log.info("Starting test: testCampusTrainingPageFilters");
//
//        campusHomePage = learnHomePage.clickCampusButton();
//        learnHomePage.switchToNewTab();
//        campusTrainingPage = campusHomePage
//                .acceptCookie()
//                .clickFindAProgramButton()
//                .clickLocationsDropDown()
//                .inputSearchField(testData.getCourseLocation())
//                .clickDropDownCheckBox()
//                .clickSkillsDropDown()
//                .inputSearchField(testData.getCourseSkill())
//                .clickDropDownCheckBox();
//
//
//        Assert.assertEquals(campusTrainingPage.getCourseCardText(),
//                testData.getCourseName(),
//                "Course card text mismatch");
//        log.info("Test testCampusTrainingPageFilters PASSED\n");
//    }
//
//    @Test(description = "Verify JavaScript course registration process")
//    public void testJavaScriptCourseRegistration() {
//        log.info("Starting test: testJavaScriptCourseRegistration");
//
//        campusHomePage = learnHomePage.clickCampusButton();
//        learnHomePage.switchToNewTab();
//        campusLoginPage = campusHomePage
//                .acceptCookie()
//                .clickFindAProgramButton()
//                .clickLocationsDropDown()
//                .inputSearchField(testData.getCourseLocation())
//                .clickDropDownCheckBox()
//                .clickSkillsDropDown()
//                .inputSearchField(testData.getCourseSkill())
//                .clickDropDownCheckBox()
//                .clickCourseCard()
//                .clickRegisterButton();
//
//        Assert.assertEquals(campusLoginPage.getLoginPageText(),
//                testData.getLoginPageText(),
//                "Login page text mismatch");
//        log.info("Test testJavaScriptCourseRegistration PASSED\n");
//    }

}
