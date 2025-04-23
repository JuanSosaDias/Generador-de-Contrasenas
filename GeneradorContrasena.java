import java.security.SecureRandom;

public class GeneradorContrasena {

    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "!@#$%^&*()-_=+<>?/";

    private static final String TODOS = MAYUSCULAS + MINUSCULAS + NUMEROS + SIMBOLOS;

    private static final SecureRandom random = new SecureRandom();

    public static String generarContrasena(int longitud) {
        if (longitud < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }

        StringBuilder contrasena = new StringBuilder();

        // Aseguramos al menos un carácter de cada tipo
        contrasena.append(MAYUSCULAS.charAt(random.nextInt(MAYUSCULAS.length())));
        contrasena.append(MINUSCULAS.charAt(random.nextInt(MINUSCULAS.length())));
        contrasena.append(NUMEROS.charAt(random.nextInt(NUMEROS.length())));
        contrasena.append(SIMBOLOS.charAt(random.nextInt(SIMBOLOS.length())));

        // El resto de los caracteres
        for (int i = 4; i < longitud; i++) {
            contrasena.append(TODOS.charAt(random.nextInt(TODOS.length())));
        }

        // Mezclar los caracteres
        return mezclar(contrasena.toString());
    }

    private static String mezclar(String entrada) {
        char[] a = entrada.toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return new String(a);
    }

    public static void main(String[] args) {
        String contrasena = generarContrasena(12);
        System.out.println("Contraseña segura generada: " + contrasena);
    }
}
