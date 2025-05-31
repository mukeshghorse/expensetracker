import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class ExpenseTrackerMain {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();

        while (true) {
            System.out.println("\n===== Expense Tracker =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Load from File");
            System.out.println("4. Save to File");
            System.out.println("5. View Monthly Summary");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = inputScanner.nextInt();
            inputScanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Select Income Category:");
                    System.out.println("1. Salary");
                    System.out.println("2. Business");
                    System.out.println("3. Freelance");
                    System.out.println("4. Investment");
                    System.out.print("Enter option: ");
                    int incomeChoice = inputScanner.nextInt();
                    inputScanner.nextLine();

                    String incomeCategory = switch (incomeChoice) {
                        case 1 -> "Salary";
                        case 2 -> "Business";
                        case 3 -> "Freelance";
                        case 4 -> "Investment";
                        default -> {
                            System.out.println("Invalid option selected. Defaulting to 'Other'");
                            yield "Other";
                        }
                    };

                    System.out.print("Enter Amount: ");
                    double incomeAmount = inputScanner.nextDouble();
                    inputScanner.nextLine();

                    System.out.print("Enter Date (yyyy-mm-dd): ");
                    String incomeDateStr = inputScanner.nextLine();

                    try {
                        LocalDate incomeDate = LocalDate.parse(incomeDateStr);
                        TransactionRecord income = new TransactionRecord("Income", incomeCategory, incomeAmount, incomeDate);
                        manager.addTransaction(income);
                        System.out.println("Income added successfully.");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Transaction not added.");
                    }
                }

                case 2 -> {
                    System.out.println("Select Expense Category:");
                    System.out.println("1. Food");
                    System.out.println("2. Rent");
                    System.out.println("3. Travel");
                    System.out.println("4. Shopping");
                    System.out.println("5. Bills");
                    System.out.print("Enter option: ");
                    int expenseChoice = inputScanner.nextInt();
                    inputScanner.nextLine();

                    String expenseCategory = switch (expenseChoice) {
                        case 1 -> "Food";
                        case 2 -> "Rent";
                        case 3 -> "Travel";
                        case 4 -> "Shopping";
                        case 5 -> "Bills";
                        default -> {
                            System.out.println("Invalid category. Defaulting to 'Other'");
                            yield "Other";
                        }
                    };

                    System.out.print("Enter Amount: ");
                    double expenseAmount = inputScanner.nextDouble();
                    inputScanner.nextLine();

                    double totalIncome = manager.getTotalByType("Income");
                    double totalExpense = manager.getTotalByType("Expense");
                    double balance = totalIncome - totalExpense;

                    if (expenseAmount > balance) {
                        System.out.println("Insufficient balance.");
                        break;
                    }

                    System.out.print("Enter Date (yyyy-mm-dd): ");
                    String expenseDateStr = inputScanner.nextLine();

                    try {
                        LocalDate expenseDate = LocalDate.parse(expenseDateStr);
                        TransactionRecord expense = new TransactionRecord("Expense", expenseCategory, expenseAmount, expenseDate);
                        manager.addTransaction(expense);
                        System.out.println("Expense added successfully.");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Transaction not added.");
                    }
                }

                case 3 -> {
                    System.out.print("Enter file name to load: ");
                    String file = inputScanner.nextLine();
                    try {
                        manager.loadFromFile(file);
                        System.out.println("Data loaded from file.");
                    } catch (Exception e) {
                        System.out.println("Error loading: " + e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.print("Enter file name to save: ");
                    String file = inputScanner.nextLine();
                    try {
                        manager.saveToFile(file);
                        System.out.println("Data saved to file.");
                    } catch (Exception e) {
                        System.out.println("Error saving: " + e.getMessage());
                    }
                }

                case 5 -> {
                    System.out.print("Enter year: ");
                    int year = inputScanner.nextInt();
                    System.out.print("Enter month (1-12): ");
                    int month = inputScanner.nextInt();
                    try {
                        manager.showMonthlySummary(year, Month.of(month));
                    } catch (Exception e) {
                        System.out.println("Invalid month.");
                    }
                }

                case 6 -> {
                    System.out.println("Thanks for using Expense Tracker!");
                    inputScanner.close();
                    return;
                }

                default -> System.out.println("Invalid option. Choose listed option only.");
            }
        }
    }
}