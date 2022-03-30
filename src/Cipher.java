public class Cipher {
    public static char[] cipherText = {
        'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X',
        'Y', 'Z'
    };

    public static char at(int n) {
        return cipherText[n % cipherText.length];
    }

    public static int find(char c) {
        for (int cdx = 0; cdx < cipherText.length; cdx++) {
            if (cipherText[cdx] == c) {
                return cdx;
            }
        }

        return -1;
    }
}

