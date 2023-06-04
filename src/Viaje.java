import java.util.*;
public class Viaje extends Elemento {
    private Date fechaSalida;
    private Date fechaLlegada;
    private int horarioDeSalida;
    private int horarioDeLlegada;
    private ArrayList<Usuario> usuarios;
    private int cupo;
    private String destino;
    private String origen;
    private int precio;

    public Viaje(Date fechaSalida, Date fechaLlegada, String nombreEmpresa, int cupo, String destino, String origen, int precio, int horarioDeSalida, int horarioDeLlegada) {
        super(nombreEmpresa);
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
            usuarios.add(usuario.getCopiaUsuario());
        }
        // comunicar error
    }

    public Viaje getCopiaViaje() {
        Viaje viajeAux = new Viaje(fechaSalida, fechaLlegada,super.getNombreEmpresa(), cupo, destino, origen, precio, horarioDeSalida, horarioDeLlegada);
        for (Usuario u : usuarios) {
            viajeAux.agregarPasajero(u.getCopiaUsuario());
        }
        return viajeAux;
    }

    @Override
    public ArrayList<Viaje> buscar(Filtro f) {
        ArrayList<Viaje> viajeAux = new ArrayList<>();
            if (f.cumple(this)) {
                viajeAux.add(this);
            }
        return viajeAux;
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
}
