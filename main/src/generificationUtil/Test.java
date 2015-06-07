package generificationUtil;

import generificationUtil.logger.Logger;

public class Test {
    public static void main(String[] args) {
        Logger.log(Logger.MSG, "Test:starting\n");
        Logger.log(Logger.DEBUG, "Test:starting\n");
        Logger.log(Logger.ERROR, "Test:die\n");
        return;
    }
}
