import java.util.Date;

public class Main {
    public static void main(String[] args) {
       Sistema s = new Sistema();
       Usuario f = new Usuario("fermin","laraste",123, "fer", "1234", null);
       Usuario c = new Usuario("franco","carafo",234 , "fran", "2345", null);
       Usuario t = new Usuario("tadeo", "villa",345 , "tade", "3456", null);
       Usuario m = new Usuario("matias", "rodriguez", 456, "mat", "4567", null);
       Usuario h = new Usuario("hector", "halty",567 , "hec", "5678", null);
       Usuario si = new Usuario("simon", "diez", 678, "sim", "6789", null);
       s.addUsuario(si);s.addUsuario(h);s.addUsuario(m);s.addUsuario(t);s.addUsuario(c);s.addUsuario(f);
       Viaje v1 = new Viaje(new Date(2022, 3, 1), new Date(2022, 3, 2), "Empresa 2", 40, "Tandil", "Azul", 150, 9, 13);
       Viaje v2 = new Viaje(new Date(2022, 4, 10), new Date(2022, 4, 12), "Empresa 1", 60, "Mar del Plata", "Buenos Aires", 200, 10, 14);
       Viaje v3 = new Viaje(new Date(2022, 6, 20), new Date(2022, 6, 25), "Empresa 3", 30, "Bariloche", "Cordoba", 300, 11, 15);
       Viaje v4 = new Viaje(new Date(2022, 8, 5), new Date(2022, 8, 7), "Empresa 2", 50, "Rosario", "Santa Fe", 180, 12, 16);
       Viaje v5 = new Viaje(new Date(2022, 9, 15), new Date(2022, 9, 16), "Empresa 1", 45, "Mendoza", "San Juan", 250, 13, 17);
       Viaje v6 = new Viaje(new Date(2022, 11, 25), new Date(2022, 11, 27), "Empresa 3", 35, "Salta", "Jujuy", 220, 14, 18);
       Viaje v7 = new Viaje(new Date(2023, 1, 3), new Date(2023, 1, 5), "Empresa 2", 55, "Neuquen", "Rio Negro", 190, 15, 19);
       Viaje v8 = new Viaje(new Date(2023, 2, 12), new Date(2023, 2, 14), "Empresa 1", 70, "Cordoba", "Buenos Aires", 230, 16, 20);
       s.addViaje(v1);s.addViaje(v2);s.addViaje(v3);s.addViaje(v4);s.addViaje(v5);s.addViaje(v6);s.addViaje(v7);s.addViaje(v8);
       s.menuInicioSistema();
    }
}