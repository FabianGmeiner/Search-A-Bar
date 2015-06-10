//Created by Fabian on 22.05.15.
package model;

import java.io.Serializable;

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

    }
}
