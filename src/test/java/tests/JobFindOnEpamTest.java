package tests;

import lombok.extern.slf4j.Slf4j;
import model.CampusCourseTestData;
import model.EpamCareersTestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.epam_portal.EpamCareersPage;
import pages.epam_portal.EpamHomePage;
import pages.epam_portal.EpamJobDetailsPage;
import pages.epam_portal.EpamJobListPage;
import service.TestDataCampusCourseService;
import service.TestDataEpamCareersService;
import service.TestDataService;
import service.TestDataServiceDecorator;
import tests.base.BaseTest;

@Slf4j
public class JobFindOnEpamTest extends BaseTest {

    private EpamHomePage epamHomePage;
    private EpamCareersPage epamCareersPage;
    private EpamJobListPage epamJobListPage;
    private EpamJobDetailsPage epamJobDetailsPage;
    private EpamCareersTestData testData;

    @BeforeMethod
    @Parameters({"environment"})
    public void setUpTest(String environment) {
        System.setProperty("environment", environment);
        epamHomePage = new EpamHomePage(driver).openPage();
        TestDataService<EpamCareersTestData> epamDataService =
                new TestDataServiceDecorator<>(new TestDataEpamCareersService());
        testData = epamDataService.getTestDataFromProperties();
    }

    @Test(description = "Verify navigation on EPAM Careers homepage")
    public void testCareersNavigation() {
        log.info("Starting test: testCareersNavigation");
        epamCareersPage = epamHomePage.clickCareersButton();

        Assert.assertEquals(epamCareersPage.getEpamCareersPageText(),
                testData.getCareersPageText(),
                "Careers page text mismatch");

        log.info("Test testCareersNavigation PASSED");
    }

//    @Test(description = "Verify job list page filters work correctly")
//    public void testJobListPageFilters() {
//        log.info("Starting test: testJobListPageFilters");
//        epamCareersPage = epamHomePage.clickCareersButton();
//
//        epamJobListPage = epamCareersPage
//                .clickFindButton()
//                .clickVisitButton();
//        epamCareersPage.switchToNewTab();
//
//        epamJobListPage.inputSkillsField(testData.getJobSkill())
//                .clickSpecialisationTextBox()
//                .clickSpecialisationCheckBox()
//                .inputLocationTextBox();
//
//
//        Assert.assertTrue(epamJobListPage.getJobCardText()
//                        .trim()
//                        .contains(testData.getJobSpecialization()),
//                "Job card title mismatch");
//        log.info("Test testJobListPageFilters PASSED");
//    }
//
//    @Test(description = "Verify job application submission process")
//    public void testJobApplicationSubmission() {
//        log.info("Starting test: testJobApplicationSubmission");
//        epamCareersPage = epamHomePage.clickCareersButton();
//        epamJobListPage = epamCareersPage
//                .clickFindButton()
//                .clickVisitButton();
//        epamCareersPage.switchToNewTab();
//
//        epamJobDetailsPage = epamJobListPage.inputSkillsField(testData.getJobSkill())
//                .clickSpecialisationTextBox()
//                .clickSpecialisationCheckBox()
//                .inputLocationTextBox()
//                .clickJobCard();
//
//        SoftAssert softAssert = new SoftAssert();
//
//        softAssert.assertTrue(epamJobDetailsPage.getJobTitleText()
//                .contains(testData.getJobSpecialization()),
//                "Job title mismatch");
//
//        softAssert.assertTrue(epamJobDetailsPage.getLocationText()
//                .contains(testData.getJobLocation()),
//                "Job location mismatch");
//
//        softAssert.assertAll();
//        log.info("Test testJobApplicationSubmission PASSED");
//    }

}
