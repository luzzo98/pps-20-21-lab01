import lab01.example.model.ATMBankAccount;
import lab01.example.model.AccountHolder;
import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the {@link SimpleBankAccountWithAtm} implementation
 */
public class SimpleBankAccountWithAtmTest extends SimpleBankAccountTest {

    private ATMBankAccount atmBankAccount;

    private final static int INITIAL_BALANCE = 0;
    private final static int TEST_AMOUNT = 100;
    private final static int LITTLE_TEST_AMOUNT = 50;

    @Override
    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        atmBankAccount = new SimpleBankAccountWithAtm(accountHolder, INITIAL_BALANCE);
        bankAccount = atmBankAccount;
    }

    @Test
    void testDepositWithATM() {
        atmBankAccount.depositWithATM(accountHolder.getId(), TEST_AMOUNT);
        assertEquals(atmBankAccount.getBalance(), TEST_AMOUNT - INITIAL_BALANCE - atmBankAccount.getTransitionFee());
    }

    @Test
    void testWrongDepositWithATM() {
        atmBankAccount.deposit(accountHolder.getId(), TEST_AMOUNT);
        atmBankAccount.depositWithATM(accountHolder.getId(), 0.5); //try to deposit an amount < of the fee
        assertEquals(atmBankAccount.getBalance(), TEST_AMOUNT);
    }

    @Test
    void testWithdrawWithATM() {
        atmBankAccount.deposit(accountHolder.getId(), TEST_AMOUNT);
        atmBankAccount.withdrawWithATM(accountHolder.getId(), LITTLE_TEST_AMOUNT);
        assertEquals(atmBankAccount.getBalance(), TEST_AMOUNT - LITTLE_TEST_AMOUNT - atmBankAccount.getTransitionFee());
    }

    @Test
    void testWrongWithdrawWithATM() {
        atmBankAccount.deposit(accountHolder.getId(), TEST_AMOUNT);
        atmBankAccount.withdrawWithATM(accountHolder.getId(), TEST_AMOUNT); //try to withdraw an amount equal to the balance
        assertEquals(atmBankAccount.getBalance(), TEST_AMOUNT);
    }
}
