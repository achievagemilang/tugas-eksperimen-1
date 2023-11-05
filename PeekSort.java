
// Reference: Nearly-Optimal Mergesorts: Fast, Practical Sorting Methods That Optimally Adapt to Existing Runs by J. Ian Munro and Sebastian Wild
// Paper doi: https://drops.dagstuhl.de/opus/volltexte/2018/9526/pdf/LIPIcs-ESA-2018-63.pdf
// Github link: https://github.com/sebawild/nearly-optimal-mergesort-code/tree/paper
public class PeekSort {

    public static void peeksort(int[] a, int l, int r) {
        int n = r - l + 1;
        peeksort(a, l, r, l, r, new int[n]);
    }

    public static void peeksort(int[] A, int left, int right, int leftRunEnd, int rightRunStart, int[] B) {
        if (leftRunEnd == right || rightRunStart == left)
            return;

        int mid = left + (right - left) / 2;

        if (mid <= leftRunEnd) {
            peeksort(A, leftRunEnd + 1, right, leftRunEnd + 1, rightRunStart, B);
            merge(A, left, leftRunEnd + 1, right, B);
        } else if (mid >= rightRunStart) {
            peeksort(A, left, rightRunStart - 1, leftRunEnd, rightRunStart - 1, B);
            merge(A, left, rightRunStart, right, B);
        } else {
            int i, j;
            i = extendRunLeft(A, mid, leftRunEnd);
            j = extendRunRight(A, mid, rightRunStart, i);

            if (i == left && j == right)
                return;

            if (mid - i < j - mid) {
                peeksort(A, left, i - 1, leftRunEnd, i - 1, B);
                peeksort(A, i, right, j, rightRunStart, B);
                merge(A, left, i, right, B);
            } else {
                peeksort(A, left, j, leftRunEnd, i, B);
                peeksort(A, j + 1, right, j + 1, rightRunStart, B);
                merge(A, left, j + 1, right, B);
            }
        }
    }

    private static int extendRunRight(int[] A, int mid, int rightRunStart, int i) {
        int j;
        if (A[mid] <= A[mid + 1]) {
            j = mid + 1 == rightRunStart ? mid
                    : extendWeaklyIncreasingRunRight(A, mid + 1, rightRunStart - 1);
        } else {
            j = mid + 1 == rightRunStart ? mid
                    : extendStrictlyDecreasingRunRight(A, mid + 1, rightRunStart - 1);
            reverseRange(A, i, j);
        }
        return j;

    }

    private static int extendRunLeft(int[] A, int mid, int leftRunEnd) {
        int i;
        if (A[mid] <= A[mid + 1]) {
            i = extendWeaklyIncreasingRunLeft(A, mid, leftRunEnd + 1);
        } else {
            i = extendStrictlyDecreasingRunLeft(A, mid, leftRunEnd + 1);
        }

        return i;
    }

    public static void merge(int[] A, int l, int m, int r, int[] B) {
        --m;
        int i, j;

        for (i = m + 1; i > l; --i)
            B[i - 1] = A[i - 1];
        for (j = m; j < r; ++j)
            B[r + m - j] = A[j + 1];
        for (int k = l; k <= r; ++k)
            A[k] = B[j] < B[i] ? B[j--] : B[i++];
    }

    public static void reverseRange(int[] a, int lo, int hi) {
        while (lo < hi) {
            int t = a[lo];
            a[lo++] = a[hi];
            a[hi--] = t;
        }
    }

    public static int extendWeaklyIncreasingRunLeft(final int[] A, int i, final int left) {
        while (i > left && A[i - 1] <= A[i])
            --i;
        return i;
    }

    public static int extendWeaklyIncreasingRunRight(final int[] A, int i, final int right) {
        while (i < right && A[i + 1] >= A[i])
            ++i;
        return i;
    }

    public static int extendStrictlyDecreasingRunLeft(final int[] A, int i, final int left) {
        while (i > left && A[i - 1] > A[i])
            --i;
        return i;
    }

    public static int extendStrictlyDecreasingRunRight(final int[] A, int i, final int right) {
        while (i < right && A[i + 1] < A[i])
            ++i;
        return i;
    }

}
