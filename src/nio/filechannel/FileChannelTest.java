package nio.filechannel;

import nio.Consts;
import sort.TimeDelta;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

    private static void originReadFile(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int readSize = inputStream.read(buffer);
            while (readSize != -1) {
                for (int i = 0; i < readSize; i++) {
                    System.out.print((char) buffer[i]);
                }
                readSize = inputStream.read(buffer);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nioFileChannelTest(String filePath) {
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(filePath, "r");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int readSize = fileChannel.read(buffer);
            while (readSize != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.compact();
                readSize = fileChannel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("nio\n");
        TimeDelta timeDelta = new TimeDelta();
        nioFileChannelTest(Consts.TEST_FILE_PATH);
        System.out.println(timeDelta.getDelta());

        System.out.println();
        System.out.println("bio\n");
        timeDelta.renew();
        originReadFile(Consts.TEST_FILE_PATH);
        System.out.println(timeDelta.getDelta());
    }
}
