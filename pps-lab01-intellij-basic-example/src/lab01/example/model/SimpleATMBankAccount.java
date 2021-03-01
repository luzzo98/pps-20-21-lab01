package lab01.example.model;

public class SimpleATMBankAccount extends SimpleBankAccount implements ATMBankAccount {

    private static final double TRANSITION_FEE = 1;

    public SimpleATMBankAccount(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    public boolean canWithdrawWithATM(double amount) {
        return super.getBalance() >= (amount + TRANSITION_FEE);
    }

    @Override
    public void applyTransitionFee(int usrID) {
        super.withdraw(usrID, TRANSITION_FEE);
    }

    @Override
    public void depositWithATM(int usrID, double amount) {
        if (usrID==super.getHolder().getId() && TRANSITION_FEE<amount) {
            this.applyTransitionFee(usrID);
            super.deposit(usrID,amount);
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
}
