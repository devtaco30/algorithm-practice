/**
 * 문제: 서버 증설
 * 
 * 문제 설명:
 * 당신은 온라인 게임을 운영하고 있습니다. 
 * 같은 시간대에 게임을 이용하는 사람이 m명 늘어날 때마다 서버 1대가 추가로 필요합니다.
 * 
 * 서버 증설 규칙:
 * - 어느 시간대의 이용자가 m명 미만이라면, 서버 증설이 필요하지 않습니다.
 * - 어느 시간대의 이용자가 n x m명 이상 (n + 1) x m명 미만이라면 최소 n대의 증설된 서버가 운영 중이어야 합니다.
 * - 한 번 증설한 서버는 k시간 동안 운영하고 그 이후에는 반납합니다.
 *   예를 들어, k = 5일 때 10시에 증설한 서버는 10 ~ 15시에만 운영됩니다.
 * 
 * 증설 횟수:
 * - 같은 시간대에 서버를 x대 증설했다면 해당 시간대의 증설 횟수는 x회입니다.
 * - 하루 동안 모든 게임 이용자가 게임을 하기 위해 서버를 최소 몇 번 증설해야 하는지 구하세요.
 * 
 * 예시:
 * m = 3, k = 5일 때의 시간대별 증설된 서버의 수와 증설 횟수 예시가 있습니다.
 * 모든 게임 이용자를 감당하기 위해 최소 7번 서버를 증설해야 하며, 
 * 이보다 적은 수의 서버 증설로는 모든 게임 이용자를 감당할 수 없습니다.
 * 
 * 입력:
 * - players: 0시에서 23시까지의 시간대별 게임 이용자의 수를 나타내는 1차원 정수 배열
 * - m: 서버 한 대로 감당할 수 있는 최대 이용자의 수
 * - k: 서버 한 대가 운영 가능한 시간
 * 
 * 출력:
 * - 모든 게임 이용자를 감당하기 위한 최소 서버 증설 횟수
 */
class Solution {
    public int solution(int[] players, int m, int k) {
        int serverCnt = 1; // 현재 서버 개수, 기본 1대 운영
        int addCnt = 0; // 증설 횟수
        int[] serverEnd = new int[24+k]; // 서버가 반납되는 시간대, 0시부터 23시까지 총 24시간, k 시간 동안 운영 후 반납

        for (int idx  = 0; idx <  24; idx++ ) {
            serverCnt -= serverEnd[idx];
            // idx 시간대에 필요한 서버의 개수
            int need = (players[idx] / m)+1;
            int add = need - serverCnt;
            if ( add > 0 ) {
                serverCnt+=add;
                serverEnd[idx+k] = add;
                addCnt+= add;
            }
        }
        return addCnt;
    }
}

class Solution01 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] players = {1, 2, 3, 4, 5};
        int m = 2;
        int k = 1;
        int result = solution.solution(players, m, k);
        System.out.println(result);
    }
}