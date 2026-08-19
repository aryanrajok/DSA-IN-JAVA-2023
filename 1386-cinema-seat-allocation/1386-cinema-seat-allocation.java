class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col < 2 || col > 9) continue; // seat 1, 10 matter nahi karte
            int bit = 1 << (col - 2);
            rowMask.put(row, rowMask.getOrDefault(row, 0) | bit);
        }
        
        final int LEFT  = 0b00001111; // seats 2,3,4,5
        final int MID   = 0b00111100; // seats 4,5,6,7
        final int RIGHT = 0b11110000; // seats 6,7,8,9
        
        // jin rows mein koi reservation nahi, unko 2-2 groups free mein mil jaate hain
        int count = 2 * (n - rowMask.size());
        
        for (int mask : rowMask.values()) {
            boolean left  = (mask & LEFT)  == 0;
            boolean mid   = (mask & MID)   == 0;
            boolean right = (mask & RIGHT) == 0;
            
            if (left && right) {
                count += 2;
            } else if (left || mid || right) {
                count += 1;
            }
        }
        
        return count;
    }
}