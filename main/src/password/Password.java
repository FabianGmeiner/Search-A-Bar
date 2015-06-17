package password;

import encryption.XKey;
import encryption.XKeys;
import encryption.XKrypter;
import generificationUtil.logger.Logger;

import java.io.Serializable;

/**
 * written by Daniel Knuettel
 * TODO:test
 * status: UNTESTED
 */
public class Password
        implements Serializable {
    XKey key;
    private String uname;
    private byte[] passwd;

    public Password(String _uname, String _passwd) {
        uname = _uname;
        byte[] unkr = _passwd.getBytes();
        byte[] name_in_bytes = uname.getBytes();

        byte[] speeds = new byte[8];
        byte[] masks = new byte[8];
        for (int i = 0; i < 8; i++) {
            speeds[i] = (byte) ((Math.random() * 3) + 1);
        }
        Logger.log(Logger.DEBUG, "new Password():speeds[0]= " + speeds[0] + "\n");

        if (name_in_bytes.length >= 8) {
            for (int i = 0; i < 8; i++) {
                masks[i] = (byte) (name_in_bytes[i] ^ (1 << (byte) ((Math.random() * 5) + 1)));
            }
        } else {
            for (int i = 0; i < 8; i++) {
                masks[i] = (byte) ((Math.random() * 30) + 2);
            }
        }
        Logger.log(Logger.DEBUG, "new Password():masks[0]= " + masks[0] + "\n");
        key = XKeys.getKey(masks, speeds);
        XKrypter k = new XKrypter(key);
        passwd = k.kryptBytes(unkr);
    }

    public String getName() {
        return uname;
    }

    public String getPasswd() {
        XKrypter k = new XKrypter(key);
        byte[] pswd = k.deKryptBytes(passwd);
        Logger.log(Logger.DEBUG, new String(pswd) + "\n");
        return new String(pswd);
    }
}
