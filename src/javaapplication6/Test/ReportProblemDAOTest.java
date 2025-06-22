package javaapplication6.Test;

import javaapplication6.dao.ReportProblemDAO;
import javaapplication6.model.ReportProblemModel;
import org.junit.Assert;
import org.junit.Test;

public class ReportProblemDAOTest {
    @Test
    public void testAddReport() {
        ReportProblemDAO dao = new ReportProblemDAO();
        ReportProblemModel report = new ReportProblemModel(
            "testuser@example.com",
            "Test Subject",
            "Test Description"
        );
        boolean result = dao.addReport(report);
        Assert.assertTrue("Adding report should succeed", result);
    }
}
