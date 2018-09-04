import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("admin");
        System.out.println(encode);
    }
}
