package calculator_gui.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zengxf on 2019/11/5.
 */
public class ExpressionFormatter {

    public static String format(String expression) {
        String res = expression.replace(" ", "");
        String regex = "\\s*([\\d|\\.]+|/|\\*|\\+|\\-|%)\\s*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        matcher.reset();
        boolean result = matcher.find();
        if (result) {
            StringBuilder sb = new StringBuilder();
            do {
                String replacement = " " + matcher.group(1) + " ";
                matcher.appendReplacement(sb, replacement);
                result = matcher.find();
            } while (result);
            matcher.appendTail(sb);
            res = sb.toString();
        }
        return res.replace("  ", " ").trim();
    }

}
