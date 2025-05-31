package Idan_Lachovitz_Idan_Pekler_Part2;

public class DepartmentException extends CollegeExceptions {
  private static final String PRE_MESSAGE = "type: Department Exception- ";

  public DepartmentException(String message) {
    super(PRE_MESSAGE+message);
  }

}
