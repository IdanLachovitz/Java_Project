package Idan_Lachovitz_Idan_Pekler_Part2;

public class Lecturer {
    private String name;
    private int id;
    private eDegree degree;
    public enum eDegree{BSc,MSc,DOCTOR,PROFESSOR};
    private String nameOfDegree;
    private int salary;
    private String department;

    public Lecturer(String name, int id, int degree, int salary, String nameOfDegree) {
        this(name,id,degree,salary,nameOfDegree,null);
    }

    public Lecturer(String name, int id, int degree,int salary, String nameOfDegree, String department) {
        setName(name);
        setId(id);
        setDegree(degree);
        setNameOfDegree(nameOfDegree);
        setSalary(salary);
        setDepartment(department);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public eDegree getDegree() {
        return degree;
    }

    public void setDegree(int deg) {
        // 1- B.S.c 2-MSc 3-doctor 4- professor
        switch (deg) {
            case 1 -> this.degree = eDegree.BSc;
            case 2 -> this.degree = eDegree.MSc;
            case 3 -> this.degree = eDegree.DOCTOR;
            case 4 -> this.degree = eDegree.PROFESSOR;
            default -> this.degree = eDegree.BSc; //default for invalid is first degree
        }
    }

    public String getNameOfDegree() {
        return nameOfDegree;
    }

    public void setNameOfDegree(String nameOfDegree) {
        this.nameOfDegree = nameOfDegree;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", degree='" + degree + '\'' +
                ", nameOfDegree='" + nameOfDegree + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }

}
