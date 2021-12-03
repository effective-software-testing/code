package ch5;

public class Triangle {
    public static boolean isTriangle(int a, int b, int c) {
        boolean hasABadSide = a >= (b + c) || c >= (b + a) || b >= (a + c);
        return !hasABadSide;
    }
}
