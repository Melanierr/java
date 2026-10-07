import java.util.Scanner;

void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a number: ");
    String inputString = input.nextLine();
    try{
        int number = Integer.parseInt(inputString);
        System.out.println(number);
    }
    catch(NumberFormatException error){
        System.out.println("Invalid number!");
    }

}

static void readFile(String filePath){
    try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
        String line;
        line = reader.readLine();
    }
    catch(FileNotFoundException e){
        System.out.println("Cannot locate file");
    }
    catch(IOException error){
        System.out.println("Cannot read file");
    }
}