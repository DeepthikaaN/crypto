import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class shahash1 {

    public static String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // Convert input into bytes and calculate the 160-bit hash
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert hash bytes to hexadecimal
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b & 0xff));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not available", e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter input: ");
        String input = sc.nextLine();

        String hash = sha1(input);

        System.out.println("160-bit Hash Value:");
        System.out.println(hash);

        sc.close();
    }
}

