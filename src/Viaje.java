import java.util.*;
public class Viaje {
    private Date fechaSalida;
    private Date fechaLlegada;
    private int horarioDeSalida;
    private int horarioDeLlegada;
    private ArrayList<Usuario> usuarios;
    private int cupo;
    private String destino;
    private String origen;
    private String nombreEmpresa;
    private int precio;

    public Viaje(Date fechaSalida, Date fechaLlegada, String nombreEmpresa, int cupo, String destino, String origen, int precio, int horarioDeSalida, int horarioDeLlegada) {
        this.nombreEmpresa = nombreEmpresa;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.usuarios = new ArrayList<>();
        this.cupo = cupo;
        this.destino = destino;
        this.origen = origen;
        this.precio = precio;
        this.horarioDeLlegada = horarioDeLlegada;
        this.horarioDeSalida = horarioDeSalida;
    }

    public int cantidadAsientosLibres() {
        return cupo - usuarios.size();
    }

    public void agregarPasajero(Usuario usuario) {
        if (cantidadAsientosLibres() < cupo) {
            usuarios.add(usuario.getCopia());
        }
        // comunicar error
    }

    public Viaje getCopia() {
        Viaje viajeAux = new Viaje(fechaSalida, fechaLlegada,nombreEmpresa, cupo, destino, origen, precio, horarioDeSalida, horarioDeLlegada);
        for (Usuario u : usuarios) {
            viajeAux.agregarPasajero(u.getCopia());
        }
        return viajeAux;
    }

    public Viaje cumpleViaje(Filtro f) {
        if (f.cumple(this))
            return  this.getCopia();
        return null;
    }

    public void imprimirViaje(){
        System.out.println("Origen: " + this.origen);
        System.out.println("Destino: " + this.destino);
        System.out.println("Empresa: " + this.nombreEmpresa);
        System.out.println("Salida: " + this.fechaSalida + " a las " + this.horarioDeSalida + "hs.");
        System.out.println("Llegada: " + this.fechaLlegada + " a las " + this.horarioDeLlegada + "hs.");
        System.out.println("Asientos disponibles: " + this.cantidadAsientosLibres());
        System.out.println("Precio: " + this.precio);
    }

    public int getHorarioDeSalida() {
        return horarioDeSalida;
    }

    public void setHorarioDeSalida(int horarioDeSalida) {
        this.horarioDeSalida = horarioDeSalida;
    }

    public int getHorarioDeLlegada() {
        return horarioDeLlegada;
    }

    public void setHorarioDeLlegada(int horarioDeLlegada) {
        this.horarioDeLlegada = horarioDeLlegada;
    }


    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}
