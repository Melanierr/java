import java.time.LocalDate;

public class Contact {
    private String contactName;
    private String contactEmail;
    private String contactNumber;
    private LocalDate contactCreatedDate;

    public Contact(String contactName, String contactEmail, String contactNumber) {
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.contactCreatedDate = LocalDate.now();
    }
    Contact(String contactName, String contactEmail, String contactNumber, LocalDate contactCreatedDate) {
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.contactCreatedDate = contactCreatedDate;
    }

    public String getName(){
        return contactName;
    }
    public String getEmail(){
        return  contactEmail;
    }
    public String getNumber(){
        return contactNumber;
    }
    public void setNewName(String name){
        this.contactName = name;
    }
    public void setNewEmail(String email){
        this.contactEmail = email;
    }
    public void setNewNumber(String number){
        this.contactNumber = number;
    }
    @Override
    public String toString(){
        return String.format("Name: %s\nEmail: %s\nPhone number: %s", getName(), getEmail(), getNumber());
    }

    public String toSave(){
        return contactName + "," + contactEmail + "," + contactNumber + "," + contactCreatedDate;
    }
}
