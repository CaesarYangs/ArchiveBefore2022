/*
本题是需要使用二分查找，怎么分是关键，举个例子：

第一类
1011110111 和 1110111101 这种。此种情况下 nums[start] == nums[mid]，分不清到底是前面有序还是后面有序，此时 start++ 即可。相当于去掉一个重复的干扰项。
第二类
22 33 44 55 66 77 11 这种，也就是 nums[start] < nums[mid]。此例子中就是 2 < 5；
这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
第三类
66 77 11 22 33 44 55 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2；
这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]。则在后半部分找，否则去前半部分找。


*/

class Solution {
    public static boolean search(int[] nums, int target) {
        int l=0,r=nums.length-1,mid=-1;

        while (l<=r){
            mid = l+(r-l)/2;
            if(nums[mid]==target){
                return true;
            }

            if(nums[l] == nums[mid]){
                ++l;
            }else if(nums[mid]<nums[r]){
                if(target<=nums[r] && target>nums[mid]){
                    l = mid+1;
                }else {
                    r = mid-1;
                }
            }else {
                if(target>=nums[l] && target<nums[mid]){
                    r = mid-1;
                }else {
                    l = mid+1;
                }
            }

        }

        return false;
    }
}

/*
1. 通过第一次判断来实现左边or右边数组递增
2. 如果不能判断左或右数组值相同，简单粗暴l++ 使得直接进入到右侧递增数组 否则再次进行判断
3. 针对每一个小的部分判断mid数组值和target 以及递增序列的大致范围 进入实际的二分查找迭代步骤
*/