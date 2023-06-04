public class FiltroHorarioSalida implements Filtro{
    private int horaMinima;
    private int horaMaxima;

    public FiltroHorarioSalida(int horaMinima, int horaMaxima) {
        this.horaMinima = horaMinima;
        this.horaMaxima = horaMaxima;
    }

    @Override
    public boolean cumple(Viaje v) {return (v.getHorarioDeSalida()>= horaMinima && v.getHorarioDeSalida()<= horaMaxima);}
}

