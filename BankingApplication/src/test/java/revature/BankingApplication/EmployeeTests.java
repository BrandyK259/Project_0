package revature.BankingApplication;

import com.revature.model.Employee;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTests {
    @Test
    public void testConstructor()
    {
        Employee e = new Employee( "testUsername",  "testPassword",  "testFirstName",  "testLastName",
                "testCurrCustomerUn",  "testJointCustomerUn");
        assertEquals("testUsername", e.username);
        assertEquals("testPassword", e.password);
        assertEquals("testFirstName", e.firstName);
        assertEquals("testLastName", e.lastName);
        assertEquals("testCurrCustomerUn", e.currCustomerUn);
        assertEquals("testJointCustomerUn", e.jointCustomerUn);
    }

}
