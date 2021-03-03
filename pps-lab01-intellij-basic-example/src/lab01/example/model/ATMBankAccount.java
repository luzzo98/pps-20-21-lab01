package lab01.example.model;

/**
 * This interface defines the concept of bank account that allow the users to deposit and withdraw using an ATM.
 */
public interface ATMBankAccount extends BankAccount {

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
     * Return the actual amount of the transaction fee
     * @return the transaction fee
     */
    double getTransitionFee();
}
