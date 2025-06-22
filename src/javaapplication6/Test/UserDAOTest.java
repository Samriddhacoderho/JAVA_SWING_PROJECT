package javaapplication6.Test;

import javaapplication6.dao.UserDAO;
import javaapplication6.model.RegisterModel;
import javaapplication6.model.LoginModel;
import javaapplication6.model.EditNameModel;
import javaapplication6.model.ResetModel;
import org.junit.Assert;
import org.junit.Test;

public class UserDAOTest {
    private String testName = "Test User";
    private String testEmail = "user" + System.currentTimeMillis() + "@example.com";
    private String testPassword = "password123";

    @Test
    public void testRegisterUser() {
        UserDAO dao = new UserDAO();
        RegisterModel user = new RegisterModel(testName, testEmail, testPassword, testPassword);
        boolean result = dao.registerUser(user);
        Assert.assertTrue("User registration should succeed", result);
    }

    @Test
    public void testLoginUser() {
        UserDAO dao = new UserDAO();
        RegisterModel user = new RegisterModel(testName, testEmail, testPassword, testPassword);
        dao.registerUser(user);

        LoginModel login = new LoginModel(testEmail, testPassword);
        boolean loginResult = dao.loginUser(login);
        Assert.assertTrue("Login should succeed with correct credentials", loginResult);

        LoginModel wrongLogin = new LoginModel(testEmail, "wrongpass");
        boolean wrongResult = dao.loginUser(wrongLogin);
        Assert.assertFalse("Login should fail with wrong password", wrongResult);
    }

    @Test
    public void testEditName() {
        UserDAO dao = new UserDAO();
        RegisterModel user = new RegisterModel(testName, testEmail, testPassword, testPassword);
        dao.registerUser(user);

        LoginModel login = new LoginModel(testEmail, testPassword);
        EditNameModel editModel = new EditNameModel("New Name");
        String result = dao.editName(editModel, login);
        Assert.assertEquals("Your name was successfully changed", result);
    }

    @Test
    public void testCheckEmail() {
        UserDAO dao = new UserDAO();
        RegisterModel user = new RegisterModel(testName, testEmail, testPassword, testPassword);
        dao.registerUser(user);

        boolean exists = dao.checkEmail(testEmail);
        Assert.assertTrue("Email should exist", exists);

        boolean notExists = dao.checkEmail("notfound" + System.currentTimeMillis() + "@example.com");
        Assert.assertFalse("Email should not exist", notExists);
    }

    @Test
    public void testResetPassword() {
        UserDAO dao = new UserDAO();
        RegisterModel user = new RegisterModel(testName, testEmail, testPassword, testPassword);
        dao.registerUser(user);

        ResetModel reset = new ResetModel(testEmail, "newpassword123");
        boolean resetResult = dao.resetPassword(reset);
        Assert.assertTrue("Password reset should succeed", resetResult);

        LoginModel login = new LoginModel(testEmail, "newpassword123");
        boolean loginResult = dao.loginUser(login);
        Assert.assertTrue("Login with new password should succeed", loginResult);
    }
}
