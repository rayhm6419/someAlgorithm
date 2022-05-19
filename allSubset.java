**Clarification**: The definition of subset is does not have order, we can either chose or not chose . Given a set of String, want to return the subset, which mean a combination from empty to all. Return List of String, input is set of String

**Assumption**: No duplicate char in the set

**Solution**: implment dfs and backtracking

1. what does it store on each level? → on each level we sote a char →每层只需要一个元素
2. How many different states should we try to put on this level → two branches →add or not add
3. How many level → n level

Time: O(2^n * n)

Space: O(n)
    
package DFS;

import java.util.ArrayList;
import java.util.List;

public class allSubset {
    public List<String> subSets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null){
            return result;
        }
        char[] charSet = set.toCharArray();
        StringBuilder sb = new StringBuilder();
        helper(charSet, 0, sb, result);
        return result;
    }
    private void helper(char[] charSet, int index, StringBuilder sb, List<String> result){
        //base case
        if (index == charSet.length){
            result.add(sb.toString());
            return;
        }
        //吃
        helper(charSet, index + 1, sb.append(charSet[index]), result);
        //吐
        sb.deleteCharAt(sb.length() - 1);
        helper(charSet, index + 1, sb, result);
    }

    public static void main(String[] args) {
        allSubset res = new allSubset();
        String set = "ABC";
        System.out.println(res.subSets(set));
    }
}
