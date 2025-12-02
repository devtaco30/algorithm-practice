/**
 * 문제: 유연근무제 이벤트
 * 
 * 배경:
 * 프로그래머스 사이트를 운영하는 그렙에서는 재택근무와 함께 출근 희망 시각을 자유롭게 정하는 
 * 유연근무제를 시행하고 있습니다. 제도 정착을 위해 오늘부터 일주일 동안 각자 설정한 출근 희망 시각에 
 * 늦지 않고 출근한 직원들에게 상품을 주는 이벤트를 진행하려고 합니다.
 * 
 * 출근 규칙:
 * - 직원들은 일주일 동안 자신이 설정한 출근 희망 시각 + 10분까지 어플로 출근해야 합니다.
 * - 예: 출근 희망 시각이 9시 58분인 직원은 10시 8분까지 출근해야 합니다.
 * - 단, 토요일, 일요일의 출근 시각은 이벤트에 영향을 끼치지 않습니다.
 * - 직원들은 매일 한 번씩만 어플로 출근합니다.
 * 
 * 시각 표현:
 * - 모든 시각은 시에 100을 곱하고 분을 더한 정수로 표현됩니다.
 * - 예: 10시 13분 → 1013
 * - 예: 9시 58분 → 958
 * 
 * 입력:
 * - schedules: 직원 n명이 설정한 출근 희망 시각을 담은 1차원 정수 배열
 * - timelogs: 직원들이 일주일 동안 출근한 시각을 담은 2차원 정수 배열
 * - startday: 이벤트를 시작한 요일을 의미하는 정수
 * 
 * 출력:
 * - 상품을 받을 직원의 수를 return
 * 
 * 입출력 예:
 * 예시 1:
 * - schedules: [700, 800, 1100]
 * - timelogs: [[710, 2359, 1050, 700, 650, 631, 659], [800, 801, 805, 800, 759, 810, 809], [1105, 1001, 1002, 600, 1059, 1001, 1100]]
 * - startday: 5 (금요일)
 * - result: 3
 * 
 * 예시 2:
 * - schedules: [730, 855, 700, 720]
 * - timelogs: [[710, 700, 650, 735, 700, 931, 912], [908, 901, 805, 815, 800, 831, 835], [705, 701, 702, 705, 710, 710, 711], [707, 731, 859, 913, 934, 931, 905]]
 * - startday: 1 (월요일)
 * - result: 2
 * 
 * 제한사항:
 * - 1 ≤ schedules의 길이 = n ≤ 1,000
 * - schedules[i]: i + 1번째 직원이 설정한 출근 희망 시각
 *   - 700 ≤ schedules[i] ≤ 1100
 * - 1 ≤ timelogs의 길이 = n ≤ 1,000
 * - timelogs[i]의 길이 = 7 (일주일)
 * - timelogs[i][j]: i + 1번째 직원이 이벤트 j + 1일차에 출근한 시각
 *   - 600 ≤ timelogs[i][j] ≤ 2359
 * - 1 ≤ startday ≤ 7
 *   - 1: 월요일, 2: 화요일, 3: 수요일, 4: 목요일, 5: 금요일, 6: 토요일, 7: 일요일
 * - 출근 희망 시각과 실제로 출근한 시각을 100으로 나눈 나머지는 59 이하입니다.
 */

public class FlexibleWorkSystem {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 예시 1
        int[] schedules = {700, 800, 1100};
        int[][] timelogs = {{710, 2359, 1050, 700, 650, 631, 659}, 
                            {800, 801, 805, 800, 759, 810, 809}, 
                            {1105, 1001, 1002, 600, 1059, 1001, 1100}};
        int startday = 5;
        int result1New = solution.solution(schedules, timelogs, startday);
        System.out.println("새 함수: " + result1New);

        // 예시 2
        int[] schedules2 = {730, 855, 700, 720};
        int[][] timelogs2 = {{710, 700, 650, 735, 700, 931, 912}, 
                            {908, 901, 805, 815, 800, 831, 835}, 
                            {705, 701, 702, 705, 710, 710, 711}, 
                            {707, 731, 859, 913, 934, 931, 905}};
        int startday2 = 1;
        int result2New = solution.solution(schedules2, timelogs2, startday2);
        System.out.println("새 함수: " + result2New);
    }
    
}


class Solution {
    // "출근 희망 시각에 늦지 않고 출근한" = 출근 희망 시각 이전 출근도 유효
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        // 각 직원에 대해
        for (int i = 0; i < schedules.length; i++) {
            int schedule = schedules[i];
            // 출근 희망 시각 + 10분을 분 단위로 계산
            int endTotalMin = (schedule / 100) * 60 + (schedule % 100) + 10;
            
            int passCount = 0; // 평일 중 조건을 만족한 날짜 수
            
            // 일주일 동안 체크
            for (int j = 0; j < 7; j++) {
                // 주말 제외: j일차의 요일이 토요일(5) 또는 일요일(6)이면 제외
                // startday: 1=월, 2=화, ..., 6=토, 7=일
                // (startday - 1 + j) % 7: 0=월, 1=화, ..., 5=토, 6=일
                int dayOfWeek = (startday - 1 + j) % 7;
                if (dayOfWeek == 5 || dayOfWeek == 6) { // 토요일 또는 일요일
                    continue;
                }
                
                int checkTime = timelogs[i][j];
                // 실제 출근 시각을 분 단위로 변환
                int checkTotalMin = (checkTime / 100) * 60 + (checkTime % 100);
                
                // 출근 희망 시각 + 10분 이전에 출근했으면 통과
                if (checkTotalMin <= endTotalMin) {
                    passCount++;
                }
            }
            
            // 평일 5일 모두 조건을 만족하면 상품 수령
            if (passCount == 5) {
                answer++;
            }
        }
        
        return answer;
    }
}