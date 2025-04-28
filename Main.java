package Idan_Lachovitz_Idan_Pekler_Part2;

import java.util.Scanner;
public class Main {
    // idan lachovitz, 322894148 + idan pekler, 207715848
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter college name: ");
        String name = s.nextLine();
        College c = new College(name);
        c.run();
    }

}
