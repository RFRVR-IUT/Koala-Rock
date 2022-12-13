package start.structure.metier.entite; //Votre package ici.

import start.structure.stockage.Security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Cette classe représente un joueur connecté
 */
public class AuthPlayer extends Player {
    private String hashedPassword;
    private byte[] salt;

    public AuthPlayer(String login) {
        super(login);
    }

    public AuthPlayer(String login, String hashedPassword, byte[] salt) {
        this(login);
        this.salt = salt;
        this.hashedPassword = hashedPassword;
    }

    public String getLogin() {
        return super.getName();
    }

    public void setLogin(String login) {
        this.setName(login);
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Hash the password in params with the salt attribute and put the newly hashedPassword in the attribute.
     *
     * @param password - A clear password
     */
    public void setPassword(String password) {
        try {
            this.hashedPassword = Security.toHexString(Security.getSHA(password, salt));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}