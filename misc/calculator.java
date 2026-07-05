package randomitem;
import java.util.Scanner;

public class calculator{
    public static void main(String[] args){
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n=== CALCULATOR ===");
            System.out.println("=== MODE ===");
            System.out.print("Select a calculating mode:\n 1.Area\n 2.Volume\n 3.Temp\n 4.Basic\n 5.Exit\n");
            String mode = scanner.nextLine();

            switch (mode.toLowerCase()) {
                case "1" -> {
                    double length;
                    double width;
                    System.out.print("Enter length: ");
                    length = scanner.nextDouble();
                    System.out.print("Enter width: ");
                    width = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.printf("%.2f", findArea(length, width));
                }
                case "2" -> {
                    double radius;
                    double height;
                    System.out.print("Enter radius: ");
                    radius = scanner.nextDouble();
                    System.out.print("Enter height: ");
                    height = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.printf("%.2f", findVolumeOfCylinder(radius, height));
                }
                case "3" -> {
                    String tempMode;
                    double temp;
                    System.out.println("=== TEMPERATURE CONVERSION ===");
                    System.out.println("1. C° -> F° \n2. C° -> K° \n3. F° -> K°");
                    tempMode = scanner.nextLine();
                    switch (tempMode) {
                        case "1" -> {
                            System.out.print("Enter temperature (C°): ");
                            temp = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.printf("%.2fF°", celsiusToFahrenheit(temp));
                        }
                        case "2" -> {
                            System.out.print("Enter temperature (C°): ");
                            temp = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.printf("%.2fK°", temp + 273);
                        }
                        case "3" -> {
                            System.out.print("Enter temperature (F°): ");
                            temp = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.printf("%.2fK°", fahrenheitToKelvin(temp));
                        }
                    }
                }
                case "4" -> {
                    double number1;
                    double number2;
                    double result = 0;
                    String operator;
                    System.out.println("First number: ");
                    number1 = scanner.nextDouble();
                    System.out.println("Second number: ");
                    number2 = scanner.nextDouble();
                    System.out.println("Operator (+, -, /, *, ^): ");
                    operator = scanner.next();
                    scanner.nextLine();
                    switch (operator) { // this can be re-written into using methods but too long, perfer optimizations
                        case "+" -> result = number1 + number2;
                        case "-" -> result = number1 - number2;
                        case "/" -> {
                            if (number2 == 0) {
                                System.out.println("Cannot divide by zero");
                            }
                            else {
                                result = number1 / number2;
                            }
                        }
                        case "*" -> result = number1 * number2;
                        case "^" -> result = Math.pow(number1, number2);
                    }
                    System.out.println(result);
                }
                case "5" -> {isExit = true;}
                default -> {System.out.println("Calculator does not support " + mode);}
            }
        }while(!isExit);
        scanner.close();
    }
    static double findVolumeOfCylinder(double radius, double height){
            double area = Math.PI * (radius * radius);
            return area*height;
        }
        static double findArea(double value1, double value2){
            return value1*value2;
        }
    static double celsiusToFahrenheit(double temp){
        return temp * ( (double) 9 / 5) + 32;
    }
    static double fahrenheitToKelvin(double temp){
        return (temp - 32) * ((double) 5/9)  + 273;
    }
}
