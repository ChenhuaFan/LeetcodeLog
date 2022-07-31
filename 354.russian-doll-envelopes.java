import java.util.Arrays;
import java.util.Comparator;

/*
 * @lc app=leetcode id=354 lang=java
 *
 * [354] Russian Doll Envelopes
 */

// @lc code=start
class Solution {

    class EnvelopesComparator implements Comparator<int[]> {

        @Override
        public int compare(int[] env1, int[] env2) {
            if (env1[0] == env2[0])
                return env1[1] - env2[1];
            return env2[0] - env1[0];
        }

    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length < 2)
            return envelopes.length;
        Arrays.sort(envelopes, new EnvelopesComparator());
        int res = 1;
        int lastIndex = 0;
        for (int i = 1; i < envelopes.length; i++) {
            if (envelopes[i][0] > envelopes[lastIndex][0] && envelopes[i][1] > envelopes[lastIndex][1]) {
                res++;
                lastIndex = i;
            }
        }
        return res;
    }
}
// @lc code=end
