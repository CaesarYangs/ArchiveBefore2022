//桶排序


class Solution {
    public static int[] topKFrequent(int[] nums, int k) {
        if(nums.length==0 || nums.length==1){   //特定情况分析
            return nums;
        }

        Map<Integer,Integer> store = new HashMap<>();
        for(int i:nums){
            store.put(i,store.getOrDefault(i,0)+1);
        }
        System.out.println(store);


        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0;i<k;i++){
            int max = 0;
            int maxkey=-1;
            for(int key:store.keySet()){
                if(store.get(key)>max){
                    max = store.get(key);
                    maxkey = key;
                }
            }
            ans.add(maxkey);
            store.put(maxkey,0);
        }
        int[] reans = new int[k];
        for (int i=0;i<ans.size();i++){
            reans[i] = ans.get(i);
        }
        return reans;
    }
}