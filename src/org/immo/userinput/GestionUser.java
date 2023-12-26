package org.immo.userinput;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class GestionUser {

    Pattern p = Pattern.compile("[0-9]+[0-9]+[0-9]+[0-9]");
    Matcher m;

    public GestionUser() {
    }

    public String promptString(String prompt) {
        System.out.print(prompt+" : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public String promptYear() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        boolean validInput = false;
        do {
            String input = scanner.nextLine();
            m = p.matcher(input);
            if (input.length() == 4 && m.matches() && (Integer.parseInt(input)>=2010)) {
                userInput = input;
                validInput = true;
            } else {
                System.out.println("Année incorrecte");
            }
        } while (!validInput);

        return userInput;
    }

    public String promptYesNo(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        boolean validInput = false;
        do {
            System.out.print(prompt + " (Y/N): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.length() == 1 && (input.equals("Y") || input.equals("N"))) {
                userInput = input;
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        } while (!validInput);

        return userInput;
    }

}