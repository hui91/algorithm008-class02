#学习笔记
## 高级动态规划
### DP 顺推模板
```java
public Object dp() {
    dp = [][] // 二维情况
    for (int i = 0; i <= m; i++) {
        for (int j = o; j <= n; j++) {
            dp[i][j] = _function(dp[_i][_j]...);
        }   
    }
    return dp[m][n];
}
```
![测试](./v2-7ac10298009c4b3558ecd25c6fb9b416_720w.jpg)
### 复杂度来源
1. 状态拥有更多维度（一维、二维、甚至需要压缩）
2. 状态方程更加复杂