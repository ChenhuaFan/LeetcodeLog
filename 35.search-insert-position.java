/*
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 */

// @lc code=start
class Solution {
    public int searchInsert(int[] nums, int target) {
        // find the left bound
        int l = 0, r = nums.length, m;
        while (l < r) {
            m = (l + r) / 2;
            if (target <= nums[m]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
// @lc code=end
