import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private final UUID uuid;
    private Type transactionType;
    private String description;
    private final LocalDate date;
    private BigDecimal amount;


    public Transaction(String description, Type type, BigDecimal amount) {
        this.description = description;
        this.date = LocalDate.now();
        this.uuid = UUID.randomUUID();
        this.transactionType = type;
        if (transactionType == Type.INCOME) {
            this.amount = amount;
        }
        else if (transactionType == Type.EXPENSE) {
            this.amount = amount.negate();
        }
    }
    public Transaction(String description, UUID uuid, Type type, BigDecimal amount, LocalDate date) {
        this.uuid = uuid;
        this.description = description;
        this.date = date;
        this.transactionType = type;
        this.amount = amount;
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

    // setters
    public void setAmount(BigDecimal amount) {
        if(transactionType.getType().equals("income")){
            this.amount = amount;
        }
        else if(transactionType.getType().equals("expense")){
            this.amount = amount.negate();
        }
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setType(Type type) {
        this.transactionType = type;
    }
    // toStrings
    @Override
    public String toString(){
        return String.format("Detail: %s, Type: %s, Date: %s%nMoney: $%s, ID: %s", getDescription(),  getTransactionType().name(), getDate(), getAmount(), getUUID().toString());
    }
    public String toSave(){
        return getDescription() + "," + getUUID().toString() + "," + getTransactionType().name() + "," + getAmount().toString() + "," + getDate().toString();
    }
}
