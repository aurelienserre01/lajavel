package org.lajavel;

import app.Main;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {

    private static String propertyName;

    public static String getValueOf(Object object, String property) {
        String value = "";
        if (property.contains("()")) {
            property = property.replace("()", "");
            value = getMethod(object, property);
        } else {
            value = getProperty(object, property);
        }
        return value;
    }


    public static String getMethod(Object obj, String methodName) {
        String returnValue = "";
        try {
            Method method = obj.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            returnValue = method.invoke(obj).toString();
        } catch (Exception e) {

        }
        return returnValue;
    }

    public static String getProperty(Object obj, String propertyName) {
        String returnValue = "";
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            returnValue = field.get(obj).toString();
        } catch (Exception e) {

        }
        return returnValue;
    }



    private static String replaceProperties(String html, Map.Entry<String, Object>... entries) {
        if (entries == null){
            return null;
        }
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile("\\{\\{(.*?)\\}\\}").matcher(html);
        while (matcher.find()) {
            String rawStringOfAnObject = matcher.group(1).replaceAll("\\s+", "");
            String[] objectAndProperty = rawStringOfAnObject.split("\\."); //Split with dot
            String objectName = objectAndProperty[0];
            String propertyName = objectAndProperty[1];
            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getKey().equals(objectName)) {
                    matcher.appendReplacement(sb, getValueOf(entry.getValue(), propertyName));
                    break;
                }
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String replaceForeach(String html, Map.Entry<String, Object>... entries) {
        Matcher matcherFor = Pattern.compile("\\{%\\s*for\\s(\\w*)\\sin\\s(\\w*)\\s%}(.*?)\\{%\\s+endfor\\s+%}", Pattern.DOTALL).matcher(html);
        StringBuffer stringBufferFinal = new StringBuffer();
        StringBuffer stringBufferForeach = new StringBuffer();

        while (matcherFor.find()) {
            String propertyName = matcherFor.group(1).replaceAll("\\s", ""); // user
            String listName = matcherFor.group(2).replaceAll("\\s", ""); //users
            String htmlOnFor = matcherFor.group(3).trim(); // tout html

            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getKey().equals(listName)) {
                    for (Object value : (List<?>) entry.getValue()) {
                        String htmlReplaced = replaceProperties(htmlOnFor, Map.entry(propertyName, value));
                        stringBufferForeach.append(htmlReplaced);
                    }
                    matcherFor.appendReplacement(stringBufferFinal, stringBufferForeach.toString());
                    break;
                }
            }
        }
        matcherFor.appendTail(stringBufferFinal);
        return stringBufferFinal.toString();
    }

    public static String importCSS(String html) {
        Matcher matcher = Pattern.compile("@import\\s\"(.*\\.css)\"").matcher(html); //récupère toutes les endrois ou @import "*.css"
        StringBuffer stringBufferCSS = new StringBuffer();

        while (matcher.find()) {
            String fileNameCSS = matcher.group(1).replaceAll("\\s", "");
            try {
                matcher.appendReplacement(stringBufferCSS, "<link rel=\"stylesheet\" href=\"css/" + fileNameCSS + "\">");
            } catch (Exception e) {
                Log.error(e.getMessage());
            }

        }
        matcher.appendTail(stringBufferCSS);
        return stringBufferCSS.toString();
    }

    public static String make(String viewName, Map.Entry<String, Object>... entries) {
        String rawHtml = View.getViewContentFromName(viewName);
        rawHtml = importCSS(rawHtml);
        rawHtml = replaceForeach(rawHtml, entries);

        return replaceProperties(rawHtml, entries);
    }

    private static String getViewContentFromName(String viewName) {
        URL resource = Main.class.getClassLoader().getResource("views/" + viewName + ".javel");

        if (resource == null) {
            throw new RuntimeException("File : " + viewName + "not found!");
        }

        try {
            return Files.readString(Path.of(resource.toURI()), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

    }


}
