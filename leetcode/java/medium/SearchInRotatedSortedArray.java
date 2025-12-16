/**
 * 문제: Search in Rotated Sorted Array
 * 
 * 문제 설명:
 * 중복이 없이 오름차순으로 정렬된 정수 배열 nums가 주어집니다.
 * 이 nums 배열은 알 수 없는 중심 인덱스 k(1 <= k < nums.length)를 기준으로 회전되어 있을 수 있습니다.
 * 
 * 회전 예시:
 * - 원본 배열: [0, 1, 2, 4, 5, 6, 7]
 * - 중심 인덱스 3을 기준으로 회전: [4, 5, 6, 7, 0, 1, 2]
 *   (인덱스 3부터 끝까지가 앞으로, 0부터 인덱스 2까지가 뒤로 이동)
 * 
 * 요구사항:
 * - 회전되어 있는 nums 배열에서 정수 target이 존재하면 그 인덱스를 리턴
 * - target이 존재하지 않으면 -1을 리턴
 * - 알고리즘은 반드시 O(log n)의 시간 복잡도를 가져야 합니다.
 * 
 * 예제 1:
 *   입력: nums = [4,5,6,7,0,1,2], target = 0
 *   출력: 4
 *   설명: target 0은 인덱스 4에 위치합니다.
 * 
 * 예제 2:
 *   입력: nums = [4,5,6,7,0,1,2], target = 3
 *   출력: -1
 *   설명: target 3은 배열에 존재하지 않습니다.
 * 
 * 예제 3:
 *   입력: nums = [1], target = 0
 *   출력: -1
 *   설명: 배열에 target이 존재하지 않습니다.
 * 
 * 제약사항:
 *   1 <= nums.length <= 5000
 *   -10^4 <= nums[i] <= 10^4
 *   nums의 값은 모두 유일합니다.
 *   nums는 알 수 없는 중심점을 기준으로 오름차순 정렬되어서 회전되어 있습니다.
 *   -10^4 <= target <= 10^4
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(solution.search(nums, target));
        nums = new int[]{4,5,6,7,0,1,2};
        target = 3;
        System.out.println(solution.search(nums, target));
        nums = new int[]{1};
        target = 0;
        System.out.println(solution.search(nums, target));
        nums = new int[]{3,1};
        target = 3;
        System.out.println(solution.search(nums, target));
    }
}

class Solution {
    public int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        
        while(left <= right) {
            int mid = (left + right) / 2;

            if(nums[mid] == target) {
                return mid;
            }

            //left sorted
            if(nums[left] <= nums[mid]) {
                if(nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            //right sorted
            else {
                if(nums[mid] <= target && target <= nums[high]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 이진 탐색으로 pivot 위치를 찾는 함수
     * pivot: 회전이 시작되는 지점 (가장 작은 값의 인덱스)
     * 
     * 예시:
     * - [4,5,6,7,0,1,2] → pivot = 4 (0이 시작되는 위치)
     * - [0,1,2,4,5,6,7] → pivot = 0 (회전되지 않은 경우)
     * 
     * 시간 복잡도: O(log n)
     */
    private int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        // 배열이 회전되지 않은 경우 (완전히 정렬된 경우)
        if (nums[left] < nums[right]) {
            return 0;
        }
        
        // 이진 탐색으로 pivot 찾기
        while (left < right) {
            int mid = (left + right) / 2;
            
            // mid가 pivot인 경우: mid+1이 mid보다 작음
            if (mid < nums.length - 1 && nums[mid] > nums[mid + 1]) {
                return mid + 1;
            }
            
            // 왼쪽 부분이 정렬되어 있으면 (nums[left] <= nums[mid])
            // pivot은 오른쪽에 있음
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {
                // 오른쪽 부분이 정렬되어 있으면 pivot은 왼쪽에 있음
                right = mid;
            }
        }
        
        return left;
    }

    public int search_better(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        
        // left ->  <- right 좁혀오면서 mid 를 찾고 target 이 좌,우 어느 쪽에 위치하는지 좁혀가며 찾는다.
        while(left <= right) {
            int mid = (left + right) / 2;

            // mid 가 target 과 일치하면 바로 return
            if(nums[mid] == target) {
                return mid;
            }

            //left sorted 에 있는지 확인 -> 회전된 배열의 왼쪽 절반이 정렬되어 있다.
            if(nums[left] <= nums[mid]) { // nums[left] 가 mid 보다 작은 구간
                if(nums[left] <= target && target <= nums[mid]) { // target 이 nums[left] 와 nums[mid] 사이에 있는지 확인
                    right = mid - 1; // target 이 left sorted 구간에 잇는게 확인됐으므로 right 를 mid 왼쪽으로 좁혀준다.
                }
                else {
                    left = mid + 1; // target 이 left sorted 구간에 없는게 확인됐으므로 left 를 mid 오른쪽으로 좁혀준다.
                }
            }
            //right sorted
            else {
                if(nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1; // target 이 right sorted 구간에 잇는게 확인됐으므로 left 를 mid 오른쪽으로 좁혀준다.
                }
                else {
                    right = mid - 1; // target 이 right sorted 구간에 없는게 확인됐으므로 right 를 mid 왼쪽으로 좁혀준다.   
                }
            }
        }

        return -1;
    }

}
