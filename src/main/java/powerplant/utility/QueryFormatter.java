package powerplant.utility;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryFormatter {

    public String formatQuery(String query, String var1){
        StringBuilder output = new StringBuilder();
        Pattern pattern = Pattern.compile(":(.*)");
        Matcher matcher = pattern.matcher(query);
        int lastStart = 0;
        while (matcher.find()) {
            String subString = query.substring(lastStart,matcher.start());
            String varName = matcher.group(1);
            String replacement = var1;
            output.append(subString).append(replacement);
            lastStart = matcher.end();
        }
        System.out.println(output.toString());
        return output.toString();
    }

    public String formatQuery(String query, Map<String,String> var1){
        StringBuilder output = new StringBuilder();
        Pattern pattern = Pattern.compile(":([a-zA-Z]*)");
        Matcher matcher = pattern.matcher(query);
        int lastStart = 0;
        while (matcher.find()) {
            String subString = query.substring(lastStart,matcher.start());
            String varName = matcher.group(1);
            String replacement = var1.get(varName);
            if(replacement!=null) {
                output.append(subString).append("'"+replacement+"'");
            }
            lastStart = matcher.end();
        }
        System.out.println(output.toString());
        return output.toString();
    }
}
