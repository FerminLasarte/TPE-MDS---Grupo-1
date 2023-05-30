public class FiltroOrigen implements Filtro {
    private String origen;

    public FiltroOrigen(String origen) {
        this.origen = origen;
    }
    @Override
    public boolean cumple(Viaje v) {
        return v.getOrigen().equals(this.origen);
    }
}
