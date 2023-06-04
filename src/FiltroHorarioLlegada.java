public class FiltroHorarioLlegada implements Filtro{
    private int horaMinima;
    private int horaMaxima;

    public FiltroHorarioLlegada(int horaMinima, int horaMaxima) {
        this.horaMinima = horaMinima;
        this.horaMaxima = horaMaxima;
    }

    @Override
    public boolean cumple(Viaje v) {return (v.getHorarioDeLlegada()>= horaMinima && v.getHorarioDeLlegada()<= horaMaxima);}
}
