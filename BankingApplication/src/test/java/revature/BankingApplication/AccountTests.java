package revature.BankingApplication;

import com.revature.dao.AccountDAOImpl;
import com.revature.dao.DAOInterface;
import com.revature.model.Account;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.management.modelmbean.DescriptorSupport;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests {
    @Test
    public void testConstructor()
    {
        Account a = new Account(0,"checking", BigDecimal.valueOf(1500),true, true, "testOwnerUN", null);

        assertEquals(0, a.id);
        assertEquals("checking", a.accountType);
        assertEquals(BigDecimal.valueOf(1500), a.balance);
        assertEquals(true, a.approved);
        assertEquals(true, a.active);
        assertEquals("testOwnerUN",a.ownerUsername);
        assertEquals(null, a.jointUsername);
    }

}
