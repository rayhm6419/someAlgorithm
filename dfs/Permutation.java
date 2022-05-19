package DFS;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public List<String> permutations(String input) {
        List<String> result = new ArrayList<String>();
        if (input == null){
            return result;
        }
        char[] array = input.toCharArray();
        permutation (array, 0 , result);
        return result;
        // Write your solution here
    }
    private void permutation (char[] array, int index, List<String> result){
        if (index == array.length){
            result.add(new String(array));
            return;
        }
        for (int i = index; i < array.length; i++){
            swap (array, index, i);
            permutation (array, index + 1, result);
            swap (array, index, i);
        }
    }
    private void swap(char[] array, int left, int right){
        char tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    public static void main(String[] args) {
        Permutation res = new Permutation();
        String set =  "ABC";
        System.out.println(res.permutations(set));
    }
}

desa
