public class Verificador {

    public static boolean contieneMinuscula(String clave) {
        for (int i = 0; i < clave.length(); i++) {
            if (Character.isLowerCase(clave.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean contieneMayuscula(String clave) {
        for (int i = 0; i < clave.length(); i++) {
            if (Character.isUpperCase(clave.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean contieneNumero(String clave) {
        for (int i = 0; i < clave.length(); i++) {
            if (Character.isDigit(clave.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
