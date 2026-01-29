import java.util.Arrays;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // Initialize the distance matrix with a high value to represent infinity
        // We use a large enough long to avoid overflow during addition
        long INF = 1000000000000L; 
        long[][] dist = new long[26][26];

        // Fill matrix with INF and set diagonal to 0
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // Build the graph using the provided transformation rules
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            // If multiple costs for the same transformation exist, take the minimum
            dist[u][v] = Math.min(dist[u][v], (long) cost[i]);
        }

        // Floyd-Warshall: Find shortest paths between all pairs of characters
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        long totalCost = 0;
        int n = source.length();

        // Calculate total cost by looking up precomputed values
        for (int i = 0; i < n; i++) {
            int u = source.charAt(i) - 'a';
            int v = target.charAt(i) - 'a';

            if (u != v) {
                if (dist[u][v] >= INF) {
                    return -1; // Transformation impossible
                }
                totalCost += dist[u][v];
            }
        }

        return totalCost;
    }
}