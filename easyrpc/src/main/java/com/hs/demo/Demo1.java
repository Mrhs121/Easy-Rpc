package com.hs.demo;

import com.hs.easyrpc.common.CommonStrings;
import com.linux.huhx.avro.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class Demo1 {


    // 将对象序列化后写入文件中
    public static void serializingTest1() throws IOException {
        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
//        user1.setFavoriteColor("yellow");

        User user2 = new User("Ben", 7, "red");

        User user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(null)
                .build();

        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(), new File(CommonStrings.AVROS_PATH +"users.avro"));

        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();
    }


    // 从文件中反序列化文件
    public static void deserializing() throws IOException {
        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(new File(CommonStrings.AVROS_PATH +"users.avro"), userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        // 测试项目结构
        System.out.println("test project structure");
        try {
            serializingTest1();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            deserializing();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
