class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int l=0;
    int r = numbers.size()-1;
    int sum;

    while (l<r){
        sum = numbers[l]+numbers[r];
        if(sum==target){
            break;
        }
        if(sum<target){
            ++l;
        } else{
            --r;
        }
    }

    return vector<int>{l+1,r+1};
}
    
};
