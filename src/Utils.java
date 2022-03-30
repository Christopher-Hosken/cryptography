public class Utils {
    public static boolean isPrime(int n) {
        for (int i = 2; i <= n / 2f; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static int modInv(int a, int m) {
        for (int x = 1; x < m; x++)
        if (m % x != 0) {
            if (((a%m) * (x%m)) % m == 1)
            return x;
        }
        return 0;
    }
}
