package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class MyClient {

    public static void main(String[] args) {
        // 서버 주소와 포트 설정
        String serverAddress = "localhost";
        int serverPort = 8080;

        try {
            // 1. SocketChannel 생성
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false); // Non-blocking 모드로 설정

            // 2. 서버에 연결
            if (!socketChannel.connect(new InetSocketAddress(serverAddress, serverPort))) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("Connecting to server...");
                }
            }
            System.out.println("Connected to server!");

            // 3. 데이터 전송
            Scanner sc = new Scanner(System.in);
            ByteBuffer.allocate(1024);
            ByteBuffer buffer;
            do {
                System.out.print("message: ");
                String msg = sc.nextLine();
                buffer = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(buffer);
                buffer.flip();
                buffer.clear(); // 버퍼 초기화
            } while (sc.hasNextLine());

            // 4. 서버로부터 응답 수신
            int bytesRead = socketChannel.read(buffer);
            if (bytesRead > 0) {
                buffer.flip();
                System.out.println("Received from server: " + new String(buffer.array(), 0, bytesRead));
            }

            // 5. 연결 종료
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
