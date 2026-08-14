import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Saver {
    public Saver(){}


    public void save(HashMap<String, Contact> contactBook){
        try(FileWriter fw = new FileWriter("savefile.txt");) {
            for(Contact contact : contactBook.values()){
                fw.write(contact.toSave()+"\n");
            }
        }
        catch(IOException error){
            System.out.println("Error while saving file.");
        }
    }
    public HashMap<String, Contact> load(){
        HashMap<String, Contact> contactBook = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("savefile.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                try {
                    String[] data = line.split(",");
                    if (data.length != 4) {
                        System.out.println("Invalid save format, skipping to the next one..");
                        continue;
                    }
                    String name = data[0];
                    String email = data[1];
                    String number = data[2];
                    LocalDate date = LocalDate.parse(data[3]);
                    Contact contact = new Contact(name, email, number, date);
                    contactBook.put(number, contact);
                }
                catch(DateTimeException error){
                    System.out.println("Error date format.");
                }
            }
        }

        catch(FileNotFoundException error){
            System.out.println("Cannot locate file");
        }
        catch(IOException error){
            System.out.println("Failed to read file.");
        }
        return contactBook;
    }
}
