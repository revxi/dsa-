class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return -1;

        // inc1: Max sum of strictly increasing leg ending at i
        long inc1 = nums[0];
        
        // dec: Max sum of "Up -> Down" ending at i
        long dec = Long.MIN_VALUE;
        
        // tri: Max sum of "Up -> Down -> Up" ending at i
        long tri = Long.MIN_VALUE;
        
        long globalMax = Long.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            long x = (long) nums[i];
            long prev = (long) nums[i-1];

            long nextInc1 = Long.MIN_VALUE;
            long nextDec = Long.MIN_VALUE;
            long nextTri = Long.MIN_VALUE;

            if (x > prev) {
                // === SLOPE UP ===
                
                // 1. Update First Leg (inc1)
                // FIX: Apply Kadane's logic here.
                // Either extend the previous increasing sequence OR start a new one at [prev, x].
                // We compare (inc1 + x) vs (prev + x).
                if (inc1 != Long.MIN_VALUE) {
                     nextInc1 = Math.max(inc1 + x, prev + x);
                } else {
                     nextInc1 = prev + x;
                }

                // 2. Update Trionic (Third Leg)
                // Option A: Extend existing trionic
                if (tri != Long.MIN_VALUE) {
                    nextTri = Math.max(nextTri, tri + x);
                }
                
                // Option B: Transition from Valley (dec -> tri)
                if (dec != Long.MIN_VALUE) {
                    if (nextTri == Long.MIN_VALUE) {
                        nextTri = dec + x;
                    } else {
                        nextTri = Math.max(nextTri, dec + x);
                    }
                }

            } else if (x < prev) {
                // === SLOPE DOWN ===

                // 1. Reset first leg
                // Current x becomes the start of a potential new increasing leg
                nextInc1 = x;

                // 2. Update Decreasing (Second Leg)
                // Option A: Extend existing decrease
                if (dec != Long.MIN_VALUE) {
                    nextDec = dec + x;
                }
                
                // Option B: Transition from Peak (inc1 -> dec)
                // Valid only if we had an ascent before this drop (i-1 was a peak > i-2)
                if (i >= 2 && nums[i-1] > nums[i-2]) {
                    long transitionSum = inc1 + x;
                    if (nextDec == Long.MIN_VALUE) {
                        nextDec = transitionSum;
                    } else {
                        nextDec = Math.max(nextDec, transitionSum);
                    }
                }

            } else {
                // === FLAT (x == prev) ===
                // Strictly increasing/decreasing broken. Reset start.
                nextInc1 = x;
            }

            inc1 = nextInc1;
            dec = nextDec;
            tri = nextTri;

            if (tri != Long.MIN_VALUE) {
                if (globalMax == Long.MIN_VALUE) {
                    globalMax = tri;
                } else {
                    globalMax = Math.max(globalMax, tri);
                }
            }
        }

        return (globalMax == Long.MIN_VALUE) ? -1 : globalMax;
    }
}