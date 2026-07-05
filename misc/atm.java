import java.util.Scanner;
public class atm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 1000;

        System.out.println("==== BANK ====");
        System.out.println("MENU");
        System.out.println("1. Deposit \n2. Withdraw \n3. Check Balance \n4. Calculate Compound Interest\n");
        
        String mode = scanner.nextLine().toLowerCase().trim();

        if (mode.equals("deposit") || mode.equals("1")) {
            System.out.printf("Your current balance is: $ %.2f \n", balance);
            System.out.println("How much do you want to deposit?");
            double depositMoney = scanner.nextDouble();
            balance += depositMoney;
            System.out.printf("Successful! Your current balance is: $ %.2f\n", balance);

        } else if (mode.equals("withdraw") || mode.equals("2")) {
            System.out.printf("Your current balance is: $ %.2f \n", balance);
            System.out.println("How much do you want to withdraw?");
            double withdrawMoney = scanner.nextDouble();
            
            if (withdrawMoney > balance) {
                System.out.println("Insufficient funds!!!");
                scanner.close();
                return;
            }
            balance -= withdrawMoney;
            System.out.printf("Successful! Your current balance is: $ %.2f\n", balance);

        } else if (mode.equals("check balance") || mode.equals("3")) {
            System.out.printf("Your current balance is : $ %.2f\n", balance);

        } else if (mode.equals("calculate compound interest") || mode.equals("4")) {
            System.out.print("Enter the deposit money: ");
            double initialMoney = scanner.nextDouble();

            System.out.print("Interest rate %: ");
            double rate = scanner.nextDouble() / 100;

            System.out.print("Times compounded/year: ");
            int timesCompounded = scanner.nextInt();
            
            System.out.print("How long (years): ");
            int years = scanner.nextInt();

            double amount = initialMoney * Math.pow(1 + rate / timesCompounded, timesCompounded * years);
            System.out.printf("Total money after %d years is $ %.2f \n", years, amount);
        } else {
            System.out.println("Invalid option chosen.");
        }
        scanner.close();
    }
}
