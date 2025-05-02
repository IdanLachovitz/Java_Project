package Idan_Lachovitz_Idan_Pekler_Part2;

import java.util.Arrays;

public class Committees {

    private String nameOfCommittee;
    private Lecturer[] lecturers;
    private int numOfLecturer;
    private Lecturer chairman;


    public Committees(String nameofCommittes, Lecturer chairman) {
        this.nameOfCommittee = nameofCommittes;
        this.chairman = chairman;
        lecturers = new Lecturer[0];
        this.numOfLecturer = 0;
    }


    public void removeLecturerByName(String name) {
        for (int i = 0; i < numOfLecturer; i++) {
            if (lecturers[i].getName().equals(name)) {
                for (int j = i; j < numOfLecturer - 1; j++) {
                    lecturers[j] = lecturers[j + 1];
                }
                lecturers[numOfLecturer - 1] = null;
                numOfLecturer--;
            }
        }
    }


    public void addLecturerToCommittee(Lecturer lecturer) {
        if(numOfLecturer==lecturers.length){
            lecturers= Arrays.copyOf(lecturers,numOfLecturer==0?2:numOfLecturer*2);
        }
        lecturers[numOfLecturer++]=lecturer;
    }


    public String getNameofCommittees () {
        return nameOfCommittee;
    }


    public void setNameofCommittees (String nameofCommittes){
        this.nameOfCommittee = nameofCommittes;
    }


    public Lecturer[] getLecturers () {
        return lecturers;
    }


    public void setLecturers (Lecturer lectu){
        if (numOfLecturer == lecturers.length) {
            lecturers = Arrays.copyOf(lecturers, numOfLecturer == 0 ? 2 : numOfLecturer * 2);
        }
        lecturers[numOfLecturer++] = lectu;
    }


    public int getNumofLecturer () {
        return numOfLecturer;
    }


    public void setNumofLecturer (int numofLecturer){
        this.numOfLecturer = numofLecturer;
    }


    public Lecturer getChairman () {
        return chairman;
    }


    public  void setChairman (Lecturer chairman){
        this.chairman = chairman;
    }

    @Override
    public String toString() {
        return "Committees{" +
                "nameOfCommittee='" + nameOfCommittee + '\'' +
                ", lecturers=" + Arrays.toString(lecturers) +
                ", numOfLecturer=" + numOfLecturer +
                ", chairman=" + chairman +
                '}';
    }
}
