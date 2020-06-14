//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        if (char1.length == char2.length) {
            int[] arr = new int[26];

            for (int i = 0;i < char1.length;i++) {
                arr[char1[i] - 'a']++;
                arr[char2[i] - 'a']--;
            }
            for (int v : arr) {
                if (v != 0)
                    return false;
            }
            return true;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
