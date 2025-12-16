/**
 * 다음과 같이 숫자로 이루어진 배열이 있을 때, 2이 존재한다면 True 존재하지 않는다면 False 를 반환하시오.
 * 
 * [0, 3, 5, 6, 1, 2, 4]
 */

public class BasicBinarySearch {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.basicBinarySearch(new int[] { 0,3,5,6,1,2,4}, 2));
    }
}

class Solution {
    public boolean basicBinarySearch(int[] array, int target) {
        // 퀵 정렬로 배열 정렬
        quickSort(array, 0, array.length - 1);
        
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int current = array[mid];
            
            if (current == target) {
                return true;
            }
            
            if (current > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return false;
    }
    
    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int pivotIndex = partition(array, left, right);
        quickSort(array, left, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, right);
    }
    
    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;
        
        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        
        swap(array, i + 1, right);
        return i + 1;
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}