package TCPServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("TCPServer.Server Start-------------");
        ServerSocket serverSocket = new ServerSocket(8080);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(32,32,0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        while(true){
            Socket socket = serverSocket.accept();

            System.out.println("Someone is online-----"+ socket.getRemoteSocketAddress());

            pool.execute(new ServerReaderThread(socket));


        }

    }
}
