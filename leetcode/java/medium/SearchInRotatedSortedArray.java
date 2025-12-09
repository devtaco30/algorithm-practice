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

/**
 * 문제가 O(log n) 을 요구한다. 이진트리 탐색.
 */
class Solution {
    public int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        
        // left ->  <- right 좁혀오면서 mid 를 찾고 target 이 좌,우 어느 쪽에 위치하는지 좁혀가며 찾는다.
        while(left <= right) {
            int mid = (left + right) / 2;

            // mid 가 target 과 일치하면 바로 return
            if(nums[mid] == target) {
                return mid;
            }

            //left sorted 에 있는지 확인 
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
