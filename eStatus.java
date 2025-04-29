package Idan_Lachovitz_Idan_Pekler_Part2;

public enum eStatus {
    SUCCESS("Added"),
    LECTURER_EXISTS("Lecturer Exists"),
    LECTURER_DONT_EXIST("Lecturer Dont Exist"),
    LECTURER_NOT_DOC_OR_PRO("Lecturer is not valid because he is not Doc or Prof"),
    LECTURER_EXISTS_IN_COMMITTEE("The Lecturer is exist in the committee"),
    LECTURER_NOT_EXISTS_IN_COMMITTEE("The Lecturer is not exist in the committee"),
    LECTURER_IS_THE_CHAIRMAN("lecturer is the chairman"),
    LECTURER_EXISTS_IN_DEPARTMENT("The Lecturer is exist in the department"),
    LECTURER_NOT_EXISTS_IN_DEPARTMENT("There are no lecturers in the department"),
    DEPARTMENT_EXIST("Department Exist"),
    DEPARTMENT_DONT_EXIST("Department Dont Exist"),
    COMMITTEE_EXIST("Committee Exist"),
    COMMITTEE_DONT_EXIST("Committee Dont Exist"),
    GENERAL_ERROR("Something Went Wrong..."),
    AVERAGE_SALARY("The average is: ");

    private final String description;

    eStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
