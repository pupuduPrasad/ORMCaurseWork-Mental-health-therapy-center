package lk.ijse.gdse.ormcaursework.controller.Login.UtilClasses;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//    (encrypt) password
    public static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

//    Match raw password with hashed password
    public static boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}

