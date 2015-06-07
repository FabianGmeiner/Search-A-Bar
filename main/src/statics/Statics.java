//Created by Fabian on 19.05.15.
package statics;

import model.Bar;
import model.Node;

import java.net.URI;

public class Statics {
    // all statics are stored here
    public static final String ADMIN_CODE ="#Admin#";

    public static final int DIALOG_CODE_EDIT = 0;
    public static final int DIALOG_CODE_NEW = 1;

    public static final String[] CATEGORYS = new String [] {"Kategorie","Kneipe","Bar","Tanz-Club","Live-Club","Strandbar","Biergarten","Allround"};
    public static final String[] AGE_RESTRICTIONS = new String [] {"Altersbeschränkung","Ab 16","Ab 18","Unbeschränkt"};
    public static final String[] AVG_AGE = new String[] {"Durchschnittsalter","unter 18", "18-25", "25-30", "30-40", "über 40"};
    public static final String[] PRICE_RANGE = new String[] {"Eintrittspreis","kostenlos","bis 5€","bis 10€","über 10€"};

    public static final int SORT_CODE_ALPHABETICAL = 2;
    public static final int SORT_CODE_POPULAR = 3;
    public static final int SORT_CODE_PRICE = 4;

    // This is a list of Bars used to test the application. Will be replaced by xml-file
    public  Node node1 = new Node(new Bar("Alte Filmbühne",
            "Hippe Bar mit \ndurchgestylten Räumen.\nWochenendes meist gerammelt\nvoll. Wenige \nSitzgelegenheiten.",
            "filmbuehne.com", "Alte Filmbühne \nTaubengäßchen 2\n93047 Regensburg", 1,
            49.020783, 12.097135, 0, 18, 23));
    public  Node node2 = new Node(new Bar("Alte Mälze",
            "Viele Großveranstaltungen \nmit garantiert \nguter Stimmung.",
            "alte-maelzerei.de", "Alte Mälzerei \nGalgenbergstraße 20\n93053 Regensburg", 7,
            49.007059, 12.099403, 0, 16, 25));
    public  Node node3 = new Node(new Bar("Apotheke",
            "Crunchige Intellektuellenkneipe \nmit bestem Bier.",
            "kneipen.de/regensburg/apotheke", "Apotheke \nRote-Hahnen-Gasse 8\n93047 Regensburg", 4,
            49.018934, 12.093299, 0, 0, 0));
    public  Node node4 = new Node(new Bar("Banane",
            "Auf den ersten Blick \nspelunkig, doch ein \nBlick in den ersten Stock \nlohnt sich allemal.",
            "club-banane.de", "Banane \nAm Römling 1\n93047 Regensburg", 1,
            49.020659, 12.092247, 2.00, 16, 21));
    public  Node node5 = new Node(new Bar("Ei",
            "Kneipe mit gutbürgerlicher \nKüche. Kleiner, \nfeiner Biergarten.",
            "...", "Ei \nKeplerstraße 3\n93047 Regensburg", 1,
            49.021140, 12.093595, 0, 16, 0));
    public  Node node6 = new Node(new Bar("Picasso",
            "Urgemütlich auf zwei \nEtagen in einer \nehemaligen Kapelle.",
            "cafe-picasso-regensburg.de", "Café Picasso \nUnter den Schwibbögen 1\n93047 Regensburg", 2,
            49.020425, 12.097696, 0, 0, 0));
    public  Node node7 = new Node(new Bar("Suite15",
            "Eher Club als \nTanzfabrik und schon \ndeshalb einen Besuch \nwert.",
            "suite15.de", "Suite15 \nAdolph-Kolping-Straße 5\n93047 Regensburg", 3,
            49.019134, 12.101690, 3.00, 16, 19));

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public Node getNode3() {
        return node3;
    }

    public Node getNode4() {
        return node4;
    }

    public Node getNode5() {
        return node5;
    }

    public Node getNode6() {
        return node6;
    }

    public Node getNode7() {
        return node7;
    }

}
