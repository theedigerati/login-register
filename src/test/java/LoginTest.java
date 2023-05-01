import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testRegisterUser_usernameIsCorrectlyFormatted() {
        parseTestData("kyl_1");

        Login login = new Login();
        login.password = "Ch&&sec@ke99!";
        String message = "";
        while(!login.userHasRegistered) {
            message = login.registerUser("Kyle", "Smith");
        }
        assertEquals("Welcome Kyle Smith, it is great to see you.", message);
    }

    @Test
    public void testRegisterUser_usernameIsIncorrectlyFormatted() {
        parseTestData("kyle!!!!!!!");

        Login login = new Login();
        login.password = "Ch&&sec@ke99!";
        String message = login.registerUser("Kyle", "Smith");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", message);
    }

    @Test
    public void testCheckPasswordComplexity_passwordMeetsRequirements() {
        parseTestData("Ch&&sec@ke99!");

        Login login = new Login();
        login.username = "kyl_1";
        String message = login.registerUser("Kyle", "Smith");
        assertEquals("Password successfully captured", message);
    }

    @Test
    public void testCheckPasswordComplexity_passwordDoesNotMeetRequirements() {
        parseTestData("password");

        Login login = new Login();
        login.username = "kyl_1";
        String message = login.registerUser("Kyle", "Smith");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", message);
    }

    @Test
    public void testLogin_loginSuccessful() {
        Login login = new Login();
        login.username = "kyl_1";
        login.password = "Ch&&sec@ke99!";
        login.registerUser("Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLogin_Failed() {
        Login login = new Login();
        login.username = "kyl_1";
        login.password = "Ch&&sec@ke99!";
        login.registerUser("Kyle", "Smith");
        assertFalse(login.loginUser("kyl_1", "password"));
    }

    @Test
    public void testUsername_correctlyFormatted() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsername_incorrectlyFormatted() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPassword_meetsRequirements() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPassword_doesNotMeetsRequirements() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    public void parseTestData(String input){
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

}