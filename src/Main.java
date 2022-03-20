import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int[] array = ArrayCreate(100_000_000, 300);

        long startSingle = System.currentTimeMillis();
        singleThreadingSum(array);
        long finishSingle = System.currentTimeMillis();
        System.out.println("Время затраченное на single решение " + (finishSingle - startSingle) + " мс");

        System.out.println();

        long startMulti = System.currentTimeMillis();
        multiThreadingSum(array);
        long finishMulti = System.currentTimeMillis();
        System.out.println("Время затраченное на multi решение " + (finishMulti - startMulti) + " мс");
    }

    public static int[] ArrayCreate(int length, int max) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max);
        }
        return array;
    }

    public static void singleThreadingSum(int[] array) {
        Calculator calculator = new Calculator(array);
        System.out.println("*** Решение задачи в одном потоке ***");
        System.out.println("Сумма элементов массива");
        int sum = calculator.calcSum(array);
        System.out.println(sum);
        System.out.println("Среднее арифметическое элементов массива");
        System.out.println(calculator.arithmeticMean(sum, array));
    }

    public static void multiThreadingSum(int[] array) {
        SumCounter sumCounter = new SumCounter(array);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int sum = forkJoinPool.invoke(sumCounter);
        System.out.println("*** Решение задачи многопоточно ***");
        System.out.println("Сумма элементов массива");
        System.out.println(sum);
        System.out.println("Среднее арифметическое элементов массива");
        System.out.println(sum / array.length);
        forkJoinPool.shutdown();
    }
}
