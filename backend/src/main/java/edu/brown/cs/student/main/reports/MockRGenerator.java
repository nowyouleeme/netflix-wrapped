package edu.brown.cs.student.main.reports;

public class MockRGenerator implements ReportGenerator{
    public MockRGenerator(){}

    public String generateReportJSON(String[][] csvData) {
        //return the mock that kathryn made for frontend
        MockReports mockReports = new MockReports();
        return mockReports.mock1;
    }
}
