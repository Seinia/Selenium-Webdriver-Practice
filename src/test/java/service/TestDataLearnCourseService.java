package service;

import model.LearnCourseTestData;
import util.TestDataReader;

public class TestDataLearnCourseService {

    private static final String TEST_DATA_HOME_PAGE_TEXT = "testdata.learnHomePageText";
    private static final String TEST_DATA_CATALOG_PAGE_TEXT = "testdata.learnCatalogPageText";
    private static final String TEST_DATA_TECHNICAL_AND_TECHNOLOGY_PAGE_TEXT = "testdata.learnTechnicalAndTechnologyPageText";
    private static final String TEST_DATA_TECHNICAL_AND_TECHNOLOGY_PAGE_FILTERS_COUNT =
            "testdata.learnTechnicalAndTechnologyPageFilterCount";
    private static final String TEST_DATA_LANGUAGE_TEXT = "testdata.learnLanguageText";
    private static final String TEST_DATA_ESTIMATED_EFFORTS_TEXT = "testdata.learnEstimatedEffortsText";

    public static LearnCourseTestData getTestDataFromProperties() {
        return new LearnCourseTestData(
                TestDataReader.getTestData(TEST_DATA_HOME_PAGE_TEXT),
                TestDataReader.getTestData(TEST_DATA_CATALOG_PAGE_TEXT),
                TestDataReader.getTestData(TEST_DATA_TECHNICAL_AND_TECHNOLOGY_PAGE_TEXT),
                TestDataReader.getTestData(TEST_DATA_TECHNICAL_AND_TECHNOLOGY_PAGE_FILTERS_COUNT),
                TestDataReader.getTestData(TEST_DATA_LANGUAGE_TEXT),
                TestDataReader.getTestData(TEST_DATA_ESTIMATED_EFFORTS_TEXT)
        );
    }
}