public class Mat3x3 {
    float[][] e = new float[3][3];

    public Mat3x3() {

    }

    public Mat3x3(float[][] elements) {
        this.e = elements;
    }

    public float det() {
        return (
            (e[0][0] * e[1][1] * e[2][2]) + 
            (e[0][1] * e[1][2] * e[2][0]) + 
            (e[0][2] * e[1][0] * e[2][1]) - 
            (e[2][0] * e[1][1] * e[0][2]) - 
            (e[2][1] * e[1][2] * e[0][0]) - 
            (e[2][2] * e[1][0] * e[0][1])
        );
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

    public Mat3x3 mod(int m) {
        Mat3x3 out = new Mat3x3();

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

    public Mat3x1 mult(Mat3x1 mat) {
        Mat3x1 out = new Mat3x1();

        out.e[0][0] = e[0][0] * mat.e[0][0] + e[0][1] * mat.e[1][0] + e[0][2] * mat.e[2][0];
        out.e[1][0] = e[1][0] * mat.e[0][0] + e[1][1] * mat.e[1][0] + e[1][2] * mat.e[2][0];
        out.e[2][0] = e[2][0] * mat.e[0][0] + e[2][1] * mat.e[1][0] + e[2][2] * mat.e[2][0];

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

    public Mat3x3 modInverse() {
        // Transpose the Matrix
        float det = det();
        float mod = det % Cipher.cipherText.length;

        while (mod < 0) {
            mod += Cipher.cipherText.length;
        }

        float invMod = Utils.modInv((int) mod, Cipher.cipherText.length);

        // Make Adjucate Matrix

        float a00 = new Mat2x2(new float[][] {
            {e[1][1], e[1][2]},
            {e[2][1], e[2][2]}
        }).det();

        float a01 = new Mat2x2(new float[][] {
            {e[0][1], e[0][2]}, 
            {e[2][1], e[2][2]}
        }).det();

        float a02 = new Mat2x2(new float[][] {
            {e[0][1], e[0][2]},
            {e[1][1], e[1][2]}
        }).det();



        float a10 = new Mat2x2(new float[][] {
            {e[1][0], e[1][2]}, 
            {e[2][0], e[2][2]}
        }).det();

        float a11 = new Mat2x2(new float[][] {
            {e[0][0], e[0][2]}, 
            {e[2][0], e[2][2]}
        }).det();

        float a12 = new Mat2x2(new float[][] {
            {e[0][0], e[0][2]}, 
            {e[1][0], e[1][2]}
        }).det();



        float a20 = new Mat2x2(new float[][] {
            {e[1][0], e[1][1]}, 
            {e[2][0], e[2][1]}
        }).det();

        float a21 = new Mat2x2(new float[][] {
            {e[0][0], e[0][1]}, 
            {e[2][0], e[2][1]}
        }).det();

        float a22 = new Mat2x2(new float[][] {
            {e[0][0], e[0][1]}, 
            {e[1][0], e[1][1]}
        }).det();

        // Make Adjugate
        Mat3x3 adjugate = new Mat3x3(new float[][] {
            {a00, -a01, a02},
            {-a10, a11, -a12},
            {a20, -a21, a22}
        });

        
        Mat3x3 modded = adjugate.mod(Cipher.cipherText.length);
        Mat3x3 inverse = modded.scalar(invMod);

        // Divide by det

        return inverse.mod(Cipher.cipherText.length);
    }
}
