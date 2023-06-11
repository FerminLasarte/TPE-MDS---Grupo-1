import java.util.Date;

public class FiltroFechaSalida implements Filtro {
    private Date fecha1;
    private Date fecha2;

    public FiltroFechaSalida(Date fecha1, Date fecha2) {
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
    }

    @Override
    public boolean cumple(Viaje v) {
        return (v.getFechaSalida().after(fecha1) && v.getFechaSalida().before(fecha2));
    }
}
