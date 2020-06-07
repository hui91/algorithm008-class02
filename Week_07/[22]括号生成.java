//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        generate(0,0,n,"",res);
        return res;
    }

    public void generate(int left,int right,int n,String val,List<String> list) {
        if (left == n && right == n) {
            list.add(val);
            return;
        }

        if (left < n) {
            generate(left + 1,right,n,val + "(",list);
        }

        if (right < left) {
            generate(left,right + 1,n,val + ")",list);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
