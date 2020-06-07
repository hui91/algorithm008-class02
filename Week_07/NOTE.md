##学习笔记
###字典树(Trie树 查找树 键树)
典型应用与统计和排序大量的字符串(但不仅限于字符串),索引经常被搜索引擎系统用于文本词频统计

它的优点是:最大限度地减少无畏的字符串比较,查找效率比哈希表高
####基本性质
- 节点本身不存完整单词
- 从根节点到某一节点,路径上经过的字符串连接起来,为该节点对应的字符串
- 每个节点的所有子节点路径代表的字符串都不行同

####模板
```java
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}

```

###AVL树
Balance Factor(平衡因子)
是它的左子树的高度减去它的右子树的高度(有时相反) 

balance factor = {-1,0,1}(范围)

通过旋转操作来进行平衡(四种)
- 左旋
- 右旋
- 左右旋
- 右左旋
不足: 结点需要存储额外信息且调整次数频繁

###红黑树
红黑树是一种近似平衡的二叉搜索树,它能够确保任何一个结点的左右子树的高度差小于两倍(大的最多是小的两倍)
具体来说,红黑树是满足如下条件的二叉搜索树:
- 每个结点要么是红色,要么是黑色
- 根节点是黑色
- 每个叶节点(NIL节点,空结点)是黑色的
- 不能有相邻的两个红色结点
- 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点  

####对比
- ALV查询更快
- 红黑树插入删除更快
- AVL存储信息更多