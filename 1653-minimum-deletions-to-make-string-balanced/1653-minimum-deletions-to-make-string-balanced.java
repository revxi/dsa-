class Solution {
    public int minimumDeletions(String s) {
        int deletions = 0;
        int bCount = 0;
        
        // Convert to char array for faster access than s.charAt(i)
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                // Keep track of 'b's encountered so far
                bCount++;
            } else {
                // If we hit an 'a', we choose the minimum between:
                // 1. Deleting this current 'a' (deletions + 1)
                // 2. Deleting all 'b's seen so far (bCount)
                deletions = Math.min(deletions + 1, bCount);
            }
        }
        
        return deletions;
    }
}