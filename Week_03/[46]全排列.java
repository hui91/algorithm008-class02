//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> all;
    public List<List<Integer>> permute(int[] nums) {
        all = new LinkedList<>();
        dfs(0,nums);
        return all;
    }

    private void dfs(int index,int[] nums) {
        if (index == nums.length) {
            all.add(toList(nums));
            return ;
        }
        for (int i = index;i < nums.length;i++) {
            nums[i] = nums[index] ^ nums[i] ^ (nums[index] = nums[i]);
            dfs(index + 1,nums);
            nums[i] = nums[index] ^ nums[i] ^ (nums[index] = nums[i]);
        }
    }

    private List<Integer> toList(int[] nums) {
        ArrayList<Integer> integers = new ArrayList<>(nums.length);
        for (int num : nums) {
            integers.add(num);
        }
        return integers;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
