package service;

import model.EpamCareersTestData;
import util.TestDataReader;

public class TestDataEpamCareersService {

    private static final String TEST_DATA_CAREERS_PAGE_TEXT = "testdata.epamCareersPageText";
    private static final String TEST_DATA_JOB_SKILL = "testdata.epamJobSkill";
    private static final String TEST_DATA_JOB_SPECIALIZATION = "testdata.epamJobSpecialization";
    private static final String TEST_DATA_JOB_LOCATION = "testdata.epamJobLocation";

    public static EpamCareersTestData getTestDataFromProperties() {
        return new EpamCareersTestData(
                TestDataReader.getTestData(TEST_DATA_CAREERS_PAGE_TEXT),
                TestDataReader.getTestData(TEST_DATA_JOB_SKILL),
                TestDataReader.getTestData(TEST_DATA_JOB_SPECIALIZATION),
                TestDataReader.getTestData(TEST_DATA_JOB_LOCATION)
        );
    }
}
