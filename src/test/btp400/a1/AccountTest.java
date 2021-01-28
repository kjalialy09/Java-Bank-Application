package test.btp400.a1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.seneca.accounts.Account;

class AccountTest {
	Account good, bad;
	@Test
	void testConstructor() {
		//good = new Account("Kyle Alialy", "A1234", 10);
		bad = new Account(null, null, 0);
	}
	
	@Test
	void testFirstName()
	{
		assertEquals(bad.getFirstName(), "");
		//assertEquals("", bad.getFirstName());
	}
	

}
