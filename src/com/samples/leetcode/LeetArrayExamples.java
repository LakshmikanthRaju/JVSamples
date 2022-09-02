package com.samples.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class LeetArrayExamples {

    private static LeetArrayExamples examples = new LeetArrayExamples();

    private LeetArrayExamples() {}

    public static LeetArrayExamples getInstance() {
        return examples;
    }

    public void testSamples() {
        int num = 15432;
        int[] arr = Integer.toString(num).chars().map(c -> c-'0').toArray();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(arr).forEach(element -> sb.append(element));
        int result = Integer.parseInt(sb.toString());
        //System.out.println(numIslands(new char[][]{}));
        //System.out.println(uniquePathsWithObstacles(new int[][]{{1},{0}})); 0 0    1 0
        //System.out.println(simplifyPath("/abc/../d/"));                     1 0    0 0
        //System.out.println(simplifyPath("/../"));
        //System.out.println(maximumUnits(new int[][]{{1,3},{2,2},{3,1}}, 4));
        //System.out.println(Arrays.toString(sortByBits(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8})));
        //System.out.println(characterReplacement("ABAB", 2));
        /*System.out.println(exist(new char[][]{{'A','B','C','E'},
                                              {'S','F','E','S'},
                                              {'A','D','E','E'}}, "ABCESEEEFS"));*/
        //System.out.println(multiply("13", "13"));
        //System.out.println(addToArrayForm(new int[]{1,2,0,0}, 34));
        System.out.println(Arrays.deepToString(kClosest(new int[][]{{-95,76},{17,7},{-55,-58},{53,20},{-69,-8},{-57,87},{-2,-42},{-10,-87},{-36,-57},{97,-39},{97,49}}, 5)));
    }

    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;
        int[] dist = new int[n];
        for (int i = 0; i < n ;i++) {
            dist[i] = (points[i][0]*points[i][0])+(points[i][1]*points[i][1]);
        }
        Arrays.sort(dist);
        int distK = dist[k-1];
        System.out.println(Arrays.toString(dist));
        System.out.println(distK);
        int[][] result = new int[k][2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int dis = (points[i][0]*points[i][0])+(points[i][1]*points[i][1]);
            System.out.println(dis);
            if (dis <= distK) {
                result[idx][0] = points[i][0];
                result[idx][1] = points[i][1];
                idx++;
            }
        }
        return result;
    }

    public int[][] kClosest2(int[][] points, int k) {
        if (points.length == 0 || k <= 0) {
            return new int[][]{};
        }
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> b[0]-a[0]);
        for (int i = 0; i < points.length; i++) {
            int[] entry = {squareDistance(points[i]), i};
            if (pq.size() < k) {
                pq.add(entry);
            } else if (entry[0] < pq.peek()[0]) {
                pq.poll();
                pq.add(entry);
            }
        }
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            int idx = pq.poll()[1];
            result[i] = points[idx];
        }
        return result;
    }

    private int squareDistance(int[] point) {
        return (point[0]*point[0])+(point[1]*point[1]);
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        if (mat.length != target.length) {
            return false;
        }
        if (mat.length == 0) {
            return true;
        }
        int n = mat.length;
        boolean p = true, q = true, r = true, s = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) {
                    //System.out.println("1- " + i + " " + j + " => " + mat[i][j] + " " + target[i][j]);
                    p = false;
                }
                if (mat[i][j] != target[j][n-i-1]) {
                    System.out.println("2- " + i + " " + j + " => " + mat[i][j] + " " + target[n-j-1][i]);
                    q = false;
                }
                if (mat[i][j] != target[n-i-1][n-j-1]) {
                    //System.out.println("3- " + i + "," + j + " => " + (n-i-1) + "," + (n-j-1));
                    r = false;
                }
                if (mat[i][j] != target[n-j-1][i]) {
                    //System.out.println("4- " + i + " " + j + " => " + mat[i][j] + " " + target[n-j-1][i]);
                    s = false;
                }
            }
        }
        return p|q|r|s;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0],newInterval[1]}};
        }
        List<int[]> result = new LinkedList<>();

        for (int[] interval: intervals) {
            if (interval[1] < newInterval[0]) {
                result.add(interval);
            } else if (interval[0] > newInterval[1]) {
                result.add(newInterval);
                newInterval = interval;
            } else if (interval[1] >= newInterval[0] || interval[0] <= newInterval[1]) {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        result.add(newInterval);
        return result.toArray(new int[result.size()][]);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new LinkedList<>();
        for (int[] interval: intervals) {
            if (result.isEmpty() || result.get(result.size()-1)[1] < interval[0]) {
                result.add(interval);
            } else {
                result.get(result.size()-1)[1] = Math.max(result.get(result.size()-1)[1], interval[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> result = new ArrayList<>();

        int i = num.length;
        while (--i >= 0 || k > 0) {
            if (i >= 0) {
                k = k + num[i];
            }
            result.add(0, k%10);
            k=k/10;
        }
        return result;
    }

    public void checkFileBitsChanged() {
        byte[] file1 = new byte[10000];
        Arrays.fill(file1, (byte) 10);
        byte[] file2 = new byte[10000];
        Arrays.fill(file2, (byte) 10);
        long aa = 65536, b = 65540, c;
        byte a = 127;
                int bb = 0xffffffff;
        System.out.println((7|0xff)==0xff);
        List<Integer> res = new ArrayList<>();
    }

    public String multiply(String num1, String num2) {

        String[] results = new String[num1.length()*num2.length()];
        int maxLen = 0, k =0;
        int factor = num1.length()+num2.length()-2;
        for (int i = num1.length()-1; i >= 0; i--) {
            for (int j = num2.length()-1; j >= 0; j--) {
                double result = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                //result = result * Math.pow(10, factor-(i+j));
                String num = String.valueOf(Math.round(result));
                if (factor-(i+j) > 0) {
                    char[] zeros = new char[factor-(i+j)];
                    Arrays.fill(zeros, '0');
                    num = num + String.valueOf(zeros);
                }
                results[k++] = num;
                maxLen = Math.max(maxLen, num.length());
            }
        }
        System.out.println(Arrays.toString(results));
        int carry = 0;
        String prod = "";
        for (int i = 0; i < maxLen; i++) {
            int sum = carry;
            for (int j = 0; j < results.length; j++) {
                int idx = (results[j].length()-1-i);
                int num = (idx>=0) ? results[j].charAt(idx)-'0':0;
                sum = sum + num;
            }
            prod = String.valueOf(sum%10) + prod;
            carry = sum/10;
        }
        if (carry > 0) {
            prod = String.valueOf(carry) + prod;
        }
        return prod.replaceAll("^0*", "").isEmpty() ? "0" : prod.replaceAll("^0*", "");
    }

    public boolean backspaceCompare(String s, String t) {
        int i = 0, j = 0;
        StringBuilder sTxt = new StringBuilder();
        StringBuilder tTxt = new StringBuilder();
        while (i < s.length()) {
            if (s.charAt(i) == '#') {
                if (sTxt.length() > 0) {
                    sTxt.deleteCharAt(sTxt.length()-1);
                }
            } else {
                sTxt.append(s.charAt(i));
            }
            i++;
        }
        while (j < t.length()) {
            if (t.charAt(j) == '#') {
                if (tTxt.length() > 0) {
                    tTxt.deleteCharAt(tTxt.length()-1);
                }
            } else {
                tTxt.append(t.charAt(j));
            }
            j++;
        }
        return sTxt.toString().equals(tTxt.toString());
    }

    public boolean repeatedSubstringPattern(String s) {
        for (int size = 1; size <= s.length()/2; size++) {
            System.out.println("1. checking for " + size);
            if (s.length()%size == 0) {
                System.out.println("checking for " + size);
                String word = s.substring(0,size);
                int j = size;
                while (j<s.length() && s.substring(j,j+size).equals(word)) {
                    j = j + size;
                }
                System.out.println("checked till " + j);
                if (j == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String decodeString(String s) {
        Stack<Integer> counts = new Stack<>();
        Stack<String> strs = new Stack<>();
        String result = "";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            System.out.println(i);
            System.out.println(result);
            System.out.println(counts);
            System.out.println(strs);
            if (Character.isDigit(s.charAt(i))) {
                count = count*10 + s.charAt(i)-'0';
            } else if (s.charAt(i) == '[') {
                counts.push(count);
                strs.push(result);
                count = 0;
                result = "";
            } else if (s.charAt(i) == ']') {
                int k = counts.pop();
                StringBuilder next = new StringBuilder(strs.pop());
                for (int j = 0; j < k; j++) {
                    next.append(result);
                }
                result = next.toString();
            } else {
                result = result + s.charAt(i);
            }
        }
        return result;
    }

    public int characterReplacement(String s, int k) {
        int k1 = k;
        int maxLen = 0;
        char c = s.charAt(0);
        int len = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                len++;
            } else {
                if (k1 == 0) {
                    maxLen = Math.max(len, maxLen);
                    c = s.charAt(i);
                    len = 1;
                    k1 = k;
                } else {
                    k1--;
                    len++;
                }
            }
        }
        maxLen = Math.max(len, maxLen);
        return maxLen;
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> nums = new Stack<>();

        for (String token: tokens) {
            int operator = token.charAt(0);
            if(operator == '+') {
                nums.push(nums.pop() + nums.pop());
            } else if(operator == '-') {
                int num = nums.pop();
                nums.push(nums.pop() - num);
            } else if(operator == '*') {
                nums.push(nums.pop() * nums.pop());
            } else if(operator == '/') {
                int num = nums.pop();
                nums.push(nums.pop() / num);
            } else {
                nums.push(Integer.parseInt(token));
            }
            System.out.println(nums);
        }
        return nums.pop();
    }

    public List<Integer> grayCode(int n) {
        List<Integer> results = new ArrayList<>();
        Integer[] resultArr = results.toArray(new Integer[0]);
        results.add(0);
        results.add(1);
        //System.out.println((int)Math.pow(2, 2));
        //System.out.println(1<<2);
        for (int i = 1; i < n; i++) {
            //int len = results.size()-1;
            int base = 1<<i;
            for (int j= results.size()-1; j >= 0; j--) {
                results.add(results.get(j)+base);
            }
        }
        return results;
    }

    public boolean exist(char[][] board, String word) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    Set<Integer> current = new HashSet<>();
                    current.add(i*10+j);
                    calculate(board, word, 1, i, j, current, result);
                }
            }
        }
        return !result.isEmpty();
    }

    private void calculate(char[][] board, String word, int index, int x, int y, Set<Integer> current, List<String> result) {
        if (index == word.length()) {
            System.out.println(word);
            result.add(word);
            return;
        }

        if (x > 0 && board[x-1][y] == word.charAt(index) && !current.contains((x-1)*10+y)) {
            current.add((x-1)*10+y);
            System.out.print(index + "--");
            System.out.print(current);
            System.out.println("");
            calculate(board, word, index+1, x-1, y, current, result);
        }
        if (y > 0 && board[x][y-1] == word.charAt(index) && !current.contains(x*10+y-1)) {
            current.add(x*10+y-1);
            System.out.print(index + "--");
            System.out.print(current);
            System.out.println("");
            calculate(board, word, index+1, x, y-1, current, result);
        }
        if (x < board.length-1 && board[x+1][y] == word.charAt(index) && !current.contains((x+1)*10+y)) {
            current.add((x+1)*10+y);
            System.out.print(index + "--");
            System.out.print(current);
            System.out.println("");
            calculate(board, word, index+1, x+1, y, current, result);
        }
        if (y < board[0].length-1 && board[x][y+1] == word.charAt(index) && !current.contains(x*10+y+1)) {
            current.add(x*10+y+1);
            System.out.print(index + "--");
            System.out.print(current);
            System.out.println("");
            calculate(board, word, index+1, x, y+1, current, result);
        }
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int maxLen = 2, len = 2;
        boolean isPositive = (nums[1]-nums[0]) > 0;
        for (int i = 2; i < nums.length; i++) {
            if (!isPositive == ((nums[i]-nums[i-1]) > 0)) {
                System.out.print(i + " ");
                System.out.print(isPositive);
                System.out.println("");
                len++;
                maxLen = Math.max(maxLen, len);
            } else {
                maxLen = Math.max(maxLen, len);
                len = 0;
            }
            isPositive = (nums[i]-nums[i-1]) > 0;
        }
        return maxLen;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> count = new ArrayList<>();
        List<Character> cur = p.chars().mapToObj((c) -> (char) c).collect(Collectors.toList());
        int i = 0, idx = 0;
        while (i < s.length()) {
            if (idx == p.length()) {
                count.add(i-idx);
                i = i-idx+1;
                idx = 0;
                cur = p.chars().mapToObj((c) -> (char) c).collect(Collectors.toList());
            } else {
                if (cur.contains(s.charAt(i))) {
                    idx = idx +1;
                    cur.remove(Character.valueOf(s.charAt(i)));
                    i++;
                }  else {
                    if (p.indexOf(s.charAt(i)) == -1) {
                        i++;
                    } else {
                        i = i-idx+1;
                    }
                    idx = 0;
                    cur = p.chars().mapToObj((c) -> (char) c).collect(Collectors.toList());
                }
            }
        }
        if (idx == p.length()) {
            count.add(i-idx);
        }
        return count;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> count = new ArrayList<>();
        int i = 0, idx = 0;
        List<Character> cur = p.chars().mapToObj((c) -> (char) c).collect(Collectors.toList());
        while (i < s.length()) {
            System.out.println(i);
            System.out.println(idx);
            System.out.println(s.charAt(i));
            //System.out.println(p.charAt(idx));
            System.out.println(cur);
            if (idx == p.length()) {
                count.add(i-idx);
                i = i-idx+1;
                idx = 0;
                cur = p.chars().mapToObj((c) -> (char) c).collect(Collectors.toList());
            } else {
                if (cur.contains(s.charAt(i))) {
                    idx = idx +1;
                    cur.remove(Character.valueOf(s.charAt(i)));
                    i++;
                }  else {
                    if (p.indexOf(s.charAt(i)) == -1) {
                        i++;
                    } else {
                        i = i-idx+1;
                    }
                    idx = 0;
                    cur = p.chars().mapToObj((c) -> (char) c).collect(Collectors.toList());
                }
            }
        }
        if (idx == p.length()) {
            count.add(i-idx);
        }
        return count;
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        final int mod = (int) (1e9 + 7);
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int ht = horizontalCuts[0];
        int wd = verticalCuts[0];
        for (int i = 1; i < horizontalCuts.length; i++) {
            if (horizontalCuts[i]-horizontalCuts[i-1] > ht) {
                ht = horizontalCuts[i]-horizontalCuts[i-1];
            }
        }
        if (h-horizontalCuts[horizontalCuts.length-1] > ht) {
            ht = h - horizontalCuts[horizontalCuts.length-1];
        }
        for (int i = 1; i < verticalCuts.length; i++) {
            if (verticalCuts[i]-verticalCuts[i-1] > wd) {
                wd = verticalCuts[i]-verticalCuts[i-1];
            }
        }
        if (w-verticalCuts[verticalCuts.length-1] > wd) {
            wd = w-verticalCuts[verticalCuts.length-1];
        }
        System.out.println((long)ht*(long)wd);
        long area = ((long)ht*(long)wd)%mod;
        System.out.println(area);
        return (int)(((long)ht*(long)wd)%mod);
    }

    public int[] sortByBits(int[] arr) {
        for (int i = 0; i< arr.length-1; i++) {
            for (int j = i+1; j< arr.length; j++) {
                if (isBig(arr[i], arr[j])) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    private boolean isBig(int a, int b) {
        int xbits = 0, ybits = 0, x = a, y = b;
        while (x > 0) {
            xbits = xbits + x%2;
            x = x / 2;
        }
        while (y > 0) {
            ybits = ybits + y%2;
            y = y / 2;
        }
        if (xbits == ybits) {
            return a>b;
        }
        return xbits > ybits;
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a,b) -> -Integer.compare(a[1], b[1]));
        for (int[] boxType : boxTypes) {
            System.out.println(Arrays.toString(boxType));
        }
        int units = 0; int boxes = 0;
        for (int[] boxType : boxTypes) {
            if (boxes + boxType[0] >= truckSize) {
                units = units + ((truckSize - boxes) * boxType[1]);
                break;
            }
            boxes = boxes + boxType[0];
            units = units + (boxType[0] * boxType[1]);
        }
        return units;
    }

    public int removeDuplicates(int[] nums) {
        int pos = 1, i = 1;
        boolean isNew = true;
        while (i < nums.length) {
            if (nums[i] == nums[i-1]) {
                if (isNew) {
                    isNew = false;
                } else {
                    while (i < nums.length && nums[i] == nums[i-1]) {
                        i++;
                    }
                    if (i == nums.length) {
                        i--;
                    }
                    isNew = true;
                }
                nums[pos++] = nums[i++];
            } else {
                nums[pos++] = nums[i++];
                isNew = true;
            }
            System.out.println(Arrays.toString(nums));
            System.out.println(i);
            System.out.println(pos);
            System.out.println(isNew);
            System.out.println("");
        }
        return pos;
    }

    public int minMoves2(int[] nums) {
        double median = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        median = (double) sum/nums.length;
        int mean = (int) Math.round(median);
        int moves = 0;
        for (int i = 0; i < nums.length; i++) {
            moves = moves + Math.abs(nums[i]-mean);
        }
        return moves;
    }

    public String simplifyPath(String path) {
        path = path.replace("/+","/");
        path = path.replace("/./","/");
        path = path.replace("/[a-zA-Z0-9_]+/../","/");
        //path = path.replace("^/../","/");
        if (path.charAt(path.length()-1) == '/') {
            path = path.substring(0, path.length()-1);
        }
        return path;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int path = 1;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                path = 0;
            }
            obstacleGrid[0][i] = path;
        }
        System.out.println(m);
        System.out.println(n);
        for (int i = 0; i < obstacleGrid.length; i++) {
            System.out.print(Arrays.toString(obstacleGrid[i]));
        }
        System.out.println("");
        path = (obstacleGrid[0][0] == 0) ? 0 : 1;
        for (int j = 1; j < m; j++) {
            if (obstacleGrid[j][0] == 1) {
                path = 0;
            }
            obstacleGrid[j][0] = path;
        }
        for (int i = 0; i < obstacleGrid.length; i++) {
            System.out.print(Arrays.toString(obstacleGrid[i]));
        }
        System.out.println("");
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                }
            }
        }
        return obstacleGrid[m-1][n-1];
    }

    public int minPathSum(int[][] grid) {
        int[] sum = new int[] {Integer.MAX_VALUE, 0};
        processPath(grid, sum, 0, 0);
        return sum[0];
    }

    private void processPath(int[][] grid, int[] sum, int x, int y) {
        if (x == grid.length-1 && y == grid[0].length-1) {
            System.out.println(sum[1]+grid[x][y]);
            sum[0] = Math.min(sum[0], sum[1]+grid[x][y]);
            //System.out.println(sum[0]);
            return;
        }
        sum[1] = sum[1] + grid[x][y];
        //System.out.println(sum[1]);
        if (y < grid[0].length-1) {
            processPath(grid, sum, x, y+1);
        }
        if (x < grid.length-1) {
            processPath(grid, sum, x+1, y);
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int islands = 0;
        for (int i = 0; i < m; i++) {
            for (int j= 0; j < n; j++) {
                visited[i][j] = (grid[i][j] == '0');
            }
        }
        int[] nextPoint = getNext(visited, m, n);
        while (nextPoint[0] != -1 && nextPoint[1] != -1) {
            coverArea(grid, visited, nextPoint[0], nextPoint[1]);
            islands++;
            nextPoint = getNext(visited, m, n);
        }
        return islands;
    }

    private int[] getNext(boolean[][] visited, int m, int n) {
        int[] nextPoint = new int[]{-1, -1};
        for (int i = 0; i < m; i++) {
            for (int j= 0; j < n; j++) {
                if (!visited[i][j]) {
                    nextPoint[0] = i;
                    nextPoint[1] = j;
                    return nextPoint;
                }
            }
        }
        return nextPoint;
    }

    private void coverArea(char[][] grid, boolean[][] visited, int x, int y) {
        if (grid[x][y] != '1') {
            return;
        }
        visited[x][y] = true;
        if (x > 0 && !visited[x-1][y]) {
            coverArea(grid, visited, x-1, y);
        }
        if (x < grid.length-1 && !visited[x+1][y]) {
            coverArea(grid, visited, x+1, y);
        }
        if (y > 0 && !visited[x][y-1]) {
            coverArea(grid, visited, x, y-1);
        }
        if (y < grid[0].length-1 && !visited[x][y+1]) {
            coverArea(grid, visited, x, y+1);
        }
    }
}
