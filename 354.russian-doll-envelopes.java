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

        /**
         * !important
         * 自定义比较函数怎么比？
         * a - b 意味着，a - b > 0 -> a ? b;
         */
        @Override
        public int compare(int[] env1, int[] env2) {
            if (env1[0] == env2[0])
                // 当 w 相同时，比较 h。并且 是 env2.h ? env1.h == env1 ? env2
                return env2[1] - env1[1];
            // 当 第一位 也就是 w。 env1.w ? env2.w == env1 ? env2
            return env1[0] - env2[0];
        }

    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length < 2)
            return envelopes.length;
        Arrays.sort(envelopes, new EnvelopesComparator());
        /**
         * !important 为什么这里第二步的问题转化为了最大上升子序列？
         * 为什么呢？稍微思考⼀下就明⽩了： ⾸先，对宽度 w 从⼩到⼤排序，确保了 这个维度可以互相嵌套，所以我们只需要专注⾼度 这个维度能够 互相嵌套即可。
         * 其次，两个 w 相同的信封不能相互包含，所以对于宽度 w 相同的信封，对⾼度 h 进⾏降序排序，保证 LIS 中 不存在多个 w
         * 相同的信封（因为题⽬说了⻓宽相同也⽆法嵌套）。
         */
        int[] dp = new int[envelopes.length];
        int size = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int[] env = envelopes[i];
            int l = 0, r = size, m;
            while (l < r) {
                m = (l + r) / 2;
                if (env[1] <= dp[m]) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            if (l == size)
                size++;
            dp[l] = env[1];
        }
        return size;
    }
}
// @lc code=end
