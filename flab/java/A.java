/**
 * JVM 메모리 구조 및 클래스 로딩 순서 예제
 * 
 * 실행 순서 (출력 결과 기준)
 * 
 * 1. 프로그램 시작 - 클래스 로드 및 Static 초기화
 *    - C class static block is loaded - C 클래스 로드 및 static 블록 실행
 *    - C class instance block is loaded - static C c = new C("static") 실행 (인스턴스 블록)
 *    - C class constructor is loaded : static - static C 객체 생성 완료
 *    - ABC,-1,-1,C@29f69090 - A의 static 블록에서 static 변수들 출력
 *    - A class static block is loaded - A 클래스 로드 및 static 블록 실행
 * 
 * 2. Main 메서드 실행
 *    - Main method Start!! - main 메서드 실행 시작
 * 
 * 3. A 객체 생성 (new A())
 *    - C class instance block is loaded - 인스턴스 변수 cc = new C("instance") 초기화 (인스턴스 블록)
 *    - C class constructor is loaded : instance - 인스턴스 C 객체 생성 완료
 *    - A class [instance] block is loaded - A의 인스턴스 블록 실행
 *    - ABC,1,C@5d740a0f - A 생성자에서 인스턴스 변수들 출력
 *    - A class [constructor] is loaded - A 생성자 실행
 * 
 * 4. B 객체 생성 (new B())
 *    - B class [static] block is loaded - B 클래스 로드 및 static 블록 실행
 *    - C class instance block is loaded - B 객체의 인스턴스 변수 cc = new C("instance") 초기화 (상속받은 A의 변수)
 *    - C class constructor is loaded : instance - 인스턴스 C 객체 생성 완료
 *    - A class [instance] block is loaded - B가 A를 상속받아 A의 인스턴스 블록 실행
 *    - ABC,1,C@20d3d15a - A 생성자에서 인스턴스 변수들 출력
 *    - A class [constructor] is loaded - A 생성자 실행 (B 생성자에서 super() 호출)
 *    - B class [instance] block is loaded - B의 인스턴스 블록 실행
 *    - B class [constructor] is loaded - B 생성자 실행
 * 
 * 핵심 정리
 *    - Static 변수는 클래스 로드 시 1번만 초기화되며, Method Area에 저장됨
 *    - 인스턴스 변수는 객체 생성 시마다 초기화되며, Heap에 저장됨
 *    - 상속 관계에서 부모 클래스의 초기화 블록과 생성자가 먼저 실행됨
 *    - Static 변수는 모든 객체가 공유하며, 인스턴스 변수는 객체마다 별도로 존재함
 * 
 */
class A {

    private static final String STR = "ABC"; //  STR 은 Method Area 에 객체 자체는 Heap 
    private static Long l = Long.valueOf(-1L); // Wrapper Class 지만 static -> Method Area, 객체는 Heap
    private static int i = -1; // primitive 타입이지만 static 이므로 Method Area 로
    private static C c = new C("static"); // 객체타입이지만, static 이므로 Method Area 로, 객체는 Heap 

    private final String a = "ABC"; // 객체타입이므로 Heap 메모리에 객체, 변수, 주소 모두 존재
    private final int ii = 1; // primitive 타입이지만 Class A 의 인스턴스 변수로 Heap 영역에 적재
    private C cc = new C("instance"); // 객테타입이고, Class A 의 인스턴스 변수로 Heap 영역에 적재

    public C getC() {
        return cc;
    }

    {
        // A 의 초기화 블럭
        System.out.println("A class [instance] block is loaded");
    }    

    A() {
        // A 의 생성자
        System.out.println(a +","+ ii +","+ cc);
        System.out.println("A class [constructor] is loaded");
        
    }

    static {
        // A 의 static 블럭
        System.out.println(STR +","+ l +","+ i +","+ c);
        System.out.println("A class static block is loaded");
        
    }

    public static void main(String[] args) {
      // JVM Memory에서 무슨 일이 일어나는지 설명해 봅시다.

      // main 함수가 실행되기 전에 A 클래스가 먼저 load 되어 Method Area 에 로드된다. (클래스 정보)
      // 그리고 static 변수들도 Method Area에 로드 된다.

      // 이후 main method 가 실행되며 아래 메세지가 출력.
      System.out.println("================================================");
      System.out.println("Main method Start!!");
      System.out.println("================================================");
      

      // 이제 객체를 생성한다.
      System.out.println("=================== create A object ===================");
      A a = new A(); 
      // A 객체가 생성되기 전에 인스턴스 변수들이 먼저 선언되며, 그 이후 생성자가 실행된다.
      // C 객체가 먼저 만들어지고, A 객체가 만들어진다.
      System.out.println("==================== A Object Created ===================");
      System.out.println("=================== create B object ===================");          
      B b = new B();
      // B 객체의 static block 이 실행되고 , 상속한 A 가 갖는 C 객체가 만들어지고, 상속할 A 객체가 만들어지고, B 객체가 만들어진다.
      System.out.println("==================== B Object Created ===================");

      // C 객체들의 주소값들을 보자
      System.out.println("=================== print static object addresses ===================");
      System.out.println("c of static A = " + A.c);
      System.out.println("cc of instance b = " + b.getC());
      System.out.println("cc of instance a = " + a.getC());
      System.out.println("==================== print static object addresses ===================");
    }
  }

    class B extends A {
        
        {
            // B 의 초기화 블럭
            System.out.println("B class [instance] block is loaded");
        }

        B() {
            // B 의 생성자
            System.out.println("B class [constructor] is loaded");
        }

        static {
            // B 의 static 블럭
            System.out.println("B class [static] block is loaded");
        }
    }


class C {

    String type = "";
    // C 의 초기화 블럭
    {
        System.out.println("C class [instance] block is loaded");
    }

    C(String type) {
        this.type = type;
        // C 의 생성자
        System.out.println("C class [constructor] is loaded : " + type);
    }

    static {
        // C 의 static 블럭
        System.out.println("C class [static] block is loaded");
    }
}
