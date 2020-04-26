## HashMap分析
### 一、构造函数
1. 无参构造函数
    ```java
    public HashMap() {
       this.loadFactor = DEFAULT_LOAD_FACTOR; // 使用默认加载因子(0.75f)
    }
    ```
2. 根据传入容量和默认加载因子(0.75f)创建HashMap
    ```java
    public HashMap(int initialCapacity) {
   	    this(initialCapacity, DEFAULT_LOAD_FACTOR); // 
    }
    ```
3. 根据传入容量和加载因子构建HashMap
    ```java
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
           throw new IllegalArgumentException("Illegal initial capacity: " +
                                              initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
           initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
           throw new IllegalArgumentException("Illegal load factor: " +
                                              loadFactor);
        // 设置加载因子
        this.loadFactor = loadFactor;
        // 根据传入值设置threshold为2的幂次
        this.threshold = tableSizeFor(initialCapacity);
    }
    ```
   当initialCapacity值大于容量时,initialCapacity使用最大容量.根据传入的初始容量(initialCapacity的值)转换为2的n次幂.
   例:当传入8时threshold等于8,当传入9时threshold等于16.tableSizeFor的代码如下:
    ```java
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
    ```
    n >>> 1;表示将最高为的1右移一位,n |= >>> 1之后最高位和最高位的后一位都将变为1,n >>> 2;会将前4位转换成1,依次类推,最后会将
    n最高位的1之后的数据全部转换成1最后再+1 就转换成了2的幂次方了.
### 二、put函数
1. 使用key和value保存数据
    ```java
    public V put(K key, V value) {
       return putVal(hash(key), key, value, false, true);
    }
    ```
    这个方法很简单,主要就是根据key进行hash,然后调用putVal函数,下面我们具体看一下putVal函数的实现
    ```java
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 当table为空时调用resize函数,
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // 根据key的hash的值计算对应的数组下标,当对应下标的数组为空时,将当前值直接存入
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            // 当hash值和key的值一样时,将e设置为相应数组下标的值(下面将用value替换当前值)
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            // 当前数组下表中的数据为树时(表明当前数据已经发生过hash碰撞)若key对应的数在树中存在时将返回,否则返回空
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                // 循环当前链表,并记录当前遍历到第几个节点
                for (int binCount = 0; ; ++binCount) {
                    // 判断当前节点是否为尾节点
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        // 当节点数量操作TREEIFY_THRESHOLD(8)时,将链表转换为红黑树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // 判断当前操作的节点是否已经存在
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            // e不会空时表明,在对相同的key操作,替换老数据 
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        // 当节点数量操作threshold时,调整map的大小
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
    ```
   下面我们来看一下resize的具体实现
   ```java
   // 第一次往map中保存值时,会根据加载因子计算数组的大小,以后再扩容时采用双倍扩容
   final Node<K,V>[] resize() {
       Node<K,V>[] oldTab = table;
       int oldCap = (oldTab == null) ? 0 : oldTab.length;
       int oldThr = threshold;
       int newCap, newThr = 0;
       // 判断是否已经在map中保存过值
       if (oldCap > 0) {
           // 当保存数量超过最大容量时,将threshold设置Integer类型最大值,并返回当前table(无法扩容)
           if (oldCap >= MAXIMUM_CAPACITY) {
               threshold = Integer.MAX_VALUE;
               return oldTab;
           }
           // 当原数组容量乘2后任小于最大容量时,将threshold扩容2倍
           else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
               newThr = oldThr << 1; // double threshold
       }
       // 当设置了初始规模时(即创建map时传入了initialCapacity),newCap设置为根据initialCapacity计算的threshold的值
       else if (oldThr > 0) 
           newCap = oldThr;
       // 若未设置initialCapacity,根据默认值设置newCap,根据加载因子计算新的threshold值
       else {              
           newCap = DEFAULT_INITIAL_CAPACITY;
           newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
       }
       // 根据用户传入值和加载因子计算新的threshold值
       if (newThr == 0) {
           float ft = (float)newCap * loadFactor;
           newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                     (int)ft : Integer.MAX_VALUE);
       }
       threshold = newThr;
       @SuppressWarnings({"rawtypes","unchecked"})
       Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
       table = newTab;
       // 判断是否发生扩容
       if (oldTab != null) {
           // 将原数据保存到新的数组中
           for (int j = 0; j < oldCap; ++j) {
               Node<K,V> e;
               if ((e = oldTab[j]) != null) {
                   oldTab[j] = null;
                   if (e.next == null)
                       newTab[e.hash & (newCap - 1)] = e;
                   else if (e instanceof TreeNode)
                       ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                   else { // preserve order
                       Node<K,V> loHead = null, loTail = null;
                       Node<K,V> hiHead = null, hiTail = null;
                       Node<K,V> next;
                       do {
                           next = e.next;
                           if ((e.hash & oldCap) == 0) {
                               if (loTail == null)
                                   loHead = e;
                               else
                                   loTail.next = e;
                               loTail = e;
                           }
                           else {
                               if (hiTail == null)
                                   hiHead = e;
                               else
                                   hiTail.next = e;
                               hiTail = e;
                           }
                       } while ((e = next) != null);
                       if (loTail != null) {
                           loTail.next = null;
                           newTab[j] = loHead;
                       }
                       if (hiTail != null) {
                           hiTail.next = null;
                           newTab[j + oldCap] = hiHead;
                       }
                   }
               }
           }
       }
       return newTab;
   }
   ```
   