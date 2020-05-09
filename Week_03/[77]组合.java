//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> all = new LinkedList<>();
    private LinkedList<Integer> tmp = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        dfs(1,n,1,k);
        return all;
    }

    private void dfs(int from ,int to,int level,int max) {
        if (level > max) {
            all.add(new ArrayList<>(tmp));
            return;
        }
        if(max - tmp.size() > to - from + 1){
            return;
        }
        for (int i = from;i <= to;i++) {
            tmp.add(i);
            dfs(i + 1,to,level + 1,max);
            tmp.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
