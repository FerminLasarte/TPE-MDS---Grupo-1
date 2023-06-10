import java.util.*;
import java.util.Scanner;
import java.util.Date;
public class Sistema {
    private ArrayList<Viaje> viajes;
    private ArrayList<Usuario> usuarios;

    public Sistema() {
        this.viajes = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void registrarUsuario() {
        //la tarjeta la paso en null por motivos practicos y el usuario
        // no lo estoy guardando en ninguna lado
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese su apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese su DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine(); // Consumir el carácter de nueva línea

        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese su clave de acceso: ");
        String claveAcceso = scanner.nextLine();

        // Crear un objeto Usuario con los datos ingresados
        Usuario usuario = new Usuario(nombre, apellido, dni, email, claveAcceso, null);
        this.usuarios.add(usuario);
    }

    public Usuario logIn(String email, String clave) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getClaveAcceso().equals(clave)) {
                return u;
            }
        }
        return null;
    }

    public void buscarPasaje(Filtro filtro, Usuario user) {
        ArrayList<Viaje> aux = new ArrayList<>();
        for (Viaje v : viajes){
            if(v.cumpleViaje(filtro) != null)
                aux.add(v.cumpleViaje(filtro));
        }
        if (!aux.isEmpty()) {
            int i = 0;
            for (Viaje a : aux) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("Numero de viaje: " + i);
                a.imprimirViaje();
                i++;
            }
            System.out.println("-----------------------------------------------------------");
        } else {
            System.out.println("No hay viajes que coincidan con los criterios establecidos.");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Desea comprar un pasaje?: s/n");
        String c = scanner.nextLine();
        if (c.equals("s") || c.equals("S")) {
            System.out.print("Ingrese el numero de viaje que desea comprar: ");
            int pasaje = scanner.nextInt();
            scanner.nextLine();
            comprarPasaje(aux.get(pasaje), user);
        }
        else {
            System.out.println("Que tenga buen dia!");
            System.out.println();
            System.out.println("Ingrese 1 para volver a realizar una operacion. Ingrese 2 si desea salir del sistema.");
            menuPrincipal(user);
        }
    }


    public void comprarPasaje(Viaje v, Usuario u) {
        if (u.getTarjeta() == null) {
            System.out.println("No tiene ninguna tarjeta asociada. Ingrese los datos de su tarjeta.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nro Tarjeta: ");
            int nroTarjeta = scanner.nextInt();
            System.out.println("Banco Emisor: ");
            String bancoEmisor = scanner.nextLine();
            System.out.println("Marca: ");
            String marcaTarjeta = scanner.nextLine();
            System.out.println("Nombre Titular: ");
            String nombreTitular = scanner.nextLine();
            System.out.println("Codigo de Seguridad: ");
            int codigoSeguridad = scanner.nextInt();
            u.setTarjeta(new Tarjeta(nroTarjeta, bancoEmisor, marcaTarjeta, nombreTitular, codigoSeguridad));
            //u.getTarjeta().validar();
        }
        v.agregarPasajero(u);
        u.addViaje(v);
        //preguntar si quiere comprar mas pasajes
    }

    public void menuPrincipal(Usuario user) {
        Scanner scanner3 = new Scanner(System.in);
        System.out.println("1. Buscar / comprar pasaje");
        System.out.println("2. Salir");
        System.out.print("Ingrese opción que desea realizar: ");
        int opcion2 = scanner3.nextInt();
        switch (opcion2) {
            case 1 -> {
                Filtro filtro = menuFiltrado();
                buscarPasaje(filtro, user);
            }
            case 2 -> System.out.println("¡Hasta luego!");
        }
        while (opcion2 > 2){
            System.out.println("Opción inválida. Intente nuevamente.");
            menuPasajes();
        }
    }

    public void menuPasajes() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menú de opciones:");
        System.out.println("1. Registrarse.");
        System.out.println("2. Iniciar Sesion.");
        System.out.print("Ingrese el número de opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> registrarUsuario();
            case 2 -> {
                System.out.print("Ingrese su email: ");
                String email = scanner.nextLine();
                System.out.print("Ingrese su clave: ");
                String clave = scanner.nextLine();
                Usuario user = logIn(email, clave);
                if (user != null) {
                    menuPrincipal(user);
                }
                else {
                    System.out.println("El usuario no existe");
                }
            }
        }
    }

    public Filtro menuFiltrado() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Opciones por las cuales filtrar:"); //no imprementamos las combianciones de todos los filtros
        System.out.println("1. Por destino.");
        System.out.println("2. Por fecha de salidad.");
        System.out.println("3. Por fecha de llegada.");
        System.out.println("4. Por horario salida.");
        System.out.println("5. Por horario llegada.");
        System.out.println("6. Por costo.");
        System.out.print("Ingrese la opcion que desea realizar: ");
        int opcion = scanner.nextInt();
        //scanner.nextLine();
        switch (opcion) {
            case 1 -> {
                System.out.print("1. Ingrese el destino: ");
                String destino = scanner.nextLine();
                scanner.nextLine();
                System.out.println(destino);

                return new FiltroDestino(destino);
            }
            case 2 -> {
                System.out.println("2. Ingrese fecha salida:");

                // DATOS INGRESADOS PARA LA FECHA MINIMA DE SALIDA
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
                System.out.println("3. Ingrese fecha de llegada:");

                // DATOS INGRESADOS PARA LA FECHA MINIMA DE LLEGADA
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

                // DATOS INGRESADOS PARA LA FECHA MAXIMA DE LLEGADA
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
                System.out.println("4. Ingrese horario salida:");
                System.out.print("Horario minimo para salir: ");
                int horarioMinimoSalida = scanner.nextInt();
                while (horarioMinimoSalida < 0 || horarioMinimoSalida > 23) {
                    System.out.println("El horario ingresado no es admitido. Por favor, ingrese nuevamente.");
                    System.out.print("Horario minimo para salir: ");
                    horarioMinimoSalida = scanner.nextInt();
                }
                System.out.print("Horario maximo para salir: ");
                int horarioMaximoSalida = scanner.nextInt();
                while (horarioMaximoSalida < horarioMinimoSalida || horarioMaximoSalida > 23) {
                    System.out.println("El horario ingresado no es admitido. Por favor, ingrese nuevamente.");
                    System.out.print("Horario maximo para salir: ");
                    horarioMaximoSalida = scanner.nextInt();
                }
                return new FiltroHorarioSalida(horarioMinimoSalida, horarioMaximoSalida);
            }
            case 5 -> {
                System.out.println("5. Ingrese horario llegada:");
                System.out.print("Horario minimo para llegar: ");
                int horarioMinimoLlegada = scanner.nextInt();
                while (horarioMinimoLlegada < 0 || horarioMinimoLlegada > 23) {
                    System.out.println("El horario ingresado no es admitido. Por favor, ingrese nuevamente.");
                    System.out.print("Horario minimo para llegar: ");
                    horarioMinimoLlegada = scanner.nextInt();
                }
                System.out.print("Horario maximo para llegar: ");
                int horarioMaximoLlegada = scanner.nextInt();
                while (horarioMaximoLlegada < horarioMinimoLlegada || horarioMaximoLlegada > 23) {
                    System.out.println("El horario ingresado no es admitido. Por favor, ingrese nuevamente.");
                    System.out.print("Horario minimo para salir: ");
                    horarioMaximoLlegada = scanner.nextInt();
                }
                return new FiltroHorarioLlegada(horarioMinimoLlegada, horarioMaximoLlegada);
            }
            case 6 -> {
                System.out.println("6. Ingrese un rango de costos:");
                System.out.print("Precio minimo a pagar: ");
                int costoMenor = scanner.nextInt();
                System.out.print("Precio maximo a pagar: ");
                int costoMayor = scanner.nextInt();
                while (costoMayor < costoMenor) {
                    System.out.println("El precio maximo debe ser mayor al precio minimo. Ingrese nuevamente.");
                    System.out.print("Precio minimo a pagar ");
                    costoMenor = scanner.nextInt();
                    System.out.print("Precio maximo a pagar: ");
                    costoMayor = scanner.nextInt();
                }
                return new FiltroCosto(costoMenor, costoMayor);
            }
        }
        while(opcion > 6){
            System.out.println("Opción inválida. Intente nuevamente.");
            menuFiltrado();
        }
        return null;
    }

    public void addUsuario(Usuario user){
        this.usuarios.add(user);
    }
    public void addViaje(Viaje viaje){
        this.viajes.add(viaje);
    }
}