package service;

import model.CampusCourseTestData;
import util.TestDataReader;

public class TestDataCampusCourseService {

    private static final String TEST_DATA_LEARN_HOME_PAGE_TEXT = "testdata.learnHomePageText";
    private static final String TEST_DATA_CAMPUS_HOME_PAGE_TEXT = "testdata.campusHomePageText";
    private static final String TEST_DATA_TRAINING_PAGE_TEXT = "testdata.trainingPageText";
    private static final String TEST_DATA_COURSE_LOCATION = "testdata.courseLocation";
    private static final String TEST_DATA_COURSE_SKILL = "testdata.courseSkill";
    private static final String TEST_DATA_COURSE_NAME = "testdata.courseName";
    private static final String TEST_DATA_LOGIN_PAGE_TEXT = "testdata.loginPageText";

    public static CampusCourseTestData getTestDataFromProperties() {
        return new CampusCourseTestData(
                TestDataReader.getTestData(TEST_DATA_LEARN_HOME_PAGE_TEXT),
                TestDataReader.getTestData(TEST_DATA_CAMPUS_HOME_PAGE_TEXT),
                TestDataReader.getTestData(TEST_DATA_TRAINING_PAGE_TEXT),
                TestDataReader.getTestData(TEST_DATA_COURSE_LOCATION),
                TestDataReader.getTestData(TEST_DATA_COURSE_SKILL),
                TestDataReader.getTestData(TEST_DATA_COURSE_NAME),
                TestDataReader.getTestData(TEST_DATA_LOGIN_PAGE_TEXT)
        );
    }
}