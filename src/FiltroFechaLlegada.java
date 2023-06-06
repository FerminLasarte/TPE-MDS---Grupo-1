import java.util.Date;

public class FiltroFechaLlegada implements Filtro{
    private Date fecha1;
    private Date fecha2;

    public FiltroFechaLlegada(Date fecha1, Date fecha2) {
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
    }

    @Override
    public boolean cumple(Viaje v) {
        return (v.getFechaLlegada().before(fecha1) && v.getFechaLlegada().after(fecha2));
    }
}
