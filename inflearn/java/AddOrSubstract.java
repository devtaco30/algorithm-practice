/**
 * Q.
 * ❓ 음이 아닌 정수들로 이루어진 배열이 있다. 이 수를 적절히 더하거나 빼서 특정한 숫자를 만들려고 한다. 예를 들어 [1, 1, 1,
 * 1, 1]로 숫자 3을 만들기 위해서는 다음 다섯 방법을 쓸 수 있다.
 * 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * 사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target_number이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서
 * 타겟 넘버를 만드는 방법의 수를 반환하시오.
 * 
 * numbers = [1, 1, 1, 1, 1]
 * target_number = 3
 */
public class AddOrSubstract {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addOrSubstract(new int[] { 1, 1, 1, 1, 1 }, 3));
    }
}

class Solution {
    public int addOrSubstract(int[] array, int target_number) {
        return addOrSubstractRecursive(array, target_number, 0);
    }

    // 재귀호출로 풀이

    // 각 숫자에는 + or - 를 붙이는 2가지 방법이 있고,
    // 각 숫자에 대해 시도한다 하면 N! 가지의 방법이 있다.

    private int addOrSubstractRecursive(int[] array, int target_number, int index) {
        // 재귀 함수는 탈출 조건이 명시되어야 한다.
        if (index == array.length) {
            // 다 돌았는데, 
            return target_number == 0 ? 1 : 0;
        }

                // target_numer 에서 다음 숫자가 빠지는 case , 더하는 case 를 모두 시도
        return addOrSubstractRecursive(array, target_number - array[index], index + 1)
                + addOrSubstractRecursive(array, target_number + array[index], index + 1);
    }
}
