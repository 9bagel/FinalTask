package by.epam.learn.bahlei.finaltask.util.encryptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class BcryptUtil {
    private static final Logger LOGGER = LogManager.getLogger(BcryptUtil.class);

    public static String generateHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean isPasswordCorrect(String candidate, String hash) {
        boolean result;
        try {
            result = BCrypt.checkpw(candidate, hash);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid password hash format in database for: " + hash);
            throw new IllegalArgumentException(e);
        }
        return result;
    }
}