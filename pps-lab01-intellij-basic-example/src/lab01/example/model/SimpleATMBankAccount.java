package lab01.example.model;

public class SimpleATMBankAccount extends SimpleBankAccount implements ATMBankAccount {

    private static final double TRANSITION_FEE = 1;

    public SimpleATMBankAccount(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    public void depositWithATM(int usrID, double amount) {
        if (usrID==super.getHolder().getId() && canDepositWithATM(amount)) {
            super.deposit(usrID,amount);
            this.applyTransitionFee(usrID);
        }
    }

    @Override
    public void withdrawWithATM(int usrID, double amount) {
        if (usrID==super.getHolder().getId() && canWithdrawWithATM(amount)) {
            this.applyTransitionFee(usrID);
            super.withdraw(usrID, amount);
        }
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
     * Return if it's possible withdraw in order to pay the fee
     * @return if it's possible to withdraw
     */
    private boolean canWithdrawWithATM(double amount) {
        return super.getBalance() >= (amount + TRANSITION_FEE);
    }

    /**
     * Decrease the balance in order to pay the transaction fee
     */
    private void applyTransitionFee(int usrID) {
        super.withdraw(usrID, TRANSITION_FEE);
    }
}
