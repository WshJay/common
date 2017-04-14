package org.wsh.common.util.serialize;

import java.io.*;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  java对象序列化和反序列化
 * since Date： 2017-04-10 11:46
 */
public class SerializeUtil {

    /**
     * 序列化
     * @param object Object
     * @return byte[]
     * @throws IOException
     */
    public static byte[] serialize(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        return baos.toByteArray();
    }

    /**
     * 反序列化
     * @param bytes byte[]
     * @return Object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object unSerialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
}
