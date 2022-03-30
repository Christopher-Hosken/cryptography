public class Mat3x1 {
    float[][] e = new float[3][1];

    public Mat3x1() {}

    public Mat3x1(float[][] elements) {
        this.e = elements;
    }

    public Mat3x3 scalar(float f) {
        Mat3x3 out = new Mat3x3();

        for (int cdx = 0; cdx < e.length; cdx++) {
            for (int rdx = 0; rdx < e[0].length; rdx++) {
                out.e[cdx][rdx] = e[cdx][rdx] * f;
            }
        }

        return out;
    }

    public Mat3x1 mod(int m) {
        Mat3x1 out = new Mat3x1();

        for (int cdx = 0; cdx < e.length; cdx++) {
            for (int rdx = 0; rdx < e[0].length; rdx++) {
                float mod = e[cdx][rdx] % m;
                while (mod < 0) {
                    mod += m;
                } 
                out.e[cdx][rdx] = mod;
            }
        }

        return out;
    }

    public String toString() {
        String out = "";
        for (int cdx = 0; cdx < e.length; cdx++) {
            for (int rdx = 0; rdx < e[0].length; rdx++) {
                out += e[cdx][rdx] + " ";
            }
            out += "\n";
        }

        return out;
    }
    public String toCipherString() {
        String out = "";
        for (int cdx = 0; cdx < e.length; cdx++) {
            for (int rdx = 0; rdx < e[0].length; rdx++) {
                out += Cipher.at((int) e[cdx][rdx]);
            }
        }

        return out;
    }
}
