import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private final UUID uuid;
    private Type transactionType;
    private String description;
    private final LocalDate date;
    private BigDecimal amount;
    private Category category;

    public Transaction(String description, Type type, Category category, BigDecimal amount) {
        this.description = description;
        this.date = LocalDate.now();
        this.uuid = UUID.randomUUID();
        this.transactionType = type;
        this.category = category;
        updateAmount(amount);
    }
    public Transaction(String description, UUID uuid, Type type, Category category, BigDecimal amount, LocalDate date) { // this one is to load
        this.uuid = uuid;
        this.description = description;
        this.date = date;
        this.transactionType = type;
        this.category = category;
        updateAmount(amount);
    }

    // getters
    public String getDescription() {
        return description;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public UUID getUUID() {
        return uuid;
    }
    public LocalDate getDate(){
        return date;
    }
    public Type getTransactionType() {
        return transactionType;
    }
    public Category getCategory() {
        return category;
    }
    // setters
    public void updateAmount(BigDecimal amount) {
        this.amount = amount.abs();
        if(transactionType ==  Type.EXPENSE){
            this.amount = this.amount.negate();
        }
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setType(Type type) {
        this.transactionType = type;
        updateAmount(this.amount);
    }
    // toStrings
    @Override
    public String toString(){
        return String.format("Detail: %s, Type: %s [%s], Date: %s, Money: $%s, ID: %s", getDescription(),  getTransactionType().name(), getCategory(), getDate(), getAmount(), getUUID().toString());
    }
    public String toSave(){
        return getDescription() + "," + getUUID().toString() + "," + getTransactionType().name() + "," + getCategory() + "," + getAmount().toString() + "," + getDate().toString();
    }
}
