import com.sun.deploy.util.StringUtils;

import java.util.*;

class Solution {
    public static int findCircleNum(int[][] friends) {
        int count = 0,n = friends.length;
        boolean[] visited = new boolean[n];

        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(friends,i,visited);
                count+=1;
            }
        }
        return count;
    }

    private static void dfs(int[][] friends, int i, boolean[] visited){
        visited[i] = true;
        for(int k=0;k<friends.length;k++)
            if (friends[i][k] == 1 && !visited[k]) {
                dfs(friends, k, visited);
            }
    }
}

public class first {

    public static void main(String[] args) {
        int[][] input= {{1,1,0},{1,1,0},{0,0,0}};
        System.out.println(Solution.findCircleNum(input));

    }

}

//通过递归调用实现了朋友圈问题较好的解法
