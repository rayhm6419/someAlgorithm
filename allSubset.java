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
