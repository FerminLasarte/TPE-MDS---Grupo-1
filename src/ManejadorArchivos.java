import java.io.*;
import java.text.*;
import java.util.*;

public class ManejadorArchivos {

    /*
    public static void crearArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
        } catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        }
    }

    public static void escribirArchivo(String nombreArchivo, String contenido){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo,true));
            salida.println(contenido);
            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }*/

    //LECTURA Y ESCRITURA DE VIAJES EN EL ARCHIVO Viajes.txt
    public static void agregarViajeAlArchivo(String nombreArchivo) {
        Scanner scanner = new Scanner(System.in);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Ingrese la fecha de salida (Formato: yyyy-MM-dd):");
        Date fechaSalida = obtenerFecha(scanner.nextLine(), dateFormat);
        System.out.println("Ingrese la fecha de llegada (Formato: yyyy-MM-dd):");
        Date fechaLlegada = obtenerFecha(scanner.nextLine(), dateFormat);
        System.out.println("Ingrese el horario de salida:");
        int horarioDeSalida = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el horario de llegada:");
        int horarioDeLlegada = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el cupo:");
        int cupo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el destino:");
        String destino = scanner.nextLine();
        System.out.println("Ingrese el origen:");
        String origen = scanner.nextLine();
        System.out.println("Ingrese el nombre de la empresa:");
        String nombreEmpresa = scanner.nextLine();
        System.out.println("Ingrese el precio:");
        int precio = scanner.nextInt();
        scanner.nextLine();

        Viaje viaje = new Viaje(fechaSalida,fechaLlegada,nombreEmpresa,cupo, destino, origen,precio,horarioDeSalida, horarioDeLlegada );

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            String linea = String.format("%s,%s,%d,%d,%d,%s,%s,%s,%d",
                    dateFormat.format(viaje.getFechaSalida()), dateFormat.format(viaje.getFechaLlegada()),
                    viaje.getHorarioDeSalida(), viaje.getHorarioDeLlegada(), viaje.getCupo(), viaje.getDestino(),
                    viaje.getOrigen(), viaje.getNombreEmpresa(), viaje.getPrecio());
            bw.write(linea);
            bw.newLine();
            System.out.println("Viaje agregado exitosamente al archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Date obtenerFecha(String fechaString, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(fechaString);
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Ingrese la fecha nuevamente.");
            Scanner scanner = new Scanner(System.in);
            return obtenerFecha(scanner.nextLine(), dateFormat);
        }
    }

    public static ArrayList<Viaje> leerArchivoViajes(String nombreArchivo) {
        ArrayList<Viaje> viajes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Viaje viaje = crearViajeDesdeLinea(linea);
                viajes.add(viaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return viajes;
    }

    private static Viaje crearViajeDesdeLinea(String linea) {
        String[] datos = linea.split(",");
        Date fechaSalida = crearFecha(datos[0], datos[1], datos[2]);
        Date fechaLlegada = crearFecha(datos[3], datos[4], datos[5]);
        int horarioDeSalida = Integer.parseInt(datos[6]);
        int horarioDeLlegada = Integer.parseInt(datos[7]);
        int cupo = Integer.parseInt(datos[8]);
        String destino = datos[9];
        String origen = datos[10];
        String nombreEmpresa = datos[11];
        int precio = Integer.parseInt(datos[12]);

        return new Viaje(fechaSalida,fechaLlegada,nombreEmpresa,cupo, destino, origen,precio,horarioDeSalida, horarioDeLlegada );
    }

    private static Date crearFecha(String anio, String mes, String dia) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = String.format("%s-%s-%s", anio, mes, dia);
        try {
            return format.parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //LECTURA Y ESCRITURA DE USUARIOS EN EL ARCHIVO Usuarios.txt
    public static ArrayList<Usuario> leerArchivoUsuarios(String nombreArchivo) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Usuario usuario = crearUsuarioDesdeLinea(linea);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private static Usuario crearUsuarioDesdeLinea(String linea) {
        String[] datos = linea.split(",");
        String nombre = datos[0];
        String apellido = datos[1];
        int DNI = Integer.parseInt(datos[2]);
        String email = datos[3];
        String claveAcceso = datos[3];

        return new Usuario(nombre,apellido,DNI,email,claveAcceso,null);
    }

    public static void agregarUsuarioAlArchivo(String nombreArchivo) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del usuario:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del usuario:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese el DNI del usuario:");
        int DNI = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el email del usuario:");
        String email = scanner.nextLine();

        System.out.println("Ingrese la clave de acceso del usuario:");
        String claveAcceso = scanner.nextLine();

        Usuario usuario = new Usuario(nombre,apellido,DNI,email,claveAcceso,null);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            String linea = String.format("%s,%s,%d,%s,%s",
                    usuario.getNombre(), usuario.getApellido(), usuario.getDNI(), usuario.getEmail(), usuario.getClaveAcceso());
            bw.write(linea);
            bw.newLine();
            System.out.println("Usuario agregado exitosamente al archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




