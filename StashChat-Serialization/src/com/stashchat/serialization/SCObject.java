package com.stashchat.serialization;

import java.util.ArrayList;
import java.util.List;

import static com.stashchat.serialization.SerializationWriter.writeBytes;

public class SCObject {

    public static final byte CONTAINER_TYPE = ContainerType.OBJECT;
    public short nameLength;
    public byte[] name;
    private int size = 1 + 2 + 4 + 2 + 2;
    private short fieldCount;
    private List<SCField> fields = new ArrayList<>();
    private short arrayCount;
    private List<SCArray> arrays = new ArrayList<>();

    public SCObject(String name){
        setName(name);
    }

    public void setName(String name) {
        assert(name.length() < Short.MAX_VALUE);

        if (this.name != null) size -= this.name.length;

        nameLength = (short) name.length();
        this.name = name.getBytes();
        size += nameLength;
    }

    public void addArray(SCArray array) {
        arrays.add(array);
        size += array.getSize();
        arrayCount = (short) arrays.size();
    }

    public void addField(SCField field) {
        fields.add(field);
        size += field.getSize();
        fieldCount = (short) fields.size();
    }

    public int getSize() {
        return size;
    }

    public int getBytes(byte[] dest, int pointer) {
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, size);

        pointer = writeBytes(dest, pointer, fieldCount);
        for (SCField field : fields)
            pointer = field.getBytes(dest, pointer);

        pointer = writeBytes(dest, pointer, arrayCount);
        for (SCArray array : arrays)
            pointer = array.getBytes(dest, pointer);

        return pointer;
    }
}
