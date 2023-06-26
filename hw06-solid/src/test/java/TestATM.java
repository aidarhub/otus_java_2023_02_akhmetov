import org.example.ATMbank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestATM {
    @Test
    public void testDeposit() {
        ATMbank atm = new ATMbank();
        atm.deposit(5000, 5);
        atm.deposit(1000, 5);
        atm.deposit(500, 20);
        assertEquals(40000, atm.getBalance());
    }

    @Test
    public void testWithdraw() {
        ATMbank atm = new ATMbank();
        atm.deposit(1000, 10);
        atm.deposit(500, 10);
        atm.deposit(200, 10);
        atm.deposit(100, 10);
        assertEquals(18000, atm.getBalance());
        assertTrue(atm.withdraw(10000));
        assertFalse(atm.withdraw(10000));
        assertEquals(8000, atm.getBalance());
    }
}



