package dataCollection;

import generificationUtil.list.List;

abstract class DataScan {


    public abstract List scan();

    protected int getOstype() {
        return 0;
    }
}
