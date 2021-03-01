package lab01.example.model;

/**
 * This interface defines the concept of bank account that allow the users to deposit and withdraw using an ATM.
 */
public interface ATMBankAccount extends BankAccount {

    /**
     * Return if it's possible execute the transition in order to pay the fee
     * @return if it's possible execute the transition
     */
    boolean canWithdrawWithATM(double amount);

    /**
     * Decrease the balance in order to pay the transition fee
     */
    void applyTransitionFee(int usrID);

    /**
     * Allows the deposit of an amount on the account with the ATM, if the given usrID corresponds to the register
     * holder ID of the bank account and if the fee is lower to the amount. This ID acts like an "identification token".
     *
     * @param usrID the id of the user that wants do the deposit
     * @param amount the amount of the deposit
     */
    void depositWithATM(int usrID, double amount);

    /**
     * Allows the withdraw of an amount with the ATM from the account, if the given usrID corresponds to the register
     * holder ID of the bank account. This ID acts like an "identification token" .
     *
     * @param usrID the id of the user that wants do the withdraw
     * @param amount the amount of the withdraw
     */
    void withdrawWithATM(int usrID, double amount);

    /**
     * Return the amount of the transition fee
     * @return the transition fee
     */
    double getTransitionFee();
}
