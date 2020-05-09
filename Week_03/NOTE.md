## 学习笔记
### 递归
#### 递归的思维要点
- 不要进行人肉递归
-  找最近重复子问题
-  数学归纳法思维
#### 代码模板
```java
    public void recursion(level,param1,param2,....) {
    	// 中断递归
    	if (level > MAX_LEVEL) {
    		print_result;
    		return;
    	}
    	// 在当前层处理业务数据
    	process_data(level,data....)
    	// 进入下一层
    	recursion(level+1,p1,p2,...)
    	// 如果需要清楚当前层状态
    	reverse_state(level)
    }
```
### 回溯
#### 白话描述
回溯法可以理解为通过选择不同的岔路口寻找目的地，一个岔路口一个岔路口的去尝试找到目的地。如果走错了路，继续返回来找到岔路口的另一条路，直到找到目的地。
#### 代码模板
```java
    public void divide_conquer(problem, param1, param2, ...):
        // 中断递归
        if (problem is None) {
            print_result;
            return;
        }
        // 处理数据,分割重复子问题
        data = prepare_data(problem)
        subproblems = split_problem(problem, data)
        // 处理重复子问题
        subresult1 = self.divide_conquer(subproblems[0], p1, ...)
        subresult2 = self.divide_conquer(subproblems[1], p1, ...)
        subresult3 = self.divide_conquer(subproblems[2], p1, ...)
        ...
    
        // 在当前层处理业务数据
        process_result(subresult1, subresult2, subresult3, …)
    
        // 如果需要清楚当前层状态
    }
```

