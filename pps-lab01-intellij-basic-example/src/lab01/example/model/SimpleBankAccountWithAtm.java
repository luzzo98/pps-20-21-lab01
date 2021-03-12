package lab01.example.model;

/**
 * This class represent a particular instance of a ATMBankAccount.
 * In particular, a Bank Account that allows, in addition to the possibilities given by
 * {@link SimpleBankAccountWithAtm}, to deposit and withdraw using the ATM; each transition
 * implies pay a fee
 */
public class SimpleBankAccountWithAtm extends SimpleBankAccount implements ATMBankAccount {

    private static final double TRANSITION_FEE = 1;

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    public void depositWithATM(int usrID, double amount) {
        if (canDepositWithATM(amount)) {
            if (super.deposit(usrID,amount)) {
                this.applyTransitionFee(usrID);
            }
        }
    }

    @Override
    public void withdrawWithATM(int usrID, double amount) {
        super.withdraw(usrID, amount + TRANSITION_FEE);
    }

    @Override
    public double getTransitionFee(){
        return TRANSITION_FEE;
    }

    /**
     * Return if it's possible execute the deposit in order to pay the fee
     * @return if it's possible to deposit
     */
    private boolean canDepositWithATM(double amount) {
        return TRANSITION_FEE<amount;
    }

    /**
     * Decrease the balance in order to pay the transaction fee
     */
    private void applyTransitionFee(int usrID) {
        super.withdraw(usrID, TRANSITION_FEE);
    }
}
