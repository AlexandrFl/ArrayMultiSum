public class Calculator {
    public int[] array;

    public Calculator(int[] array) {
        this.array = array;
    }

    public int calcSum(int[] array) {
        int sum = 0;
        for (int x:array) {
            sum = sum + x;
        }
        return sum;
    }

    public int arithmeticMean(int sum, int[] array) {
        return sum / array.length;
    }
}
