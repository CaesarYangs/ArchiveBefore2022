class Solution {
public:
    
int judgeInclude(string s,string src){

    int i=0,j=0;
    for (;i<s.size()&&j<src.size();i++){
        if(s[i]==src[j]){
            ++j;
        }
    }

    if(j==src.size())
        return 1;
    else
        return 0;
}


string findLongestWord(string s, vector<string>& dictionary) {
    string ans;

    for(string src:dictionary){
       if(judgeInclude(s,src)){
           if(src.size()>ans.size() || (src.size()==ans.size()&&src<ans))
           ans = src;
       }
    }


    return ans;
}
};