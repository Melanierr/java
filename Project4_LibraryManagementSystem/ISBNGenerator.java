import java.util.Random;

public class ISBNGenerator {
    private final Random rand = new Random();

    public ISBNGenerator() {}
    public String generateISBN() {
        int[] isbn = new int[13];

        isbn[0] = 9;
        isbn[1] = 7;
        isbn[2] = rand.nextBoolean() ? 8 : 9;

        for (int i = 3; i < 12; i++) {
            isbn[i] = rand.nextInt(10);
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int weight = (i % 2 == 0) ? 1 : 3;
            sum += isbn[i] * weight;
        }

        int remainder = sum % 10;
        isbn[12] = (10 - remainder) % 10;

        StringBuilder builder = new StringBuilder();
        for (int digit : isbn) {
            builder.append(digit);
        }

        return builder.toString();
    }
}