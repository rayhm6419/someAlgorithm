package DFS;

import java.util.ArrayList;
import java.util.List;

public class coins {
    public List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();
        helper(target, coins, 0, cur, result);
        return result;
        // Write your solution here
    }

    private void helper(int target, int[] coins, int index, List<Integer> cur, List<List<Integer>> result) {
        if (index == coins.length - 1) {  //检查是不是在最后一个coin的level
            if (target % coins[coins.length - 1] == 0) { //检查最后一个coin能不能跟剩下的coin整除
                cur.add(target / coins[coins.length - 1]);
                result.add(new ArrayList<Integer>(cur));
                cur.remove(cur.size() - 1);
            }
            return;
        }
        int max = target / coins[index];
        for (int i = 0; i <= max; i++) {
            cur.add(i);
            helper(target - i * coins[index], coins, index + 1, cur, result);
            cur.remove(cur.size() - 1);
        }
    }
    //weilong method
//public List<List<Integer>> combinations(int target, int[] coins) {
//    List<List<Integer>> res = new ArrayList<>();
//    if (coins == null || coins.length == 0) {
//        return res;
//    }
//    List<Integer> comb = new ArrayList<>();
//    for (int i = 0; i < coins.length; i++) {
//        comb.add(0);
//    }
//    dfs(coins, 0, target, comb, res);
//    return res;
//}
//
//    private void dfs(int[] coins, int level, int left, List<Integer> comb, List<List<Integer>> res) {
//        if (level == coins.length) {
//            if (left == 0) {
//                res.add(new ArrayList<>(comb));
//            }
//            return;
//        }
//        int deno = coins[level];
//        int num = left / deno;
//        for (int i = 0; i <= num; i++) {
//            comb.set(level, i);
//            dfs(coins, level + 1, left - i * deno, comb, res);
//            comb.set(level, 0);
//        }
//    }

    public static void main(String[] args) {
        coins res = new coins();
        int[] coins = {2,1};
        System.out.println(res.combinations(4,coins));
    }
}
