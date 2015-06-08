package generificationUtil.serializer;

/**
 * written by Daniel Knuettel
 */

import generificationUtil.list.List;
import generificationUtil.logger.Logger;
import generificationUtil.stack.Stack;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer {
    /**
     * the Deserializer does not support unbuffered mode.
     */
    public final int queueMode = 1;
    public final int stackMode = 2;

    private int mode = queueMode;

    private String fileName = "default.ser";

    private List deserialized = new List();

    public Deserializer(int _mode) {
        if (_mode != queueMode && _mode != stackMode) {
            Logger.log(Logger.ERROR, "new Deserializer(): mode(" + _mode + ")not supported\n");
            System.err.printf("new Deserializer():ERROR: check log.\n");
            return;
        }
        mode = _mode;
        switch (mode) {
            case stackMode: {
                deserialized = new Stack();
            }
            break;
            case queueMode: {

            }
            break;
        }
    }

    public Deserializer(String _fileName) {
        fileName = _fileName;
    }

    public Deserializer(String _fileName, int _mode) {
        if (_mode != queueMode && _mode != stackMode) {
            Logger.log(Logger.ERROR, "new Deserializer(): mode(" + _mode + ")not supported\n");
            System.err.printf("new Deserializer():ERROR: check log.\n");
            return;
        }
        mode = _mode;
        switch (mode) {
            case stackMode: {
                deserialized = new Stack();
            }
            break;
            case queueMode: {

            }
            break;
        }
        fileName = _fileName;
    }

    public void readObjs() {
        FileInputStream file;
        ObjectInputStream objIn;
        Object swp;
        try {
            file = new FileInputStream(fileName);
            objIn = new ObjectInputStream(file);
        } catch (IOException e) {
            Logger.log(Logger.ERROR, e.toString());
            return;
        }
        try {
            swp = objIn.readObject();
            while (swp != null) {
                deserialized.insert(swp);
                swp = objIn.readObject();
            }
        } catch (Exception e) {
            Logger.log(Logger.ERROR, e.toString());
            return;
        }
        return;
    }

    public Object getObject() {
        return deserialized.remove();
    }
}
