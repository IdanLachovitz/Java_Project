package Idan_Lachovitz_Idan_Pekler_Part2;

import java.util.Arrays;

public class Lecturer {
    protected String name;
    protected String id;
    protected eDegree degree;
    public enum eDegree{BSc,MSc,DOCTOR,PROFESSOR};
    protected String nameOfDegree;
    protected int salary;
    protected String department;
    protected Committees[] committees;
    protected int numOfCommittees;

    public Lecturer(String name, String id, eDegree degree, int salary, String nameOfDegree) {
        setName(name);
        setId(id);
        setDegree(degree);
        setNameOfDegree(nameOfDegree);
        setSalary(salary);
        committees = new Committees[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public eDegree getDegree() {
        return degree;
    }

    public void setDegree(eDegree deg) {
        this.degree = deg;
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

    public Committees[] getCommittees() {
        return committees;
    }

    public void setCommittees(Committees committee) {
        if (numOfCommittees == committees.length) {
            committees = Arrays.copyOf(committees, numOfCommittees == 0 ? 2 : numOfCommittees * 2);
        }
        committees[numOfCommittees++] = committee;
    }

    public int getNumOfCommittees() {
        return numOfCommittees;
    }

    public void setNumOfCommittees(int numOfCommittees) {
        this.numOfCommittees = numOfCommittees;
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
                ", committees=" + committees + '\'' +
                '}';
    }

}

