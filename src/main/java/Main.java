import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.lang.*;

public class Main {



    public static void main(String[] args){
    String[] list = {"add", "remove", "list", "exit"};
    String file = "tasks.csv";
    optionList(list);
    ReadFileGiveToArray(file);
    }

    public static void optionList(String[] list){
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        for (String s : list) {
            System.out.println(s);
        }
    }
    public static String[][] ReadFileGiveToArray(String file){
        Path directory = Paths.get(file);
        String tab[][] = null;
        if(!Files.exists(directory)){
            System.out.println("File not exist");
            System.exit(0);
        }
            try {
                List<String> strings = Files.readAllLines(directory);
                tab = new String[strings.size()][strings.get(0).split(",").length];
                for (int i = 0; i < strings.size(); i++) {
                    String[] split = strings.get(i).split(",");
                    for (int j = 0; j <split.length ; j++) {
                        tab[i][j] = split[j];
                        System.out.println(tab[i][j]);
                    }
                }
            }catch(IOException e){
                System.err.println(e.getLocalizedMessage());
            }
        return tab;
    }
}


