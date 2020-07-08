package ru.nsu.fit.popov.compiler.bytecode;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;

public final class ConstantPool {

    public static final short SYSTEM_OUT    = 0x0c;
    public static final short PRINT_INT     = 0x12;
    public static final short PRINT_STRING  = 0x18;

//    ------   tags   ------
    private static final byte STRING        = 0x01;
    private static final byte INT           = 0x03;
    private static final byte CLASS         = 0x07;
    private static final byte STRING_REF    = 0x08;
    private static final byte FIELD         = 0x09;
    private static final byte METHOD        = 0x0a;
    private static final byte DESCRIPTOR    = 0x0c;

    private static final List<byte[]> pool = new ArrayList<>();

    private static final Map<String, Short> utfs = new HashMap<>();
    private static final Map<Short, Short> strings = new HashMap<>();
    private static final Map<Integer, Short> integers = new HashMap<>();
    private static final Map<Short, Short> classes = new HashMap<>();
    private static final Map<Integer, Short> descriptors = new HashMap<>();
    private static final Map<Integer, Short> methods = new HashMap<>();

    public static void init(String name) {
        putMethod((short) 0x03, (short) 0x09);      //  0x01:   Object constructor
        putClass((short) 0x0a);                     //  0x02:   self class
        putClass((short) 0x0b);                     //  0x03:   java/lang/Object
        putUtf("<init>");                           //  0x04
        putUtf("()V");                              //  0x05
        putUtf("Code");                             //  0x06
        putUtf("main");                             //  0x07
        putUtf("([Ljava/lang/String;)V");           //  0x08
        putDescriptor((short) 0x04, (short) 0x05);  //  0x09:   <init>:()V
        putUtf(name);                               //  0x0a
        putUtf("java/lang/Object");                 //  0x0b
        putField((short) 0x0d, (short) 0x0f);       //  0x0c:   System.out
        putClass((short) 0x0e);                     //  0x0d:   java/lang/System
        putUtf("java/lang/System");                 //  0x0e
        putDescriptor((short) 0x10, (short) 0x11);  //  0x0f:   out:Ljava/io/PrintStream
        putUtf("out");                              //  0x10
        putUtf("Ljava/io/PrintStream;");            //  0x11
        putMethod((short) 0x13, (short) 0x15);      //  0x12:   println(int)
        putClass((short) 0x14);                     //  0x13:   java/io/PrintStream
        putUtf("java/io/PrintStream");              //  0x14
        putDescriptor((short) 0x16, (short) 0x17);  //  0x15:   println:(I)V
        putUtf("println");                          //  0x16
        putUtf("(I)V");                             //  0x17
        putMethod((short) 0x13, (short) 0x19);      //  0x18    println(String)
        putDescriptor((short) 0x16, (short) 0x1a);  //  0x19    println:(Ljava/lang/String;)V
        putUtf("(Ljava/lang/String;)V");            //  0x1a
    }

    private static short putUtf(String utf) {
        byte[] bytes = utf.getBytes(Charset.forName("UTF-8"));

        byte[] entry = ByteBuffer.allocate(1 + 2 + bytes.length)
                .put(STRING)
                .putShort((short) bytes.length)
                .put(bytes)
                .array();
        pool.add(entry);

        final short ref = (short) pool.size();

        utfs.put(utf, ref);

        return ref;
    }

    private static short putInteger(int value) {
        byte[] entry = ByteBuffer.allocate(1 + 4)
                .put(INT)
                .putInt(value)
                .array();
        pool.add(entry);

        final short ref = (short) pool.size();

        integers.put(value, ref);

        return ref;
    }

    private static short putClass(short nameRef) {
        byte[] entry = ByteBuffer.allocate(1 + 2)
                .put(CLASS)
                .putShort(nameRef)
                .array();
        pool.add(entry);

        final short ref = (short) pool.size();

        classes.put(nameRef, ref);

        return ref;
    }

    private static short putStringRef(short stringRef) {
        byte[] entry = ByteBuffer.allocate(1 + 2)
                .put(STRING_REF)
                .putShort(stringRef)
                .array();
        pool.add(entry);

        final short ref = (short) pool.size();

        strings.put(stringRef, ref);

        return ref;
    }

    private static void putField(short classRef, short descriptorRef) {
        byte[] entry = ByteBuffer.allocate(1 + 2 + 2)
                .put(FIELD)
                .putShort(classRef)
                .putShort(descriptorRef)
                .array();
        pool.add(entry);
    }

    private static short putMethod(short classRef, short descriptorRef) {
        byte[] entry = ByteBuffer.allocate(1 + 2 + 2)
                .put(METHOD)
                .putShort(classRef)
                .putShort(descriptorRef)
                .array();
        pool.add(entry);

        final short ref = (short) pool.size();

        final int key = (classRef << 8) | (0xff & descriptorRef);
        methods.put(key, ref);

        return ref;
    }

    private static short putDescriptor(short nameRef, short typeRef) {
        byte[] entry = ByteBuffer.allocate(1 + 2 + 2)
                .put(DESCRIPTOR)
                .putShort(nameRef)
                .putShort(typeRef)
                .array();
        pool.add(entry);

        final short ref = (short) pool.size();

        final int key = (nameRef << 8) | (0xff & typeRef);
        descriptors.put(key, ref);

        return ref;
    }

    public static byte[] getBytes() {
        int size = 2 + pool.stream()
                .mapToInt(bytes -> bytes.length)
                .sum();

        final ByteBuffer buffer = ByteBuffer.allocate(size);
        buffer.putShort((short) (pool.size() + 1));
        for (byte[] bytes : pool) {
            buffer.put(bytes);
        }

        return buffer.array();
    }

    private static short getUtfRef(String utf) {
        Short utfRef = utfs.get(utf);
        if (utfRef == null)
            utfRef = putUtf(utf);

        return utfRef;
    }

    private static short getDescriptorRef(String methodName, String type) {
        final short nameRef = getUtfRef(methodName);
        final short typeRef = getUtfRef(type);
        final int key = (nameRef << 8) | (0xff & typeRef);

        Short descriptorRef = descriptors.get(key);
        if (descriptorRef == null)
            descriptorRef = putDescriptor(nameRef, typeRef);

        return descriptorRef;
    }

    public static short getIntegerRef(int value) {
        Short ref = integers.get(value);
        if (ref == null)
            ref = putInteger(value);

        return ref;
    }

    public static short getStringRef(String string) {
        final short utfRef = getUtfRef(string);

        Short ref = strings.get(utfRef);
        if (ref == null)
            ref = putStringRef(utfRef);

        return ref;
    }

    public static short getClassRef(String className) {
        final short nameRef = getUtfRef(className);

        Short classRef = classes.get(nameRef);
        if (classRef == null)
            classRef = putClass(nameRef);

        return classRef;
    }

    public static short getMethodRef(String className, String methodName, String type) {
        final short classRef = getClassRef(className);
        final short descriptorRef = getDescriptorRef(methodName, type);
        final int key = (classRef << 8) | (0xff & descriptorRef);

        Short methodRef = methods.get(key);
        if (methodRef == null)
            methodRef = putMethod(classRef, descriptorRef);

        return methodRef;
    }
}
