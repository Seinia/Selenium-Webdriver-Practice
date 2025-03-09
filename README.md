# README

## Test Scenarios Description

### First Scenario: Course Catalog Verification

1. Go to [EPAM Learn](https://learn.epam.com/) and verify the correct display of the page text.
2. Click on the "Catalog" button and verify the correct display of the page text.
3. Click the "English" checkbox in the LANGUAGE field.
4. Click the "More than 20 hours" checkbox in the ESTIMATED EFFORTS field.
5. Click the "Intermediate" checkbox in the TARGET LEVEL field.
6. Click the "EPAM" checkbox in the VENDOR field.
7. Verify the number of applied filters.
8. Select the ".Net Fundamentals: C# Advanced and Data Processing" course.
9. Verify the course duration.
10. Verify the course language.

### Second Scenario: Finding a Training Program

1. Go to [EPAM Learn](https://learn.epam.com/) and verify the correct display of the page text.
2. Click on the "Campus" button and verify the correct display of the page text.
3. Click on the "Find a program" button and verify the correct display of the page text.
4. Set the location to "Ukraine".
5. Select the skill "Automated Testing in JS".
6. Choose the "Automated Testing in JS" course.
7. Verify the correct filtering by course card.
8. Click on the first course card.
9. Click the "Register" button.
10. Verify the correct transition to the login form.
11. Click the "Register" button.

### Third Scenario: Job Search on EPAM

1. Go to [EPAM](https://www.epam.com/) and verify the correct display of the page text.
2. Click on the "Careers" button.
3. Click on the "Find" button.
4. Click on the "Visit Epam UA" button.
5. Enter the skill "Java".
6. Select the location "Kyiv".
7. Choose the specialization "Business and Data Analysis".
8. Verify the correct filtering by job card.
9. Click on the first job card.
10. Verify the location.
11. Verify the job title.

## Acceptance Criteria

- The scenarios are linear (no need to implement complex logic for now). 3 scenarios in total.
- Different locator strategies are used for a task.
- Usage of auto-generated locators is avoided.
- WebDriver API is widely used.
- Different methods of waits are used.
- Test scenarios are clear, stable, and well-structured.
- Each method in a test scenario has assertions.
- Page Objects have a consistent structure (proper decomposition of POs).
- Test scenarios are clear, stable, and well-structured.
- There is at least one level of inheritance between pages (Abstract Page exists).
- There is no code duplication at all.
- The inner implementation of PO is hidden from tests.
- Naming and Code Conventions should be followed.

## Parallel Test Execution

To execute tests in parallel, use the `testng_parallel.xml` file.

