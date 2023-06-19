import java.util.Date;
import java.util.Scanner;

public class Menu {

    private static Sistema sistema;

    public Menu(Sistema sistema){
        this.sistema = sistema;
    }


    public static void menuInicioSistema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de Plataforma 9 3/4!");
        System.out.println("1. Registrarse.");
        System.out.println("2. Iniciar Sesion.");
        System.out.println("3. Salir.");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> sistema.registrarUsuario(scanner);
            case 2 -> sistema.iniciarSesion(scanner);
            case 3 -> System.out.println("Gracias por utilizar nuestros servicios.");
            default -> {
                System.out.println("Opción inválida. Inténtelo nuevamente.");
                menuInicioSistema();
                menuInicioSistema();
            }
        }
    }

    public static void menuPrincipal(Usuario user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("MENU PRINCIPAL");
        System.out.println("1. Buscar / comprar pasaje");
        System.out.println("2. Cerrar Sesión");
        System.out.println("3. Salir");
        System.out.print("Ingrese el nro de opción que desea realizar: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> {
                Filtro filtro = menuFiltrado();
                sistema.buscarPasaje(filtro,user);
            }
            case 2 -> menuInicioSistema();
            case 3 -> System.out.println("¡Hasta luego!");
            default -> {
                System.out.println("Opción inválida. Intente nuevamente.");
                menuPrincipal(user);
            }
        }
    }

    public static void menuAdministrador() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("MENU ADMINISTRADOR");
        System.out.println("1. Agregar Viaje");
        System.out.println("2. Cerrar Sesión");
        System.out.println("3. Salir");
        System.out.print("Ingrese el nro de opción que desea realizar: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> {
                sistema.adminAgregaViaje(scanner);
                menuAdministrador();
            }
            case 2 -> menuInicioSistema();
            case 3 -> System.out.println("¡Hasta luego!");
            default -> {
                System.out.println("Opción inválida. Intente nuevamente.");
                menuAdministrador();
            }
        }
    }

    public static Filtro menuFiltrado() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Buscar:");
        System.out.println("0. Todos los viajes disponibles.");
        System.out.println("1. Por destino.");
        System.out.println("2. Por fecha de salida.");
        System.out.println("3. Por fecha de llegada.");
        System.out.println("4. Por horario salida.");
        System.out.println("5. Por horario llegada.");
        System.out.println("6. Por costo.");
        System.out.print("Ingrese la opcion deseada: ");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 0 -> {
                return new FiltroTodos();
            }
            case 1 -> {
                Scanner stringScanner = new Scanner(System.in);
                System.out.print("Ingrese el destino: ");
                String destino = stringScanner.nextLine();

                return new FiltroDestino(destino);
            }
            case 2 -> {
                System.out.print("Ingrese el día minimo de salida: ");
                int diaMinimoSalida = scanner.nextInt();
                while (diaMinimoSalida < 1 || diaMinimoSalida > 31) {
                    System.out.println("El dia ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el día minimo de salida: ");
                    diaMinimoSalida = scanner.nextInt();
                }
                System.out.print("Ingrese el mes minimo de salida: ");
                int mesMinimoSalida = scanner.nextInt();
                while (mesMinimoSalida < 1 || mesMinimoSalida > 12) {
                    System.out.println("El mes ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el mes minimo de salida: ");
                    mesMinimoSalida = scanner.nextInt();
                }
                System.out.print("Ingrese el año minimo de salida: ");
                int anioMinimoSalida = scanner.nextInt();
                while (anioMinimoSalida < 2022 || anioMinimoSalida > 2024) {
                    System.out.println("El anio ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el anio minimo de salida: ");
                    anioMinimoSalida = scanner.nextInt();
                }
                Date fechaMinimaSalida = new Date(anioMinimoSalida, mesMinimoSalida, diaMinimoSalida);

                // DATOS INGRESADOS PARA LA FECHA MAXIMA DE SALIDA
                System.out.print("Ingrese el día maximo de salida: ");
                int diaMaximoSalida = scanner.nextInt();
                while (diaMaximoSalida < 1 || diaMaximoSalida > 31) {
                    System.out.println("El dia ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el día maximo de salida: ");
                    diaMaximoSalida = scanner.nextInt();
                }
                System.out.print("Ingrese el mes maximo de salida: ");
                int mesMaximoSalida = scanner.nextInt();
                while (mesMaximoSalida < 1 || mesMaximoSalida > 12) {
                    System.out.println("El mes ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el mes maximo de salida: ");
                    mesMaximoSalida = scanner.nextInt();
                }
                System.out.print("Ingrese el año maximo de salida: ");
                int anioMaximoSalida = scanner.nextInt();
                while (anioMaximoSalida < anioMinimoSalida || anioMaximoSalida > 2024) {
                    System.out.println("El anio ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el anio maximo de salida: ");
                    anioMaximoSalida = scanner.nextInt();
                }
                Date fechaMaximaSalida = new Date(anioMaximoSalida, mesMaximoSalida, diaMaximoSalida);

                return new FiltroFechaSalida(fechaMinimaSalida, fechaMaximaSalida);
            }
            case 3 -> {
                System.out.print("Ingrese el día minimo de llegada: ");
                int diaMinimoLlegada = scanner.nextInt();
                while (diaMinimoLlegada < 1 || diaMinimoLlegada > 31) {
                    System.out.println("El dia ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el día minimo de llegada: ");
                    diaMinimoLlegada = scanner.nextInt();
                }
                System.out.print("Ingrese el mes minimo de llegada: ");
                int mesMinimoLlegada = scanner.nextInt();
                while (mesMinimoLlegada < 1 || mesMinimoLlegada > 12) {
                    System.out.println("El mes ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el mes minimo de llegada: ");
                    mesMinimoLlegada = scanner.nextInt();
                }
                System.out.print("Ingrese el año minimo de llegada: ");
                int anioMinimoLlegada = scanner.nextInt();
                while (anioMinimoLlegada < 2022 || anioMinimoLlegada > 2024) {
                    System.out.println("El anio ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el anio minimo de llegada: ");
                    anioMinimoLlegada = scanner.nextInt();
                }
                Date fechaMinimaLlegada = new Date(anioMinimoLlegada, mesMinimoLlegada, diaMinimoLlegada);

                System.out.print("Ingrese el día maximo de llegada: ");
                int diaMaximoLlegada = scanner.nextInt();
                while (diaMaximoLlegada < 1 || diaMaximoLlegada > 31) {
                    System.out.println("El dia ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el día maximo de llegada: ");
                    diaMaximoLlegada = scanner.nextInt();
                }
                System.out.print("Ingrese el mes maximo de llegada: ");
                int mesMaximoLlegada = scanner.nextInt();
                while (mesMaximoLlegada < 1 || mesMaximoLlegada > 12) {
                    System.out.println("El mes ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el mes maximo de llegada: ");
                    mesMaximoLlegada = scanner.nextInt();
                }
                System.out.print("Ingrese el año maximo de llegada: ");
                int anioMaximoLlegada = scanner.nextInt();
                while (anioMaximoLlegada < anioMinimoLlegada || anioMaximoLlegada > 2024) {
                    System.out.println("El anio ingresado no es valido. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el anio maximo de llegada: ");
                    anioMaximoLlegada = scanner.nextInt();
                }
                Date fechaMaximaLlegada = new Date(anioMaximoLlegada, mesMaximoLlegada, diaMaximoLlegada);

                return new FiltroFechaLlegada(fechaMinimaLlegada, fechaMaximaLlegada);
            }
            case 4 -> {
                System.out.print("Horario minimo de salida: ");
                int horarioMinimoSalida = scanner.nextInt();
                while (horarioMinimoSalida < 0 || horarioMinimoSalida > 23) {
                    System.out.println("El horario ingresado no es admitido. Por favor, ingrese nuevamente.");
                    System.out.print("Horario minimo de salida: ");
                    horarioMinimoSalida = scanner.nextInt();
                }
                // DATOS INGRESADOS PARA HORARIO MAXIMO DE SALIDA
                System.out.print("Horario maximo de salida: ");
                int horarioMaximoSalida = scanner.nextInt();
                while (horarioMaximoSalida < horarioMinimoSalida || horarioMaximoSalida > 23) {
                    System.out.println("El horario ingresado no es admitido. Por favor, ingrese nuevamente.");
                    System.out.print("Horario maximo de salida: ");
                    horarioMaximoSalida = scanner.nextInt();
                }
                return new FiltroHorarioSalida(horarioMinimoSalida, horarioMaximoSalida);
            }
            case 5 -> {
                // DATOS INGRESADOS PARA HORARIO MINIMO DE LLEGADA
                System.out.print("Horario minimo de llegada: ");
                int horarioMinimoLlegada = scanner.nextInt();
                while (horarioMinimoLlegada < 0 || horarioMinimoLlegada > 23) {
                    System.out.println("El horario ingresado no es admitido. Por favor, ingrese nuevamente.");
                    System.out.print("Horario minimo de llegada: ");
                    horarioMinimoLlegada = scanner.nextInt();
                }
                // DATOS INGRESADOS PARA HORARIO MAXIMO DE LLEGADA
                System.out.print("Horario maximo de llegada: ");
                int horarioMaximoLlegada = scanner.nextInt();
                while (horarioMaximoLlegada < horarioMinimoLlegada || horarioMaximoLlegada > 23) {
                    System.out.println("El horario ingresado no es admitido. Por favor, ingrese nuevamente.");
                    System.out.print("Horario minimo de llegada: ");
                    horarioMaximoLlegada = scanner.nextInt();
                }
                return new FiltroHorarioLlegada(horarioMinimoLlegada, horarioMaximoLlegada);
            }
            case 6 -> {
                System.out.print("Precio minimo: ");
                int costoMenor = scanner.nextInt();
                System.out.print("Precio maximo: ");
                int costoMayor = scanner.nextInt();
                while (costoMayor < costoMenor) {
                    System.out.println("El precio maximo debe ser mayor al precio minimo. Ingrese nuevamente.");
                    System.out.print("Precio minimo: ");
                    costoMenor = scanner.nextInt();
                    System.out.print("Precio maximo: ");
                    costoMayor = scanner.nextInt();
                }
                return new FiltroCosto(costoMenor, costoMayor);
            }
            default -> {
                System.out.println("Opción inválida. Intente nuevamente.");
                menuFiltrado();
            }
        }
        return null;
    }

}
