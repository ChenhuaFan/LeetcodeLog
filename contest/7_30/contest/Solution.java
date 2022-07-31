import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    // q1
    private boolean allZero(int[] nums) {
        for (int num : nums) {
            if (num != 0)
                return false;
        }
        return true;
    }

    public int minimumOperations(int[] nums) {
        int steps = 0;
        while (!allZero(nums)) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < min && nums[i] != 0) {
                    min = nums[i];
                }
            }
            //
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[i] -= min;
                }
            }
            steps++;
        }
        return steps;
    }

    // q2
    public int maximumGroups(int[] grades) {
        if (grades.length == 1)
            return 1;
        Arrays.sort(grades);
        int n = grades.length,
                k = 1,
                ct = 0;
        while (n > 0) {
            n -= k;
            ct++;
            k++;
            if (k > n)
                break;
        }
        return ct;
    }

    // q3
    private int[] helper(int[] edges, int node1, int node2) {
        Map<Integer, Integer> path = new HashMap<>();
        // build path for node1, until reach -1
        int curNode = node1, step = 0;
        while (!path.containsKey(curNode) && curNode != -1) {
            path.put(curNode, step++);
            curNode = edges[curNode];
        }
        // through node2;
        curNode = node2;
        step = 0;
        Set<Integer> path2 = new HashSet<>();
        while (!path.containsKey(curNode)) {
            if (path2.contains(curNode)) {
                int[] res = { -1, -1 };
                return res;
            }
            path2.add(curNode);
            curNode = edges[curNode];
            step++;
        }
        int maxDis = Math.max(step, path.get(curNode));
        int[] res = { maxDis, curNode };
        return res;
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        if (node1 == node2)
            return node1;
        int[] res1 = helper(edges, node1, node2);
        int[] res2 = helper(edges, node2, node1);
        if (res1[1] == -1 || res2[1] == -1)
            return -1;
        if (res1[0] == res2[0])
            return Math.min(res1[1], res2[1]);
        if (res1[0] < res2[0])
            return res1[1];
        else
            return res2[1];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        // test
        int[] test = { 2, 2, 3, -1 };
        System.out.println(s.closestMeetingNode(test, 0, 1));
    }
}
