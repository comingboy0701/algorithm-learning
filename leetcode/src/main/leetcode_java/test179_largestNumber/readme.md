## Java 和Python分别对二维数组进行排序
### 1 Java - lambda 的使用
(1)按照第一列升序，第二列升序进行排序

```Java
public class test02_sort_lambda {
    public static void main(String[] args) {
        int[][] nums = {{6,2},{2,3},{2,1},{5,3},{5,2},{4,3}};
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
        Arrays.sort(nums,(o1,o2)->{
            if(o1[0]==o2[0]) return o1[1]-o2[1];//第一列 升序
            else return o1[0]-o2[0];//第一列 升序
        });
        System.out.println("============");
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }
}

```
(2)按照第一列升序，第二列降序进行排序

```Java
public class test02_sort_lambda {
    public static void main(String[] args) {
        int[][] nums = {{6,2},{2,3},{2,1},{5,3},{5,2},{4,3}};
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
        Arrays.sort(nums,(o1,o2)->{
            if(o1[0]==o2[0]) return o2[1]-o1[1];//第二列 降序
            else return o1[0]-o2[0];//第一列 升序
        });
        System.out.println("============");
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }
}
```


###  2 Python-lambda 的使用
按照第2列进行排序，可以用reverse 进行降序排序
```Python
import numpy as np
data = np.array([[2,2,5],[2,2,4],[1,1,3],[1,2,3],[2,3,6]])
sorted_data = sorted(data,key=lambda x:x[1])
print (sorted_data)

```

### 3 Python - np.lexsort 的使用
按照第1列升序进行排序，第2列降序，第3列升序

```Python
import numpy as np
data = np.array([[2,2,5],[2,2,4],[1,1,3],[1,2,3],[2,3,6]])
idex=np.lexsort([data[:,2], -1*data[:,1], data[:,0]])
sorted_data = data[idex, :]
print (sorted_data)

```