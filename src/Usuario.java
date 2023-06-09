import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String apellido;
    private int DNI;
    private String email;
    private String claveAcceso;
    private boolean tarjeta;
    private ArrayList<Viaje> viajesComprados;

    public Usuario(String nombre, String apellido, int DNI, String email, String claveAcceso, boolean tarjeta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.email = email;
        this.claveAcceso = claveAcceso;
        this.tarjeta = tarjeta;
        viajesComprados = new ArrayList<Viaje>();
    }

    public Usuario getCopia() {
        Usuario usuarioAux = new Usuario(this.nombre, this.apellido, this.DNI, this.email, this.claveAcceso,this.tarjeta);
        return usuarioAux;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean tieneTarjeta() {
        return tarjeta;
    }

    public void setTarjeta() {
        this.tarjeta = true;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public ArrayList<Viaje> getViajesComprados() {
        return viajesComprados;
    }

    public void addViaje(Viaje v) {
        viajesComprados.add(v);
    }

}

