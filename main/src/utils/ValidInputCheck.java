//Created by Fabian on 07.06.15.
package utils;

import statics.Statics;

public class ValidInputCheck {

    public static boolean isValidInput(String input, int code) {
        boolean valid = true;
        switch (code) {
            case Statics.VALID_CODE_INT: {
                try {
                    int i = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    valid = false;
                }
            }
            case Statics.VALID_CODE_DOUBLE: {
                try {
                    double d = Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    valid = false;
                }
            }
        }
        return valid;
    }

}
