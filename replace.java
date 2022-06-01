package String;

import java.util.ArrayList;


public class replace {
    public String replace(String input, String original, String subString) {
        char[] array = input.toCharArray();
        if (original.length() >= subString.length()) return shorter(array, original, subString);
         return longer(array, original, subString);
    }
    private String shorter(char[] array, String original, String subString){
        int slow = 0;
        int fast = 0;
        while (fast < array.length){
            if (fast <= array.length - original.length() && equalSubstring(array, fast, original)){
                copySubstring(array, slow, subString);
                slow += subString.length();
                fast += original.length();
            } else {
                array[slow++] = array[fast++];
            }
        }
        return new String(array, 0, slow);
    }

    private String longer(char[] input, String s, String t){
        ArrayList<Integer> matches = getAllMatches(input, s);
        char[] result = new char[input.length + matches.size() * (t.length() - s.length())];
        int lastIndex = matches.size() - 1;
        int fast = input.length -1;
        int slow = result.length - 1;
        while (fast >= 0){
            if (lastIndex >= 0 && fast == matches.get(lastIndex)){
                copySubstring(result, slow - t.length() + 1, t);
                slow -= t.length();
                fast -= s.length();
                lastIndex--;

            } else {
                result[slow--] = input[fast--];
            }
        }
        return new String(result);
    }
    private boolean equalSubstring(char[] input, int fromIndex, String original){
        for (int i = 0; i < original.length(); i++){
            if (input[fromIndex + i] != original.charAt(i)){
                return false;
            }
        }
        return true;
    }
    private void copySubstring(char[] result, int fromIndex, String subString){
        for (int i = 0; i < subString.length(); i++){
            result[fromIndex + i] = subString.charAt(i);
        }
    }

    private ArrayList<Integer> getAllMatches(char[] input, String s){
        ArrayList<Integer> matches = new ArrayList<>();
        int i = 0;
        while (i <= input.length - s.length()){
            if(equalSubstring(input, i , s)){
                matches.add(i + s.length() - 1);
                i += s.length();
            } else {
                i++;
            }
        }
        return matches;
    }

    public static void main(String[] args) {
        replace replace = new replace();
        String input = "appledog";
        String source = "apple";
        String target = "cat";
        System.out.println(replace.replace(input,source,target));
    }
}
