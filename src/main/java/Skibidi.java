import java.util.Objects;
import java.util.Scanner;

public class Skibidi {
    public static String toAlternateCaps(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (i % 2 == 0) {
                // Even index, convert to uppercase
                result.append(Character.toUpperCase(c));
            } else {
                // Odd index, convert to lowercase
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
    public static void main(String[] args) {
        String name = "Skibidi";
        System.out.println("Ohio skibidi on the wall, who's the skibidiest of 'em all?!! " +
                "\nHello! I'm " + name + " the skibidiest of 'em all"+ " \nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input.trim(), "bye")) {
                System.out.println("Bye! Hope to never see you again soon!");
                break;
            } else {
                System.out.println("Here's what you sound like: " + toAlternateCaps(input));
            }
        }

        scanner.close();
    }
}
