public class Key {
    public Mat3x3 matrix = new Mat3x3();

    public Key() {}

    public Key(Mat3x3 keyMatrix) {
        matrix = keyMatrix;
    }

    public Key(float[][] floatKeys) {
        matrix = new Mat3x3(floatKeys);
    }

    public Key(String stringKey) {
        matrix = stringToMatrix(stringKey);
    }

    public boolean isValid() {
        float det = matrix.det();

        if (det != 0) {
            det %= Cipher.cipherText.length;

            if (det != 0) {
                while (det < 0) {
                    det += Cipher.cipherText.length;
                }
    
                if (Utils.isPrime((int) det) && det != Cipher.cipherText.length / 2) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public String matrixToString() {
        String out = "";

        for (int cdx = 0; cdx < matrix.e.length; cdx++) {
            for (int rdx = 0; rdx < matrix.e[0].length; rdx++) {
                out += Cipher.at((int) matrix.e[cdx][rdx]);
            }
        }

        return out;
    }   

    public String toString() {
        float det = matrix.det();

        float mod_det = det % Cipher.cipherText.length;

            while (mod_det < 0) {
                det += Cipher.cipherText.length;
            }

        return (
            "Key: " + matrixToString() + "\n" +
            "Det: " + det + "\n" +
            "Mod: " + mod_det
        );
    }

    public static Mat3x3 stringToMatrix(String stringKey) {
        return new Mat3x3(stringToFloatKeys(stringKey));
    }


    public static float[][] stringToFloatKeys(String stringKey) {
        float[][] out = new float[3][3];

        for (int sdx = 0; sdx < stringKey.length(); sdx += 3) {
            out[sdx / 3][0] = Cipher.find(stringKey.charAt(sdx));
            out[sdx / 3][1] = Cipher.find(stringKey.charAt(sdx + 1));
            out[sdx / 3][2] = Cipher.find(stringKey.charAt(sdx + 2));
        }

        return out;
    }

    public static Key randomKey() {
        float[][] floatKey = new float[3][3];

        for (int cdx = 0; cdx < floatKey.length; cdx++) {
            for (int rdx = 0; rdx < floatKey[0].length; rdx++) {
                floatKey[cdx][rdx] = (int) (Math.random() * Cipher.cipherText.length);
            }
        }

        return new Key(floatKey);
    }

    public static Key generateKey() {
        Key key = new Key();
        int i = 1;

        while (true) {
            System.out.println("KEY GENERATION: Iteration " + i);
            key = randomKey();

            if (key.isValid()) {
                break;
            }

            i++;
        }

        return key;
    }
}
