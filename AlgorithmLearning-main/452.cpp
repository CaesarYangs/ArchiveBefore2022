#include <iostream>
#include <vector>

using namespace std;


int findMinArrowShots(vector<vector<int>>& points) {

    /*if(points.empty()){
        return 0;
    }

    if(points.size()==1){
        return 1;
    }

    int num = points.size();

    sort(points.begin(),points.end(),[](vector<int>&a,vector<int>&b){
        return a[1]<b[1];
    });

    int totalarrow = 1;
    int pre1;
    int pre0;
    int mark = 0;

    for(int i=0;i<num-1;i++){
        mark = 0;

        if(points[i][0]!=NULL && points[i][1]!=NULL){
            pre0 = points[i][0];
            pre1 = points[i][1];
            for(int j=i+1;j<num;j++){
                if(points[j][0]<=pre1 && points[j][1]>=pre0){
                    mark = 1;
                    points[j][0] = points[j][1] = NULL;
                }
            }
            totalarrow++;

        } else{
            continue;
        }

    }

    if(points[num-1][0]!=NULL && points[num-1][1]!=NULL){
        totalarrow++;
    }

    return totalarrow;*/

    if(points.size() < 1) return 0;
    sort(points.begin(),points.end(),[](vector<int>&a,vector<int>&b){
        return a[1]<b[1];
    });
    int count = 1;
    int axis = points[0][1];

    for(int i = 1; i < points.size(); ++i) {
        if(axis < points[i][0]) {
            count++;
            axis = points[i][1];
        }
    }

    return count;


}


int main() {

    vector<vector<int>> input = {{1,2},{1,4}};
    vector<int>a = {0};

    cout<<findMinArrowShots(input);

    return 0;
}
