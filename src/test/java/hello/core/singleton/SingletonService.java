package hello.core.singleton;

public class SingletonService {
    //1. static 영역에 객체를 하나만 생셩한다.
    private static final SingletonService instance = new SingletonService();
    
    //2. public으로 위 객체를 조회만 할 수 있도록 한다.
    public static SingletonService getInstance(){
        return instance;
    }
    
    //3. 생성자를 private로 선언해서 외부에서 객체를 생성하지 못하도록 한다.
    private SingletonService(){
    }
    
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
    
}
