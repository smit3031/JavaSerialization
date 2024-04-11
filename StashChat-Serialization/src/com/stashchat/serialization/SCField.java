package com.stashchat.serialization;

import static com.stashchat.serialization.SerializationWriter.*;

public class SCField {
    public static final byte CONTAINER_TYPE = ContainerType.FIELD;
    public short nameLength;
    public byte[] name;
    public byte type;
    public byte[] data;

    private SCField(){}

    public void setName(String name) {
        assert(name.length() < Short.MAX_VALUE);
        nameLength = (short) name.length();
        this.name = name.getBytes();
    }

    public int getBytes(byte[] dest, int pointer){
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, type);
        pointer = writeBytes(dest, pointer, data);
        return pointer;
    }

    public int getSize(){
        assert(data.length == Type.getSize(type));
        return 1 + 2 + name.length + 1 + data.length;
    }

    public static SCField Byte(String name, byte value){
        SCField field = new SCField();
        field.setName(name);
        field.type = Type.BYTE;
        field.data = new byte[Type.getSize(Type.BYTE)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static SCField Short(String name, short value){
        SCField field = new SCField();
        field.setName(name);
        field.type = Type.SHORT;
        field.data = new byte[Type.getSize(Type.SHORT)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static SCField Char(String name, char value){
        SCField field = new SCField();
        field.setName(name);
        field.type = Type.CHAR;
        field.data = new byte[Type.getSize(Type.CHAR)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static SCField Integer(String name, int value){
        SCField field = new SCField();
        field.setName(name);
        field.type = Type.INTEGER;
        field.data = new byte[Type.getSize(Type.INTEGER)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static SCField Long(String name, long value){
        SCField field = new SCField();
        field.setName(name);
        field.type = Type.LONG;
        field.data = new byte[Type.getSize(Type.LONG)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static SCField Float(String name, float value){
        SCField field = new SCField();
        field.setName(name);
        field.type = Type.FLOAT;
        field.data = new byte[Type.getSize(Type.FLOAT)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static SCField Double(String name, double value){
        SCField field = new SCField();
        field.setName(name);
        field.type = Type.DOUBLE;
        field.data = new byte[Type.getSize(Type.DOUBLE)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static SCField Boolean(String name, boolean value){
        SCField field = new SCField();
        field.setName(name);
        field.type = Type.BOOLEAN;
        field.data = new byte[Type.getSize(Type.BOOLEAN)];
        writeBytes(field.data, 0, value);
        return field;
    }
}
