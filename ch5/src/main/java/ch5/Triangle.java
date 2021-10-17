package ch5;

public class Triangle {
    public static boolean isTriangle(int a, int b, int c) {
        if (a >= (b + c) || c >= (b + a) || b >= (a + c))
            return false;
        return true;
    }
}
