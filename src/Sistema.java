import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
public class Sistema {
    private ArrayList<Viaje> viajes;
    private ArrayList<Usuario> usuarios;

    public Sistema(ArrayList<Viaje> viajes, ArrayList<Usuario> usuarios) {
        this.viajes = new ArrayList<Viaje>();
        this.usuarios = new ArrayList<Usuario>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public void addUsuario(Usuario u){
        this.usuarios.add(u);
    }
    public void addViaje(Viaje v){
        this.viajes.add(v);
    }
    public void registrarUsuario() {             //la tarjeta la paso en null por motivos practicos y el usuario
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
            if (u.getEmail().equals(email) || u.getClaveAcceso().equals(clave)) {
                return u;
            }
        }
        return null;
    }

    public void buscarPasaje(Filtro f, Usuario u) {
        ArrayList<Viaje> aux = new ArrayList<Viaje>();
        for (Viaje v : viajes) {
            aux.add(v.cumpleViaje(f));
        }
        if (aux.isEmpty()) {
            System.out.println("No hay viajes que coincidan con los criterios establecidos.");
        } else {
            int i = 0;
            for (Viaje a : aux) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("Numero de viaje: " + i);
                a.imprimirViaje();
                System.out.println("-----------------------------------------------------------");
                i++;
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Desea comprar un pasaje?: s/n");
        String c = scanner.nextLine();
        if (c.equals("s") || c.equals("S")) {
            System.out.println("Ingrese el numero de viaje que desea comprar");
            int pasaje = scanner.nextInt();
            comprarPasaje(aux.get(pasaje), u);
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

    public Filtro crearFiltro() {
        Scanner scanner = new Scanner(System.in);
        Filtro p=null;
        int opcion;
        do {
            System.out.println("Opciones por las cuales filtrar:"); //no imprementamos las combianciones de todos los filtros
            System.out.println("1. Por destino :");
            System.out.println("2. Por fecha de salidad :");
            System.out.println("3. Por fecha de llegada :");
            System.out.println("4. Por horario llegada :");
            System.out.println("5. Por horario salida :");
            System.out.println("6. Por costo :");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("1. ingrese destino:");
                    String destino = scanner.nextLine();
                    System.out.println("llego");
                    FiltroDestino f = new FiltroDestino(destino);
                    //p=f;

                    return f;

                case 2:System.out.println("2. Ingrese fecha salida:");
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
                    FiltroFechaLlegada f1 = new FiltroFechaLlegada(fecha2, fecha3);
                    p=f1;
                    return p;
                case 3:
                    System.out.println("3. Ingrese fecha de llegada:");
                    System.out.print("Ingrese el día: ");
                    dia = scanner.nextInt();
                    System.out.print("Ingrese el mes: ");
                    mes = scanner.nextInt();
                    System.out.print("Ingrese el año: ");
                    anio = scanner.nextInt();
                    Date fecha = new Date(dia, mes, anio);
                    ;
                    System.out.print("Ingrese el día: ");
                    dia = scanner.nextInt();
                    System.out.print("Ingrese el mes: ");
                    mes = scanner.nextInt();
                    System.out.print("Ingrese el año: ");
                    anio = scanner.nextInt();
                    Date fecha1 = new Date(dia, mes, anio);
                    FiltroFechaSalida f2 = new FiltroFechaSalida(fecha, fecha1);
                    p=f2;
                    return p;
                case 4:
                    System.out.println("4. Ingrese horario llegada:");
                    System.out.print("Entre las: ");
                    int hora = scanner.nextInt();
                    System.out.print("Y las: ");
                    int hora1 = scanner.nextInt();
                    FiltroHorarioLlegada s = new FiltroHorarioLlegada(hora, hora1);
                    p=s;
                    return p;
                case 5:
                    System.out.println("5. Ingrese horario salida:");
                    System.out.print("Entre las: ");
                    int hora2 = scanner.nextInt();
                    System.out.print("Y las: ");
                    int hora3 = scanner.nextInt();
                    FiltroHorarioSalida s1 = new FiltroHorarioSalida(hora2, hora3);
                    p=s1;
                    return p;
                case 6:
                    System.out.println("6. Ingrese costo maximo:");
                    System.out.print("Entre los: ");
                    int c = scanner.nextInt();
                    System.out.print("Y los: ");
                    int c1 = scanner.nextInt();
                    FiltroCosto c3 = new FiltroCosto(c, c1);
                    p=c3;
                    return p;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
        while (opcion != 7);
        scanner.close();
        return p;
    }

    public void MenuPasajes() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de opciones:");
            System.out.println("1. Registrarse");
            System.out.println("2. login");
            System.out.print("Ingrese el número de opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    System.out.print("Ingrese su email: ");
                    String email = scanner.nextLine();
                    scanner.nextLine();
                    System.out.print("Ingrese su clave: ");
                    String clave = scanner.nextLine();

                    Usuario u = logIn(email, clave);
                    if (!u.equals(null)) {
                        Scanner scanner2 = new Scanner(System.in);
                        int opcion2;
                        do {
                            System.out.println("1. Comprar pasaje");
                            System.out.println("2. Salir");
                            System.out.print("Ingrese el número de opción: ");
                            opcion2 = scanner2.nextInt();
                            switch (opcion2) {
                                case 1:
                                    Filtro f = crearFiltro();
                                    buscarPasaje(f, u);
                                    break;
                                case 2:
                                    System.out.println("¡Hasta luego!");
                                    break;
                                default:
                                    System.out.println("Opción inválida. Intente nuevamente.");
                                    break;
                            }
                        }
                        while (opcion2 != 3);
                        scanner2.close();

                    }
                }

        }
        while (opcion != 3);
        scanner.close();
    }
}
