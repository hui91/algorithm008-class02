## 学习笔记
### 搜索
#### 深度优先代码模板
```java
    Set visited = new HashSet();
    public void dfs(node,visited) {
    	visited.add(node)
    	// 处理当前节点
    	......
    	for (next_node in node.children()) {
    	    if visited.contains(next_node) {
                dfs(node,visited)
    	    }
    	}
    }
```
#### 广度优先代码模板
```java
    Set visited = new HashSet();
    public void bfs(node) {
    	LinkedList queue;
    	queue.offer(s);
        while(!queue.empty()){
            取出队首元素top;
    	    node = queue.poll()
    	    visited.add(node)
    		
    	    process(node)
            nodes = generate_related_nodes(node)
    	    queue.offer(nodes)
        }
    	// 其他处理工作
    }
```

#### 贪心算法适用场景
问题能够分解成子问题来解决,子问题的最优解能递推到最终问题的最优解. 这种子问题最优解称为最优子结构.

贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择,不能回退. 动他规划则会保存以前的运算结果,并根据以前的结果对当前进行选择,有回退功能.

#### 搜索旋转排序数组 
    使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方
#####思路
    通过观察可以发现将数组从中间分开成左右两部分的时候,一定有一部分是有序的,判断需要查找的值是否在有序的那一半数组里,
    如果在的话就去有序的数组里继续是否二分搜索如果不在就去无序的那一半数组里搜索(无序的那一半数组继续二分还是会存在一半有序一半无序),
    一直搜索下去,直到left > right或者找到需要搜索的值的下标.
