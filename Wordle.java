import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class Wordle{
    
    public static int timesTried = 0; //times tried is a global variable for CheckIsSame and Main functions.
                                      //round counter
    public static boolean CheckLength(String word){
        //check if length is like indicated. 
        if(word.length() > 5 || word.length() < 5){
            System.out.println("The length of word must be five!");
            return false;
        }
    
        return true;
    }
    
    //reading all the data from dict.txt
    //                                                     ***I used google only for how to get data from a txt file.***
    public static String[] GetWordsFromFile() throws FileNotFoundException{
        //getting the whole data to "wordsOfDict" array.
        File myDict = new File("dict.txt");     
        Scanner reader = new Scanner(myDict);
        String[] wordsOfDict = new String[2318];
        int indx = 0;
        while(reader.hasNextLine()){
            wordsOfDict[indx] = reader.nextLine();
            indx++;
        }
        reader.close();

        return wordsOfDict;
    }
    //check if in the dictionary.
    public static boolean CheckInDict(String word, String[] wordsFromDict){
        
        if(Arrays.asList(wordsFromDict).contains(word)){
            return true;  //if it is present.
        }
        System.out.println("The word does not exist in the dictionary!");
        return false; //not found
    }


    //check if the word is already same.
    //I'm doing it because the memory addresses in the String array differs. So, I decided to check character by character.
    public static boolean CheckIsSame(String word, String keyWord){
        
        //success should reach 5 which means they are both same.
        int success = 0;
        for(int i = 0; i < 5; i++){
            if(word.charAt(i) == keyWord.charAt(i)){
                success++;
                continue;
            }
        }
        if(success == 5){
            return true;
        }
        return false;
    }

    //print the output and how many times repeated.
    public static void SameTrue(){
        if(timesTried == 1){
            System.out.print("Congratulations! You guess right in "+ timesTried +"st shot!");
        } 
        else if(timesTried ==  2){
            System.out.print("Congratulations! You guess right in "+ timesTried +"nd shot!");
        } 
        else if(timesTried ==  3){
            System.out.print("Congratulations! You guess right in "+ timesTried +"rd shot!");
        } 
        else{ 
            System.out.print("Congratulations! You guess right in "+ timesTried +"th shot!");
        }
        
    }

    //checking letter positions.
    public static void CheckLetterPositions(String word, final String keyWord){
        
        for(int i = 1; i <= 5; i++){
            if(word.charAt(i-1) == keyWord.charAt(i-1)){
                System.out.println(i + ". letter exists and located in right position.");
            }
            else if(keyWord.contains(String.valueOf(word.charAt(i-1)))){
                System.out.println(i + ". letter exists but located in wrong position.");
            }else{
                System.out.println(i + ". letter does not exit.");
            }
        }
    }



    //the default code is set according to Example 1 in the instruction pdf.

    public static void main(String[] args) throws FileNotFoundException {
        final String keyWord = "GRIPE";
        int tryNumber = 1;
        //get all the data from dict.txt
        String[] wordsFromDict = GetWordsFromFile();
        //here is loop for inputs.
        for(String word : args){
            //game starting. try starting.
            timesTried++;  //round counter

            //we exceeded maximum try.
            //terminate now if testNumber is 7.
            if(tryNumber == 7){
                System.out.println("You exceed maximum try shot!");
                System.out.println("You failed! The key word is " + keyWord + ".");
                break;
            }
            
            System.out.println("Try"+ tryNumber + " (" +  word + "):");
            //first check if the length is 5
            if(CheckLength(word) == true){
                //second check if the word in the dict.txt
                if(CheckInDict(word, wordsFromDict) == true){
                    //third check if the word is already found.
                    if(CheckIsSame(word, keyWord) == false){
                        CheckLetterPositions(word, keyWord);
                    }else{
                        SameTrue();
                        break;
                    }
                }
            }
            tryNumber++;
        }


    }
}