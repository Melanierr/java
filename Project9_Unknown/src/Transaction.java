import java.math.BigDecimal;

class Transaction {
    private String description;
    private BigDecimal amount;
    private String type;
    private String category;

    public Transaction(String description, BigDecimal amount,
                       String type, String category) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return description + " | " + amount + " | "
                + type + " | " + category;
    }
}