package Idan_Lachovitz_Idan_Pekler_Part2;

import java.util.Arrays;

public class Department {

    private String name;
    private int numOfStudent;
    private Lecturer[] arrLecturer;
    private int numOfLecturer;

    public Department(String name) {
        this.name = name;
        arrLecturer = new Lecturer[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public Lecturer[] getArrLecturer() {
        return arrLecturer;
    }

    public int getNumOfLecturer() {
        return numOfLecturer;
    }

    public void setArrLecturer(Lecturer lecturer) {
        if (numOfLecturer == arrLecturer.length) {
            arrLecturer = Arrays.copyOf(arrLecturer, numOfLecturer == 0 ? 2 : numOfLecturer * 2);
        }
        arrLecturer[numOfLecturer++] = lecturer;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", numOfStudent=" + numOfStudent +
                ", arrLecturer=" + Arrays.toString(arrLecturer) +
                '}';
    }

}
