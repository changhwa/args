package kr.sadalmelik;

import java.text.ParseException;
import java.util.*;

public class Args {
    private String schema;
    private boolean valid = true;
    private int currentArgument;

    private Map<Character, ArgsType> argsMap;
    private List<String> args;

    public Args(String schema, String[] args) throws ParseException {
        this.schema = schema;
        this.args = Arrays.asList(args);
        this.argsMap = new HashMap<Character, ArgsType>();
        valid = parse();
    }

    private boolean parse() throws ParseException {
        if (schema.length() == 0 && args.size() == 0)
            return true;
        parseSchema();
        try {
            parseArguments();
        } catch (ArgsException e) {
        }
        return valid;
    }

    private boolean parseSchema() throws ParseException {
        for (String element : schema.split(",")) {
            if (element.length() > 0) {
                String trimmedElement = element.trim();
                parseSchemaElement(trimmedElement);
            }
        }
        return true;
    }

    private void parseSchemaElement(String element) throws ParseException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);

        //굳이 한줄인데.. 메소드를 만들 필요가 있나.
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
            parseElement(arg.charAt(1));
    }

    private void parseElement(char argChar) throws ArgsException {
        setArgument(argChar);
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
        return String.valueOf(argsMap.get(arg).get());
    }


    public static void main(String[] args) {
        try {
            Args arg = new Args("l,p#,d*", args);
            String directory = arg.getString('d');
            System.out.println("directory : " + directory);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}