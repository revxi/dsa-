class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        // We need at least 4 elements to satisfy 0 < p < q < n-1
        // Indices involved: 0 ... p ... q ... n-1
        if (n < 4) return false;

        int i = 0;

        // Phase 1: Strictly Increasing (0 to p)
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // Check: Must have moved from start, but not reached the end yet
        if (i == 0 || i == n - 1) return false;

        // Phase 2: Strictly Decreasing (p to q)
        int p = i;
        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }

        // Check: Must have moved from p, but not reached the end yet
        if (i == p || i == n - 1) return false;

        // Phase 3: Strictly Increasing (q to n-1)
        int q = i;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // Final Check: Did we reach exactly the end of the array?
        return i == n - 1;
    }
}