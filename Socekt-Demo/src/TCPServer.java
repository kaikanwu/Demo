import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author kaikanwu
 * @date 09/03/2019
 */
public class TCPServer {

    public static void main(String[] args)throws Exception {

        // 创建 socket，并将 socket 绑定到 65000 端口
        ServerSocket ss = new ServerSocket(65000);

        while (true) {
            // 监听这个端口，直到返回信息
            Socket socket = ss.accept();
            new LengthCalculator(socket).start();
        }
    }

}
