/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

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
        try {
            FileInputStream fin = new FileInputStream("pets.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            petArray = (ArrayList < petRecord > ) ois.readObject();
        } catch (Exception ex) {
            //ex.printStackTrace();
        }


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
                    petArray = UpdatePetRecord(petArray);
                    break;
                case 4:
                    petArray = RemovePetRecord(petArray);
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

        try {
            FileOutputStream fout = new FileOutputStream("Pets.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(petArray);
            oos.close();
            fout.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Goodbye!");

    }

    public static class petRecord implements Serializable {
        String petName;
        int petAge;
    }

    public static int Menu() {
        System.out.println(" ");
        System.out.println("What would you like to do?");
        System.out.println(" 1) View all pets");
        System.out.println(" 2) Add more pets");
        System.out.println(" 3) Update an existing pet");
        System.out.println(" 4) Remove an existing pet");
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
                boolean hasException = false;
                int petAge = 0;
                int spaceAt = myInput.indexOf(" ");

                if (spaceAt == -1) {
                    System.out.println("Error: " + myInput + " is not a valid input.");
                    hasException = true;
                }

                if (!hasException) {

                    String petName = myInput.substring(0, spaceAt);
                    try {
                        petAge = Integer.parseInt(myInput.substring(spaceAt + 1));
                    } catch (Exception ex) {
                        System.out.println("Error: " + myInput + " is not a valid input.");
                        hasException = true;
                    }

                    if (!hasException) {
                        int arraySize = petArray.size();

                        if (arraySize == 5) {
                            System.out.println("Error: Database is full.");
                            return petArray;
                        } else if ((petAge < 1) || (petAge > 20)) {
                            System.out.println("Error: " + String.valueOf(petAge) + " is not a valid age.");
                            return petArray;
                        } else {
                            petRecord myPet = new petRecord();
                            myPet.petName = petName;
                            myPet.petAge = petAge;

                            petArray.add(myPet);

                            petsAdded = petsAdded + 1;
                        }
                    }
                }
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
        String myInput = in .nextLine();
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
        int myInput = in .nextInt();
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

    public static ArrayList < petRecord > UpdatePetRecord(ArrayList < petRecord > petArray) {
        System.out.println(" ");
        ViewPetRecords(petArray);

        System.out.println("Enter the pet ID you want to update: ");
        Scanner in = new Scanner(System.in);
        int myInputID = in .nextInt();
        System.out.println("Enter new name and new age: ");
        Scanner in2 = new Scanner(System.in);
        String myInputRecord = in2.nextLine();

        petRecord myPet = petArray.get(myInputID);
        String orgName = myPet.petName;
        int orgAge = myPet.petAge;

        int spaceAt = myInputRecord.indexOf(" ");
        String petName = myInputRecord.substring(0, spaceAt);
        int petAge = Integer.parseInt(myInputRecord.substring(spaceAt + 1));

        myPet.petName = petName;
        myPet.petAge = petAge;

        petArray.set(myInputID, myPet);

        System.out.println(orgName + " " + String.valueOf(orgAge) + " changed to " + petName + " " + String.valueOf(petAge));

        return petArray;
    }

    public static ArrayList < petRecord > RemovePetRecord(ArrayList < petRecord > petArray) {
        System.out.println(" ");
        ViewPetRecords(petArray);

        System.out.println("Enter the pet ID to remove: ");
        Scanner in = new Scanner(System.in);
        int myInputID = in .nextInt();

        int arraySize = petArray.size();

        if ((myInputID < arraySize - 1) || (myInputID > arraySize - 1)) {
            System.out.println("Error: ID " + String.valueOf(myInputID) + " does not exist.");
            return petArray;
        }

        petRecord myPet = petArray.get(myInputID);
        String orgName = myPet.petName;
        int orgAge = myPet.petAge;

        petArray.remove(myInputID);

        System.out.println(orgName + " " + String.valueOf(orgAge) + " is removed.");

        return petArray;
    }
}