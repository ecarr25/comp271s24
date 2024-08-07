public class Sorting271 {

    // Method to merge two sorted arrays into a single sorted array
    public static int[] merge(int[] left, int[] right) {
        int[] mergedArray = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

        // Merge elements from left and right arrays in sorted order
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                mergedArray[mergedIndex++] = left[leftIndex++];
            } else {
                mergedArray[mergedIndex++] = right[rightIndex++];
            }
        }

        // Copy remaining elements from left array, if any
        while (leftIndex < left.length) {
            mergedArray[mergedIndex++] = left[leftIndex++];
        }

        // Copy remaining elements from right array, if any
        while (rightIndex < right.length) {
            mergedArray[mergedIndex++] = right[rightIndex++];
        }

        return mergedArray;
    }

    // Auxiliary method to copy a range of an array
    public static int[] copyOfRange(int[] original, int start, int end) {
        int[] range = new int[end - start];
        for (int i = start; i < end; i++) {
            range[i - start] = original[i];
        }
        return range;
    }

    // Method to sort an array using iterative merge sort
    public static int[] sort(int[] array) {
        if (array.length <= 1) {
            return array; // An array with one element is already sorted
        }

        int n = array.length;
        int[] tempArray = new int[n];

        // Iterate over widths: 1, 2, 4, 8, ...
        for (int width = 1; width < n; width *= 2) {
            // Merge adjacent subarrays of the current width
            for (int i = 0; i < n; i += 2 * width) {
                int leftStart = i;
                int leftEnd = Math.min(i + width, n);
                int rightStart = leftEnd;
                int rightEnd = Math.min(i + 2 * width, n);

                int[] left = copyOfRange(array, leftStart, leftEnd);
                int[] right = copyOfRange(array, rightStart, rightEnd);

                int[] merged = merge(left, right);

                // Copy the merged subarray back to the original array
                for (int j = 0; j < merged.length; j++) {
                    array[leftStart + j] = merged[j];
                }
            }
        }

        return array;
    }

    // Main method for testing the merge sort implementation
    public static void main(String[] args) {
        int[] arrayToSort = {10, 8, 5, 3, 7, 6, 1, 2};
        System.out.println("Original Array: " + java.util.Arrays.toString(arrayToSort));

        int[] sortedArray = sort(arrayToSort);
        System.out.println("Sorted Array: " + java.util.Arrays.toString(sortedArray));
    }
}