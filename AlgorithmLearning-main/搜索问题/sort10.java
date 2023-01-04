import java.lang.reflect.Array;
import java.util.Arrays;

public class sort_10 {

    public static void bubbleSort(int[] array){
//        int[] array = Arrays.copyOf(sourceArray,sourceArray.length);
        //普通常规解法
        for(int i=1;i<array.length;i++){    //一共用这些规模的数字 所以最坏情况要交换这么多的次数
            boolean flag = false;
            for(int j=0;j<array.length-i;j++){  //每经历过判断-临近交换后一轮 最后一个都会是当前的最大值
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = true;
                }
            }
            if(!flag)
                break;
        }

        //进阶优化方案-改进版冒泡排序
        int pos=array.length-1; //记忆序列中最后一次发生交换的位置 下一次到此为之进行排序 减少浪费
        int last = 0;
        for(int i=1;i<array.length;i++){    //一共用这些规模的数字 所以最坏情况要交换这么多的次数
            boolean flag = false;
            for(int j=0;j<pos;j++){  //每经历过判断-临近交换后一轮 最后一个都会是当前的最大值
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = true;
                    last = j;
                }
            }
            pos = last;
            if(!flag)
                break;
        }


    }


    public static void selectionSort(int[] array){
        //简单粗暴之选择排序 所有序列的时间复杂度相同
        for(int i=0;i<array.length-1;i++){
            int min = i;
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[min]){
                    min = j;
                }
            }
            if(min!=i){
                int term = array[i];
                array[i] = array[min];
                array[min] = term;
            }
        }
    }


    public static void main(String[] args){
        int intputArray[] = {3,44,38,5,36,15,27,2,89,1,50};
//        bubbleSort(intputArray);
        selectionSort(intputArray);
        for(int i=0;i<intputArray.length;i++){
            System.out.print(intputArray[i]+",");
        }
    }
}

