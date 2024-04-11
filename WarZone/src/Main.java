import com.stashchat.serialization.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static com.stashchat.serialization.SerializationWriter.writeBytes;

public class Main {
    public static void main(String[] args) {

//        Field field = Field.Long("Test", 18);
//        byte[] data = new byte[100];
//        field.getBytes(data, 0);
        Random random = new Random();
        int[] data = new int[10000];
        for (int i=0; i< data.length; i++){
            data[i] = random.nextInt();
        }

        SCDatabase database = new SCDatabase("Database");

        SCArray array  = SCArray.Integer("Test", data);
        SCField field = SCField.Integer("Integer", 8);


        SCObject object = new SCObject("Entity");
        object.addArray(array);
        object.addField(field);

        database.addObject(object);

        byte[] stream = new byte[database.getSize()];
        database.getBytes(stream, 0);
        saveToFile("test.scd", stream);
//        printBytes(stream);

//        byte[] data = new byte[] {0x0, 0x0, 0x27, 0x10};
//        printBytes(data);
    }

    private static void printBytes(byte[] data) {
        for (byte d:data){
            System.out.printf("0x%x ", d);
        }
    }

    static void saveToFile(String path, byte[] data){
        try{
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
            stream.write(data);
            stream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
