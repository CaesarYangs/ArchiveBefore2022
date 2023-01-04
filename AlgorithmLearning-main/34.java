class Solution {
    public static int left(int[] nums,int target){
        int l=0,r=nums.length-1,mid=-1;
        if(r==0)
            return 0;
        while (l<=r){
            mid = l+(r-l)/2;
            if(nums[mid]>=target){
                r = mid-1;  //通过这一个判断来控制相等的情况属于哪一类 如果相等则那一侧的边界数组指针会按照既定路线前进 则归到那一类就会导致其一直变化 突破临界 而另一侧则是正常的侧边
            }else {
                l = mid+1;
            }
        }
        return l;
    }

    public static int right(int[] nums,int target){
        int l=0,r=nums.length-1,mid=-1;
        if(r==0)
            return 0;
        while (l<=r){
            mid = l+(r-l)/2;
            if(nums[mid]>target){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return r;
    }
    public static int[] searchRange(int[] nums, int target) {
        int[] ans={-1,-1};
        int mark=0;
        for(int i:nums){
            if(i==target){
                mark = 1;
                break;
            }
        }

        if(mark==1){
            ans[0] = Solution.left(nums,target);
            ans[1] = Solution.right(nums,target);
        }else {
            return ans;
        }


        return ans;
    }


}

public class first {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        for(int i: Solution.searchRange(nums,target)){
            System.out.print(i+" ");
        }

    }

}


