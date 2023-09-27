package com.solid.class_notes;

import java.io.*;


public class FileIO {
    public static void main(String[] args) {
//        fileReaderExample("/Users/richardmmakuaebo/IdeaProjects/AssignmentCode/src/com/solid/class_notes/example.txt");
//        fileWriterExample("/Users/richardmmakuaebo/IdeaProjects/AssignmentCode/src/com/solid/class_notes/example.txt");
//        readFromInputStream("/Users/richardmmakuaebo/AndroidStudioProjects/tms-app-android/app/build/tmp/kapt3/incApCache/debug/apt-cache.bin");
//        writeToOutputStream("/Users/richardmmakuaebo/IdeaProjects/AssignmentCode/src/com/solid/class_notes/index.bin");

//        serializePerson("/Users/richardmmakuaebo/IdeaProjects/AssignmentCode/src/com/solid/class_notes/person.ser");
//        deserializePerson("/Users/richardmmakuaebo/IdeaProjects/AssignmentCode/src/com/solid/class_notes/person.ser");
    }

    /*
    * Input and Output in Java
    * Reading from and writing to files
    * Using streams for input and output
    * Serialization and Deserialization
    *
    *
    * Reading from file:
    * 1. File
    * 2. FileReader
    * 3. BufferedReader
    *
    * Writing to a File
    * 1. File
    * 2. FileWriter
    * 3. BufferedWriter
    *
    * Input/Output Stream:
    * 1. InputStream
    * 2. OutputStream class
    * 3. FileInputStream
    * 3. FileOutputStream
    *
    * Serialization and Deserialization:
    * Java provides a Serializable interface
    *
    *
    * */


    static void fileReaderExample(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    static void fileWriterExample(String fileName){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write("Hello World");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    static void readFromInputStream(String fileName){
        try(InputStream inputStream = new FileInputStream(fileName)){
            int data;
            while((data = inputStream.read()) != -1){
                System.out.println((char) data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static void writeToOutputStream(String fileName) {
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            String text = "Hello OutputStream";
            byte[] bytes = text.getBytes();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void serializePerson(String fileName) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            Person person = new Person("Alice", 30);
            objectOutputStream.writeObject(person);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void deserializePerson(String fileName){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))){
            Person person = (Person) objectInputStream.readObject();
            System.out.println("Name: " + person.name);
            System.out.println("Age: " + person.age);
        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

}

class Person implements Serializable{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}