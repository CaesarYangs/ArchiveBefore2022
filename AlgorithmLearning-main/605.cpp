class Solution {
public:
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        if(flowerbed.empty()){
        return false;
    }

    flowerbed.push_back(0);
    flowerbed.insert(flowerbed.begin(),0);
    int maxflowernum=0;
    int num = flowerbed.size();


    for(int i=1;i<num-1;i++){
        if(flowerbed[i]==0){
            if(flowerbed[i-1]==0 && flowerbed[i+1]==0){
                flowerbed[i] = 1;
                maxflowernum++;
            }
        }
    }



    if(maxflowernum>=n){
        return true;
    } else{
        return false;
    }
    }
};
