package hello.core.singleton;

public class StatefulService {

//    private int price;  // 상태를 유지하는 필드 10000 -> 20000

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
