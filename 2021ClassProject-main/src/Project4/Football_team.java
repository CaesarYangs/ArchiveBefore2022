package Project4;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Football_team {



    public static void main(String[] args) {
        ArrayList<ArrayList> teams = new ArrayList<ArrayList>();
        ArrayList<String> allteams = new ArrayList<String>();

        allteams.add("科特迪瓦");
        allteams.add("阿根廷");
        allteams.add("澳大利亚");
        allteams.add("塞尔维亚");
        allteams.add("荷兰");
        allteams.add("尼日利亚");
        allteams.add("日本");
        allteams.add("美国");
        allteams.add("中国");
        allteams.add("新西兰");
        allteams.add("巴西");
        allteams.add("比利时");
        allteams.add("韩国");
        allteams.add("喀麦隆");
        allteams.add("洪都拉斯");
        allteams.add("意大利");

        Vector result = new Vector();


        while (result.size()!=16){
            int num = (int) (0+Math.random()*(16-1-0+1));
            if(result.lastIndexOf(num)==-1){
                result.add(num);
            }
        }

        int now=0;
        ArrayList<String> team1 = new ArrayList<String>();
        for(int i=0;i<4;i++){
            team1.add(allteams.get((int)result.get(now)));
            ++now;
        }
        teams.add(team1);
        ArrayList<String> team2 = new ArrayList<String>();
        for(int i=0;i<4;i++){
            team2.add(allteams.get((int)result.get(now)));
            ++now;
        }
        teams.add(team2);
        ArrayList<String> team3 = new ArrayList<String>();
        for(int i=0;i<4;i++){
            team3.add(allteams.get((int)result.get(now)));
            now++;
        }
        teams.add(team3);
        ArrayList<String> team4 = new ArrayList<String>();
        for(int i=0;i<4;i++){
            team4.add(allteams.get((int)result.get(now)));
            now++;
        }
        teams.add(team4);

        for(int i=0;i<4;i++){
            int j=i+1;
            System.out.println("Group"+j+":");
            System.out.print(teams.get(i)+" ");
            System.out.println();
        }


    }


}
