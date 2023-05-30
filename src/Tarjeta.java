public class Tarjeta {
    private int nroTarjeta;
    private String bancoEmisor;
    private String marcaTarjeta;
    private String nombreTitular;
    private int codigoSeguridad;

    public Tarjeta(int nroTarjeta, String bancoEmisor, String marcaTarjeta, String nombreTitular, int codigoSeguridad) {
        this.nroTarjeta = nroTarjeta;
        this.bancoEmisor = bancoEmisor;
        this.marcaTarjeta = marcaTarjeta;
        this.nombreTitular = nombreTitular;
        this.codigoSeguridad = codigoSeguridad;
    }

    public int getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(int nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getBancoEmisor() {
        return bancoEmisor;
    }

    public void setBancoEmisor(String bancoEmisor) {
        this.bancoEmisor = bancoEmisor;
    }

    public String getMarcaTarjeta() {
        return marcaTarjeta;
    }

    public void setMarcaTarjeta(String marcaTarjeta) {
        this.marcaTarjeta = marcaTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }
}
