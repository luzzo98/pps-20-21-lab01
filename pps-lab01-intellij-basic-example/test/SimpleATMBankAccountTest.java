import lab01.example.model.ATMBankAccount;
import lab01.example.model.AccountHolder;
import lab01.example.model.SimpleATMBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleATMBankAccountTest {

    private AccountHolder accountHolder;
    private ATMBankAccount bankAccount;

    private final static int INITIAL_BALANCE = 0;
    private final static int TEST_AMOUNT = 100;
    private final static int LITTLE_TEST_AMOUNT = 50;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleATMBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testDepositWithATM() {
        bankAccount.depositWithATM(accountHolder.getId(), TEST_AMOUNT);
        assertEquals(bankAccount.getBalance(), TEST_AMOUNT - INITIAL_BALANCE - bankAccount.getTransitionFee());
    }

    @Test
    void testWrongDepositWithATM() {
        bankAccount.depositWithATM(accountHolder.getId(), 0);
        assertEquals(bankAccount.getBalance(), 0);
    }

    @Test
    void testWithdrawWithATM() {
        bankAccount.deposit(accountHolder.getId(), TEST_AMOUNT);
        bankAccount.withdrawWithATM(accountHolder.getId(), LITTLE_TEST_AMOUNT);
        assertEquals(bankAccount.getBalance(), TEST_AMOUNT-LITTLE_TEST_AMOUNT-bankAccount.getTransitionFee());
    }

    @Test
    void testWrongWithdrawWithATM() {
        bankAccount.deposit(accountHolder.getId(), TEST_AMOUNT);
        bankAccount.withdrawWithATM(accountHolder.getId(), TEST_AMOUNT);
        assertEquals(bankAccount.getBalance(), TEST_AMOUNT);
    }
}
