public class Encrypter {
    public static String encrypt(String message, Key key) {
        Mat3x1[] splits = split(message);

        String out = "";

        for (Mat3x1 mat : splits) {
            Mat3x1 keyed = key.matrix.mult(mat);
            Mat3x1 modded = keyed.mod(Cipher.cipherText.length);

            out += modded.toCipherString();
        }

        return out;
    }

    private static Mat3x1[] split(String message) {
        int r = (3 - (message.length() % 3));
        if (r != 3) {
            message += "X".repeat(r);
        }

        Mat3x1[] out = new Mat3x1[message.length() / 3];

        for (int mdx = 0; mdx < out.length; mdx++) {
            out[mdx] = new Mat3x1(
                new float[][] {
                    {Cipher.find(message.charAt(mdx * 3))},
                    {Cipher.find(message.charAt(mdx * 3 + 1))},
                    {Cipher.find(message.charAt(mdx * 3 + 2))}
                }
            );
        }

        return out;
    } 

    public static String decrypt(String encrypted, Key key) {
        Mat3x1[] splits = split(encrypted);

        Mat3x3 inverse = key.matrix.modInverse();

        Mat3x3 moddedInverse = inverse.mod(Cipher.cipherText.length);

        String out = "";

        for (Mat3x1 mat : splits) {
            Mat3x1 unkeyed = moddedInverse.mult(mat);
            Mat3x1 modded = unkeyed.mod(Cipher.cipherText.length);

            out += modded.toCipherString();
        }

        return out;
    }
}
