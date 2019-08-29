# 数组

## 定义


### 一维数组

> var 变量名称 [数组长度]类型#{初始值逗号分隔可选}#

默认通过var声明的数组"{}"数组初始值可选填,如若没够给定,golang讲话根类类型进行初始化值
1. int [0 0 0 0]
2. string [    ]
3. bool [false false false false]
``` golang
 // 基本定义
 var arr1 [5]int
```

> 变量名称 := [数组长度]类型{初始值逗号分隔}

``` golang
 // 给定初始值
 arr2 := [3]int{1, 2, 3}
```

> 变量名称 := [...]类型{初始值逗号分隔}

``` golang
 // 才有切片,自动计算长度 
 arr3 := [...]int{4, 5, 6, 7}
```

### 二维数组

> 变量名称 := [行数][列数]类型{初始值逗号分隔}

``` golang
 var grid [4][5]int
```

## 遍历

> 基本遍历方式

> range关键字遍历[推荐]

通过range方式遍历,可以根据需求,获取数组的下标和值,具体示例如下:

``` golang
arr3 := [...]int{2,4,6,7,10}
// 通过range关键字遍历,获取数组下标
for i := range arr3{
    fmt.Println(arr3[i])
}
// 获取数字下标和数值
for i, v := range arr3{
    fmt.Println(i, v)
}
// 不要下标只要数值
for _, v := range arr3{
    fmt.Println(v)
}
```

## 数值为值类型

数组在golang中为值类型,则函数中定义数组,入参的数组只是对原数组的一种拷贝(copy),具体示例如下:

``` golang
func printArray(arr [5]int) {
	arr[0] = 100
	for i, v := range arr {
		fmt.Println(i, v)
	}
}
func main() {
	var arr1 [5]int
	arr2 := [3]int{1,3,5}
	arr3 := [...]int{2,4,6,7,10}
	// 传入arr2编译器会提示错误,因为golang认为arr2是另外一种参数类型,因为printArray函数需要传入长度5的数组,而arr2长度只有3
	// printArray(arr2)
	fmt.Println("---------------------函数遍历arr1输出-------------------------")
	printArray(arr1)
	fmt.Println("---------------------函数遍历arr3输出-------------------------")
	printArray(arr3)
	fmt.Println("---------------------arr1, arr3输出-------------------------")
	fmt.Println(arr1, arr3)

}
```
控制台输出结果,发现arr1和arr3的下标为0的值并没有改变
``` conslog
---------------------函数遍历arr1输出-------------------------
0 100
1 0
2 0
3 0
4 0
---------------------函数遍历arr3输出-------------------------
0 100
1 4
2 6
3 7
4 10
---------------------arr1, arr3输出-------------------------
[0 0 0 0 0] [0 4 6 7 10]
```



