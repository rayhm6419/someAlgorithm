package recursion2;

import java.util.ArrayList;
import java.util.List;

// Solution: Implement recrusion dfs
// 1. Recrusion Rule:
// a. every row trying to put a queen,
// b. and there are n rows, so we have level of n

// 2. we can use a 1D array to record the result (to traverse), because rows can be the level of the index,
//   and cols can be the i using for loop

// 3. condition for placing the queens -> in the same row, same cols and diagonal = invalid
//** where is the check for horizontal?
// does not need, because every row placing one queen
public class nQueen {
//    //method 1: Validate the queen position in O(n) each time
public static List<List<Integer>> nqueens(int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> rows = new ArrayList<>();
    helper(n, rows, result); //what we need in the recrusion helper function:
    return result;
}
    private static void helper (int n, List<Integer> rows, List<List<Integer>> result){
        //base case -> when the queen placed the whole board -> rows = n, stop
        if (rows.size() == n){
            result.add(new ArrayList(rows));
            return;
        }
        //start trying to place the queen in rows, and look for which cols is valid to place
        for (int cols = 0; cols < n; cols++){
            if(checkValid(rows, cols)){
                rows.add(cols);
                //吃，go to next level
                helper (n, rows, result);
                //吐
                rows.remove(rows.size() - 1);
            }
        }
    }
    //cur position that wanted to place the queen,      //the value passed from helper function
    private static boolean checkValid (List<Integer> curPostion, int lastCols){
        int cuurentRow = curPostion.size();
        //start traverse for each col in the current Row
        for (int i = 0; i < cuurentRow; i++){
            //check vertical            //check diagonal,
            if (curPostion.get(i) == lastCols || Math.abs(curPostion.get(i) - lastCols) == cuurentRow - i){
                return false;
            }
        }
        return true;
    }

//
    //method 2 validate the queen position in O(1) each time
//    public static List<List<Integer>> nqueenII(int n) {
//        List<List<Integer>> result = new ArrayList<List<Integer>>();
//        int[] cur = new int[n];
//        boolean[] usedColumns = new boolean[n];
//        boolean[] usedDiagonals = new boolean[2* n -1];
//        boolean[] usedRevDiagonals = new boolean[2* n -1];
//        helper(n,0,cur,result,usedColumns,usedDiagonals,usedRevDiagonals);
//        return result;
//    }
//    private static void helper(int n, int row, int[] cur, List<List<Integer>> result, boolean[] usedColumns, boolean[] usedDiagonals, boolean[] usedRevDiagonals){
//        if (row == n){
//            result.add(toList(cur));
//            return;
//        }
//        for (int i = 0; i < n; i++){
//            if (valid(n,row,i,usedColumns,usedDiagonals,usedRevDiagonals)){
//                mark(n,row,i,usedColumns,usedDiagonals,usedRevDiagonals);
//                cur[row] = i;
//                helper(n,row +1,cur,result,usedColumns,usedDiagonals,usedRevDiagonals);
//                unMark(n,row,i,usedColumns,usedDiagonals,usedRevDiagonals);
//            }
//        }
//    }
//    private static boolean valid(int n, int row, int column, boolean[] usedColumns, boolean[] usedDiagonals, boolean[] usedRevDiagonals){
//        return !usedColumns[column] && !usedDiagonals[column + row] && !usedRevDiagonals[column - row + n - 1];
//    }
//    private static void mark(int n, int row, int column, boolean[] usedColumns, boolean[] usedDiagonals, boolean[] usedRevDiagonals){
//        usedColumns[column] = true;
//        usedDiagonals[column + row] = true;
//        usedRevDiagonals[column - row + n - 1] = true;
//    }
//    private static void unMark(int n, int row, int column, boolean[] usedColumns, boolean[] usedDiagonals, boolean[] usedRevDiagonals){
//        usedColumns[column] = false;
//        usedDiagonals[column + row] = false;
//        usedRevDiagonals[column - row + n - 1] = false;
//    }
//    private static List<Integer> toList(int[] array){
//        List<Integer> list = new ArrayList<>();
//        for (int num : array){
//            list.add(num);
//        }
//        return list;
//    }
//
    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> result = nqueens(4);
        System.out.println(result);

    }
}
