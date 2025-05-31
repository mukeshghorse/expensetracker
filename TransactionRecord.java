import java.time.LocalDate;

public class TransactionRecord {
    private String transactionType;
    private String category;
    private double amount;
    private LocalDate transactionDate;

    public TransactionRecord(String transactionType, String category, Double amount, LocalDate transactionDate) {
        this.transactionType = transactionType;
        this.category = category;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "transactionType='" + transactionType + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
