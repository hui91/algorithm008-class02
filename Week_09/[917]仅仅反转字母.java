//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseOnlyLetters(String S) {
        if (S == null || S.length() < 2) {
            return S;
        }
        char[] arr = S.toCharArray();
        int left = 0,right = arr.length - 1;
        do {
            while (left < right && !isValid(arr[left])) left++;
            while (left < right && !isValid(arr[right])) right--;
            if (left < right) {
                arr[left] ^= arr[right];
                arr[right] ^= arr[left];
                arr[left] ^= arr[right];
            }
        } while (++left < --right);
        return new String(arr);
    }

    private boolean isValid(char letter) {
        return (letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z');
    }
}
//leetcode submit region end(Prohibit modification and deletion)
