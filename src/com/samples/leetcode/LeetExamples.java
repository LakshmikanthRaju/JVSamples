package com.samples.leetcode;

import java.util.*;

public class LeetExamples {

    private static LeetExamples examples = new LeetExamples();

    private LeetExamples() {}

    public static LeetExamples getInstance() {
        return examples;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int val) { this.val = val; }
    }

    public void testSamples() {
        //System.out.println("Hello world " + lengthOfLongestSubstring("pwwkew"));
        //System.out.println("Number: " + romanToInt("MCMXCIV"));
        String s[] = {"flower", "flow", "flight"};
        int[] heights = new int[]{1,1};
        //System.out.println(intToRoman(9));
        //System.out.println(addBinary("1", "111"));
        int[] values = {1,2,-1,-2,2,1,-2,1,4,-5,4};
        //System.out.println(maxSubArray(values));
        int[] nums1 = {4,5,6,0,0,0};
        int[] nums2 = {1,2,3};
        //merge(nums1, 3, nums2, 3);
        //test("7.0.3.00600");
        //System.out.println(getRow(7));
        //System.out.println(canConstruct("aa", "ab"));
        //int[] val = {2,3,1};
        //nextPermutation(val);
        int[] val = {1,3};
        //System.out.println(Arrays.toString(searchRange(val, 1)));
        char[][] sudoku = {{'7','.','.','.','4','.','.','.','.'},{'.','.','.','8','6','5','.','.','.'},{'.','1','.','2','.','.','.','.','.'},{'.','.','.','.','.','9','.','.','.'},{'.','.','.','.','5','.','5','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','2','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'}};
        //System.out.println(isValidSudoku(sudoku));
        //rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        //System.out.println(spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
        //merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        //generateMatrix(3);
        System.out.println(freqAlphabets(""));
    }

    public String freqAlphabets(String s) {
        Map<String, Character> maps = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            maps.put(String.valueOf(i), (char)('a'-1+i));
        }

        int i = 0;
        String result = "";
        while (i < s.length()) {
            char letter = s.charAt(i);
            if (letter > '2' && letter <= '9') {
                result = result + maps.get(Character.toString(letter));
                i++;
            } else if (letter == '1' || letter == '2') {
                if (i == s.length()-1) {
                    result = result + maps.get(Character.toString(letter));
                    break;
                }
                String temp = "";
                while (i < s.length()) {
                    if (s.charAt(i) == '#') {
                        i++;
                        break;
                    }
                    temp = temp + Character.toString(s.charAt(i));
                    i++;
                }
                for (int k = 0; k < temp.length()-2; k++) {
                    result = result + maps.get(Character.toString(letter));
                }
                temp = temp.substring(temp.length()-2);
                result = result + maps.get(temp);
            }
        }
        return result;
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        for (int len = 1; len <= arr.length; len = len + 2) {
            for (int i = 0; i <= arr.length-len; i++) {
                for (int j = i; j < i+len; j++) {
                    System.out.print("len=" + len);
                    System.out.print(" ,i=" + i);
                    System.out.println(" ,j=" + j);
                    sum = sum + arr[j];
                }
                System.out.println("sum=" + sum);
            }
            System.out.println("   sum=" + sum);
        }
        return sum;
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int num = 1;
        int rowStart = 0, rowEnd = n, colStart = 0, colEnd = n;
        while (rowStart < rowEnd && colStart < colEnd) {
            for (int i = colStart; i < colEnd; i++) {
                result[rowStart][i] = num++;
            }
            printMatrix(result);
            rowStart++;
            for (int i = rowStart; i < rowEnd; i++) {
                result[i][colEnd-1] = num++;
            }
            printMatrix(result);
            colEnd--;
            if (rowStart < rowEnd) {
                for (int i = colEnd-1; i >= colStart; i--) {
                    result[rowEnd-1][i] = num++;
                }
                printMatrix(result);
                rowEnd--;
            }
            if (colStart < colEnd) {
                for (int i = rowEnd-1; i >= rowStart; i--) {
                    result[i][colStart] = num++;
                }
                printMatrix(result);
                colStart++;
            }
        }
        return result;
    }

    public int[][] merge(int[][] intervals) {
        //Arrays.sort(intervals, (a,b) ->  Integer.compare(a[0], b[0]));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        //List<List<Integer>> result = new ArrayList<>();
        LinkedList<int[]> result = new LinkedList<>();
        int i = 0;
        while (i < intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] < end) {
                end = intervals[j][1];
                j++;
            }
            //result.add(Arrays.asList(start, end));
            result.add(new int[]{start, end});
            i = j;
        }
        /*int[][] ans = new int[result.size()][2];
        i = 0;
        for (List<Integer> item: result) {
            ans[i][0] = item.get(0);
            ans[i][1] = item.get(1);
            System.out.println(Arrays.toString(ans[i]));
            i++;
        }
        return ans;*/
        return result.toArray(new int[result.size()][]);
    }

    public boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (max <= i && nums[i] == 0) {
                return false;
            }
            if (max < i+nums[i]) {
                max = i + nums[i];
            }
            if (max >= nums.length-1) {
                return true;
            }
        }
        return true;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }
        int m = matrix.length, n = matrix[0].length;
        int rowStart = 0, colStart = 0, rowEnd = m, colEnd = n;
        int k;
        while (rowStart<rowEnd && colStart<colEnd) {
            for (k = colStart; k < colEnd; k++) {
                result.add(matrix[rowStart][k]);
            }
            rowStart++;
            for (k = rowStart; k < rowEnd; k++) {
                result.add(matrix[k][colEnd-1]);
            }
            colEnd--;
            if (rowStart<rowEnd) {
                for (k = colEnd-1; k >=colStart; k--) {
                    result.add(matrix[rowEnd-1][k]);
                }
                rowEnd--;
            }
            if (colStart<colEnd) {
                for (k = rowEnd-1; k >=rowStart; k--) {
                    result.add(matrix[k][colStart]);
                }
                colStart++;
            }
        }
        return result;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteUtil(nums, 0, result);
        return result;
    }

    private void permuteUtil(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for(int num: nums){
                list.add(num);
            }
            result.add(list);
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            permuteUtil(nums, index+1, result);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int k=0; k<n;k++) {
            System.out.println(Arrays.toString(matrix[k]));
        }
        System.out.println("");
        for (int i = 0 ; i < (n+1)/2; i++) {
            for (int j = 0; j < n/2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
            for (int k=0; k<n;k++) {
                System.out.println(Arrays.toString(matrix[k]));
            }
            System.out.println("");
        }
    }

    public boolean isValidSudoku(char[][] board) {
        boolean[] valid = new boolean[9];
        for (int i = 0; i < 9; i++) {
            valid = new boolean[9];
            System.out.println(Arrays.toString(board[i]));
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (valid[board[i][j]-'1']) {
                        System.out.println("hori");
                        return false;
                    }
                    valid[board[i][j]-'1'] = true;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            valid = new boolean[9];
            //System.out.println(Arrays.toString(board[i]));
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    if (valid[board[j][i]-'1']) {
                        System.out.println("vert");
                        return false;
                    }
                    valid[board[j][i]-'1'] = true;
                }
            }
        }
        for (int k = 0; k < 9; k++) {
            valid = new boolean[9];
            int idx = (k/3);
            int jdx = (k%3);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[(idx*3)+i][(jdx*3)+j] != '.') {
                        if (valid[board[(idx*3)+i][(jdx*3)+j]-'1']) {
                            System.out.println("box");
                            return false;
                        }
                        valid[board[(idx*3)+i][(jdx*3)+j]-'1'] = true;
                    }
                }
            }
        }
        return true;
    }

    public int[] searchRange(int[] nums, int target) {
        int start = 0, end = nums.length-1, mid = -1;
        while (start <= end) {
            mid = start + (end-start)/2;
            System.out.println(mid);
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] < target) {
                start = mid +1;
            } else {
                end = mid -1;
            }
            System.out.println(start);
            System.out.println(end);

        }
        System.out.println("");
        if (nums[mid] != target) {
            return new int[]{-1,-1};
        }
        start = mid;
        end = mid;
        while (start > 0) {
            if (nums[start-1] == target) {
                start--;
            } else {
                break;
            }
        }
        while (end < nums.length-1) {
            if (nums[end+1] == target) {
                end++;
            } else {
                break;
            }
        }
        return new int[]{start,end};
    }

    public void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int idx = nums.length-1;
        int temp = 0, i = idx-1;
        while (idx>=0) {
            while (i>=0) {
                if (nums[i] < nums[idx]) {
                    break;
                }
                i--;
            }
            if (i>=0) {
                break;
            } else {
                idx--;
                i = idx-1;
            }
        }
        System.out.println(i);
        System.out.println(idx);
        if (i >= 0) {
            temp = nums[i];
            nums[i] = nums[idx];
            i++;
            System.out.println(Arrays.toString(nums));
            while (i <= idx) {
                int temp2 = nums[i];
                nums[i] = temp;
                temp = temp2;
                i++;
                System.out.println(Arrays.toString(nums));
            }
        } else {
            for (i = 0; i < nums.length/2; i++) {
                temp = nums[i];
                nums[i] = nums[nums.length-i-1];
                nums[nums.length-i-1] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public int arrangeCoins(int n) {
        if (n == 1) return n;
        int row = 0, i = 1, total = n;
        while (i < total) {
            System.out.println("N: " + n);
            System.out.println("i: " + i);
            System.out.println("row: " + row);
            if (n-i < 0) {
                System.out.println("Return: " + row);
                return row;
            }
            n = n - i;
            row = row +1;
            i++;
            System.out.println("N: " + n);
            System.out.println("i: " + i);
            System.out.println("row: " + row);
        }
        return row;
    }

    public int countSegments(String s) {
        if (s.length() == 0) {
            return 0;
        }
        System.out.println(Arrays.toString(s.split("\\s+")));
        return s.trim().split("\\s+").length;
    }

    public int longestPalindrome(String s) {
        Map<Character,Integer> palMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            palMap.put(s.charAt(i), palMap.getOrDefault(s.charAt(i),0)+1);
        }
        int oddCount = 0;
        for (Character c: palMap.keySet()) {
            if (palMap.get(c)%2 != 0) {
                oddCount++;
            }
        }
        return (oddCount == 0) ? s.length() : s.length()-oddCount+1;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        for (int i = 0; i < ransomNote.length(); i++) {
            int idx = magazine.indexOf(ransomNote.charAt(i));
            if (idx < 0) {
                return false;
            }
            magazine = magazine.replaceFirst(Character.toString(ransomNote.charAt(i)), " ");
        }
        return true;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int [] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> pascal = new ArrayList<>();
        int mid = rowIndex/2;
        for (int i = 0; i <= mid; i++) {
            pascal.add(getValue(i, rowIndex));
        }
        int i = (rowIndex-1)/2;
        while(i>=0) {
            pascal.add(pascal.get(i--));
        }
        return pascal;
    }

    private int getValue(int index, int rowIndex) {
        if (index == 0 || rowIndex == 0 || index == rowIndex) {
            return 1;
        }
        return getValue(index-1, rowIndex-1) + getValue(index, rowIndex-1);
    }

    public void test(String version) {
        String[] info = version.split("\\.");
        System.out.println(Integer.parseInt(info[0]));
        System.out.println(Integer.parseInt(info[1]));
        System.out.println(Integer.parseInt(info[2]));
        if (info.length == 4) {
            System.out.println(Integer.parseInt(info[3]));
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        if (m == 0) {
            for (int i = 0; i < n; i++)
                nums1[i] = nums2[i];
            return;
        }
        int i = m-1;
        int j = n-1;
        int idx = m+n-1;
        while (i>= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[idx] = nums1[i];
                i--;
            } else {
                nums1[idx] = nums2[j];
                j--;
            }
            idx--;
            printList(nums1, m+n);
            printList(nums2, n);
            System.out.println(i + " " + j + " " + idx);
        }
        while (i>=0) {
            nums1[idx--] = nums1[i];
            i--;
        }
        printList(nums1, m+n);
        printList(nums2, n);
        while (j>=0) {
            nums1[idx--] = nums2[j];
            printList(nums1, m+n);
            printList(nums2, n);
            System.out.println(i + " " + j + " " + idx);
            j--;
        }
        printList(nums1, m+n);
        printList(nums2, n);
    }
    private void printList(int[] nums1, int m) {
        for (int k=0; k < m; k++) {
            System.out.print(nums1[k]);
        }
        System.out.println("   ====> Array " + m);
    }

    public int maxSubArray(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        System.out.println(sum);
        int i = 0, j = nums.length-1, finalSum = sum;
        while (i<j) {
            int m = i, n = j;
            if (nums[i] == nums[j]) {
                while (m<n && nums[m] == nums[n]) {
                    m++;
                    n--;
                }
            }
            if (nums[m] < nums[n]) {
                sum = sum - nums[i];
                i++;
            } else {
                sum = sum - nums[j];
                j--;
            }

            if (finalSum < sum) {
                finalSum = sum;
            }
        }
        return finalSum;
    }

    public String addBinary(String a, String b) {
        String result = "";
        int aLast = a.length()-1;
        int bLast = b.length()-1;
        int carryOn = 0, i = 0;
        int aNum = 0, bNum = 0, sum = 0;
        while (i <= aLast || i <= bLast) {
            aNum = (i > aLast) ? 0 : a.charAt(aLast-i)-'0';
            bNum = (i > bLast) ? 0 : b.charAt(bLast-i)-'0';
            sum = aNum + bNum + carryOn;
            if (sum == 2) {
                result = "0" + result;
                carryOn = 1;
            } else if (sum == 3) {
                result =  "1" + result;
                carryOn = 1;
            } else {
                result = sum + result;
                carryOn = 0;
            }
            System.out.println(result);
            i++;
        }
        return (carryOn == 1) ? "1" + result : result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> values = new ArrayList<List<Integer>>();
        for (int i = 0 ;i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (i != j && j != k && i != k) {
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            values.add(Arrays.asList(i, j, k));
                        }
                    }
                }
            }
        }
        return values;
    }
    
    public String intToRoman(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        String numValue = "";

        for(int i = 0; i < values.length;i++) {
            while (num >= values[i]) {
                numValue = numValue + romans[i];
                num = num - values[i];
            }
        }
        return numValue;
    }

    public String intToRoman2(int num) {
        Map<Integer,String> maps = new HashMap();
        maps.put(1,"I");
        maps.put(5,"V");
        maps.put(10, "X");
        maps.put(50, "L");
        maps.put(100, "C");
        maps.put(500, "D");
        maps.put(1000, "M");

        String value = "";
        int i = 1;
        while (num > 0) {
            int temp = num%10;
            if (temp < 4) {
                value = getValue(temp, maps.get(i)) + value;
            } else if (temp == 4) {
                value = maps.get(i) + maps.get(i*5) + value;
            } else if (temp == 5) {
                value = maps.get(i*5) + value;
            } else if (temp < 9) {
                value = maps.get(i*5) + getValue(temp%5, maps.get(i)) + value;
            } else {
                value = maps.get(i) + maps.get(i*10) + value;
            }
            i = i * 10;
            num = num/10;
        }
        return value;
    }

    private String getValue(int times, String value) {
        String finalValue = "";
        while (times > 0) {
            finalValue += value;
            times--;
        }
        return finalValue;
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int x = 0, y = height.length-1;
        //System.out.println(String.format("%d - %d", x,y));
        //System.out.println(String.format("%d - %d", height[x], height[x]));
        while (x<y) {
            int high = Math.min(height[x], height[y]);// (height[x] < height[y]) ? height[x] : height[y];
            if (maxArea < (high*(y-x))) {
                maxArea = (high*(y-x));
            }
            if (height[x] < height[y]) {
                x++;
            } else {
                y--;
            }
        }
        return maxArea;
    }

    public int myAtoi(String s) {
        if (s == null || s == "") return 0;
        int len = s.length();
        boolean isPositive = true;
        int value = 0;
        int i = 0;
        while (s.charAt(i) == ' ') {
            i++;
        }
        while(i < len) {
            if (s.charAt(i) == '-') {
                isPositive = false;
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if (value > Integer.MAX_VALUE/10 ) {
                    return (isPositive) ? Integer.MAX_VALUE: Integer.MIN_VALUE;
                } else {
                    value = (value*10) + (s.charAt(i)-'0');
                }
            }
            i++;
        }
        return (isPositive) ? value: -value;
    }

    public int reverse(int x) {
        if (x > 0) {
            return reverseProcess(x);
        } else if (x<0) {
            return -reverseProcess(-x);
        }
        return x;
    }

    public int reverseProcess(int x) {
        int val = 0;
        while(x>0) {
            if (val < 0) {
                return 0;
            }
            val = (val*10) + (x%10);
            x = x/10;
            System.out.println(val);
        }
        return val;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                //System.out.println(S[i] + " indexOf " + prefix + " = " + S[i].indexOf(prefix));
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        for (char c: romanMap.keySet()) {

        }

        int num = 0, i;

        for (i = 0; i < s.length()-1; i++) {
            int value = romanMap.get(s.charAt(i));
            int nextValue = romanMap.get(s.charAt(i+1));
            if (nextValue > value) {
                num = num + nextValue - value;
                i++;
            } else {
                num = num + value;
            }
        }
        if (i < s.length()) {
            num = num + romanMap.get(s.charAt(i));
        }
        return num;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> hashSet = new HashSet<>();
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            hashSet.add(s.charAt(i));
            System.out.println(hashSet);
            for (int j = i+1; j < s.length(); j++) {
                System.out.println(hashSet);
                if (hashSet.contains(s.charAt(j))) {
                    break;
                } else {
                    System.out.println(hashSet);
                    hashSet.add(s.charAt(j));
                }
            }
            if (hashSet.size() > length) {
                length = hashSet.size();
            }
            hashSet.clear();
        }
        return length;
    }

    public int lengthOfLongestSubstring_1(String s) {
        return 0;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    public int[] twoSum_1(int[] nums, int target) {
        return new int[] {0,0};
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        int sum = l1.val + l2.val;
        int carryOn = sum/10;
        ListNode l = new ListNode(sum%10);
        ListNode temp = l;

        while (l1.next != null && l2.next != null) {
            sum = l1.next.val + l2.next.val + carryOn;
            carryOn = sum / 10;
            temp.next = new ListNode(sum%10);
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1.next == null) {
            while (l2.next != null) {
                sum = l2.next.val + carryOn;
                carryOn = sum / 10;
                temp.next = new ListNode(sum%10);
                temp = temp.next;
                l2 = l2.next;
            }
        }

        if (l2.next == null) {
            while (l1.next != null) {
                sum = l1.next.val + carryOn;
                carryOn = sum / 10;
                temp.next = new ListNode(sum%10);
                temp = temp.next;
                l1 = l1.next;
            }
        }

        if (carryOn != 0) {
            temp.next = new ListNode(carryOn);
        }

        return l;
    }

    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0);
        int value1, value2, sum, carryOn = 0;
        while (l1.next != null || l2.next != null) {
            value1 = (l1.next == null) ? 0 : l1.next.val;
            value2 = (l2.next == null) ? 0 : l2.next.val;
            sum = value1 + value2 + carryOn;
            carryOn = sum / 10;
            temp.next = new ListNode(sum%10);
            temp = temp.next;
            if (l1.next != null)  l1 = l1.next;
            if (l2.next != null)  l2 = l2.next;
        }
        return temp.next;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        Stack<Integer> stk = new Stack<>();
        int temp = x;
        while (temp > 0) {
            stk.push(temp%10);
            temp = temp/10;
        }
        temp = x;
        while(temp > 0) {
            if (temp%10 != stk.pop()) {
                return false;
            }
            temp = temp/10;
        }
        return true;
    }

    public boolean isPalindrome_1(int x) {
        if (x < 0) return false;
        if (x != 0 && x%10 == 0) return false;

        int rev = 0;
        while (x > rev) {
            rev = (rev *10) + x%10;
            x = x / 10;
        }
        return x == rev || x == rev / 10;
    }
}
