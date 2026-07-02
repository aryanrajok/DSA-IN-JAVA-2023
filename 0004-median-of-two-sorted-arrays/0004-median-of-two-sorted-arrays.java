class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[] merged = new int[n + m];

        int i = 0, j = 0, k = 0;

        // Merge the two sorted arrays
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // Copy remaining elements of nums1
        while (i < n) {
            merged[k++] = nums1[i++];
        }

        // Copy remaining elements of nums2
        while (j < m) {
            merged[k++] = nums2[j++];
        }

        int total = n + m;

        // If total number of elements is odd
        if (total % 2 == 1) {
            return merged[total / 2];
        }

        // If total number of elements is even
        return (merged[total / 2] + merged[(total / 2) - 1]) / 2.0;
    }
}