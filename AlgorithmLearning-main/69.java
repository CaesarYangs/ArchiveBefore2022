class Solution {
    public static int mySqrt(int x) {
        if(x==0){
            return 0;
        }
        int l=1,r=x,mid=0,sqrt;

        while (l<=r){
            mid = (l+r)/2;
            sqrt = x/mid;
            if(mid==sqrt){
                return sqrt;
            }else if(sqrt<mid){
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
        return r;
    }
}
//解法一



class Solution {
    public static int mySqrt(int x) {
        if(x==0){
            return 0;
        }
        int l=1,r=x,mid=0;

        while (l<=r){
            mid = l + (r - l) / 2;
            if((long)mid*mid==x){
                return mid;
            }else if((long)mid*mid<x){
                l = mid+1;
            }else {
                r = mid-1;
            }
        }
        return r;
    }
}
//解法二

//注：一定要注意整形会溢出的情况