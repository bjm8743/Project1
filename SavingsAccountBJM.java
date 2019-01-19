/**
 * Governs actions specific to a savings account.
 * @author Ben Morrison
 * @version 11/28/2016
 */
public class SavingsAccountBJM extends BankAccount
{
   private double interestRate;
   private double minimumBalance;
  
   /**
    * Constructor for a savings account with a zero balance
    * @param name the name on the account
    * @param interestRate the interest rate for the account
    */
   public SavingsAccountBJM(String name, double interestRate)
   {
      super(name);
      this.interestRate = interestRate;
      minimumBalance = 0.0;
   }
   
   /**
    * Constructor for a savings account with a given balance
    * @param name the name on the account
    * @param balance the balance of the account
    * @param interestRate the interest rate for the account
    * @param minimumBalance the minimum balance on the account
    */
   public SavingsAccountBJM(String name, 
                            double balance,
                            double minimumBalance,
                            double interestRate)
   {
      super(name, balance);
      this.interestRate = interestRate;
      this.minimumBalance = minimumBalance;
   }
   
   /**
    * Overwrites the BankAccount withdraw method to do things specific to
    * withdrawing from a savings account
    * @param amount the amount being withdrawn
    */
   public void withdraw (double amount)
   {
      super.withdraw(amount);
         
      if (super.getBalance() < minimumBalance)
      {
         minimumBalance = super.getBalance();
      }
   }
   
   /**
    * Do the month end processing
    */
   public void monthEnd()
   {
      double interest = minimumBalance * interestRate;
      super.deposit(interest);
      minimumBalance = super.getBalance();
   }
   
   /**
    * Generate the string representation necessary for writing to
    * the data file
    * @return a formatted string with the account information
    */
   public String printFormatted()
   {
      return String.format("S%d %1.2f %1.2f %1.2f %s%n", 
                           super.getAccountNumber(), super.getBalance(),
                           minimumBalance, interestRate, super.getName());
   }
   
   /**
    * Overrides toString for simple reporting of savings account info
    * @return a string representation of a savings account
    */
   public String toString()
   {
      return super.toString() + "[int=" + interestRate + 
                                ",minbal=" + minimumBalance + "]";                                
   }
  
}