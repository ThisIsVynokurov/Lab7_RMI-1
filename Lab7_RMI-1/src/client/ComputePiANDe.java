package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.math.BigDecimal;
import java.util.Scanner;

import compute.Compute;
import compute.Task;

public class ComputePiANDe {
    public static void main (String[] args){
        try{
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введіть бажану точність обчислення pi: ");
            int piDigits = validateInput (scanner);
            Task<BigDecimal> PiTask = new pi(piDigits);

            System.out.print ("Введіть бажану точність обчислення e: ");
            int eDigits = validateInput (scanner);
            Task<BigDecimal> eTask = new e(eDigits);

            Registry registry = LocateRegistry.getRegistry("localhost", 8080);
            String name = "Compute";
            Compute comp = (Compute) registry.lookup(name);

            BigDecimal pi = comp.executeTask(PiTask);
            BigDecimal e = comp.executeTask(eTask);

            System.out.println ("Значення pi: " + pi);
            System.out.println ("Значення e: " + e);

        } catch (Exception e){
            System.err.println("ComputePi exception: ");
            e.printStackTrace();
        }
    }

    private static int validateInput(Scanner scanner){
        int numOfDigits = 0;
        boolean validInput = false;
        while (!validInput){
            try{
                numOfDigits = scanner.nextInt();
                if (numOfDigits <= 0){
                    System.out.println("Введіть, будь ласка, натуральне число!");
                } else{
                    validInput = true;
                }
            } catch (Exception e){
                System.err.println ("Введіть, будь ласка, натуральне число!");
            }
        }
        return numOfDigits;
    }
}
