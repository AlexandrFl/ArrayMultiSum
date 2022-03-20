import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class SumCounter extends RecursiveTask<Integer> {
    private int[] array;

    public SumCounter(int[] array) {
        this.array = array;
    }

    @Override
    public Integer compute() {
        if(array.length <= 100) {
            return Arrays.stream(array).sum();
        }
        SumCounter firstHalf = new SumCounter(Arrays.copyOfRange(array, 0, array.length / 2));
        SumCounter secondHalf = new SumCounter(Arrays.copyOfRange(array, array.length / 2, array.length));
        firstHalf.fork();
        secondHalf.fork();
        return firstHalf.join() + secondHalf.join();
    }
}
