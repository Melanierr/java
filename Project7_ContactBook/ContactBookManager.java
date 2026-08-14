import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ContactBookManager {
    private HashMap<String, Contact> contactBook; // number, contact object

    public ContactBookManager(){
        contactBook = new HashMap<>();
    }

    // contact handler
    public void viewContacts(){
        for(Contact contact: contactBook.values()){
            System.out.println(contact);
        }
    }
    public Contact searchContactNumber(String number){
        return contactBook.get(number);
    }
    public Contact searchContactName(String name){
        for(Contact contact : contactBook.values()){
            if(contact.getName().equalsIgnoreCase(name)){
                return contact;
            }
        }
        return null;
    }
    public void addContact(String name, String email, String number){ // so what if one person has many phone number?
        boolean found = searchContactNumber(number) != null; // experimenting new check condition sees if it works
        if(found){
            System.out.println("This number already exists.");
            return;
        }
        Contact newContact = new Contact(name, email, number);
        contactBook.put(number, newContact);
        System.out.println("New contact has been added.");
    }
    public void deleteContact(String number){
        Contact removed = contactBook.remove(number);
        if(removed == null){
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Deleted successfully.");

    }
    public void updateContact(Contact editContact, String...updateInfo){
        String old_number = editContact.getNumber(); // get old number

        editContact.setNewName(updateInfo[0]); // update the new info
        editContact.setNewEmail(updateInfo[1]);
        editContact.setNewNumber(updateInfo[2]);

        contactBook.remove(old_number); // remove the old hash key
        contactBook.put(editContact.getNumber(), editContact); // make a new one
    }
    //sorting
    public ArrayList<Contact> viewSortedList(){
        ArrayList<Contact> contacts = new ArrayList<>(contactBook.values());
        contacts.sort(Comparator.comparing(Contact::getName));
        return contacts;
    }
    // save
    public HashMap<String, Contact> getContactBook(){
        return contactBook;
    }
    // load
    public void setContactBook(HashMap<String, Contact> contactBook){
        this.contactBook = contactBook;
    }


}
