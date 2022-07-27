import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 */

// @lc code=start
class Solution {

    int n;
    List<List<String>> res;
    List<StringBuilder> board;

    private boolean isValid(int row, int col) {
        // for each row, col, diag
        for (int i = 0; i < n; i++) {
            if (i != col && board.get(row).charAt(i) == 'Q')
                return false;
        }
        for (int i = 0; i < n; i++) {
            if (i != row && board.get(i).charAt(col) == 'Q')
                return false;
        }
        int y = row - 1, x = col - 1;
        while (y >= 0 && x >= 0) {
            if (board.get(y--).charAt(x--) == 'Q')
                return false;
        }
        y = row + 1;
        x = col + 1;
        while (y < n && x < n) {
            if (board.get(y++).charAt(x++) == 'Q')
                return false;
        }
        y = row + 1;
        x = col - 1;
        while (y < n && x >= 0) {
            if (board.get(y++).charAt(x--) == 'Q')
                return false;
        }
        y = row - 1;
        x = col + 1;
        while (y >= 0 && x < n) {
            if (board.get(y--).charAt(x++) == 'Q')
                return false;
        }
        return true;
    }

    public void makeDecesion(int row) {
        // when touch the bottom. nomal row should be [0, N)
        if (row == n) {
            List<String> t = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                t.add(board.get(i).toString());
            }
            res.add(new ArrayList<>(t));
            return;
        }
        for (int i = 0; i < n; i++) {
            // make decision
            board.get(row).setCharAt(i, 'Q');
            if (isValid(row, i))
                makeDecesion(row + 1);
            // revocer
            board.get(row).setCharAt(i, '.');
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        res = new ArrayList<>();
        board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append('.');
            }
            board.add(sb);
        }
        makeDecesion(0);
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solveNQueens(8));
    }
}
// @lc code=end
