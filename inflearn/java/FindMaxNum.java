class FindMaxNum {
    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println("정답 = 6 / 현재 풀이 값 = " + solution.findMaxNum(new int[]{3, 5, 6, 1, 2, 4}));
        System.out.println("정답 = 6 / 현재 풀이 값 = " + solution.findMaxNum(new int[]{6, 6, 6}));
        System.out.println("정답 = 1888 / 현재 풀이 값 = " + solution.findMaxNum(new int[]{6, 9, 2, 7, 1888}));
    }    
}

class Solution {
    public int findMaxNum(int[] array) {
        int maxNum = array[0];

        for (int i = 1; i < array.length; i++) {
            if (maxNum < array[i])  {
                maxNum = array[i];
            }
        }
        return maxNum;
    }
}

