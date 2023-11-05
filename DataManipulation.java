
import java.util.Random;

class DataManipulation {
    public static int[] generateData(int size, String status) {
        int[] data = new int[size];
        Random rand = new Random();

        switch (status) {
            case "sorted":
                for (int i = 0; i < size; i++) {
                    data[i] = i + 1;
                }
                break;
            case "random":
                for (int i = 0; i < size; i++) {
                    data[i] = rand.nextInt(size) + 1;
                }
                break;
            case "reversed":
                for (int i = 0; i < size; i++) {
                    data[i] = size - i;
                }
                break;
        }

        return data;
    }

    public static long[] measureRadixData(int[] data) {
        long[] res = new long[5];

        long startTime = System.currentTimeMillis();
        long beforeUsedMem = memoryUsage();

        RadixSort.radixSort(data, data.length);

        long afterUsedMem = memoryUsage();
        long endTime = System.currentTimeMillis();

        res[0] = endTime - startTime;
        res[1] = Math.abs(afterUsedMem - beforeUsedMem);

        return res;

    }

    public static long[] measurePeekData(int[] data) {
        long[] res = new long[5];

        long startTime = System.currentTimeMillis();
        long beforeUsedMem = memoryUsage();

        PeekSort.peeksort(data, 0, data.length - 1);

        long afterUsedMem = memoryUsage();
        long endTime = System.currentTimeMillis();

        res[0] = endTime - startTime;
        res[1] = Math.abs(afterUsedMem - beforeUsedMem);

        return res;

    }

    private static long memoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

}