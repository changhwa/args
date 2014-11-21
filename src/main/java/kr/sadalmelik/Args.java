package kr.sadalmelik;

import java.text.ParseException;
import java.util.*;

public class Args {
    private String schema;
    private int currentArgument;

    private Map<Character, ArgsType> argsMap;
    private List<String> args;

    public Args(String schema, String[] args) throws ParseException, ArgsException {

        this.schema = schema;
        this.args = Arrays.asList(args);
        this.argsMap = new HashMap<Character, ArgsType>();

        parseSchema();
        parseArguments();
    }

    private void parseSchema() throws ParseException {
        for (String element : schema.split(","))
            if (element.length() > 0)
                parseSchemaElement(element.trim());
    }

    private void parseSchemaElement(String element) throws ParseException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);

        if (elementTail.equals("*"))
            argsMap.put(elementId , new StringArgsTypeImpl());
    }

    private void validateSchemaElementId(char elementId) throws ParseException {
        if (!Character.isLetter(elementId)) {
            throw new ParseException(
                    "Bad character:" + elementId + "in Args format: " + schema, 0);
        }
    }

    private boolean parseArguments() throws ArgsException {
        for(String arg: args){
            parseArgument(arg);
            currentArgument++;
        }
        return true;
    }

    private void parseArgument(String arg) throws ArgsException {
        if (arg.startsWith("-"))
            setArgument(arg.charAt(1));
    }

    private boolean setArgument(char argChar) throws ArgsException {
        if(argsMap.containsKey(argChar)){
            currentArgument++;
            try {
                argsMap.get(argChar).set(args, currentArgument);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArgsException();
            }
        }
        return true;
    }

    public String getString(char arg) {
        return (String)argsMap.get(arg).get();
    }

}