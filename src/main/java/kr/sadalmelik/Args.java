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

        if(elementTail.length() == 0)
            argsMap.put(elementId, new BooleanArgsTypeImpl());
        else if (elementTail.equals("*"))
            argsMap.put(elementId , new StringArgsTypeImpl());
        else if(elementTail.equals("#"))
            argsMap.put(elementId , new IntegerArgsTypeImpl());
        else
            throw new ParseException(
                    String.format("Argument: %c has invalid format: %s.",
                            elementId, elementTail), 0);

    }

    private void validateSchemaElementId(char elementId) throws ParseException {
        if (!Character.isLetter(elementId)) {
            throw new ParseException(
                    "Bad character:" + elementId + "in Args format: " + schema, 0);
        }
    }

    private boolean parseArguments() throws ArgsException {

        for(currentArgument = 0; currentArgument < args.size(); currentArgument++){
            parseArgument(args.get(currentArgument));
        }

        return true;
    }

    private void parseArgument(String arg) throws ArgsException {
        if (arg.startsWith("-"))
            setArgument(arg.charAt(1));
    }

    private boolean setArgument(char argChar) throws ArgsException {
        if(argsMap.containsKey(argChar)){
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

    public int getInt(char arg) {
        return (Integer)argsMap.get(arg).get();
    }

    public boolean getBoolean(char arg){
        return (Boolean)argsMap.get(arg).get();
    }

}