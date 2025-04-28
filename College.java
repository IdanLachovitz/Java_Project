package Idan_Lachovitz_Idan_Pekler_Part2;

import java.util.Arrays;
import java.util.Scanner;

public class College {

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
            "show Average Salary FromSpecific Department",
            "show Lecturers Info",
            "show Committee Info"

    };
    private Scanner s = new Scanner(System.in);
    private String collegeName;
    private Lecturer[] lecturer;
    private int numOfLecturer;
    private Committees[] committees;
    private int numOfCommittee;
    private Department[] departments;
    private int numOfDepartment;

    public College(String collegeName) {
        setCollegeName(collegeName);
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void run() {
        lecturer = new Lecturer[0];
        committees = new Committees[0];
        departments = new Department[0];
        int userChosen;
        do {
            userChosen = showMenu(s);
            switch (userChosen) {
                case 0 -> System.out.println("Done... Bye");
                case 1 -> addLecturer();
                case 2 -> addCommittee();
                case 3 -> addLecturerToCommittee();
                case 4 -> updateChairman();
                case 5 -> removeFromCommittee();
                case 6 -> addDepartment();
                case 7 -> addLecturerToDepartment();
                case 8 -> showAverageProfessorsSalary();
                case 9 -> showAverageSalaryFromSpecificDepartment();
                case 10 -> showInfo(lecturer, numOfLecturer);
                case 11 -> showInfo(committees, numOfCommittee);

                default -> System.out.println("Unexpected value");
            }
        } while (userChosen != 0);
    }

    private void addCommittee() {
        s.nextLine();
        System.out.println("Enter committee name: ");
        String committeeName = s.nextLine();
        while (true) {
            if (Util.isExist(committeeName, committees, numOfCommittee)) {
                System.out.println(committeeName + " already exist...");
                System.out.println("Enter committee name: ");
                committeeName = s.nextLine();
            } else {
                System.out.println("Enter chairhead name: ");
                String chairman = s.nextLine();
                if (Util.isDocOrPro(Util.getLecturerFromName(chairman, lecturer))) {
                    if (numOfCommittee == committees.length) {
                        committees = Arrays.copyOf(committees, numOfCommittee == 0 ? 2 : numOfCommittee * 2);
                    }
                    committees[numOfCommittee++] = new Committees(committeeName, Util.getLecturerFromName(chairman, lecturer));
                    System.out.println(Util.printNames(committees, numOfCommittee, Util.getCommitteeFromName(committeeName,committees).getLecturers(), Util.getCommitteeFromName(committeeName,committees).getNumofLecturer()));
                    break;
                }
            }
        }
    }

    private void addLecturer() {
        s.nextLine();
        System.out.println("Enter lecturer name: ");
        String name = s.nextLine();
        while (true) {
            if (Util.isExist(name,lecturer, numOfLecturer)) {
                System.out.println(name + " already exist...");
                System.out.println("Enter Lecturer name: ");
                name = s.nextLine();
            } else {
                if (numOfLecturer == lecturer.length) {
                    lecturer = Arrays.copyOf(lecturer, numOfLecturer == 0 ? 2 : numOfLecturer * 2);
                }
                System.out.println("Enter lecturer id: ");
                String id = s.nextLine();
                System.out.println("Enter your degree: \n1- BSc\n2- MSc\n3- doctor\n4- professor");
                int degree = s.nextInt();
                s.nextLine();
                System.out.println("Enter name of the degree: ");
                String nameOfDegree = s.nextLine();
                System.out.println("Enter your salary: ");
                int salary = s.nextInt();
                s.nextLine();
                while (salary < 0){
                    System.out.println("Salary cant be negative");
                    System.out.println("Enter your salary: ");
                    salary = s.nextInt();
                    s.nextLine();
                }
                System.out.println("Enter the department from the list below (can be empty): ");
                System.out.println(Util.printArrayNames(departments));
                String department = s.nextLine();
                while (true) {
                    if (department.isEmpty()) {
                        break;
                    } else if (!Util.isExist(department, departments, numOfDepartment)) {
                        System.out.println("Department is not exist, enter an existed one or empty");
                        System.out.println("Enter the department from the list below (can be empty): ");
                        System.out.println(Util.printArrayNames(departments));
                        department = s.nextLine();
                    }
                    break;
                }
                lecturer[numOfLecturer++] = new Lecturer(name, id, degree, salary, nameOfDegree, department);
                break;
            }
        }
    }

    private void addLecturerToCommittee() {
        s.nextLine();
        System.out.println("Enter committee name: ");
        String committeeName = s.nextLine();
        while (true) {
            if (Util.isExist(committeeName, committees, numOfCommittee)) {
                System.out.println("Enter lecturer name: ");
                String name = s.nextLine();
                if (Util.isExist(name,lecturer,numOfLecturer)){
                    if (Util.isExist(name, Util.getCommitteeFromName(committeeName, committees).getLecturers(), Util.getCommitteeFromName(committeeName, committees).getNumofLecturer())){
                        System.out.println("The lecturer is exist in the committee...");
                    }else {
                        Committees c = Util.getCommitteeFromName(committeeName, committees);
                        c.addLecturerToCommittee(Util.getLecturerFromName(name, lecturer));
                        break;
                    }
                }
            }
            System.out.println(committeeName + " is not exist...");
            System.out.println("Enter committee name: ");
            committeeName = s.nextLine();
        }
        System.out.println(Util.printNames(committees, numOfCommittee, Util.getCommitteeFromName(committeeName,committees).getLecturers(), Util.getCommitteeFromName(committeeName,committees).getNumofLecturer()));
    }

    private void updateChairman() {
        s.nextLine();
        System.out.println("What Committee: ");
        String committeeName = s.nextLine();
        while (true) {
            if (!Util.isExist(committeeName, committees, numOfCommittee)) {
                System.out.println("committee doesnt exist, try again: ");
                committeeName = s.nextLine();
            } else {
                break;
            }
        }
        System.out.println("enter new head of committee: ");
        String newChairMan = s.nextLine();
        while (true) {
            if (!Util.isExist(newChairMan, lecturer, numOfLecturer)) {
                System.out.println("lecturer doesn't exist, enter again: ");
                newChairMan = s.nextLine();
            } else {
                if (Util.isDocOrPro(Util.getLecturerFromName(newChairMan, lecturer))) {
                    Util.getCommitteeFromName(committeeName, committees).setChairman(Util.getLecturerFromName(newChairMan, lecturer));
                }else {
                    System.out.println("not valid head, head didnt changed..");
                }
                break;
            }
        }
        System.out.println(Arrays.toString(committees));
    }

    private void removeFromCommittee() {
        s.nextLine();
        System.out.println("Enter lecturer name: ");
        String name = s.nextLine();
        while (true) {
            if (Util.isExist(name, lecturer, numOfLecturer)) {
//                Committees.removeLecturerByName(name);
                System.out.println("Enter committee name: ");
                String committeeName = s.nextLine();
                Committees c = Util.getCommitteeFromName(committeeName, committees);
                c.removeLecturerByName(name);

                break;
            }
            System.out.println(name + " is not exist...");
            System.out.println("Enter Lecturer name: ");
            name = s.nextLine();
        }
        System.out.println(Arrays.toString(committees));
    }


    private void addDepartment() {
        s.nextLine();
        System.out.println("Enter department name: ");
        String depName = s.nextLine();
        while (true) {
            if (Util.isExist(depName, departments, numOfDepartment)) {
                System.out.println(depName + " already exist...");
                System.out.println("Enter another department name: ");
                depName = s.nextLine();
            } else {
                if (numOfDepartment == departments.length) {
                    departments = Arrays.copyOf(departments, numOfDepartment == 0 ? 2 : numOfDepartment * 2);
                }
                departments[numOfDepartment++] = new Department(depName);
                break;
            }
        }
    }

    private void addLecturerToDepartment() {
        s.nextLine();
        System.out.println("Enter lecturer name: ");
        System.out.println(Arrays.toString(lecturer));
        String lectName = s.nextLine();
        if (Util.isExist(lectName, lecturer, numOfLecturer)){
            Lecturer l1 = Util.getLecturerFromName(lectName, lecturer);
            System.out.println("Enter department name: ");
            System.out.println(Arrays.toString(departments));
            String deptName = s.nextLine();
            if (Util.isExist(deptName, departments, numOfDepartment)){
                Department.setArrLecturer(l1);
            }
        }
    }

    private void showAverageProfessorsSalary() {
        float sum = 0;
        if (numOfLecturer != 0){
            for (int i = 0; i < numOfLecturer; i++) {
                sum += lecturer[i].getSalary();
            }
            System.out.println("The average salary of all the lecturers is: " + sum/numOfLecturer);
        } else {
            System.out.println("There is no salarys updated...");
        }
    }

    private void showAverageSalaryFromSpecificDepartment() {
        float sum = 0;
        s.nextLine();
        System.out.println("Enter department name: ");
        System.out.println(Arrays.toString(departments));
        String deptName = s.nextLine();
        if (Util.isExist(deptName, departments, numOfDepartment)) {
            Lecturer[] l1 = Util.getDepartmentFromName(deptName,departments).getArrLecturer();
            if (Util.getDepartmentFromName(deptName,departments) != null){
                for (int i = 0; i < Util.getDepartmentFromName(deptName,departments).getNumOfLecturer(); i++) {
                    sum += l1[i].getSalary();
                }
                System.out.println(sum/Util.getDepartmentFromName(deptName,departments).getNumOfLecturer());
            }
        }
    }

    private void showInfo(Object[] obj, int numOfObj) {
        for (int i = 0; i < numOfObj; i++) {
            System.out.println(obj[i]);
        }
    }


    private static String str(Object[] arr, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]);
            if (i < size-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
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
