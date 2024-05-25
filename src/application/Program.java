package application;

import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            int number = sc.nextInt();
            System.out.print("Check-in date (dd/MM/yyyy): ");
            Date checkIn = sdf.parse(sc.next());
            System.out.print("Check-Out date (dd/MM/yyyy): ");
            Date checkOut = sdf.parse(sc.next());

            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println(reservation);

            System.out.println();
            System.out.println("Enter Data to update the reservation:");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());
            System.out.print("Check-Out date (dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());

            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
        } catch (ParseException e) {
            logsException(e);
            System.out.println("Invalid Date Format");
        } catch (IllegalArgumentException e) {
            logsException(e);
            System.out.println("Error in Reservation: " + e.getMessage());
        } catch (Exception e) {
            logsException(e);
            System.out.println("Unexpected Error");
        }
        sc.close();
    }
    public static void logsException(Exception e){
        try (FileWriter fw = new FileWriter("C:\\Users\\firey\\Documents\\logsExecp.txt", true); // Escreve em um Arquivo
            PrintWriter pw = new PrintWriter(fw)){ // Permite a escrita no arquivo FW
                StringWriter sw = new StringWriter(); // Captura a saída de texto em uma String
                e.printStackTrace(new PrintWriter(sw)); // Escreve a Pilha
                pw.println(sw.toString()); // Escreve a String Capturada para o Print Writer
        } catch (IOException e1){
            System.err.println("Error " + e.getMessage());
        }
    }
}
