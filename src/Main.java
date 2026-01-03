import util.Conexion;
import org.mindrot.jbcrypt.BCrypt;
public class Main {
    public static void main(String[] args) {

        String hash = BCrypt.hashpw("1234", BCrypt.gensalt(10));

        if (BCrypt.checkpw("1234",hash))
            System.out.printf("Clave original: 1234, su hash " + hash);
    }
}
