//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> all = new LinkedList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        dfs(0,nums,new ArrayList<>(nums.length),new boolean[nums.length]);
        return all;
    }

    private void dfs(int index,int[] nums,List<Integer> path,boolean[] isUsed) {
        if (index == nums.length) {
            all.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0;i < nums.length;i++) {
            if (i > 0 && nums[i] == nums[i - 1] && isUsed[i - 1] == false) {
                continue;
            }
            if (isUsed[i] == false) {
                isUsed[i] = true;
                path.add(nums[i]);
                dfs(index + 1,nums,path,isUsed);
                path.remove(index);
                isUsed[i] = false;
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
