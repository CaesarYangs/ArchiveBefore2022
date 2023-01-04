import java.lang.reflect.Array;
import java.util.Arrays;

class sort {
    public static void quick_sort(int[] nums,int low,int high){

        if(low>=high){
            return;
        }
        int l = low;
        int r = high;
        int p = nums[l];

        while (l < r){

            while (nums[r]>=p && l < r){
                --r;
            }
            if(l<r){
                nums[l++] = nums[r];
            }
            while (nums[l]<=p && l < r){
                ++l;
            }
            if(l<r){
                nums[r--] = nums[l];
            }


        }
        nums[l] = p;
        quick_sort(nums,low,r-1);
        quick_sort(nums,r+1,high);
    }
}


public class first {

    public static void main(String[] args) {
        int[] nums = {3,4,1,2,6,2};
        sort.quick_sort(nums,0,nums.length-1);
//        for(int i : nums){
//            System.out.print();
//        }
        System.out.println(Arrays.toString(nums));


    }

}


