import javax.sound.midi.Track;

/*
 * @lc app=leetcode id=300 lang=java
 *
 * [300] Longest Increasing Subsequence
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (50.15%)
 * Likes:    13297
 * Dislikes: 250
 * Total Accepted:    942.3K
 * Total Submissions: 1.9M
 * Testcase Example:  '[10,9,2,5,3,7,101,18]'
 *
 * Given an integer array nums, return the length of the longest strictly
 * increasing subsequence.
 * 
 * A subsequence is a sequence that can be derived from an array by deleting
 * some or no elements without changing the order of the remaining elements.
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore
 * the length is 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 * 
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time
 * complexity?
 * 
 */

// @lc code=start
class Solution {

    private int findLeftBound(int[] nums, int r, int target) {
        int l = 0, m;
        while (l < r) {
            m = (l + r) / 2;
            if (target <= nums[m])
                r = m;
            else
                l = m + 1;
        }
        return l;
    }

    public int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = findLeftBound(top, size, nums[i]);
            if (l == size)
                size++;
            top[l] = nums[i];
        }
        return size;
    }
}
// @lc code=end
