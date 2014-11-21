package kr.sadalmelik;

import java.text.ParseException;

public class ArgsMain {

    public static void main(String[] args) throws ArgsException {
        try {
            Args arg = new Args("l,p#,d*", args);
            Object directory = arg.getString('d');
            System.out.println("directory : " + directory);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
