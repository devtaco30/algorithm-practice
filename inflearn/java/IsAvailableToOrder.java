import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

/**
 * 상점에서 현재 가능한 메뉴가 ["떡볶이", "만두", "오뎅", "사이다", "콜라"] 일 때, 유저가 ["오뎅", "콜라", "만두"]
 * 를 주문했다.
 * 
 * 그렇다면, 현재 주문 가능한 상태인지 여부를 반환하시오.
 * 
 * menus = ["떡볶이", "만두", "오뎅", "사이다", "콜라"]
 * orders = ["오뎅", "콜라", "만두"]
 */
public class IsAvailableToOrder {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isAvailableToOrder(new String[] {"떡볶이", "만두", "오뎅", "사이다", "콜라"}, new String[] {"오뎅", "콜라", "만두"}));
    }
}

class Solution {
    public boolean isAvailableToOrder(String[] menus, String[] orders) {

        Set<String> menuSet = new HashSet<>(Arrays.asList(menus)); // O(N)

        for (String order: orders) { // O(M)
            if (!menuSet.contains(order)) { // O(1) Set 이기 때문에
                return false;
            }
        } // O(M)
        return true; // O(1)
    } // O(N + M)

}
