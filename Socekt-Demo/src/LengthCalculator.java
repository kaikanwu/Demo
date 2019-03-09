import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author kaikanwu
 * @date 09/03/2019
 */
public class LengthCalculator extends Thread {

    private Socket socket;

    public LengthCalculator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            // 获取 socket 的输入流
            OutputStream os = socket.getOutputStream();
            // 获取 socket 的输出流
            InputStream is = socket.getInputStream();

            // buff 主要用来读取输入的内容，存成 byte 数组
            int ch = 0;
            byte[] buff = new byte[1024];
            // ch 主要用来获取读到数组的长度
            ch = is.read(buff);

            String content = new String(buff, 0, ch);
            System.out.println(content);

            // 在输出流中，写入获得字符串的长度，回发给客户端
            os.write(String.valueOf(content.length()).getBytes());

            // 关闭输入输出和 socket
            is.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
