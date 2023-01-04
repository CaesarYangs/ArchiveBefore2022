class Solution {
public:
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
    if(intervals.empty()){
        return 0;
    }

    int num = intervals.size();
    sort(intervals.begin(),intervals.end(),[](vector<int>&a,vector<int>&b){
        return a[1]<b[1];
    });


    int total = 0;
    int pre1 = intervals[0][1];
    int pre0 = intervals[0][0];

    for(int i=1;i<num;i++){
        if(intervals[i][0]==pre0 && pre1<intervals[i][1]){
            total++;
            continue;
        }

        if(pre1<=intervals[i][0]){
            pre0 = intervals[i][0];
            pre1 = intervals[i][1];
        } else{
            total++;
            continue;
        }
    }

    return total;
    }
};
