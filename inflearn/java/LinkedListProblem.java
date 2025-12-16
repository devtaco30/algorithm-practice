/**
 * ❓ 다음과 같은 두 링크드 리스트를 입력받았을 때, 합산한 값을 반환하시오.
 * 
 * 예를 들어 아래와 같은 링크드 리스트를 입력받았다면,
 * 각각 678, 354 이므로 두개의 총합
 * 678 + 354 = 1032 를 반환해야 한다.
 * 
 * 단, 각 노드의 데이터는 한자리 수 숫자만 들어갈 수 있다.
 * 
 * ex) [6] -> [7] -> [8]
 * [3] -> [5] -> [4]
 */
public class LinkedListProblem {

    public static void main(String[] args) {
        LinkedList list1 = new LinkedList(new Node(6));
        list1.append(7);
        list1.append(8);
        LinkedList list2 = new LinkedList(new Node(3));
        list2.append(5);
        list2.append(4);
        list2.append(13);
        list2.append(99);
        list2.append(3205);
        list2.append(10000);
        Solution solution = new Solution();
        System.out.println(solution.linkedListSum(list1, list2));
        System.out.println(solution.findKthFromEnd(list2, 2));


    }
}

class Solution {
    public int linkedListSum(LinkedList list1, LinkedList list2) {
        int result = list1.calculate() + list2.calculate();
        return result;
    }

    /**
     * . linkedlist 의 끝에서 K 번째 값을 반환.
     * 
     *  slow, fast pointer 를 사용해서 풀면 더 빠르게 풀 수 있다.
     * @param list
     * @param n
     * @return
     */
    public int findKthFromEnd(LinkedList list, int k) {
        Node slow = list.head;
        Node fast = list.head;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow.data;
    }


}


class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    
    Node head;

    int length;

    public LinkedList() {
        this.head = null;
        this.length = 0;
    }

    public LinkedList(Node head) {
        this.head = head;
        this.length = 1;
    }

    public void append(int data) {
        Node newNode = new Node(data);
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        length++;
    }

    public int calculate() {
        int result = 0;
        Node current = head;
        while (current != null) {
            result = result * 10 + current.data;
            current = current.next;
        }
        return result;
    }   
}
