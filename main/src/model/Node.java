//Created by Fabian on 22.05.15.
package model;
import java.io.*;

public class Node 
implements Serializable
{
    private Object mContent;

    public Node(Object content) {
        mContent = content;
    }

    public Object getContent() {
        return mContent;
    }
}
