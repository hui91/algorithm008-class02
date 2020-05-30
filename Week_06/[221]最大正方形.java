//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。 
//
// 示例: 
//
// 输入: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4 
// Related Topics 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
            int max = matrix[0][0] - '0';
            int[][] dp = new int[2][matrix[0].length];
            for (int i = 0;i < matrix.length;i++) {
                int c = i & 1;
                int l = (i + 1) & 1; // 减1可能越界
                for (int j = 0;j < matrix[i].length;j++) {
                    if (i == 0 || j == 0) {
                        dp[c][j] = matrix[i][j] - '0';
                        if (matrix[i][j] == '1')  max = max > 1 ? max : 1;
                    } else {
                        int min = Math.min(Math.min(dp[c][j - 1],dp[l][j]),dp[l][j - 1]);
                        dp[c][j] = min > 0 && matrix[i][j] > '0' ? ++min : matrix[i][j] - '0';
                        max = max > dp[c][j] ? max : dp[c][j];
                    }
                }
            }
            return max * max;
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
