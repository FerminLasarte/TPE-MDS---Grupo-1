public class FiltroCosto implements Filtro{
    private int precioMinimo;
    private int precioMaximo;

    public FiltroCosto(int precioMinimo, int precioMaximo) {
        this.precioMinimo = precioMinimo;
        this.precioMaximo = precioMaximo;
    }

    @Override
    public boolean cumple(Viaje v) {return (v.getPrecio()>= precioMinimo && v.getPrecio()<= precioMaximo);}
}


