//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//11110
//11010
//11000
//00000
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//11000
//11000
//00100
//00011
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {
        int num = 0;
        for (int x = 0;x < grid.length;x++){
            for (int y = 0;y < grid[x].length;y++) {
                if (grid[x][y] == '1') {
                    dfs(grid,x,y);
                    num++;
                }
            }
        }
        return num;
    }

    private void dfs(char[][] grid,int x,int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || grid[x][y] != '1') {
            return;
        }
        grid[x][y] = '0';
        dfs(grid,x + 1,y);
        dfs(grid,x - 1,y);
        dfs(grid,x,y + 1);
        dfs(grid,x,y - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
