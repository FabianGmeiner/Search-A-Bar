package generificationUtil.serializer;

import generificationUtil.list.List;
import generificationUtil.list.OneList;
import generificationUtil.logger.Logger;
import generificationUtil.stack.Stack;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Serializer {
    public final int queueMode = 1;
    public final int stackMode = 2;
    public final int unbufferedMode = 3;

    private int mode = queueMode;

    private String fileName = "default.ser";

    private List toSerialize = new List();

    public Serializer(int _mode) {
        if (_mode != queueMode && _mode != stackMode && _mode != unbufferedMode) {
            Logger.log(Logger.ERROR, "new Serializer(): mode(" + _mode + ")not supported\n");
            System.err.printf("new Serializer():ERROR: check log.\n");
            return;
        }
        mode = _mode;
        if (mode == unbufferedMode) {
            Logger.log(Logger.WARN, "Serializer: unbuffered Mode is not suggested\n");
        }
        switch (mode) {
            case stackMode: {
                toSerialize = new Stack();
            }
            break;
            case queueMode: {

            }
            break;
            case unbufferedMode: {
                toSerialize = new OneList();
            }
        }
    }

    public Serializer(String _fileName) {
        fileName = _fileName;
    }

    public Serializer(String _fileName, int _mode) {
        if (_mode != queueMode && _mode != stackMode && _mode != unbufferedMode) {
            Logger.log(Logger.ERROR, "new Serializer(): mode(" + _mode + ")not supported\n");
            System.err.printf("new Serializer():ERROR: check log.\n");
            return;
        }
        mode = _mode;
        if (mode == unbufferedMode) {
            Logger.log(Logger.WARN, "Serializer: unbuffered Mode is not suggested\n");
        }
        fileName = _fileName;

    }

    public void addObject(Object _obj) {
        toSerialize.insert(_obj);
        if (mode == unbufferedMode) {
            Logger.log(Logger.WARN, "Serializer:addObject():using unbufferedMode, so I will serialize Object now\n");
            serialize();
        }
    }

    public void serialize() {
        Object swp = toSerialize.remove();
        ObjectOutputStream objOut;
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            objOut = new ObjectOutputStream(file);
        } catch (IOException e) {
            System.err.printf("Error:Serializer.serialize():cannot open %s\n", fileName);
            Logger.log(Logger.ERROR, "Serializer.serialize():cannot open " + fileName + "skipping.\n");
            Logger.log(Logger.ERROR, e.toString());
            return;
        }
        try {
            while (swp != null) {
                objOut.writeObject(swp);
                swp = toSerialize.remove();
            }
        } catch (IOException e) {
            Logger.log(Logger.ERROR, "Serializer.serialize(): cannot write Object (implements it Serializable?)\n");
            Logger.log(Logger.ERROR, e.toString());
        }
    }

    public void addObjectList(List _list) {
        if (mode == unbufferedMode) {
            Logger.log(Logger.ERROR, "Serializer.addObjectList(): mode is unbuffered cannot add list.\n");
            return;
        }
        Object swp = _list.remove();
        while (swp != null) {
            toSerialize.insert(swp);
            swp = _list.remove();
        }
    }


}
