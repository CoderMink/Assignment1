/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author ninte
 */
public class petRecords {



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList < petRecord > petArray = new ArrayList < petRecord > ();
        int menuResponse = 0;

        System.out.println("Pet Database Program.");

        while (menuResponse != 7) {
            menuResponse = Menu();

            switch (menuResponse) {
                case 1:
                    ViewPetRecords(petArray);
                    break;
                case 2:
                    petArray = AddPetRecord(petArray);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    SearchPetRecordsByName(petArray);
                    break;
                case 6:
                    SearchPetRecordsByAge(petArray);
                    break;
                default:
                    break;
            }
        }


    }

    public static class petRecord {
        String petName;
        int petAge;
    }

    public static int Menu() {
        System.out.println(" ");
        System.out.println("What would you like to do?");
        System.out.println(" 1) View all pets");
        System.out.println(" 2) Add more pets");
        System.out.println(" 3) Update an existing pet");
        System.out.println(" 4) Remove and existing pet");
        System.out.println(" 5) Search pets by name");
        System.out.println(" 6) Search pets by age");
        System.out.println(" 7) Exit program");
        System.out.println("Your Choice: ");
        Scanner in = new Scanner(System.in);
        int returnValue = in .nextInt();
        return returnValue;
    }

    public static ArrayList < petRecord > AddPetRecord(ArrayList < petRecord > petArray) {
        int petsAdded = 0;
        boolean isDone = false;
        System.out.println(" ");
        while (isDone == false) {
            System.out.println("add pet (name, age): ");
            Scanner in = new Scanner(System.in);
            String myInput = in .nextLine();
            if (Objects.equals(myInput, "done")) {
                isDone = true;
                System.out.println(String.valueOf(petsAdded) + " pets added.");
                return petArray;
            } else {
                int spaceAt = myInput.indexOf(" ");
                String petName = myInput.substring(0, spaceAt);
                int petAge = Integer.parseInt(myInput.substring(spaceAt + 1));

                petRecord myPet = new petRecord();
                myPet.petName = petName;
                myPet.petAge = petAge;

                petArray.add(myPet);

                petsAdded = petsAdded + 1;
            }
        }
        return petArray;
    }

    public static void ViewPetRecords(ArrayList < petRecord > petArray) {
        int arrayLength = petArray.size();
        System.out.printf("+----------------------+%n");
        System.out.printf("| ID | NAME      | AGE |%n");
        System.out.printf("+----------------------+%n");
        for (int x = 0; x < arrayLength; x++) {
            petRecord myPet = new petRecord();
            myPet = petArray.get(x);

            System.out.printf("|  " + String.valueOf(x) + " | " + myPet.petName + String.format("%1$" + (10 - myPet.petName.length()) + "s", "") + "| " + String.valueOf(myPet.petAge) + String.format("%1$" + (4 - String.valueOf(myPet.petAge).length()) + "s", "") + "|%n");
        }
        System.out.printf("+----------------------+%n");
        System.out.printf(String.valueOf(arrayLength) + " rows in set.%n");
    }
    
    public static void SearchPetRecordsByName(ArrayList < petRecord > petArray) {
        int arrayLength = petArray.size();
        int petsFound = 0;
        
        System.out.println(" ");
        System.out.println("Enter a name to search: ");
        Scanner in = new Scanner(System.in);
        String myInput = in.nextLine();
        System.out.println(" ");
        
        System.out.printf("+----------------------+%n");
        System.out.printf("| ID | NAME      | AGE |%n");
        System.out.printf("+----------------------+%n");
        
        for (int x = 0; x < arrayLength; x++) {
            petRecord myPet = new petRecord();
            myPet = petArray.get(x);
            
            if (Objects.equals(myPet.petName, myInput)) {
                System.out.printf("|  " + String.valueOf(x) + " | " + myPet.petName + String.format("%1$" + (10 - myPet.petName.length()) + "s", "") + "| " + String.valueOf(myPet.petAge) + String.format("%1$" + (4 - String.valueOf(myPet.petAge).length()) + "s", "") + "|%n");
                petsFound = petsFound + 1;
            }

        }
        System.out.printf("+----------------------+%n");
        System.out.printf(String.valueOf(petsFound) + " rows in set.%n");
        
    }
    
    public static void SearchPetRecordsByAge(ArrayList < petRecord > petArray) {
        int arrayLength = petArray.size();
        int petsFound = 0;
        
        System.out.println(" ");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter age to search: ");
        int myInput = in.nextInt();
        System.out.println(" ");
        
        System.out.printf("+----------------------+%n");
        System.out.printf("| ID | NAME      | AGE |%n");
        System.out.printf("+----------------------+%n");
        
        for (int x = 0; x < arrayLength; x++) {
            petRecord myPet = new petRecord();
            myPet = petArray.get(x);
            
            if (Objects.equals(myPet.petAge, myInput)) {
                System.out.printf("|  " + String.valueOf(x) + " | " + myPet.petName + String.format("%1$" + (10 - myPet.petName.length()) + "s", "") + "| " + String.valueOf(myPet.petAge) + String.format("%1$" + (4 - String.valueOf(myPet.petAge).length()) + "s", "") + "|%n");
                petsFound = petsFound + 1;
            }

        }
        System.out.printf("+----------------------+%n");
        System.out.printf(String.valueOf(petsFound) + " rows in set.%n");
    }

}