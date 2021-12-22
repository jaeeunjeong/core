package hello.core.lifecycle;

/**
 * 빈 생명주기를 알아야하는 이유.
 * -> 네트워크나 DB연결과 같이 다른 애플리케이션과의 연결이 필요할 때, 객체를 언제 연결 시켜줄 것인가?
 * 1. 의존성을 주입할 때 -> 다른 애플리케이션 정보를 알 수가 없음.
 * 2. 의존성을 주입된 객체를 사용할 떄-> 이미 늦음.
 * 정답 : 의존성을 주입한 후, 사용 전에 초기화해준다.
 * 그렇다면 의존성 주입 시기를 어떻게 알 수 있을까? -> 스프링과 자바에서 제공하는 빈 생명주기 콜백을 이용한다.
 * <p>
 * 이러한 작업은 꼭 종료까지 해줘야한다.
 * <p>
 * cf) 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
 * 소멸전 콜백 : 빈이 소멸되기 직전에 호출출
 */
public class NetworkClient {

    private String url;

    public NetworkClient() {
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message : " + message);
    }

    public void disconnect() {
        System.out.println("close : " + url);
    }
}
