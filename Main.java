package Idan_Lachovitz_Idan_Pekler_Part2;

import java.net.CookieHandler;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // idan lachovitz, 322894148 + idan pekler, 207715848
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter college name: ");
        String name = s.nextLine();
        College c = new College(name);
        run(c);
    }

    private static final String[] MENU = {
            "Exit Program",
            "Add Lecturer",
            "Add Committee",
            "add Lecturer To Committee",
            "update Chairman",
            "remove From Committee",
            "add Department",
            "add Lecturer To Department",
            "show Average Lecturers Salary",
            "show Average Salary From Specific Department",
            "show Lecturers Info",
            "show Committee Info",
            "comparison between doc and prof by articles",
            "committee comparison",
            "copy committee to new committee"

    };

    public static void run(College college) {
        int userChosen;
        do {
            userChosen = showMenu(s);
            switch (userChosen) {
                case 0 -> System.out.println("Done... Bye");
                case 1 -> addLecturer(college);
                case 2 -> addCommittee(college);
                case 3 -> addLecturerToCommittee(college);
                case 4 -> updateChairman(college);
                case 5 -> removeFromCommittee(college);
                case 6 -> addDepartment(college);
                case 7 -> addLecturerToDepartment(college);
                case 8 -> showAverageProfessorsSalary(college);
                case 9 -> showAverageSalaryFromSpecificDepartment(college);
                case 10 -> showLecturersInfo(college.getLecturer(), college.getNumOfLecturer());
                case 11 -> showCommitteesInfo(college.getCommittees(), college.getNumOfCommittee());
                case 12 -> docProfCompare(college);
                case 13 -> departmentComparison();
                case 14 -> copyCommittee(college);

                default -> System.out.println("Unexpected value");
            }
        } while (userChosen != 0);
    }


    private static void addLecturer(College college) {
        s.nextLine();
        boolean bool = false;
        while (true) {
            System.out.println("Enter lecturer name: (press 0 to return to the menu) ");
            String name = s.nextLine();
            if (name.equals("0")){
                break;
            } else {
                System.out.println("Enter lecturer id: ");
                String id = s.nextLine();
                System.out.println("Enter your degree (the default is 1): \n1- BSc\n2- MSc\n3- doctor\n4- professor");
                int degree = s.nextInt();
                Lecturer.eDegree deg;
                switch (degree){
                    case 1 -> deg = Lecturer.eDegree.BSc;
                    case 2 -> deg = Lecturer.eDegree.MSc;
                    case 3 -> deg = Lecturer.eDegree.DOCTOR;
                    case 4 -> deg = Lecturer.eDegree.PROFESSOR;
                    default -> deg = Lecturer.eDegree.BSc; //default for invalid is first degree
                }
                s.nextLine();
                System.out.println("Enter name of the degree: ");
                String nameOfDegree = s.nextLine();
                int salary;
                while (true) {
                    try {
                        System.out.println("Enter your salary: ");
                        salary = s.nextInt();
                        s.nextLine();
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }
                try{
                    if (degree == 3){
                        System.out.println("Enter the articles: ");
                        String articles = s.nextLine();
                        college.addLecturer(name, id, deg, salary, nameOfDegree, articles.split(" "));
                    } else if (degree == 4){
                        System.out.println("Enter the articles: ");
                        String articles = s.nextLine();
                        System.out.println("Enter professorship name: ");
                        String professorshipName = s.nextLine();
                        college.addLecturer(name, id, deg, salary, nameOfDegree, articles.split(" "), professorshipName);
                    } else {
                        college.addLecturer(name, id, deg, salary, nameOfDegree);
                    }
                } catch (LecturerException e){
                    System.out.println(e.getMessage());
                    bool = true;
                }
                if (bool == false){
                    System.out.println(eStatus.SUCCESS);
                    System.out.println(Util.printNames(college.getLecturer(), college.getNumOfLecturer()));
                }
                break;
            }
        }
    }


    private static void addCommittee(College college) {
        s.nextLine();
        boolean bool = false;
        System.out.println("Enter committee name: ");
        String committeeName = s.nextLine();
        System.out.println("Enter chairhead name: ");
        String chairman = s.nextLine();
        try {
            college.addCommittee(committeeName, chairman);
        } catch (CommitteeException | LecturerException e){
            System.out.println(e.getMessage());
            bool = true;
        }
        if (bool == false){
            System.out.println(eStatus.SUCCESS);
            System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee()));
        }
    }


    private static void addLecturerToCommittee(College college) {
        s.nextLine();
        boolean bool = false;
        System.out.println("Enter committee name: ");
        System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee()));
        String committeeName = s.nextLine();
        System.out.println("Enter lecturer name: ");
        System.out.println(Util.printNames(college.getLecturer(), college.getNumOfLecturer()));
        String name = s.nextLine();
        try {
            college.addLecturerToCommittee(committeeName, name);
        } catch (CommitteeException | LecturerException e){
            System.out.println(e.getMessage());
            bool = true;
        }
        if (bool == false){
            System.out.println(eStatus.SUCCESS);
            Util.getLecturerFromName(name, college.getLecturer()).setCommittees(Util.getCommitteeFromName(committeeName, college.getCommittees()));
            System.out.println(Util.printNames(Util.getCommitteeFromName(committeeName, college.getCommittees()), Util.getCommitteeFromName(committeeName, college.getCommittees()).getLecturers(), Util.getCommitteeFromName(committeeName, college.getCommittees()).getNumofLecturer()));
        }
    }


    private static void updateChairman(College college){
        s.nextLine();
        boolean bool = false;
        System.out.println("What Committee: ");
        System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee()));
        String committeeName = s.nextLine();
        System.out.println("enter new head of committee: ");
        System.out.println(Util.printNames(college.getLecturer(), college.getNumOfLecturer()));
        String newChairMan = s.nextLine();
        try{
            college.updateChairman(committeeName, newChairMan);
            Util.getCommitteeFromName(committeeName,college.getCommittees());
        } catch (CommitteeException | LecturerException e){
            System.out.println(e.getMessage());
            bool = true;
        }
        if (bool == false){
            System.out.println(eStatus.SUCCESS);
            System.out.println(Util.printNames(Util.getCommitteeFromName(committeeName, college.getCommittees()), Util.getCommitteeFromName(committeeName, college.getCommittees()).getLecturers(), Util.getCommitteeFromName(committeeName, college.getCommittees()).getNumofLecturer()));
        }
    }


    private static void removeFromCommittee(College college) {
        s.nextLine();
        boolean bool = false;
        System.out.println("Enter committee name: ");
        System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee()));
        String committeeName = s.nextLine();
        System.out.println("Enter lecturer name: ");
        System.out.println(Util.printNamesFromCommittee(Util.getCommitteeFromName(committeeName, college.getCommittees()).getLecturers(), Util.getCommitteeFromName(committeeName, college.getCommittees()).getNumofLecturer()));
        String name = s.nextLine();
        try{
            college.removeFromCommittee(name, committeeName);
        } catch (CommitteeException | LecturerException e){
            System.out.println(e.getMessage());
            bool = true;
        }
        if (bool == false){
            System.out.println(eStatus.SUCCESS);
            System.out.println(Util.printNames(Util.getCommitteeFromName(committeeName, college.getCommittees()), Util.getCommitteeFromName(committeeName, college.getCommittees()).getLecturers(), Util.getCommitteeFromName(committeeName, college.getCommittees()).getNumofLecturer()));
        }

    }


    private static void addDepartment(College college) {
        s.nextLine();
        boolean bool = false;
        System.out.println("Enter department name: ");
        String depName = s.nextLine();
        try {
            college.addDepartment(depName);
        } catch (DepartmentException e){
            System.out.println(e.getMessage());
            bool = true;
        }
        if (bool == false){
            System.out.println(eStatus.SUCCESS);
            System.out.println(Util.printNames(college.getDepartments(), college.getNumOfDepartment()));
        }
    }


    private static void addLecturerToDepartment(College college) {
        s.nextLine();
        boolean bool = false;
        System.out.println("Enter department name: ");
        System.out.println(Util.printNames(college.getDepartments(), college.getNumOfDepartment()));
        String deptName = s.nextLine();
        System.out.println("Enter lecturer name: ");
        System.out.println(Util.printNames(college.getLecturer(), college.getNumOfLecturer()));
        String lectName = s.nextLine();
        try {
            college.addLecturerToDepartment(lectName,deptName);
        } catch (LecturerException | DepartmentException e){
            System.out.println(e.getMessage());
            bool = true;
        }
        if (bool == false){
            System.out.println(eStatus.SUCCESS);
            System.out.println(Util.printNames(Util.getDepartmentFromName(deptName, college.getDepartments()).getArrLecturer(), Util.getDepartmentFromName(deptName, college.getDepartments()).getNumOfLecturer(), deptName));
        }
    }


    private static void showAverageProfessorsSalary(College college) {
        float sum = 0;
        boolean bool = false;
        try {
            sum = college.showAverageProfessorsSalary();
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
            bool = true;
        }
        if (bool == false){
            System.out.println(eStatus.AVERAGE_SALARY + " " + sum);
        }

    }


    private static void showAverageSalaryFromSpecificDepartment(College college) {
        s.nextLine();
        boolean bool = false;
        float sum = 0;
        System.out.println("Enter department name: ");
        System.out.println(Util.printNames(college.getDepartments(), college.getNumOfDepartment()));
        String deptName = s.nextLine();
        try {
            sum = college.showAverageSalaryFromSpecificDepartment(deptName);
        } catch (DepartmentException e){
            System.out.println(e.getMessage());
            bool = true;
        }
        if (bool == false){
            System.out.println(eStatus.AVERAGE_SALARY + " " + sum);
        }
    }


    private static void showLecturersInfo(Lecturer[] arr, int numOfLecturers) {
        StringBuilder sb = new StringBuilder();
        if (numOfLecturers == 0){
            System.out.println(eStatus.GENERAL_ERROR);
        } else {
            for (int i = 0; i < numOfLecturers; i++) {
                sb.append("Name: " + arr[i].getName() + " , ID: " + arr[i].getId() + " , Degree: " + arr[i].getDegree() + " , Degree name: " + arr[i].getNameOfDegree() + " , Salary: " + arr[i].getSalary() + " , " + Util.printNames(arr[i].getCommittees(),arr[i].getNumOfCommittees()) + " , Department: " + arr[i].getDepartment() + "\n");
            }
            System.out.println(sb);
        }
    }


    private static void showCommitteesInfo(Committees[] arr, int numOfCommittees) {
        StringBuilder sb = new StringBuilder();
        if (arr == null){
            System.out.println(eStatus.GENERAL_ERROR);
        } else {
            for (int i = 0; i < numOfCommittees; i++) {
                sb.append("committee name: " + arr[i].getNameofCommittees() + " , The chairman: " + arr[i].getChairman().getName() + " , " + Util.printNames(arr[i].getLecturers(), arr[i].getNumofLecturer()) + "\n");
            }
            System.out.println(sb);
        }
    }


    private static void docProfCompare(College college) {
        s.nextLine();
        int choose;
        while (true){
            try{
                System.out.println("Which one do you want to compare?\n1)Doctors\n2)Professors");
                choose = s.nextInt();
                break;
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        }
        switch (choose){
            case 1 -> college.doctorsCompare();
            case 2 -> college.professorsCompare();
        }
    }



    private static void departmentComparison() {
    }


    private static void copyCommittee(College college) {
        s.nextLine();
        Boolean bool = false;
        System.out.println("Enter committee name you want to copy: ");
        System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee()));
        String committeeName = s.nextLine();
        try{
            college.copyCommittee(Util.getCommitteeFromName(committeeName, college.getCommittees()));
        } catch (CommitteeException e){
            System.out.println(e.getMessage());
            bool = true;
        }
        if (bool == false){
            System.out.println(eStatus.SUCCESS);
            System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee()));
        }
    }


    private static int showMenu(Scanner s) {
        System.out.println("\n====== Menu =======");
        for (int i = 0; i < MENU.length; i++) {
            System.out.println(i + ". " + MENU[i]);
        }
        System.out.println("Please enter your choose : ");
        return s.nextInt();
    }
}
