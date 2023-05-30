import java.util.ArrayList;

public abstract class Elemento {

    private String nombreEmpresa;

    public Elemento(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public abstract ArrayList<Viaje> buscar(Filtro f);

    public abstract String getNombreEmpresa();

}
