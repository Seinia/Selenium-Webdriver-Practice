package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.learn_portal.LearnCatalogPage;
import pages.learn_portal.LearnDotNetCoursePage;
import pages.learn_portal.LearnHomePage;
import pages.learn_portal.LearnTechnicalAndTechnologyPage;
import tests.base.BaseTest;

public class CourseRegistrationOnLearnTest extends BaseTest {

    private LearnHomePage learnhomePage;
    private LearnCatalogPage learncatalogPage;
    private LearnTechnicalAndTechnologyPage learnTechnicalAndTechnologyPage;
    private LearnDotNetCoursePage learnDotNetCoursePage;

    @BeforeMethod
    public void setUpTest() {
        learnhomePage = new LearnHomePage(driver).openPage().acceptCookie();
    }

    @Test(description = "Test learn home page display")
    public void testHomePageIsDisplayed() {
        Assert.assertEquals(learnhomePage.getHomePageText(),
                "Home",
                "Home page text mismatch");
    }

    @Test(description = "Test catalog home page display")
    public void testCatalogNavigation() {
        learncatalogPage = learnhomePage.clickCatalogButton();
        Assert.assertEquals(learncatalogPage.getCatalogPageText(),
                "Catalog",
                "Catalog page text mismatch");
    }

    @Test(description = "Test technical and technology page filters")
    public void testFiltersOnTechnicalAndTechnologyPage() {
        learncatalogPage = learnhomePage.clickCatalogButton();
        learnTechnicalAndTechnologyPage = learncatalogPage.clickTechnicalAndTechnologyCard();

        Assert.assertEquals(learnTechnicalAndTechnologyPage.getTechnicalAndTechnologyPageText(),
                "Technical and Technology",
                "Page text mismatch");

        learnTechnicalAndTechnologyPage
                .clickEnglishCheckBox()
                .clickMoreThanTwentyHoursCheckBox()
                .clickIntermediateCheckBox()
                .clickEpamCheckBox();

        Assert.assertEquals(learnTechnicalAndTechnologyPage.getListOfFiltersSize(),
                4,
                "Filters count mismatch");
    }

    @Test(description = "Test registration for the course on learn portal")
    public void testDotNetCourseDetails() {
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
                "ENG",
                "Course language mismatch");
        softAssert.assertEquals(learnDotNetCoursePage.getEstimatedEffortsText(),
                "145 hr 41 min",
                "Course duration mismatch");

        softAssert.assertAll();
    }
}

