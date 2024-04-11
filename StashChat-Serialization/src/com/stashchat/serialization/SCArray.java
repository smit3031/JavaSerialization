package com.stashchat.serialization;

import static com.stashchat.serialization.SerializationWriter.writeBytes;

public class SCArray {
    public static final byte CONTAINER_TYPE = ContainerType.ARRAY;
    public short nameLength;
    public byte[] name;
    public int size = 1 + 2 + 4 + 1 + 4;
    public byte type;
    public int count;
    public byte[] data;

    private char[] charData;
    private short[] shortData;
    private int[] intData;
    private long[] longData;
    private float[] floatData;
    private double[] doubleData;
    private boolean[] boolData;

    private SCArray(){}
    public void setName(String name) {
        assert(name.length() < Short.MAX_VALUE);

        if (this.name != null) size -= this.name.length;

        nameLength = (short) name.length();
        this.name = name.getBytes();
        size += nameLength;
    }

    public int getBytes(byte[] dest, int pointer){
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, type);
        pointer = writeBytes(dest, pointer, count);

        switch (type) {
            case Type.BYTE    -> pointer = writeBytes(dest, pointer, data);
            case Type.CHAR    -> pointer = writeBytes(dest, pointer, charData);
            case Type.SHORT   -> pointer = writeBytes(dest, pointer, shortData);
            case Type.INTEGER -> pointer = writeBytes(dest, pointer, intData);
            case Type.LONG    -> pointer = writeBytes(dest, pointer, longData);
            case Type.FLOAT   -> pointer = writeBytes(dest, pointer, floatData);
            case Type.DOUBLE  -> pointer = writeBytes(dest, pointer, doubleData);
            case Type.BOOLEAN -> pointer = writeBytes(dest, pointer, boolData);
        }
        return pointer;
    }

    public int getSize(){
        return size;
    }

    public int getDataSize(){
        return switch (type) {
            case Type.BYTE -> data.length * Type.getSize(Type.BYTE);
            case Type.CHAR -> charData.length * Type.getSize(Type.CHAR);
            case Type.SHORT -> shortData.length * Type.getSize(Type.SHORT);
            case Type.INTEGER -> intData.length * Type.getSize(Type.INTEGER);
            case Type.LONG -> longData.length * Type.getSize(Type.LONG);
            case Type.FLOAT -> floatData.length * Type.getSize(Type.FLOAT);
            case Type.DOUBLE -> doubleData.length * Type.getSize(Type.DOUBLE);
            case Type.BOOLEAN -> boolData.length * Type.getSize(Type.BOOLEAN);
            default -> 0;
        };
    }

    private void updateSize(){
        size += getDataSize();
    }

    public static SCArray Byte(String name, byte[] data){
        SCArray array = new SCArray();
        array.setName(name);
        array.type = Type.BYTE;
        array.count = data.length;
        array.data = data;
        array.updateSize();
        return array;
    }

    public static SCArray Char(String name, char[] data){
        SCArray array = new SCArray();
        array.setName(name);
        array.type = Type.CHAR;
        array.count = data.length;
        array.charData = data;
        array.updateSize();
        return array;
    }

    public static SCArray Short(String name, short[] data){
        SCArray array = new SCArray();
        array.setName(name);
        array.type = Type.SHORT;
        array.count = data.length;
        array.shortData = data;
        array.updateSize();
        return array;
    }

    public static SCArray Integer(String name, int[] data){
        SCArray array = new SCArray();
        array.setName(name);
        array.type = Type.INTEGER;
        array.count = data.length;
        array.intData = data;
        array.updateSize();
        return array;
    }

    public static SCArray Long(String name, long[] data){
        SCArray array = new SCArray();
        array.setName(name);
        array.type = Type.LONG;
        array.count = data.length;
        array.longData = data;
        array.updateSize();
        return array;
    }

    public static SCArray Float(String name, float[] data){
        SCArray array = new SCArray();
        array.setName(name);
        array.type = Type.FLOAT;
        array.count = data.length;
        array.floatData = data;
        array.updateSize();
        return array;
    }

    public static SCArray Double(String name, double[] data){
        SCArray array = new SCArray();
        array.setName(name);
        array.type = Type.DOUBLE;
        array.count = data.length;
        array.doubleData = data;
        array.updateSize();
        return array;
    }

    public static SCArray Boolean(String name, boolean[] data){
        SCArray array = new SCArray();
        array.setName(name);
        array.type = Type.BOOLEAN;
        array.count = data.length;
        array.boolData = data;
        array.updateSize();
        return array;
    }
}
