import java.lang.reflect.Array;
import java.util.*;

import static com.sun.tools.javac.jvm.ByteCodes.swap;

class Solution {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<List<Integer>> temp = new ArrayList<>();
        if(k<=0 || n<k){
            return ans;
        }
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, ans);
        return ans;
    }

    private static void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> ans) {
        if(path.size() == k){
            ans.add(new ArrayList<>(path));
            return;
        }

        for(int i=begin;i<=n;i++){
            path.add(i);
            dfs(n,k,i+1,path,ans);
            path.removeLast();
        }
    }


}

public class first {

    public static void main(String[] args) {
        System.out.println(Solution.combine(4,2));

    }

}


