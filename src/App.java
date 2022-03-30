public class App {

    public static boolean encrypt = false;
    public static Key key = new Key("IHATETHIS");
    public static String message = "DOYOULOVEFOOD";
    public static String encrypted = "SUPLFKFUJESTQTT";

    public static void main(String[] args) throws Exception {
        if (encrypt) {
            System.out.println("Welcome to Encrypter!"  + "\n");

            System.out.println("Creating Encryption Key..." );

            if (key == null) {
                key = Key.generateKey();
            }

            System.out.println("\n" + "Key Generated: " + key.matrixToString() + "\n");
            System.out.println("Key Info: " + key.matrix.det() % 26);
    
            System.out.println("Message: " + message + "\n");
            System.out.println("Encrypting Message...");
    
            encrypted = Encrypter.encrypt(message, key);
    
            System.out.println("\n" + "Message Encrypted: " + encrypted + "\n\n");
        } else {
            System.out.println("Welcome to Decrypter!"  + "\n");
            System.out.println("Key: " + key.matrixToString());
            System.out.println("Encrypted Message: " + encrypted + "\n");
    
            System.out.println("Decrypting Message...");
    
            String decrypted = Encrypter.decrypt(encrypted, key);
            System.out.println("Message Decrypted: " + decrypted + "\n");
        }
        

    }
}
