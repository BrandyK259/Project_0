package revature.BankingApplication;

import com.revature.model.bankUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {

    //test user constructor
    @Test
    public void testConstructor()
    {
        bankUser u = new bankUser("testUsername", "testPassword", true, false, false );
        assertEquals("testUsername",u.username);
        assertEquals("testPassword",u.password);
        assertEquals(true,u.isCustomer);
        assertEquals(false,u.isEmployee);
        assertEquals(false,u.isAdmin);
    }

}
