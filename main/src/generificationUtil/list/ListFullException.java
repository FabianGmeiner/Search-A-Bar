package generificationUtil.list;

/**
 * Not used but there..<br/><br/>
 * Written by Daniel Knuettel
 */
public class ListFullException
        extends Exception {
    private String stdmsg = "List is already full.";

    public ListFullException() {
        super("List is already full.");
    }

}
