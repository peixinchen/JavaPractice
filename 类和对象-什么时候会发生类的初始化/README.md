# 类(T)的初始化只会发生在这 5 种情况下

1. 一个 T 类的实例被构造时；

2. 一个 T 声明的静态方法被调用时（必须是声明在 T 中的静态方法）；

3. 一个 T 声明的静态属性被赋值时（必须时声明在 T 中的静态属性）；

4. 一个 T 声明的静态属性被使用时，并且该静态属性不是常量变量（备注 1）；

5. T 是顶级类并且执行 assert 语句。


备注 1：大体来说常量是 final 变量的子集，要求是基本类型或者是 String 类型，并且可以在编译期间推导出值；不是简单的理解为 final 变量。
