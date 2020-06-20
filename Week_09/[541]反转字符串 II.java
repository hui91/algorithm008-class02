//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) return "";

        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        int len = arr.length,doubleK = 2 * k,endIndex = arr.length - 1;
        do {
            if (len < k) {
                sb.append(reverseRange(arr,len,endIndex));
                break;
            } else if (len < doubleK) {
                int end = endIndex - len + k;
                sb.append(reverseRange(arr,k,end));
                sb.append(Arrays.copyOfRange(arr,end + 1,endIndex + 1));
                break;
            } else {
                int end = endIndex - len + k;
                sb.append(reverseRange(arr,k,end));
                sb.append(Arrays.copyOfRange(arr,end + 1,end + k + 1));
            }
            len -= doubleK;
        }while (len > 0);
        return sb.toString();
    }

    private char[] reverseRange(char[] arr,int len,int end) {
        char[] ret = new char[len];
        for (int i = 0;i < len; i++) ret[i] = arr[end - i];
        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
