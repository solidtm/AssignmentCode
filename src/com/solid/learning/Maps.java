package com.solid.learning;

import java.util.*;

/*
*    Map: Map is a data structure that store data as key-value pairs
*
*    //operations in map
*   1.  put -> putting a value based on a key
*       put value 1: student1, 2: student2,
*       Map -> [1: student1, 2: student2]
*
*   2. remove -> retrieve the value based on the key
*      map[2] => return student2
*
*
* */
public class Maps {
    public static void main(String[] args) {
        Map<Integer, Student> studentMap = new HashMap<>();
        studentMap.put(1, new Student("Amanda Joe", 15));
//        studentMap.put(2, new Student("Jane Doe", 20));
//        studentMap.put(3, new Student("Dave Peterson", 24));
//        studentMap.put(4, new Student("Liam McDonald", 23));
//        studentMap.put(5, new Student("Susie Parker", 18));
        studentMap.putIfAbsent(2, new Student("Don Michael", 19));

        System.out.println(studentMap);
        System.out.println("The value with the key 6 is: " + studentMap.get(6));
        System.out.println("The value with the key 6 is: " + studentMap.getOrDefault(6, new Student("No name", 0)));
    }
}

class Student{
    private String fullName;
    private int age;
    List<Course> courses;

    public Student(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
        courses = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", courses=" + courses +
                '}';
    }
}

class Course{
     private String courseName;
     private String courseTitle;
     private String curriculum;

    public Course(String courseName, String courseTitle, String curriculum) {
        this.courseName = courseName;
        this.courseTitle = courseTitle;
        this.curriculum = curriculum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", curriculum='" + curriculum + '\'' +
                '}';
    }
}
