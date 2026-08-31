class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int firstIdx = -1, lastIdx = -1, minDist = Integer.MAX_VALUE;
        int idx = 1;
        ListNode prev = head, curr = head.next;

        while (curr.next != null) {
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {
                if (firstIdx == -1) {
                    firstIdx = idx;
                } else {
                    minDist = Math.min(minDist, idx - lastIdx);
                }
                lastIdx = idx;
            }
            prev = curr;
            curr = curr.next;
            idx++;
        }

        if (firstIdx == -1 || firstIdx == lastIdx) return new int[]{-1, -1};
        return new int[]{minDist, lastIdx - firstIdx};
    }
}