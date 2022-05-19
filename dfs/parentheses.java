package DFS;

import java.util.ArrayList;
import java.util.List;

public class parentheses {
    public static List<String> validParentheses(int n) {
        List<String> result = new ArrayList<String>();
        char[ ] cur = new char[n *2];
        System.out.println(cur);
        helper(cur, n ,n ,0, result);
        return result;
    }

    public static void  helper(char[] cur, int left, int right, int index, List<String> result){
        //basecase
        if (left == 0 && right == 0){
            result.add(new String(cur));
            return;
        }
        if (left > 0){
            cur[index] = '(';
            helper(cur, left -1, right,  index +1 , result);

        }
        if (right > left){
            cur[index] = ')';
            helper(cur, left, right - 1, index +1, result);
        }
        // Write your solution here
    }


    public static void main(String[] args) {
        System.out.println(validParentheses(2));

    }
}
