package okio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class OkioSerializeUtils {
    /**
     * 对象序列化为string字符串
     */
    public static <T extends Serializable> String serializeString(T obj) {
        return serializeHexString(obj);
    }

    /**
     * 对象序列化为16进制字符串
     */
    public static <T extends Serializable> String serializeHexString(T obj) {
        return serialize(obj).hex();
    }

    /**
     * 对象序列化为base64编码
     */
    public static <T extends Serializable> String serializeBase64(T obj) {
        return serialize(obj).base64();
    }


    /**
     * string 反序列化对象
     */
    public static <T extends Serializable> T deserializeString(String string) {
        return deserialize(deserializeHexString(string));
    }


    /**
     * base64编码反序列化对象
     */
    public static <T extends Serializable> T deserializeBase64(String base64String) {
        return deserialize(ByteString.decodeBase64(base64String));
    }

    /**
     * 16进制字符串反序列化对象
     */
    public static <T extends Serializable> T deserializeHexString(String hexString) {
        return deserialize(ByteString.decodeHex(hexString));
    }

    /**
     * 序列化
     */
    private static <T extends Serializable> ByteString serialize(T obj) {
        Buffer buffer = new Buffer();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(buffer.outputStream())) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.readByteString();
    }

    /**
     * 反序列化
     */
    private static <T extends Serializable> T deserialize(ByteString byteString) {
        Buffer buffer = new Buffer();
        buffer.write(byteString);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(buffer.inputStream())) {
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
