public class FiltroDestino implements Filtro {
    private String destino;

    public FiltroDestino(String destino) {
        this.destino = destino;
    }
    @Override
    public boolean cumple(Viaje v) {
        return v.getDestino().equals(this.destino);
    }
}