package revature.BankingApplication;

import com.revature.model.Admin;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTests {
    @Test
    public void testConstructor()
    {
        Admin a = new Admin( "testUsername",  "testPassword",  "testFirstName",  "testLastName",
                "testCurrCustomerUn",  "testJointCustomerUn");
        assertEquals("testUsername", a.username);
        assertEquals("testPassword", a.password);
        assertEquals("testFirstName", a.firstName);
        assertEquals("testLastName", a.lastName);
        assertEquals("testCurrCustomerUn", a.currCustomerUn);
        assertEquals("testJointCustomerUn", a.jointCustomerUn);
    }
}
