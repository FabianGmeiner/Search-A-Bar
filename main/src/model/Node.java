//Created by Fabian Gmeiner on 22.05.15.

package model;

import java.io.Serializable;

// simple class used as nodes for the graph to allow for universal useablility

public class Node implements Serializable {
    private Object mContent;
    private boolean checkmark = false;

    public Node(Object content) {
        mContent = content;
    }
    public Object getContent() {
        return mContent;
    }

    public boolean getCheckmark() {
        return checkmark;
    }
    public void setCheckmark(boolean value) {
        checkmark = value;
    }

    public void initialiseDepthSearch() {
        setCheckmark(false);
    }
}
