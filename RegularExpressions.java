import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {

    static List<Answer> Answer = new ArrayList<>();
    private static int ManyFound = 0;


    public static void main(String[] args) throws FileNotFoundException {

        //1
        String alphaB = "[Aa][Bb][Cc][Dd][Ee][Ff][Gg][Hh][Ii][Jj][Kk][Ll][Mm][Nn][Oo][Pp][Qq][Rr][Ss][Tt][Uu][Vv][Xx][Yy][Zz]";
        readFiles(alphaB, "Uppg.1", true);

        //2
        String ola = "[Oo][Ll][Aa]";
        readFiles(ola, "Uppg.2", true);

        //3
        String all3t5 = "[Aa]{3,5}";
        readFiles(all3t5, "Uppg.3", false);
        Answer.add(new Answer("Uppg.3", ManyFound));
        ManyFound = 0;
        readFiles(all3t5, "Uppg.3", true);

       //4
        String AlphaValues = "[\\W]{2,}";
        readFiles(AlphaValues, "Uppg.4", false);
        Answer.add(new Answer("Uppg.4", ManyFound));
        ManyFound = 0;

        //5
        String email = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}";
        readFiles(email, "Uppg.5", false);
        Answer.add(new Answer("Uppg.5", ManyFound));
        ManyFound = 0; //reset
        readFiles(email, "Uppg.5", true);

        //6
        String PhoneNr = "0{1,2}[0-9]{1,3}[\\s/-]?[0-9]{5,}";
        readFiles(PhoneNr, "Uppg.6", false);
        Answer.add(new Answer("Uppg.6", ManyFound));
        ManyFound = 0; 
        readFiles(PhoneNr, "Uppg.6", true);

        //7
        String fourAlike = "([A-Za-z])\\1{3}";
        readFiles(fourAlike, "Uppg.7", false);
        Answer.add(new Answer("Uppg.7", ManyFound));
        ManyFound = 0;

        //8
        String license = "[A-Za-z]{3}[/_][0-9]{3}";
        readFiles(license, "Uppg.8", true);

        //9
        String matching = "[(][A-Za-z]+[)]|[(][0-9]+[)]|[(][\\s]+[)]";
        Answer.add(new Answer("Uppg.9", ManyFound));
        ManyFound = 0; 
        readFiles(matching, "Uppg.9", true);

        //10
        String ifLoop = "if[\\s]?[(]|for[\\s]?[(]";
        readFiles(ifLoop, "Uppg.10", true);
        System.out.println(Answer);
    }
//finder
    public static void finder(String inputString, String pattern, String showPart, int currentLine, boolean gettingPosition)  {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(inputString);


        while (matcher.find()) {
            if (matcher.group().length() != 0) {
                if (gettingPosition) {
                    Answer.add(new Answer(showPart, matcher.group(), currentLine, matcher.start(), matcher.end()));
                } else {
                    ManyFound++;
                }

            }
        }
    }
//Scanner
    public static void readFiles(String inputPattern, String showPart, boolean gettingPosition) throws FileNotFoundException {
        File file = new File("text.txt");
        Scanner fileRead = new Scanner(file);

        int counter = 1;

        while (fileRead.hasNextLine()) {
            String thisLineAsString = fileRead.nextLine();

            finder(thisLineAsString, inputPattern, showPart, counter, gettingPosition);

            counter++;
        }
        fileRead.close();
    }

}