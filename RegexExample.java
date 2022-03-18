package com.ozgur;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {

    public static void main(String[] args){

        final String findPattern = "(\\d+(\\.|\\,|\\\\,|\\/|\\-)\\d+(\\s||\\-)+t\\w*)|(\\d+(\\s||\\-)+t\\w*)";
        final String removeTextPattern = "[^(\\d+(\\.|\\,|\\\\|\\/|\\-)\\d+)]";
        final String commaPattern = "\\,";
        final String numberPattern = "\\d+";

        String text = "1k 11-12 ton 14 tons k2 13 tons";
        //String text1 = "k1 15.14 tons k2 13 tons";

        Pattern p = Pattern.compile(findPattern);
        Matcher m = p.matcher(text);

        if(m.find()){
            System.out.println("Text: "+m.group());
            String onlyNumber = m.group().replaceAll(removeTextPattern,"");
            System.out.println("Text removed: "+onlyNumber);
            if (!(onlyNumber.contains(".") || onlyNumber.contains(","))){
                System.out.println("Calculating the average of the numbers...");
                double sum = 0;
                double average = 0;
                double size = 0;
                p = Pattern.compile(numberPattern);
                m = p.matcher(onlyNumber);
                while (m.find()){
                    sum = sum + Integer.parseInt(m.group());
                    size ++;
                }
                average = sum / size;
                onlyNumber = String.valueOf(average);
            }
            String numberReplaceComma = onlyNumber.replaceAll(commaPattern,".");
            System.out.println("Number with dot: "+numberReplaceComma);
            float f = Float.parseFloat(numberReplaceComma);
            System.out.println("Convert to float: "+f);
        }
    }
}
