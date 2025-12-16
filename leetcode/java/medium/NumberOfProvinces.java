/**
 * n개의 도시가 있습니다. 어떤 도시들은 연결되어 있고 어떤 도시들은 그렇지 않습니다. 만약 도시 a가 도시 b와 직접 연결되어있고, 도시
 * b가 도시 c와 직접 연결되어있다면 도시 a는 도시 c와 간접적으로 연결되어있습니다.
 * 
 * province는 직접 혹은 간접적으로 연결된 도시들의 그룹을 나타냅니다.
 * 
 * 각 도시들의 관계를 나타내는 n * n으로 이루어진 행렬 isConnected가 주어지며, i번째 도시와 j번째 도시가 직접연결되어있다면
 * isConnected[i][j] = 1이 되고 그 외에는 isConnected[i][j] = 0이 됩니다.
 * 
 * 모든 province의 개수를 리턴하세요.
 * 
 */

public class NumberOfProvinces {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] isConnected = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
        System.out.println(solution.findCircleNum(isConnected)); // 예상 출력: 2
    }
}

class Solution {
    /**
     * DFS (Depth-First Search, 깊이 우선 탐색)를 사용한 풀이
     * 
     * 핵심: count는 "province의 개수"를 세는 변수
     * 
     * 왜 visited[i] == false일 때 count++를 하나?
     * 
     * 핵심 원리:
     * - visited[i] == false → "이 도시가 속한 province를 아직 세지 않았다"
     * - count++ → "새로운 province를 발견했다!"
     * - DFS(i) 호출 → 이 도시와 연결된 모든 도시를 visited=true로 만듦
     * - 결과: 같은 province의 다른 도시들은 나중에 visited=true이므로 건너뜀
     * 
     * 따라서 각 province마다 정확히 한 번만 count++가 발생!
     * 
     * 예시: isConnected = [[1,1,0], [1,1,0], [0,0,1]]
     * 
     * Province 1: {도시 0, 도시 1} (연결됨)
     * Province 2: {도시 2} (혼자)
     * 
     * 단계별 동작:
     * 1. i=0: visited[0]=false 
     *    → count=1 (Province 1 발견! count는 province 개수)
     *    → DFS(0) 호출 → visited[0]=true, visited[1]=true
     * 
     * 2. i=1: visited[1]=true 
     *    → 건너뛰기 (이미 Province 1에 포함되어 세었음, count는 증가 안 함)
     * 
     * 3. i=2: visited[2]=false 
     *    → count=2 (Province 2 발견! count는 province 개수)
     *    → DFS(2) 호출 → visited[2]=true
     * 
     * 최종: count = 2 (province 개수)
     * 
     * 시간 복잡도: O(n^2) - 각 도시마다 최대 n개의 도시를 확인
     * 공간 복잡도: O(n) - visited 배열과 재귀 호출 스택
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;  // province의 개수를 세는 변수
        
        // 모든 도시를 순회하면서
        for (int i = 0; i < n; i++) {
            // visited[i] == false인 도시를 발견 = 새로운 province 발견!
            // (이 도시가 속한 province를 아직 세지 않았다는 의미)
            if (!visited[i]) {
                count++;  // 새로운 province 발견! → province 개수 증가
                // 이 도시와 연결된 모든 도시를 DFS로 방문 처리
                // (연결된 도시들은 모두 visited=true가 되어, 나중에 건너뛰게 됨)
                dfs(isConnected, visited, i);
            }
        }
        
        return count;  // province의 개수 반환
    }
    
    /**
     * DFS 함수: 현재 도시와 연결된 모든 도시를 재귀적으로 방문
     * 
     * @param isConnected 연결 관계 행렬
     * @param visited 방문 여부 배열
     * @param currentCity 현재 탐색 중인 도시 인덱스
     */
    private void dfs(int[][] isConnected, boolean[] visited, int currentCity) {
        // 현재 도시를 방문 처리
        visited[currentCity] = true;
        
        // 모든 도시를 확인하면서
        for (int j = 0; j < isConnected.length; j++) {
            // 현재 도시와 연결되어 있고, 아직 방문하지 않은 도시라면
            if (isConnected[currentCity][j] == 1 && !visited[j]) {
                // 그 도시로부터 다시 DFS 수행 (재귀 호출)
                dfs(isConnected, visited, j);
            }
        }
    }
}
