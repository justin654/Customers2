/* Justin Pullen
 * Customers 2 - Nested If/else, Compound 9-24
 * This program is for taking in user data pertaining to credit cards. The user enters their account number, beginning
 * monthly balance, new monthly charges, total already paid, and their credit limit. After we have that information
 * we first calculate how to determine owed balance, how much over limit is, and then how to determine avail credit and
 * store it for use. After, we print out users account number and their owed balance. If what they owe is over their
 * limit, we print a message letting user know and by how much, if owed is lower than limit, let them know avail credit.
 * Finally, if it isn't over or under, then it must be equal, so let user know they are at their limit and show a warning.
*/

import java.util.Scanner; // import scanner for us to use

public class Customers2 {
    public static void main(String[] args) {
        int accNum; // Initialize the accNum as int since it doesn't need decimal places
        double beginBal, totalNew, totalPaid, owedBal, creditLimit ,overLimitBy, availCredit; // Initialize these as double since we are working with money

        Scanner scan = new Scanner(System.in); //create new scanner object to use input

        System.out.print("Please enter your account number: ");
        accNum = scan.nextInt(); // check input for next Int and store it ass accNum
        System.out.print("Please enter beginning monthly balance: ");
        beginBal = scan.nextDouble(); // check input for next double and store as beginBal
        System.out.print("Please enter new monthly charges: ");
        totalNew = scan.nextDouble();
        System.out.print("Please enter total already paid: ");
        totalPaid = scan.nextDouble();
        System.out.print("Please enter credit limit: ");
        creditLimit = scan.nextDouble();

        owedBal = (beginBal + totalNew - totalPaid); // owedBal is equal to beginning balance + new charges - total paid
        overLimitBy = (owedBal - creditLimit); // calc over limit, owed balance minus the credit limit
        availCredit = (creditLimit - owedBal); // calc avail credit, the limit minus owed

        System.out.println("Account Number: " + accNum); // print out acc number, println is fine since we arent needing decimals
        System.out.printf("Ending Balance: $%.2f \n", owedBal); // print out how much owed using printf and specify 2 decimal places

        if (owedBal > creditLimit) System.out.printf("Credit Limit Exceeded by $%.2f", overLimitBy); // if credit limit exceeded, printf and show by how much
        else if (owedBal < creditLimit) System.out.printf("Available Credit: $%.2f", availCredit); // if under limit, printf and show how much credit left
        else System.out.print("Warning: You are at your credit limit."); // if not over or under then it must be equal to credit limit so print warning about being at limit
    }
}
