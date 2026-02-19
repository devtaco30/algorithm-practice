package primitive;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Primitive_01 {

    private static short[] preComputedParity = new short[65536];
    /** 16비트 값 i에 대해 비트 역순(0~65535)을 저장. reverseBits()용 */
    private static int[] preComputedReverse = new int[65536];

    static {
        for (int i = 0; i < preComputedParity.length; i++) {
            preComputedParity[i] = parity((long) i);
        }
        for (int i = 0; i < preComputedReverse.length; i++) {
            preComputedReverse[i] = reverse16(i);
        }
    }

    /** 16비트 값의 비트 역순을 반환 (하위 16비트만 유효) */
    private static int reverse16(int x) {
        int r = 0;
        for (int i = 0; i < 16; i++) {
            r = (r << 1) | (x & 1);
            x >>>= 1;
        }
        return r;
    }

    public static void main(String[] args) {
    
        // int x = 10;
        // log.info("countBits: {}", countBits(x));

        // bit operation
        log.info(" 6&4: {}", 6&4); // 4 
        log.info("1|2: {}", 1|2); // 3
        log.info("-16>>2: {}", -16>>2);  // -4
        log.info("1<<10: {}", 1<<10); // 1024
        log.info("~0: {}", ~0); //  0000 0000 0000 0000 -> 1111 1111 1111 1111 이걸 해석하면 일단 signed 기준으로 보려면, 보수+1 을 하면 0000 0000 0000 0000에 +1을 하면 1이고 해석하면 -1이다.
        int x = 2;
        log.info("15^x: {}", 15^x); // ^ 는 비트 단위로 같지 않는 것을 의미한다. XOR . -> 13 dlek.

        log.info("Integer.MIN_VALUE: {}", Integer.MIN_VALUE);
        log.info("Float.MAX_VALUE: {}", Float.MAX_VALUE);
        log.info("Double.SIZE: {}", Double.SIZE);
        log.info("Boolen.TRUE: {}", Boolean.TRUE);

        
        log.info("Double.valueOf(\"1.23\"): {}", Double.valueOf("1.23")); // -> Box 타입으로 변환된다.
        log.info("Boolean.valueOf(true): {}", Boolean.valueOf(true));
        log.info("Integer.parseInt(\"42\"): {}", Integer.parseInt("42")); // -> Box 타입이 아닌 primitive 타입으로 변환된다.
        log.info("Float.toString(-1.23f): {}", Float.toString(-1.23f));

        
        
    }

    public static short countBits(int x) {
        // 비트 연산으로 몇개인지 셀 변수.
        short numBits = 0;

        int cnt = 0;
        // int x 가 0이 될 때까지 반복
        while (x != 0) {
            log.info ("cnt: {}, x: {}", cnt, x);
            // x 와 1 을 &(and) 연산하면 
            numBits += (x & 1);
            x >>>=1;
            cnt++;
        }
        return numBits;
    }

    public static short parity(long x) {
        short result = 0;
        while (x !=0 ) {
            result ^= (x & 1);
            // x >>>= 1; // 오른쪽으로 1비트씩 shift 하면서 0이 될때까지 반복 O(n) 시간복잡도를 가진다.
            x &= (x - 1); // x 에서 1을 뺀 값과 & 연산을 하면, 최하위의 1비트만 사라진다. O(k) 시간복잡도를 가진다. k 는 1의 개수.
        }
        return result;
    }

    /**
     * 시간 복잡도는 해시 테이블의 키값의 크기에 따라 달라진다. 
     * 만약 해시 테이블에 사용될 키값의 비트 수가 L이고 전체 비트 수가 n이라면, 시간 복잡도는 O(n/L)이다.
     * @param x
     * @return
     */
    // 하지만 '매우 큰 수' 의 parity 를 구하는 경우에는 위의 방법은 비효율적이다.
    /*
     * [이해용 예시] 16비트를 4비트 4덩이로 쪼갠 경우:
     *   x = (1011 0010 1100 1001)₂
     *   - 덩어리0(최하위 4비트): 1001 → 1이 2개 → 패리티 0
     *   - 덩어리1: 1100 → 1이 2개 → 패리티 0
     *   - 덩어리2: 0010 → 1이 1개 → 패리티 1
     *   - 덩어리3(최상위 4비트): 1011 → 1이 3개 → 패리티 1
     *   시프트+마스크: x>>>12→1011, x>>>8&0xF→0010, x>>>4&0xF→1100, x&0xF→1001
     *   각 덩어리 패리티를 룩업한 뒤 XOR → 0 ^ 0 ^ 1 ^ 1 = 0 (최종 패리티)
     * 아래 구현은 64비트를 16비트 4덩이로 쪼갠 동일한 방식이다.
     */
    public static short parity_lookupTable(long x) {
        final int WORD_SIZE = 16;
        final int BIT_MASK = 0xFFFF;
        return (short) (
            preComputedParity[(int)((x >>> (3*WORD_SIZE)) & BIT_MASK)] // 최상위 16비트 의 parity
            ^ 
            preComputedParity[(int)((x >>> (2*WORD_SIZE)) & BIT_MASK)] // 중간 16비트 의 parity
            ^
            preComputedParity[(int)((x >>> (1*WORD_SIZE)) & BIT_MASK)] // 하위 16비트 의 parity
            ^
            preComputedParity[(int)((x >>> (0*WORD_SIZE)) & BIT_MASK)] // 최하위 16비트 의 parity
        );
    }

    /**
     * 시간 복잡도는 O(log n) 이다.
     * @param x
     * @return
     */
    public static short parity_by_half_shift(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return (short) (x & 1);
    }

    /**
     * 응용 : 비트 연산, 동등성 검사 및 불 연산자를 사용 O(1) 시간복잡도를 가지면서 다음을 수행하는 표현식을 작성해보자.
     * 
     * 1) x 에서 가장 오른쪽에 설정된 비트를 오른쪽으로 전달하라. 예를 들면 01010000 는 01011111 이 된다.
     * 
     * x - 1 = 01001111  , x | (x - 1) = 01011111 
     * 
     * 2) x mod '2의 거듭제곱' 을 계산하라. 예를 들어 77 mod 64 는 13 이 된다.
     * 
     * x mod 2^k 를 구하라는 것 예시는 77 mod 2^6 (64) 는 13 이 된다.
     * 그럼 mod 는 어떻게 연산하는가? '나머지' 는 
     * 
     * 3) x 가 2의 거듭제곱인지 검사하라. x = 1, 2, 4, 8, ,,, 일 때는 ㅅrue, 그 외의 값은 false 로 평가한다.
     * 2진수로 x & (x-1) 을 하면 최하위 1비트가 사라진다.  x & (x-1) == 0 이라는건 나머지가 없다는 거고, 2진수 연산이기때문에 2의 거듭제곱이 된다. 
     * 
     */


    public static long rightPropagate(long x) {
        return x | (x - 1);
    }

    public static long modPowerOfTwo(long x, long power){
        return x & (power - 1);
    }

    public static boolean isPowerOfTwo(long x) {
        return (x & (x - 1)) == 0;
    }

    /**
     * Primitive_01 에서 x & (x - 1)은 최하위 1비트를 제거한 결과이고,
     * x & ~(x - 1)은 최하위 비트만 남기고 모두 제거한 결과이다.
     * 예를 들면
     * x = 10100000
     * x - 1 = 10011111
     * ~(x - 1) = 01100000
     *  x & ~(x - 1) = 00100000 
     * 
     * 64비트 짜리 정수를 길이가 64인 배열로 생각해본다. 
     * 배열의 0번째에는 최하위비트 LSB (Least Significant Bit) 가 존재한다. 
     * 63번째에는 최상위비트 MSB (Most Significant Bit) 가 존재한다. 
     * 
     * 64비트 정수가 주어졌을 때 i 번째 비트와 j 번째 비트를 swap 하는 코드를 작성해보자
     * 
     */

    public static long swapBits(long x, int i, int j) {
        if ((( x >>> i)&1) != ((x>>>j) & 1)) {
            // 각 비트가 다르면! swap
            long bitMask = (1L << i) | (1L << j);
            x ^= bitMask;
        }
        // 각 비트가 같으면! 그냥 그대로 반환
        return x;
    }
    // 이렇게 되면 시간 복잡도는 O(1) 이 된다.


    /**
     * 64비트 숫자가 주어졋을 때 이를 '역순'으로 재구성한 숫자를 반환하는 코드를 작성해보자. 
     */
    public static long reverseBits(long x) {
        final int WORD_SIZE = 16;
        final int BIT_MASK = 0xFFFF;
        return (
            (preComputedReverse[(int)(x & BIT_MASK)] & 0xFFFFL) << (3*WORD_SIZE) |
            (preComputedReverse[(int)((x >>> WORD_SIZE) & BIT_MASK)] & 0xFFFFL) << (2*WORD_SIZE) |
            (preComputedReverse[(int)((x >>> (2*WORD_SIZE)) & BIT_MASK)] & 0xFFFFL) << (1*WORD_SIZE) |
            (preComputedReverse[(int)((x >>> (3*WORD_SIZE)) & BIT_MASK)] & 0xFFFFL)
        );
    }
    // 이렇게 되면 시간 복잡도는 O(1) 이 된다.

    /**
     * 문제 4.4 같은 무게를 가진 가장 가까운 정수 찾기
     * 음이 아닌 어떤 정수의 무게는 이 정수를 2진수로 표현했을 때 1로 세팅된 비트 의 개수라고 정의해 보자, 예를 들어, 92의 2진수는 (1011100)이 되므로 92의 무게는 4가 된다.
     * 음이 아닌 정수 x가 주어졌을 때, x와 무게는 같지만 x와의 차이, 즉 |y- x|가 최 소가 되는 y를 반환하는 프로그램을 작성하라. 단, x의 모든 비트가 0혹은 1인 경 우는 고려하지 않아도 된다. 예를 들어 2= 6일 때 5를 반환하면 된다.
     */
    public static long closestIntSameBitCount(long x) {
        final int NUM_UNSIGNED_BITS = 63;
        // x 를 음이 아닌 정수라고 가정, 맨 앞 비트는 0이라는 사실을 알 수 있다. -> MSB 는 0 이다.
        // 63의 최하위 비트에만 집중하자. 
        for (int i = 0 ; i < NUM_UNSIGNED_BITS - 1; i++) { // 0 부터 62까지 16비트 4덩이를 모두 검사한다.
            // x >>>i shift 하고 1 -> 이 i 번쨰 비트가 1인지 검사
            // x >>> (i + 1) shift 하고 1 -> 이 (i + 1) 번쨰 비트가 1인지 검사
            if (((x >>> i) & 1) != ((x >>> (i + 1)) & 1)) {
                // 즉, k1, k2 번째 비트를 swap 하면 된다.
                x ^= (1L << i) | (1L << (i + 1)); // XOR 연산을 하면 두 비트가 서로 바뀌게 된다.
                return x;
            }
        }
        throw new IllegalArgumentException("All bits are 0 or 1");
    }

    /**
     * 문제 4.5 곱셈과 덧셈 없이 x * y계산하기
     * 음이 아닌 정수 두 개의 곱셈을 수행하는 프로그램을 작성하라. 사용할 수 있는 연산자는 다음과 같다.
     * • 대입 연산자
     * • >, < l, &,~,^와 같은 비트 연산자
     * • 동등성 확인과 불 조합 연산
     * • 루프와 함수도 여러분이 직접 작성해야 한다. 증감 연산자와 <, >, <=, >= 와 같은 비교 연산도 불가능하다.
     * 
     */
    public static long multiply(long x, long y) {
        long sum = 0;
        while(x != 0) {
            if ( (x & 1) != 0 ) { // 현재 x 의 최하위 비트가 0 이 아니면, (즉 1) -> 곱셈을 진행.
                sum = add(sum, y); // 곱셈이라기보다는 sum 에 y 를 더한다.
            }
            x >>>= 1; // 루프를 돌면서 1비트씩 right shift 하면서 0이 될때까지 반복.
            y <<= 1; // y 를 루프를 돌면서 1비트씩 left shift 하면서 2^k 로 위치를 맞춰주는 방식. y 는 계속 shift 되면서 더해질 값이 된다.
        }
        return sum;
    }

    public static long add(long a, long b) {
        long sum = 0, carryin = 0, tempA = a, tempB = b;
        long k = 1; // 지금 비트 위치를 나타내는 변수.

        while (tempA != 0 || tempB != 0) {
            long ak = tempA & k; // a 의 k 번째 비트
            long bk = tempB & k; // b 의 k 번째 비트

            // ak, bk, carryin 중 하나라도 1이면 carryout 은 1이 된다. -> 이런 경우 올림이 발생한다.
            long carryout = (ak & bk) | (ak & carryin) | (bk & carryin); 
            
    
            sum |= (ak ^ bk ^ carryin);
            carryin = carryout << 1;
            tempA >>>= 1;
            tempB >>>= 1;
            k <<= 1;
        }
        
        return sum | carryin;
    }

    /**
     * 문제 4.6 x/y 계산하기
     */
    public static long divide(long x, long y) {
        long result = 0;
        long power = 32; // 
        long yPower = y << power;
        while (x >= y) {
            while (yPower > x) {
                yPower >>>= 1;
                power--;
            }
            result += 1L << power;
            x -= yPower;
        }
        return result;


    }
}
