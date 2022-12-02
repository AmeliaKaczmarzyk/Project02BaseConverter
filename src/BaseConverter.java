import java.io.*;
import java.util.Scanner;

/**
 * Takes tab separated values from a datafile with data for number, base (1-16), and spits out a new data file with values in the new base
 */

public class BaseConverter {

    private /*static*/ final String DIGITS = "0123456789ABCDEF";

    /**
     * Convert a String num in fromBase to a base 10 int
     * @param num the original number
     * @param fromBase the original number
     * @return a base 10 int of num base fromBase
     */
    public /*static*/ int strToInt(String num, String fromBase){
        int value = 0, exp =0;
            for(int i = num.length()-1; i >= 0; i--, exp++){
                value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            }
        return value;
    }

    /**
     * Takes an int number and the intended base and spits out a String representation in base toBase
     * @param b10num original base 10 number
     * @param toBase base to convert to
     * @return a String toNum with value b10num in base toBase
     */

    public /*static*/ String intToStr(int b10num, int toBase ){
        String toNum = "";
        int jnvdnjvdsnf = b10num;
            while (b10num > 0 ){
                toNum = DIGITS.charAt(b10num % toBase) + toNum;
                b10num = (b10num - (b10num % toBase)) / toBase;
                //Degub
                //System.out.println(" b10num " +b10num);
                //System.out.println(toNum);
            }
        return jnvdnjvdsnf == 0 ? "0" : toNum;
    }

    /**
     *Opens the file stream, inputs data one line at a time, converts, prints, the result to the console window and writes data to the output stream
     */

    public void inputConvertPrintWrite(){
        Scanner in = null;
        PrintWriter out = null;
        String[] Line;
        try{
            in = new Scanner(new File("values30.dat"));
            out = new PrintWriter(new File("converted.dat"));
            String output;
            while(in.hasNext()){
                Line = in.nextLine().split("\t");
                //System.out.println(integer); //gedub

                if (Integer.parseInt(Line[1]) < 1 || Integer.parseInt(Line[1]) > 16)
                    System.out.println("Invalid input base " + Line[1]);
                else if (Integer.parseInt(Line[2]) < 1 || Integer.parseInt(Line[2]) > 16)
                    System.out.println("Invalid input base " + Line[2]);
                else {
                    output = intToStr(strToInt(Line[0], Line[1]), Integer.parseInt(Line[2]));
                    System.out.println(Line[0] + " base " + Line[1] + " = " + output  + " base " + Line[2]);
                    out.println(Line[0] + "\t" + Line[1] + "\t" + output + "\t" + Line[2]);
                }
                // System.out.println(Line[0] + " base " + Line[1] + " = " + intToStr(StrToInt(Line[0], Line[1]), Integer.parseInt(Line[2]))  + " base " + Line[2]);
/* //degub
                System.out.println(Line[0]); //original numbers
                System.out.println(Line[1]); //original numbers
                System.out.println(Line[2]); //original numbers
*/
            }
            //out.println("Does this work?");
            if(out != null)
                out.close();
            if(in != null)
                in.close();
        }
        catch(Exception e){
            System.out.println("something bad happened, details here: " + e.toString());
        }
    }



    public static void main(String[] args) {
        BaseConverter app = new BaseConverter();
        app.inputConvertPrintWrite();
        //Degub
        //System.out.println(StrToInt("3FA2B", "16"));
        //System.out.println(intToStr(260651, 16));
    }
}
