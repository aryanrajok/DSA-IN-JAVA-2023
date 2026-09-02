class Solution {
    public boolean uniformArray(int[] nums1) {
        int oddCount = 0;
        for (int x : nums1) if ((x & 1) == 1) oddCount++;
        
        boolean evenWorks = (oddCount != 1);
        boolean oddWorks  = (oddCount != 0);
        
        return evenWorks || oddWorks; // always true, but derived explicitly
    }
}