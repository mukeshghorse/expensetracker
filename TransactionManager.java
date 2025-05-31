import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<TransactionRecord> recordList = new ArrayList<>();

    public void addTransaction(TransactionRecord record) {
        recordList.add(record);
    }

    public void loadFromFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String type = parts[0];
                String category = parts[1];
                double amt = Double.parseDouble(parts[2]);
                LocalDate date = LocalDate.parse(parts[3]);
                TransactionRecord tr = new TransactionRecord(type, category, amt, date);
                recordList.add(tr);
            }
        }
        br.close();
    }

    public void saveToFile(String path) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write("Type,Category,Amount,Date\n");
        for (TransactionRecord tr : recordList) {
            bw.write(tr.toString() + "\n");
        }
        bw.close();
    }

    public void showMonthlySummary(int year, Month month) {
        double totalIncome = 0;
        double totalExpense = 0;

        System.out.println("\n--- Summary for " + month + " " + year + " ---");
        for (TransactionRecord tr : recordList) {
            if (tr.getTransactionDate().getYear() == year &&
                    tr.getTransactionDate().getMonth() == month) {

                if (tr.getTransactionType().equalsIgnoreCase("Income")) {
                    totalIncome += tr.getAmount();
                } else if (tr.getTransactionType().equalsIgnoreCase("Expense")) {
                    totalExpense += tr.getAmount();
                }

                System.out.println(tr.getTransactionType() + " | " +
                        tr.getCategory() + " | " +
                        tr.getAmount() + " | " +
                        tr.getTransactionDate());
            }
        }

        System.out.println("Total Income  : " + totalIncome);
        System.out.println("Total Expense : " + totalExpense);
        System.out.println("Savings       : " + (totalIncome - totalExpense));
    }
    public double getTotalByType(String type) {
        return recordList.stream()
                .filter(tr -> tr.getTransactionType().equalsIgnoreCase(type))
                .mapToDouble(TransactionRecord::getAmount)
                .sum();
    }

}
