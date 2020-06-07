//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> start = new HashSet<>(),end = new HashSet<>(),words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        start.add(beginWord);
        end.add(endWord);
        return dfs(start,end,words,2);
    }
    private int dfs(HashSet<String> start, HashSet<String> end, HashSet<String> words, int depth) {
        if (start.isEmpty()) return 0;
        HashSet<String> next = new HashSet<>();
        int res = bfs(start,end,next,words,depth);
        return res != -1 ? res : next.size() > end.size() ? dfs(end,next,words,depth + 1) : dfs(next,end,words,depth + 1);
    }
    private int bfs(HashSet<String> start, HashSet<String> end,HashSet<String> next, HashSet<String> words, int depth) {
        words.removeAll(start);
        for (String word : start) {
            char[] chars = word.toCharArray();
            for (int i = 0;i < chars.length;i++) {
                char tmp = chars[i];
                for (char j = 'a';j <= 'z';j++) {
                    chars[i] = j;
                    String newWord = new String(chars);
                    if (words.contains(newWord)) {
                        if (end.contains(newWord)) return depth;
                        next.add(newWord);
                    }
                }
                chars[i] = tmp;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
