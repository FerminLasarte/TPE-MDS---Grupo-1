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
                System.out.println("-----------------------------------------------------------");
                i++;
            }
        } else {
            System.out.println("No hay viajes que coincidan con los criterios establecidos.");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Desea comprar un pasaje?: s/n");
        String c = scanner.nextLine();
        if (c.equals("s") || c.equals("S")) {
            System.out.println("Ingrese el numero de viaje que desea comprar");
            int pasaje = scanner.nextInt();
            scanner.nextLine();
            comprarPasaje(aux.get(pasaje), user);
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

    public void menuPasajes() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menú de opciones:");
        System.out.println("1. Registrarse");
        System.out.println("2. login");
        System.out.println("Ingrese el número de opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> registrarUsuario();
            case 2 -> {
                System.out.println("Ingrese su email: ");
                String email = scanner.nextLine();

                System.out.print("Ingrese su clave: ");
                String clave = scanner.nextLine();
                Usuario user = logIn(email, clave);
                if (user != null) {
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.println("1. Comprar pasaje");
                    System.out.println("2. Salir");
                    System.out.print("Ingrese el número de opción: ");
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
                else
                { System.out.println("El usuario no existe");}

            }
        }
    }

    public Filtro menuFiltrado() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Opciones por las cuales filtrar:"); //no imprementamos las combianciones de todos los filtros
        System.out.println("1. Por destino :");
        System.out.println("2. Por fecha de salidad :");
        System.out.println("3. Por fecha de llegada :");
        System.out.println("4. Por horario llegada :");
        System.out.println("5. Por horario salida :");
        System.out.println("6. Por costo :");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1 -> {
                System.out.println("1. ingrese destino:");
                String destino = scanner.nextLine();
                scanner.nextLine();
                System.out.println(destino);

                return new FiltroDestino(destino);
            }
            case 2 -> {
                System.out.println("2. Ingrese fecha salida:");
                System.out.print("Ingrese el día: ");
                int dia = scanner.nextInt();
                System.out.print("Ingrese el mes: ");
                int mes = scanner.nextInt();
                System.out.print("Ingrese el año: ");
                int anio = scanner.nextInt();
                Date fecha2 = new Date(anio, mes, dia);
                System.out.print("Ingrese el día: ");
                dia = scanner.nextInt();
                System.out.print("Ingrese el mes: ");
                mes = scanner.nextInt();
                System.out.print("Ingrese el año: ");
                anio = scanner.nextInt();
                Date fecha3 = new Date(anio, mes, dia);
                return new FiltroFechaLlegada(fecha2, fecha3);
            }
            case 3 -> {
                System.out.println("3. Ingrese fecha de llegada:");
                System.out.print("Ingrese el día: ");
                int dia = scanner.nextInt();
                System.out.print("Ingrese el mes: ");
                int mes = scanner.nextInt();
                System.out.print("Ingrese el año: ");
                int anio = scanner.nextInt();
                Date fecha = new Date(dia, mes, anio);
                System.out.print("Ingrese el día: ");
                dia = scanner.nextInt();
                System.out.print("Ingrese el mes: ");
                mes = scanner.nextInt();
                System.out.print("Ingrese el año: ");
                anio = scanner.nextInt();
                Date fecha1 = new Date(dia, mes, anio);
                return new FiltroFechaSalida(fecha, fecha1);
            }
            case 4 -> {
                System.out.println("4. Ingrese horario llegada:");
                System.out.print("Entre las: ");
                int hora = scanner.nextInt();
                System.out.print("Y las: ");
                int hora1 = scanner.nextInt();
                return new FiltroHorarioLlegada(hora, hora1);
            }
            case 5 -> {
                System.out.println("5. Ingrese horario salida:");
                System.out.print("Entre las: ");
                int hora2 = scanner.nextInt();
                System.out.print("Y las: ");
                int hora3 = scanner.nextInt();
                return new FiltroHorarioSalida(hora2, hora3);
            }
            case 6 -> {
                System.out.println("6. Ingrese costo maximo:");
                System.out.print("Entre los: ");
                int c = scanner.nextInt();
                System.out.print("Y los: ");
                int c1 = scanner.nextInt();
                return new FiltroCosto(c, c1);
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
