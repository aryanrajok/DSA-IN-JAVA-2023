class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (hoursNeeded(piles, mid) <= h) {
                right = mid - 1;   // speed valid, chhota try karo
            } else {
                left = mid + 1;    // speed slow, badhao
            }
        }
        
        return left;
    }
    
    private long hoursNeeded(int[] piles, int speed) {
        long hours = 0;
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed;  // ceil division
        }
        return hours;
    }
}