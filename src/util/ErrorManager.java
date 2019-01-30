package util;

import java.util.ArrayList;

public class ErrorManager {
    public static String getErrorMessages(ArrayList<Error> errors) {
        StringBuilder messages = new StringBuilder();

        for (Error error : errors) {
            messages.append(error.getMessage().concat("|"));
        }

        return messages.toString();
    }
}
