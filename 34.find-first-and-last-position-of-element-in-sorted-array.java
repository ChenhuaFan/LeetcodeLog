/*
 * @lc app=leetcode id=34 lang=java
 *
 * [34] Find First and Last Position of Element in Sorted Array
 */

// @lc code=start
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = { -1, -1 };
        if (nums.length == 0)
            return res;
        int l = 0, r = nums.length - 1, m;
        while (l < r) {
            m = (l + r) / 2;
            if (target <= nums[m]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (nums[l] != target)
            return res;
        res[0] = l;
        l = 0;
        r = nums.length - 1;
        while (l < r) {
            m = (l + r + 1) / 2;
            if (target >= nums[m]) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        res[1] = r;
        return res;
    }
}
// @lc code=end
