import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] A = new int[] { 2, 5, 8, 4, 3, 10, 12, 13, 11, 6, 7, 1, 9 };
        System.out.println(Arrays.toString(A));
        PeekSort.peeksort(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));

        int[] sizes = { 1000, 10000, 100000 };
        String[] statuses = { "sorted", "random", "reversed" };

        System.out.println("------------------RADIX--------------------\n");
        for (int size : sizes) {
            for (String status : statuses) {
                int[] data = DataManipulation.generateData(size, status);

                System.out.print("RADIX - SIZES " + size + " - ");
                System.out.println(status.toUpperCase());

                long[] measureRadix = DataManipulation.measureRadixData(data);
                System.out.println("Time = " + measureRadix[0] + " ms");
                System.out.println("Memory used for sorting: " + measureRadix[1] + " bytes");

                System.out.println();
            }
            System.out.println("------------------------------------------");
        }

        System.out.println("------------------PEEK--------------------\n");
        for (int size : sizes) {
            for (String status : statuses) {
                int[] data = DataManipulation.generateData(size, status);

                System.out.print("PEEK - SIZES " + size + " - ");
                System.out.println(status.toUpperCase());

                long[] measurePeek = DataManipulation.measurePeekData(data);
                System.out.println("Time = " + measurePeek[0] + " ms");
                System.out.println("Memory used for sorting: " + measurePeek[1] + " bytes");

                System.out.println();
            }
            System.out.println("------------------------------------------");
        }
        System.out.println();
    }

}
