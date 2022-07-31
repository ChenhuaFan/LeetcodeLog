/*
 * @lc app=leetcode id=74 lang=java
 * 
 * [74] Search a 2D Matrix
 */
// @lc code=start
class Solution {

    /**
     * 重要！因为此题目的特殊性质，可以将2维数组展开为1维数组。
     * （matrix[m][0] > matrix[m-1][n-1])
     * 所以，可以使用坐标映射的方法。i = n*x+y, x = i/n, y = i%n
     * 与 240 相关，240并不能展开为一维数组。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,
                n = matrix[0].length,
                l = 0,
                r = m * n - 1,
                mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (target <= matrix[mid / n][mid % n]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return matrix[l / n][l % n] == target;
    }
}
// @lc code=end