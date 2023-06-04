public class FiltroNot implements Filtro{
    private Filtro f1;

    public FiltroNot(Filtro f1) {
        this.f1 = f1;
    }

    @Override
    public boolean cumple(Viaje v) {
        return !f1.cumple(v);
    }


}
