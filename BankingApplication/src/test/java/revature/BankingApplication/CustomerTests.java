package revature.BankingApplication;

import com.revature.model.Customer;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTests {
    @Test
    public void testConstructor()
    {
        Customer c = new Customer( "testUsername",  "testPassword",  "testFirstName",  "testLastName",
                "testEmail", 0, 0,0);
        assertEquals("testUsername", c.username);
        assertEquals("testPassword", c.password);
        assertEquals("testFirstName", c.firstName);
        assertEquals("testLastName", c.lastName);
        assertEquals("testEmail", c.email);
        assertEquals(0, c.checkingAcctId);
        assertEquals(0, c.savingsAcctId);
        assertEquals(0, c.jointAcctId);
    }

}
