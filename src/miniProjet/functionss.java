package miniProjet;

import javax.swing.*;
import java.io.File;
import java.util.regex.Pattern;

public class functionss {

    public boolean isIdent(String str) {
        String regex = "^[a-z][_a-zA-Z_0-9]*$";
        return Pattern.matches(regex, str);
    }

    static boolean isint(String str) {
        String regex = "[0-9]+";
        return Pattern.matches(regex, str);

    }

    boolean isChaine(String str) {
        try {
            String regex = "^[a-zA-Z0-9' ]+$";
            return Pattern.matches(regex, str);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isSpace(String str) {
        try {
            String regex = " +";
            return Pattern.matches(regex, str);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isFloat(String str) {
        try {
            String regex = "[0-9]+.[0-9]+";
            return Pattern.matches(regex, str);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isString(String str) {
        try {
            String regex = "[a-zA-Z0-9\\'-]+[a-zA-Z0-9]+";
            return Pattern.matches(regex, str);
        } catch (NumberFormatException e) {
            return false;
        }

    }
    boolean symbole(String str) {
        boolean bool;
        bool = "<".equals(str) || ">".equals(str) || "<=".equals(str) || ">=".equals(str) || "==".equals(str);
        return bool;
    }
}