package org.github.yibing.spring.ioc.autowire;

public class Teacher {
    private String subject;
    private String id;
    //    依赖Student类
    private Student student;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "subject='" + subject + '\'' +
                ", id='" + id + '\'' +
                ", student=" + student +
                '}';
    }
}
