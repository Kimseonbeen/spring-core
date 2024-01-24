package hello.core.singleton;

public class StatefulService {

//    private int price;  // 상태를 유지하는 필드 10000 -> 20000


    /**
     * 공용으로 사용 하는 필드 값 price를 this.price = price; 코드로
     * 변경하기 때문에 문제가 발생한다.
     * 싱글톤은 무상태를 유지하게 코드를 작성해야한다.
     *
     * 무상태(stateless)로 설계해야 한다!
     * 특정 클라이언트에 의존적인 필드가 있으면 안된다.
     * 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다!
     * 가급적 읽기만 가능해야 한다.
     * 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
     *
     */
//    public void order(String name, int price) {
//        System.out.println("name = " + name + ", price = " + price);
//        this.price = price; //여기가 문제
//    }


    /**
     *  price 지역변수를 사용해서 무상태로 만들고
     *  return price를 해주면
     *  스프링 빈을 항상 무상태로 설계하는게 중요 !!
     */

    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
//        this.price = price; //여기가 문제
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
