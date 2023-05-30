import java.util.*;
public class Empresa extends Elemento {
    private ArrayList<Viaje> viajes;

    public Empresa(String nombreEmpresa) {
        super(nombreEmpresa);
        this.viajes = new ArrayList<>();
    }

    public void agregarViaje(Viaje viaje) {
        viajes.add(viaje.getCopiaViaje());
    }

    @Override
    public ArrayList<Viaje> buscar(Filtro f) {
        ArrayList<Viaje> viajeAux = new ArrayList<>();
        for (Viaje v : viajes) {
            if (f.cumple(v)) {
                viajeAux.addAll(v.buscar(f));
            }
        }
        return viajeAux;
    }

    @Override
    public String getNombreEmpresa() {
        return
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}
