class Solution {
public:
    bool judgeSquareSum(int c) {
     vector<int>numbers;
    long sum=0;
    int sc = sqrt(c);
    for(int i=0;i<=sc;i++){
        numbers.push_back(i);
    }

    int l=0;
    int r = numbers.size()-1;
    while (l<=r){
        sum = pow(numbers[l],2)+pow(numbers[r],2);
        if(sum>c){
            --r;
        } else if (sum<c){
            ++l;
        } else{
            return true;
        }
    }

    return false;
    }
};