import java.util.*;
import java.time.LocalTime;//for current time
import java.time.LocalDate;//for current date
// create accountdelails class to store details of user and other atm details .
class Accountdetails{
    private String userid, userpin, username;//variables to hold private details
    private double balance;//variable balance
    private List<String> history;//to store transaction history

    // Constructor will initialize the user details and other transaction history which will pass in object
    public Accountdetails(String accountid, String pin, String accountuserName, double balance) {
        this.userid = accountid;//this is used for as
        this.userpin = pin;
        this.username = accountuserName;
        this.balance = balance;
        this.history = new ArrayList<>();
    }
    public String getUserid() {//get methods are used to access private details
        return userid;
    }
    public String getUserpin() {
        return userpin;
    }
    public String getUsername() {
        return username;
    }

    // WITHDRAW
    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlease enter the amount to withdraw from your account: ");
        double amount = sc.nextDouble();

        if (amount<=balance && amount>0  ) {
            balance = balance-amount;
            history.add("Withdraw: " + amount);//add is used to store transaction in transaction history
            System.out.println("\nTime: "+LocalTime.now()+"\t Date: "+LocalDate.now()+"\nWithdrawal success!.\nCurrent balance:Rs " + balance);
        } else {
            System.out.println("wrong amount!. Please check account balance & try again!.");
        }
    }

    // DEPOSIT
    public void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n Please enter amount to deposit to your account: ");
        double amount = sc.nextDouble();

        if (amount > 0) {
            balance = balance+amount;
            history.add("Deposit: " + amount);//to add the transaction in transaction history
            System.out.println("\nTime: "+LocalTime.now()+"\t Date: "+LocalDate.now()+"\nDeposit success!.\n Current balance:Rs. " + balance);
        } else {
            System.out.println("\nWrong amount. Please try again.");
        }
    }

    // to TRANSFER amount from users acount to other account and  to store the transaction history.
    public void transfer(Accountdetails[] accounts) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPlease enter recipient account id: ");
        String recipientAccountNumber = scanner.nextLine();
        System.out.print("Please enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount<=balance && amount>0) {
            balance = balance-amount;
            System.out.println("\nRs. "+amount+" transferred to account number "+recipientAccountNumber + " from " + userid);
            System.out.println("\nTime: "+LocalTime.now()+"\t Date: "+LocalDate.now()+"Transfer successful. \nCurrent balance:Rs." + balance);
        } else {
            System.out.println("\nwrong transfer. Please check account balance & try again.");
        }
    }

    // TRANSACTION HISTORY.
    public void seeHistory() {
        System.out.println("\nTime: "+LocalTime.now()+"\t Date: "+LocalDate.now()+"\nTransaction history:");
        for (String transaction : history) {
            System.out.println(transaction);//transaction history printed
        }
    }
}

public class Atmmain{// create a ATMMAIN class to display atm interface.
        private static final int Maxuseracc = 5;//maximum no. of acc user can create is 5
        private static Accountdetails[] accounts = new Accountdetails[Maxuseracc];
        private static int no_acc = 0;

        // for creating new account with user details
        public static void createAccount() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nPlease enter the account id: ");
            String accountid = scanner.nextLine();
            System.out.print("Please enter the account Pin: ");
            String pin = scanner.nextLine();
            System.out.print("Please enter account user name: ");
            String accountusername = scanner.nextLine();
            System.out.print("Please enter initial balance: ");
            double balance = scanner.nextDouble();

            if (no_acc < Maxuseracc) {
                accounts[no_acc] = new Accountdetails(accountid, pin, accountusername, balance);
                no_acc++;//increment the count of no.of accounts
                System.out.println("\nTime: "+LocalTime.now()+"\t Date: "+LocalDate.now()+"\nNew account creation success!.");
            } else {
                System.out.println("\nAccount creation limit reached.Cannot create new account.");
            }
        }

        // checking user id and pin while login
        public static Accountdetails userlogin() {
            Scanner sc = new Scanner(System.in);
            System.out.print("\nPlease enter the account id: ");
            String accountid = sc.nextLine();
            System.out.print("Please enter the account Pin: ");
            String pin = sc.nextLine();

            for (int i=0; i<no_acc; i++) {
                if (accounts[i].getUserid().equals(accountid) && accounts[i].getUserpin().equals(pin)) {
                    System.out.println("\nAuthentication success!!.");
                    return accounts[i];
                }
            }

            System.out.println("\nwrong accountid or PIN. Please try again.");
            return null;
        }

        // atm transaction menue
        public static void Menu(Accountdetails account) {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n-------------------------------------------------------------\nAccount Menu: " + account.getUsername());
                System.out.println("1. Withdraw \n2.Deposit \n3.Transfer\n4.Transaction history\n5.logout");
                System.out.print("Please enter your choice: ");
                int c = sc.nextInt();

                switch (c) {
                    case 1:
                        account.withdraw();
                        break;
                    case 2:
                        account.deposit();
                        break;
                    case 3:
                        account.transfer(accounts);
                        break;
                    case 4:
                        account.seeHistory();
                        break;
                    case 5:
                        System.out.println("logout");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again(1 to 5)");
                        break;
                }
            }
        }

        //
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            Accountdetails usercurrentAccount = null;

            while (true) {
                System.out.println("\n-------------------------------------------------------------");
                System.out.println("Welcome to the ATM!!.\n1.Create account\n2.login\n3.ATM Exit");
                System.out.print("Please enter your choice: ");
                int c = sc.nextInt();

                switch (c) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        usercurrentAccount = userlogin();
                        if (usercurrentAccount != null) {
                            Menu(usercurrentAccount);
                        }
                        break;
                    case 3:
                        System.out.println("\n-----ATM EXIT-----");
                        return;
                    default:
                        System.out.println("\nInvalid choice. Please try again(between 1 to 3.");
                        break;
                }
            }//while loop close
        }
    }//atmmain class close