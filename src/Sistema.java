import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private ArrayList<Viaje> viajes;
    private ArrayList<Usuario> usuarios;

    public void registrarUsuario(Usuario user){
        usuarios.add(user);
    }

    public Usuario logIn (String email,String clave){
        for (Usuario u: usuarios) {
            if (u.getEmail().equals(email) || u.getClaveAcceso().equals(clave)) {
                return u;
            }
        }
        //registrarUsuario();
        return null;
    }
    public void mostrarViajes(Filtro f,Usuario u){
        ArrayList<Viaje> aux = new ArrayList<>();
        for (Viaje v : viajes){
            aux.add(v.cumpleViaje(f));
        }
        if (aux.isEmpty()){
            System.out.println("No hay viajes que coincidan con los criterios establecidos.");
        }else {
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
        if (c.equals("s") || c.equals("S")){
            System.out.println("Ingrese el numero de viaje que desea comprar");
            int pasaje = scanner.nextInt();
            comprarPasaje(aux.get(pasaje),u);
        }

    }
    public void comprarPasaje(Viaje v,Usuario u) {
        if (u.getTarjeta() == null){
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
            u.setTarjeta(new Tarjeta(nroTarjeta,bancoEmisor,marcaTarjeta,nombreTitular,codigoSeguridad));
            //u.getTarjeta().validar();
        }
        v.agregarPasajero(u);
        u.addViaje(v);
        //preguntar si quiere comprar mas pasajes
    }

    public Sistema() {
        this.viajes = new ArrayList<Viaje>();
        this.usuarios = new ArrayList<Usuario>();
    }
}
