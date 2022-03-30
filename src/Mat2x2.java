public class Mat2x2 {
    float[][] e = new float[2][2];

    public Mat2x2() {}

    public Mat2x2(float[][] elements) {
        this.e = elements;
    }

    public float det() {
        return (
            (e[0][0] * e[1][1]) - (e[0][1] * e[1][0])
        );
    }

    public Mat2x2 inverse() {
        float det = det();
        Mat2x2 neg = new Mat2x2(new float[][] {
            {e[1][1], -e[0][1]},
            {-e[1][0], e[0][0]}
        });

        return neg.scalar(1f / det);
    }

    public Mat2x2 scalar(float f) {
        Mat2x2 out = new Mat2x2();

        for (int cdx = 0; cdx < e.length; cdx++) {
            for (int rdx = 0; rdx < e[0].length; rdx++) {
                out.e[cdx][rdx] = e[cdx][rdx] * f;
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
}