import java.util.ArrayList;

public class Sistema {
    private ArrayList<Empresa> empresas;
    private ArrayList<Usuario> usuarios;


    public void registrarUsuario(Usuario user){
        usuarios.add(user);
    }




    public Sistema() {
        this.empresas = new ArrayList<Empresa>();
        this.usuarios = new ArrayList<Usuario>();
    }
}
