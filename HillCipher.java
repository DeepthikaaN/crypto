import java.util.Scanner;

public class HillCipher {

    // Encrypt using 2x2 Hill Cipher
    public static String encrypt(String text, int[][] key) {

        text = text.toUpperCase().replaceAll("[^A-Z]", "");

        // If odd length, add X
        if (text.length() % 2 != 0) {
            text += "X";
        }

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {

            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';

            int c1 = (key[0][0] * a + key[0][1] * b) % 26;
            int c2 = (key[1][0] * a + key[1][1] * b) % 26;

            cipher.append((char) (c1 + 'A'));
            cipher.append((char) (c2 + 'A'));
        }

        return cipher.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        // Example key matrix
        int[][] key = {
                {3, 3},
                {2, 5}
        };

        String encrypted = encrypt(plaintext, key);

        System.out.println("Encrypted text: " + encrypted);

        sc.close();
    }
}
