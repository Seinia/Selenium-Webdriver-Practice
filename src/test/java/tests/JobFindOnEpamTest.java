package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.epam_portal.EpamCareersPage;
import pages.epam_portal.EpamHomePage;
import pages.epam_portal.EpamJobDetailsPage;
import pages.epam_portal.EpamJobListPage;
import tests.base.BaseTest;

public class JobFindOnEpamTest extends BaseTest {

    private EpamHomePage epamHomePage;
    private EpamCareersPage epamCareersPage;
    private EpamJobListPage epamJobListPage;
    private EpamJobDetailsPage epamJobDetailsPage;

    @BeforeMethod
    public void setUpTest() {
        epamHomePage = new EpamHomePage(driver).openPage().acceptCookie();
    }

    @Test(description = "Test epam home page display")
    public void testCareersNavigation() {
        epamCareersPage = epamHomePage.clickCareersButton();
        Assert.assertEquals(epamCareersPage.getEpamCareersPageText(), "Find Your Dream Job",
                "Careers page text mismatch");
    }

    @Test(description = "Test epam job list page filters")
    public void testFiltersOnEpamJobListPage() {
        epamCareersPage = epamHomePage.clickCareersButton();
        epamJobListPage = epamCareersPage
                .clickFindButton()
                .clickVisitButton();
        epamCareersPage.switchToNewTab();

        epamJobListPage.inputSkillsField("Java")
                .clickSpecialisationTextBox()
                .clickSpecialisationCheckBox()
                .inputLocationTextBox();

        Assert.assertTrue(epamJobListPage
                        .getJobCardText().trim().contains("Data"),
                "Job card title mismatch");
    }

    @Test(description = "Test job application on epam careers portal")
    public void testJobDetails() {
        epamCareersPage = epamHomePage.clickCareersButton();
        epamJobListPage = epamCareersPage
                .clickFindButton()
                .clickVisitButton();
        epamCareersPage.switchToNewTab();

        epamJobDetailsPage = epamJobListPage.inputSkillsField("Java")
                .clickSpecialisationTextBox()
                .clickSpecialisationCheckBox()
                .inputLocationTextBox()
                .clickJobCard();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(epamJobDetailsPage
                        .getJobTitleText().contains("Data"),
                "Job title mismatch");
        softAssert.assertTrue(epamJobDetailsPage
                        .getLocationText().contains("Kyiv"),
                "Job location mismatch");
        softAssert.assertAll();
    }
}
