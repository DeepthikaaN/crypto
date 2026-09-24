import java.math.BigInteger;
import java.security.*;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class dss {

    public static void main(String[] args) throws Exception {

        // Message whose signature has to be verified
        String message = "Hello, this is a digitally signed message.";

        // Generate DSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(2048);

        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // ---------------- SIGNING ----------------
        Signature signer = Signature.getInstance("SHA256withDSA");
        signer.initSign(privateKey);

        signer.update(message.getBytes(StandardCharsets.UTF_8));

        byte[] digitalSignature = signer.sign();

        System.out.println("Original Message:");
        System.out.println(message);

        System.out.println("\nDigital Signature:");
        System.out.println(Base64.getEncoder().encodeToString(digitalSignature));

        // ---------------- VERIFICATION ----------------
        Signature verifier = Signature.getInstance("SHA256withDSA");
        verifier.initVerify(publicKey);

        verifier.update(message.getBytes(StandardCharsets.UTF_8));

        boolean verified = verifier.verify(digitalSignature);

        System.out.println("\nSignature Verification Result:");

        if (verified) {
            System.out.println("Signature is VALID.");
        } else {
            System.out.println("Signature is INVALID.");
        }
    }
}
