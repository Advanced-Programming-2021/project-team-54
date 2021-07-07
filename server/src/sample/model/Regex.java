package sample.model;

import java.util.regex.Pattern;

public class Regex {
    private static final Pattern createUserPattern1 = Pattern.compile("userconfig --create --username (?<username>.*) --password (?<password>.*)");
}
