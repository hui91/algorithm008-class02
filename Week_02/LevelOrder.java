//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
//
// 例如，给定一个 3叉树 :
//
//
//
//
//
//
//
// 返回其层序遍历:
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
//
//
//
//
// 说明:
//
//
// 树的深度不会超过 1000。
// 树的节点总数不会超过 5000。
// Related Topics 树 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
	private List<List<Integer>> res = new ArrayList<>();

	public List<List<Integer>> levelOrder(Node root) {
		if (root == null)
			return res;

		dfs(root,0);
		return res;
	}

	private void dfs(Node root,int level) {
		if (root == null)
			return;
		if (res.size() == level) {
			res.add(new ArrayList<>());
		}
		res.get(level).add(root.val);

		for (Node node : root.children) {
			dfs(node,level + 1);
		}

	}
}
//leetcode submit region end(Prohibit modification and deletion)
