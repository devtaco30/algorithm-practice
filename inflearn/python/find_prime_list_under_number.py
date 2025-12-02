def find_prime_list_under_number(number):
    prime_list = []

    for n in range(2, number + 1):
        for i in prime_list:
            if i * i <= n and n % i == 0:
                break
        else:
            prime_list.append(n)

    return prime_list


def find_prime_list_under_number_by_sieve(number):
    prime_list = [True] * (number + 1)
    
    prime_list[0], prime_list[1] = False, False
    
    for i in range(2, int(number**0.5) + 1):
        if prime_list[i] == True : 
            for j in range(i*i, number + 1, i): 
                prime_list[j] = False
    
    for i in range(2, number + 1):
        if prime_list[i] == True:
            prime_list.append(i)

    return prime_list

result = find_prime_list_under_number(input)
print(result)