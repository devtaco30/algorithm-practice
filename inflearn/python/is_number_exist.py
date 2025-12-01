# 빅오 -> 최악 O(N) -> 보통 최악의 성능을 가지고 파악을 한다.
# 빅오메가 -> 최선 Ω(1)

def is_number_exist(number, array):
    # 이 부분을 채워보세요!
    
    for el in array:
        if el == number:
            return True
    return False


result = is_number_exist
print("정답 = True 현재 풀이 값 =", result(3, [3,5,6,1,2,4]))
print("정답 = Flase 현재 풀이 값 =", result(7, [6,6,6]))
print("정답 = True 현재 풀이 값 =", result(2, [6,9,2,7,1888]))