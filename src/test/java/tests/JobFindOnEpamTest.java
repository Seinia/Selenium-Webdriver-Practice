package tests;

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
import service.TestDataEpamCareersService;
import tests.base.BaseTest;

public class JobFindOnEpamTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(JobFindOnEpamTest.class);

    private EpamHomePage epamHomePage;
    private EpamCareersPage epamCareersPage;
    private EpamJobListPage epamJobListPage;
    private EpamJobDetailsPage epamJobDetailsPage;
    private EpamCareersTestData testData;

    @BeforeMethod
    @Parameters({"environment"})
    public void setUpTest(String environment) {
        System.setProperty("environment", environment);
        logger.info("Setting up test environment: {}", environment);
        epamHomePage = new EpamHomePage(driver).openPage();
        logger.debug("Opened EPAM homepage");
        testData = TestDataEpamCareersService.getTestDataFromProperties();
        logger.info("Loaded test data from properties");
    }

    @Test(description = "Verify navigation on EPAM Careers homepage")
    public void testCareersNavigation() {
        logger.info("Starting test: testCareersNavigation");
        epamCareersPage = epamHomePage.clickCareersButton();
        logger.debug("Navigated to EPAM Careers page");
        String actualText = epamCareersPage.getEpamCareersPageText();
        String expectedText = testData.getCareersPageText();

        logger.info("Asserting careers page text: expected '{}', found '{}'", expectedText, actualText);
        Assert.assertEquals(actualText, expectedText, "Careers page text mismatch");

        logger.info("Test testCareersNavigation PASSED");
    }

    @Test(description = "Verify job list page filters work correctly")
    public void testJobListPageFilters() {
        logger.info("Starting test: testJobListPageFilters");
        epamCareersPage = epamHomePage.clickCareersButton();
        logger.debug("Navigated to EPAM Careers page");

        epamJobListPage = epamCareersPage
                .clickFindButton()
                .clickVisitButton();
        epamCareersPage.switchToNewTab();
        logger.debug("Switched to new tab for job list page");

        epamJobListPage.inputSkillsField(testData.getJobSkill())
                .clickSpecialisationTextBox()
                .clickSpecialisationCheckBox()
                .inputLocationTextBox();

        logger.info("Applied filters: Skill '{}', Specialisation '{}', Location '{}'",
                testData.getJobSkill(), testData.getJobSpecialization(), testData.getJobLocation());

        boolean isJobCorrect = epamJobListPage.getJobCardText().trim().contains(testData.getJobSpecialization());
        logger.info("Asserting job card title contains specialization: '{}'", testData.getJobSpecialization());

        Assert.assertTrue(isJobCorrect, "Job card title mismatch");
        logger.info("Test testJobListPageFilters PASSED");
    }

    @Test(description = "Verify job application submission process")
    public void testJobApplicationSubmission() {
        logger.info("Starting test: testJobApplicationSubmission");
        epamCareersPage = epamHomePage.clickCareersButton();
        logger.debug("Navigated to EPAM Careers page");
        epamJobListPage = epamCareersPage
                .clickFindButton()
                .clickVisitButton();
        epamCareersPage.switchToNewTab();
        logger.debug("Switched to new tab for job list page");

        epamJobDetailsPage = epamJobListPage.inputSkillsField(testData.getJobSkill())
                .clickSpecialisationTextBox()
                .clickSpecialisationCheckBox()
                .inputLocationTextBox()
                .clickJobCard();
        logger.debug("Opened job details page");

        SoftAssert softAssert = new SoftAssert();

        String actualJobTitle = epamJobDetailsPage.getJobTitleText();
        String actualLocation = epamJobDetailsPage.getLocationText();

        logger.info("Asserting job title: expected '{}', found '{}'", testData.getJobSpecialization(), actualJobTitle);
        softAssert.assertTrue(actualJobTitle.contains(testData.getJobSpecialization()), "Job title mismatch");

        logger.info("Asserting job location: expected '{}', found '{}'", testData.getJobLocation(), actualLocation);
        softAssert.assertTrue(actualLocation.contains(testData.getJobLocation()), "Job location mismatch");

        softAssert.assertAll();
        logger.info("Test testJobApplicationSubmission PASSED");
    }

}
