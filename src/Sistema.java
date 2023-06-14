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

    public void menuInicioSistema() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de Plataforma 9 3/4!");
        System.out.println("1. Registrarse.");
        System.out.println("2. Iniciar Sesion.");
        System.out.println("3. Salir.");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> registrarUsuario(scanner);
            case 2 -> iniciarSesion(scanner);
            case 3 -> System.out.println("Gracias por utilizar nuestros servicios.");
            default -> {
                System.out.println("Opción inválida. Inténtelo nuevamente.");
                menuInicioSistema();
            }
        }
    }

    public void menuPrincipal(Usuario user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("MENU PRINCIPAL");
        System.out.println("1. Buscar / comprar pasaje");
        System.out.println("2. Salir");
        System.out.print("Ingrese el nro de opción que desea realizar: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> {
                Filtro filtro = menuFiltrado();
                buscarPasaje(filtro, user);
            }
            case 2 -> System.out.println("¡Hasta luego!");
            default -> {
                System.out.println("Opción inválida. Intente nuevamente.");
                menuPrincipal(user);
            }
        }
    }

    public void menuAdministrador() {
        //OPCION DE AGREGAR VIAJE
    }

    public void registrarUsuario(Scanner scanner) {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese su DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine();
        String email = ingresarEmail(scanner);
        String claveAcceso = ingresarClaveAcceso(scanner);
        Usuario nuevoUsuario = new Usuario(nombre, apellido, dni, email, claveAcceso, null);
        usuarios.add(nuevoUsuario);
        System.out.println("¡Registro exitoso! ¿Desea asociar una tarjeta de credito a su cuenta? s/n");
        String opcion = scanner.nextLine();
        if (opcion.equalsIgnoreCase("s")) {
            System.out.print("Ingrese el numero de la tarjeta: ");
            int nroTarjeta = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el banco emisor: ");
            String bancoEmisor = scanner.nextLine();
            System.out.print("Ingrese la marca: ");
            String marcaTarjeta = scanner.nextLine();
            System.out.print("Ingrese el nombre del titular: ");
            String nombreTitular = scanner.nextLine();
            System.out.print("Ingrese el codigo de seguridad: ");
            int codigoSeguridad = scanner.nextInt();
            scanner.nextLine();
            nuevoUsuario.setTarjeta(new Tarjeta(nroTarjeta,bancoEmisor,marcaTarjeta,nombreTitular,codigoSeguridad));
            System.out.println("Tarjeta asociada con exito.");
        }
        menuInicioSistema();
    }

    public void iniciarSesion(Scanner scanner) {
        String salir = "n";
        System.out.print("Ingrese su Email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su clave de acceso: ");
        String clave = scanner.nextLine();

        if (email.equalsIgnoreCase("admin") && clave.equalsIgnoreCase("admin")) {
            menuAdministrador();
        }
        else {
            while ((!validacionUsuario(email, clave)) && (salir.equalsIgnoreCase("n")) ) {
                System.out.println("Nombre de usuario o contraseña incorrectos.");
                System.out.println("¿Desea terminar el proceso? s(salir)/n(volver a cargar sus datos).");
                salir = scanner.nextLine();
                if (salir.equalsIgnoreCase("n")) {
                    System.out.print("Ingrese su Email: ");
                    email = scanner.nextLine();
                    System.out.print("Ingrese su clave de acceso: ");
                    clave = scanner.nextLine();
                }
                else {
                    System.out.println("Muchas gracias.");
                    System.out.println();
                    menuInicioSistema();
                }
            }
        }
    }

    public void buscarPasaje(Filtro filtro, Usuario user) {
        ArrayList<Viaje> aux = new ArrayList<>();
        boolean hayViajes = false;
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        for (Viaje v : viajes){
            if(v.cumpleViaje(filtro) != null)
                aux.add(v.cumpleViaje(filtro));
        }
        if (!aux.isEmpty()) {
            hayViajes = true;
            for (Viaje a : aux) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("Numero de viaje: " + i);
                a.imprimirViaje();
                i++;
            }
            System.out.println("-----------------------------------------------------------");
        } else {
            hayViajes = false;
            System.out.println("No hay viajes que coincidan con los criterios establecidos.");
            System.out.println("¿Desea buscar por otro criterio? s/n");
            String opcionOtroCriterio = scanner.nextLine();
            if (opcionOtroCriterio.equalsIgnoreCase("s")){
                buscarPasaje(menuFiltrado(),user);
            }else{
                System.out.println("¡Hasta luego!");
            }
        }
        if (hayViajes){
            System.out.println("¿Desea comprar un pasaje?: s/n");
            String opcionComprar = scanner.nextLine();
            while (!opcionComprar.equalsIgnoreCase("s") && !opcionComprar.equalsIgnoreCase("n")) {
                System.out.println("La opcion ingresada es incorrecta. Por favor, ingrese nuevamente.");
                System.out.println("¿Desea comprar un pasaje?: s/n");
                opcionComprar = scanner.nextLine();
            }
            if (opcionComprar.equalsIgnoreCase("s")) {
                System.out.print("Ingrese el numero de viaje que desea comprar: ");
                int pasaje = scanner.nextInt();
                scanner.nextLine();
                while (pasaje < 0 || pasaje > i) {
                    System.out.println("El pasaje ingresado no se encuentra disponible. Por favor, ingrese nuevamente.");
                    System.out.print("Ingrese el numero de viaje que desea comprar: ");
                    pasaje = scanner.nextInt();
                    scanner.nextLine();
                }
                System.out.println("Cantidad de asientos disponibles: " + aux.get(pasaje).cantidadAsientosLibres());
                System.out.print("Ingrese la cantidad de pasajes que desea comprar: ");
                int cantidadPasajes = scanner.nextInt();
                scanner.nextLine();
                while (cantidadPasajes < 0 || cantidadPasajes > aux.get(pasaje).cantidadAsientosLibres()) {
                    System.out.println("No se encuentran disponibles esa cantidad de pasajes.");
                    System.out.print("Por favor, ingrese nuevamente la cantidad de pasajes que desea comprar: ");
                    cantidadPasajes = scanner.nextInt();
                    scanner.nextLine();
                }
                comprarPasaje(aux.get(pasaje), user, cantidadPasajes);
            }
            else {
                System.out.println("Que tenga buen dia!");
                System.out.println();
                menuPrincipal(user);
            }
        }
    }

    public void comprarPasaje(Viaje viaje, Usuario usuario, int cantidadPasajes) {
        Scanner intScanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);
        System.out.println("Ingrese el metodo de pago:");
        System.out.println("1. Efectivo.");
        System.out.println("2. Tarjeta de credito.");
        int opcion = intScanner.nextInt();
        intScanner.nextLine();
        switch (opcion){
            case 1 -> {
                System.out.println("A continuacion recibira los codigos de su/s pasaje/s.");
                System.out.println("Acuda a su terminal mas cercana con el/los codigo/s para realizar el pago en efectivo.");
                Random random = new Random();
                int numeroPasaje = random.nextInt(99999 - 10000 + 1) + 10000;
                for (int i = 1; i <= cantidadPasajes; i++){
                    System.out.print("Pasaje " + i + "| Codigo: ");
                    System.out.println(numeroPasaje+i);
                }
            }
            case 2 -> {
                if (usuario.getTarjeta() == null) {
                    System.out.println("No tiene ninguna tarjeta asociada. Ingrese los datos de su tarjeta.");
                    System.out.print("Numero de Tarjeta: ");
                    int nroTarjeta = intScanner.nextInt();
                    intScanner.nextLine();
                    System.out.print("Banco Emisor: ");
                    String bancoEmisor = stringScanner.nextLine();
                    System.out.print("Marca: ");
                    String marcaTarjeta = stringScanner.nextLine();
                    System.out.print("Nombre Titular: ");
                    String nombreTitular = stringScanner.nextLine();
                    System.out.print("Codigo de Seguridad: ");
                    int codigoSeguridad = intScanner.nextInt();
                    intScanner.nextLine();
                    usuario.setTarjeta(new Tarjeta(nroTarjeta, bancoEmisor, marcaTarjeta, nombreTitular, codigoSeguridad));
                }
                System.out.println("Su compra ha sido completada con exito! Recibira la informacion de el/los pasaje/s por correo electronico.");
            }
            default -> {
                System.out.println("Opción inválida. Inténtelo nuevamente.");
                comprarPasaje(viaje,usuario,cantidadPasajes);
            }
        }
        for (int i = 1; i <= cantidadPasajes; i++) {
            viaje.agregarPasajero(usuario);
            usuario.addViaje(viaje);
        }
        System.out.println("Muchas gracias por confiar en nosotros.");
        menuPrincipal(usuario);
    }

    public Filtro menuFiltrado() {
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

    public String ingresarEmail(Scanner scanner) {
        boolean registrado = false;
        String email = null;

        while (!registrado) {
            System.out.print("Ingrese su email: ");
            email = scanner.nextLine();

            if (existeUsuarioRegistrado(email)) {
                System.out.println("Ya existe un usuario con este Email. Intentelo nuevamente.");
            } else {
                registrado = true;
            }
        }
        return email;
    }

    public boolean existeUsuarioRegistrado(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public String ingresarClaveAcceso(Scanner scanner) {
        boolean claveValida = false;
        String clave = null;
        
        while (!claveValida) {
            System.out.print("Ingrese una clave de acceso (mínimo 8 caracteres, incluyendo al menos una minúscula, una mayúscula y un número): ");
            clave = scanner.nextLine();

            if (clave.length() >= 8 && contieneMinuscula(clave) && contieneMayuscula(clave) && contieneNumero(clave)) {
                claveValida = true;
            } else {
                System.out.println("La clave de acceso no cumple con los requisitos. Inténtelo nuevamente.");
            }
        }

        return clave;
    }

    public boolean contieneMinuscula(String clave) {
        for (int i = 0; i < clave.length(); i++) {
            if (Character.isLowerCase(clave.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean contieneMayuscula(String clave) {
        for (int i = 0; i < clave.length(); i++) {
            if (Character.isUpperCase(clave.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean contieneNumero(String clave) {
        for (int i = 0; i < clave.length(); i++) {
            if (Character.isDigit(clave.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean validacionUsuario(String email, String clave) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getClaveAcceso().equals(clave)) {
                System.out.println("Inicio de sesion exitoso. Bienvenido, " + usuario.getNombre() + "!");
                menuPrincipal(usuario);
                return true;
            }
        }
        return false;
    }

    public void addUsuario(Usuario user){
        this.usuarios.add(user);
    }
    public void addViaje(Viaje viaje){
        this.viajes.add(viaje);
    }
}