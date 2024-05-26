package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        // your code goes here
        studentMap.put(student.toString(), student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        teacherMap.put(teacher.toString(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            if(teacherStudentMapping.containsKey(teacher)){
                teacherStudentMapping.get(teacher).add(student);
            } else {
                List<String> students = new ArrayList<>();
                students.add(student);
                teacherStudentMapping.put(teacher, students);
            }
        }
    }

    public Student findStudent(String student){
        // your code goes here
        return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        return teacherMap.get(teacher);
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        return teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
        // find student list corresponding to a teacher
    }

    public List<String> findAllStudents(){
        // your code goes here
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        teacherMap.remove(teacher);
        List<String> students = teacherStudentMapping.remove(teacher);
        if(students != null){
            for(String studentName : students){
                studentMap.remove(studentName);
            }
        }
    }

    public void deleteAllTeachers(){
        // your code goes here
        for(String teacherName : teacherStudentMapping.keySet()){
            List<String> students = teacherStudentMapping.get(teacherName);
            for(String studentName : students){
                studentMap.remove(studentName);
            }
        }
        teacherMap.clear();
        teacherStudentMapping.clear();
    }
}