import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Sistema sistema = new Sistema();

    System.out.println("Ingrese su correo electronico: ");
    String email = scanner.nextLine();
    while (!sistema.emailValido(email)){
        System.out.println("El email ya esta registrado");
        System.out.println("Ingrese su correo electronico nuevamente: ");
        String email = scanner.nextLine();
    }


    System.out.println("Ingrese nombre de usuario: ");
    String name = scanner.nextLine();
    System.out.println("Ingrese un password: ");
    String pass = scanner.nextLine();
    System.out.println("Ingrese su numero de DNI: ");
    String dni = scanner.nextLine();


}