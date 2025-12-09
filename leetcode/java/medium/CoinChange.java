/**
 * 각각 다른 액면가를 가진 동전을 나타내는 정수 배열 coins가 주어지고, 전체 금액을 나타내는 정수 amount가 주어집니다.
 * 주어진 동전을 이용하여 amount를 만들 수 있는 가장 적은 개수를 리턴하세요. 
 * 만약 주어진 동전으로 amount를 만들 수 없다면 -1을 리턴하세요.
 * 각 동전의 개수는 무한하다고 가정합니다.
 * 
 * 예제 1:
 *   입력: coins = [1,2,5], amount = 11
 *   출력: 3
 *   설명: 11 = 5 + 5 + 1
 * 
 * 예제 2:
 *   입력: coins = [2], amount = 3
 *   출력: -1
 * 
 * 예제 3:
 *   입력: coins = [1], amount = 0
 *   출력: 0
 * 
 * 예제 4:
 *   입력: coins = [1,2,3,5,6,9,10,11,13,14], amount = 2000
 *   출력: 143
 * 
 * 제약사항:
 *   1 <= coins.length <= 12
 *   1 <= coins[i] <= 2^31 - 1
 *   0 <= amount <= 10^4
 */


public class CoinChange {
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(solution.coinChange(new int[]{2,10 }, 17));
        System.out.println(solution.coinChange(new int[]{1,2,3,5,6,9,10,11,13,14}, 2000));
    }
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        
        int[] dp = new int[amount+1];
        dp[0] = Integer.MAX_VALUE; // 0번 인덱스는 MAX_VALUE로 설정
        
        for( int i = 1; i <= amount; i++) {
            for( int coin : coins) {
                if (i - coin == 0) { // coin 과 현재 amount 가 같은 경우 -> 동전 1개로 만들 수 있음
                    dp[i] = dp[i] == 0 ? 1 : Math.min(dp[i], 1); // 0이 아니면, 현재
                } else if (i - coin > 0 && dp[i-coin] != Integer.MAX_VALUE && dp[i-coin] != 0) {
                    // i-coin > 0이고, dp[i-coin]이 계산되어 있으면
                    dp[i] = dp[i] == 0 ? dp[i-coin] + 1 : Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }

        return dp[amount] == 0 ? -1 : dp[amount];
    }

    public int coinChange_(int[] coins, int amount) {
        // dp[i]: i원을 만드는 데 필요한 최소 동전 개수
        // dp[0] = 0 (0원을 만드는 데 필요한 동전 개수는 0개)
        // dp[i] = min(dp[i], dp[i-coin] + 1) for each coin
        
        if (amount == 0) return 0;
        
        int[] dp = new int[amount + 1];
        // 각 금액을 최대값으로 초기화 (만들 수 없는 경우를 표시)
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0; // 0원은 0개의 동전으로 만들 수 있음
        
        // 각 금액에 대해
        for (int i = 1; i <= amount; i++) {
            // 각 동전에 대해
            for (int coin : coins) {
                // 현재 금액에서 동전을 사용할 수 있고, 이전 금액을 만들 수 있는 경우
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}