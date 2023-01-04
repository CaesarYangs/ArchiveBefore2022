class Solution {
public:
    int candy(vector<int>& ratings) {
    int num=0;
    int candy[ratings.size()];
    for(int i=0;i<ratings.size();i++){
        candy[i] = 1;
    }

   for(int i=0;i<ratings.size()-1;i++){
       if (ratings[i+1]>ratings[i]){
           candy[i+1] = candy[i]+1;
       }
   }

    for(int i=ratings.size()-1;i>0;i--){
        if (ratings[i-1]>ratings[i] && candy[i-1]<=candy[i]){
            candy[i-1] = candy[i]+1;
        }
    }


    for(int i=0;i<ratings.size();i++){
        num = num + candy[i];
    }
    return num;

    }
};
