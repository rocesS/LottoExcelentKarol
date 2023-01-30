package pl.lotto.resultannouncer;

import java.util.regex.Pattern;

public class UuidValidator {
    private final static Pattern pattern =  Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$");

    public static boolean isValidUUID(String uuid) {
        if (uuid == null ) return false;
        return pattern.matcher(uuid).matches();
    }
}
