import java.util.*;
import java.util.Scanner;
import java.util.Date;
public class Sistema {
    private ArrayList<Viaje> viajes;
    private ArrayList<Usuario> usuarios;
    private Menu menu;

    public Sistema() {
        this.viajes = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.menu = new Menu(this);
    }

    public void iniciar() {
        menu.menuInicioSistema();
    }

    public void adminAgregaViaje(Scanner scanner){
        System.out.print("Ingrese el origen: ");
        String origen = scanner.nextLine();
        System.out.print("Ingrese el destino: ");
        String destino = scanner.nextLine();
        System.out.print("Ingrese el nombre de la empresa: ");
        String empresa = scanner.nextLine();
        System.out.print("Ingrese el cupo: ");
        int cupo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el dia de salida: ");
        int diaSalida = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el mes de salida: ");
        int mesSalida = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el anio de salida: ");
        int anioSalida = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la hora de salida: ");
        int horaSalida = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el dia de llegada: ");
        int diaLlegada = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el mes de llegada: ");
        int mesLlegada = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el anio de llegada: ");
        int anioLlegada = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la hora de llegada: ");
        int horaLlegada = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el costo del viaje: ");
        int precio = scanner.nextInt();
        Viaje nuevo = new Viaje(new Date(anioSalida,mesSalida,diaSalida),new Date(anioLlegada,mesLlegada,mesSalida), empresa,cupo,destino,origen,precio,horaSalida,horaLlegada);
        this.viajes.add(nuevo);
        System.out.println("El viaje ha sido agregado correctamente. Los datos del mismo son:");
        nuevo.imprimirViaje();
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
        menu.menuInicioSistema();
    }

    public void iniciarSesion(Scanner scanner) {
        String salir = "n";
        System.out.print("Ingrese su Email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su clave de acceso: ");
        String clave = scanner.nextLine();

        if (email.equalsIgnoreCase("admin") && clave.equalsIgnoreCase("admin")) {
            Menu.menuAdministrador();
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
                    menu.menuInicioSistema();
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
                buscarPasaje(Menu.menuFiltrado(),user);
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
                Menu.menuPrincipal(user);
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
        Menu.menuPrincipal(usuario);
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

            if (clave.length() >= 8 && Verificador.contieneMinuscula(clave) && Verificador.contieneMayuscula(clave) && Verificador.contieneNumero(clave)) {
                claveValida = true;
            } else {
                System.out.println("La clave de acceso no cumple con los requisitos. Inténtelo nuevamente.");
            }
        }
        return clave;
    }

    public boolean validacionUsuario(String email, String clave) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getClaveAcceso().equals(clave)) {
                System.out.println("Inicio de sesion exitoso. Bienvenido, " + usuario.getNombre() + "!");
                Menu.menuPrincipal(usuario);
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