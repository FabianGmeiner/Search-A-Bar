package krypt;
/** Written by Daniel Knuettel*/

import generificationUtil.list.ListStore;

public class XKrypter {
    private XKey key;

    public XKrypter(XKey _key) {
        key = _key;
    }


    private byte[] _kryptBytes(byte[] _bytes, byte[][] key) {
        byte[] krypted = new byte[_bytes.length];
        // initialized as 0
        byte[] positions = new byte[8];
        byte buffer;
        byte mask;

        for (int j = 0; j < _bytes.length; j++) {
            buffer = _bytes[j];
            for (int i = 0; i < 8; i++) {
                /* key [0] is the mask */
                mask = (byte) (((byte) (1 << positions[i])) & key[0][i]);
                /* key [1] is the speed */
                positions[i] = XKey.increment(positions[i], key[1][i]);
                buffer ^= mask;
            }
            krypted[j] = buffer;
        }
        return krypted;

    }

    private byte[] _deKryptBytes(byte[] _bytes, byte[][] key) {
        byte[] dekrypted = new byte[_bytes.length];
        // initialized as 0
        byte[] positions = new byte[8];
        byte buffer;
        byte mask;

        for (int j = 0; j < _bytes.length; j++) {
            buffer = _bytes[j];
            for (int i = 0; i < 8; i++) {
				/* key [0] is the mask */
                mask = (byte) (((byte) (1 << positions[i])) & key[0][i]);
				/* key [1] is the speed */
                XKey.increment(positions[i], key[1][i]);
                buffer ^= mask;
            }
            dekrypted[j] = buffer;
        }
        return dekrypted;

    }

    public byte[] kryptBytes(byte[] bytes) {
        ListStore keys = key.getKeys();
        byte[][] swp;
        byte[] krypted = bytes;
        swp = (byte[][]) keys.remove();
        while (swp != null) {
            krypted = _kryptBytes(krypted, swp);
            swp = (byte[][]) keys.remove();
        }
        return krypted;

    }

    public byte[] deKryptBytes(byte[] bytes) {
        ListStore keys = key.getKeys();
        byte[][] swp;
        byte[] krypted = bytes;
        swp = (byte[][]) keys.remove();
        while (swp != null) {
            krypted = _deKryptBytes(krypted, swp);
            swp = (byte[][]) keys.remove();
        }
        return krypted;

    }
}
