/**
 * Governs actions specific to a checking account.
 * @author Ben Morrison
 * @version 11/28/2016
 */
public class CheckingAccountBJM extends BankAccount
{
   private int numberOfWithdrawals;
   private static final int MAX_FREE_WITHDRAWALS = 3;
   private static final double WITHDRAWAL_FEE = 1.00;
  
   /**
    * Constructs a new checking account
    * @param name the name on the account
    */
   public CheckingAccountBJM(String name)
   {
      super(name);
      numberOfWithdrawals = 0;
   }
   
   /**
    * Constructs a checking account with existing balance and withdrawls
    * @param name the name on the account
    * @param balance the current balance of the account
    * @param withdrawals the number of withdrawls
    */
   public CheckingAccountBJM(String name, double balance, int withdrawals)
   {
      super(name, balance);
      numberOfWithdrawals = withdrawals;
   }
  
   /**
    * Overrides withdraw to keep the balance from going below zero and to
    * properly count the number of withdrawls
    * @param amount the amount being withdrawn
    */
   public void withdraw (double amount)
   {
      if (numberOfWithdrawals <= MAX_FREE_WITHDRAWALS)
      {
         super.withdraw(amount);
         numberOfWithdrawals++;
      }
      else
      {
         if (super.getBalance() > (amount + WITHDRAWAL_FEE))
         {
            super.withdraw(amount + WITHDRAWAL_FEE);
            numberOfWithdrawals++;
         }
         else
         {
            System.out.print("Withdrawal not allowed");
         }
      }
   }
  
   /**
    * Do month end processing
    */
   public void monthEnd()
   {
      numberOfWithdrawals = 0;
   }
   
   /**
    * Generate the string representation necessary for writing to
    * the data file
    * @return a formatted string with the account information
    */
   public String printFormatted()
   {
      return String.format("C%d %1.2f %d %s%n", super.getAccountNumber(),
                            super.getBalance(), numberOfWithdrawals,
                            super.getName());
   }
   
   /**
     * Overrides toString for simple reporting of savings account info
     * @return a string representation of a savings account
     */
    public String toString()
    {
       return super.toString() + "[withs=" + numberOfWithdrawals + "]";                                
    }
}