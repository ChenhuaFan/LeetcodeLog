/*
 * @lc app=leetcode id=240 lang=java
 *
 * [240] Search a 2D Matrix II
 * 这道题不太像二分查找。
 * !import 为什么是二分查找？二分查找终究还是因为条件的两面性，所导致的完全不同的两个结果。
 * 例如此题，如果是左上角出发，往右往下都是增大；同理从右下角出发都是减少。我们无法判断到底该往哪个方向走。
 * 但是从右上角出发，往左则是减小，往右则是增大。这便是二分性。我们可以通过每次的判断来决定该往哪个方向前进。
 * 二分查找不一定必须要是一次甩掉半边。注意二分性！
 */

// @lc code=start
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length, i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target)
                return true;
            if (matrix[i][j] < target)
                i++;
            else
                j--;
        }
        return false;
    }
}
// @lc code=end
