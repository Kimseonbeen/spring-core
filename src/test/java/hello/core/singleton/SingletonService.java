package hello.core.singleton;

public class SingletonService {

    /**
     * 자바가 실행되면 new SingletonService(); 인스턴스 만듬
     * static final 이라 생성후 변경 불가
     * private SingletonService() {}로 생성자 막아둠
     * public static SingletonService getInstance() 메서드만으로 호출이 가능
     */
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {}
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
