package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.campus_portal.CampusHomePage;
import pages.campus_portal.CampusLoginPage;
import pages.campus_portal.CampusTrainingPage;
import pages.learn_portal.LearnHomePage;
import tests.base.BaseTest;

public class CourseRegistrationOnCampusTest extends BaseTest {

    private LearnHomePage learnHomePage;
    private CampusHomePage campusHomePage;
    private CampusTrainingPage campusTrainingPage;
    private CampusLoginPage campusLoginPage;

    @BeforeMethod
    public void setUpTest() {
        learnHomePage = new LearnHomePage(driver).openPage().acceptCookie();
    }

    @Test(description = "Test learn home page display")
    public void testHomePageIsDisplayed() {
        Assert.assertEquals(learnHomePage.getHomePageText(),
                "Home",
                "Learn home page text mismatch");
    }

    @Test(description = "Test campus home page display")
    public void testCampusNavigation() {
        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        Assert.assertEquals(campusHomePage.getHomePageText(),
                "Start your",
                "Campus page text mismatch");
    }

    @Test(description = "Test campus training page display")
    public void testFindAProgramNavigation() {
        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        campusTrainingPage = campusHomePage
                .acceptCookie()
                .clickFindAProgramButton();
        Assert.assertEquals(campusTrainingPage.getTrainingPageText(),
                "Training programs",
                "Campus training page text mismatch");
    }

    @Test(description = "Test campus training page filters")
    public void testFiltersOnCampusCoursePage() {
        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        campusTrainingPage = campusHomePage
                .acceptCookie()
                .clickFindAProgramButton()
                .clickLocationsDropDown()
                .inputSearchField("Ukraine")
                .clickDropDownCheckBox()
                .clickSkillsDropDown()
                .inputSearchField("Automated Testing in JS")
                .clickDropDownCheckBox();

        Assert.assertEquals(campusTrainingPage.getCourseCardText(),
                "Automated Testing in JavaScript",
                "Course card text mismatch");
    }

    @Test(description = "Test registration for the course on campus portal")
    public void testJavaScriptCourseRegistration() {
        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        campusLoginPage = campusHomePage
                .acceptCookie()
                .clickFindAProgramButton()
                .clickLocationsDropDown()
                .inputSearchField("Ukraine")
                .clickDropDownCheckBox()
                .clickSkillsDropDown()
                .inputSearchField("Automated Testing in JS")
                .clickDropDownCheckBox()
                .clickCourseCard()
                .clickRegisterButton();

        Assert.assertEquals(campusLoginPage.getLoginPageText(),
                "Welcome to EPAM",
                "Login page text mismatch");
    }
}
