import java.io.*;
import java.text.*;
import java.util.*;

public class ManejadorArchivos {

    public static void escribirArchivo(String nombreArchivo, String contenido){
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo,true));
            salida.println(contenido);
            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
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
        int anioSalida = Integer.parseInt(datos[0]);
        int mesSalida = Integer.parseInt(datos[1]);
        int diaSalida = Integer.parseInt(datos[2]);
        Date fechaSalida = new Date(anioSalida,mesSalida,diaSalida);
        int anioLlegada = Integer.parseInt(datos[3]);
        int mesLlegada = Integer.parseInt(datos[4]);
        int diaLlegada = Integer.parseInt(datos[5]);
        Date fechaLlegada = new Date(anioLlegada,mesLlegada,diaLlegada);
        int horarioDeSalida = Integer.parseInt(datos[6]);
        int horarioDeLlegada = Integer.parseInt(datos[7]);
        int cupo = Integer.parseInt(datos[8]);
        String destino = datos[9];
        String origen = datos[10];
        String nombreEmpresa = datos[11];
        int precio = Integer.parseInt(datos[12]);

        return new Viaje(fechaSalida,fechaLlegada,nombreEmpresa,cupo, destino, origen,precio,horarioDeSalida,horarioDeLlegada);
    }

    //LECTURA DE USUARIOS EN EL ARCHIVO Usuarios.txt
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
        String claveAcceso = datos[4];
        if (datos[5].equalsIgnoreCase("no")) {
            return new Usuario(nombre, apellido, DNI, email, claveAcceso, false);
        } else{
            return new Usuario(nombre, apellido, DNI, email, claveAcceso, true);
        }
    }

}




