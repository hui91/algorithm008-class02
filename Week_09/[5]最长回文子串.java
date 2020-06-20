//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int start = 0, end = 0;

    public String longestPalindrome(String s) {
        if (s == null || s.equals("")) return "";
        char[] charArr = s.toCharArray();
        helper(charArr, 0);
        return s.substring(start, end + 1);
    }

    private void helper(char[] cs, int index) {
        if (index >= cs.length - 1) {
            return;
        }
        int cur_start = index, cur_end = index;
        while(cur_end < cs.length-1 && cs[cur_end+1] == cs[cur_start]){
            cur_end++;
        }
        index = cur_end;
        while(cur_start >0 && cur_end<cs.length-1 && cs[cur_start-1] == cs[cur_end+1]){
            cur_start--;
            cur_end++;
        }
        if (cur_end - cur_start > end - start) {
            start = cur_start;
            end = cur_end;
        }
        helper(cs, index + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
