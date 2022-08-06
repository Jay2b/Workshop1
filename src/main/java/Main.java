import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String[] list = {"add", "remove", "list", "exit"};
    static final String file = "tasks.csv";
    static String[][] tasks;



    public static void main(String[] args){

    optionList(list);
    tasks = ReadFileGiveToArray(file);
    printTab(tasks);
    Scanner scanner = new Scanner(System.in);
    while(scanner.hasNextLine()){
        String line = scanner.nextLine();
        switch(line) {
            case "add":
                addtask();
                break;
            case "remove":
                removeTask(tasks,getNumber());
                System.out.println("Task was succesfully deleted");
                break;
            case "list":
                printTab(tasks);
                break;
            case "exit":
                break;
            default:
                System.out.println("Please select a correct option");

             }
        }
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
                    }
                }
            }catch(IOException e){
                System.err.println(e.getLocalizedMessage());
            }
        return tab;
    }
    public static void printTab(String[][] tasks){
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(tasks[i] + " : ");
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.println(tasks[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void addtask(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task due to date");
        String dueDate = scanner.nextLine();
        System.out.println("Is your task important: true/false");
        String isImportant = scanner.nextLine();

        tasks = Arrays.copyOf(tasks,tasks.length+1);
        tasks[tasks.length-1] = new String[3];
        tasks[tasks.length-1][0] = description;
        tasks[tasks.length-1][1] = dueDate;
        tasks[tasks.length-1][2] = isImportant;

    }
    public static boolean isNumberGreaterEqualZero(String input){
    if(NumberUtils.isParsable(input)){
        return Integer.parseInt(input) >= 0;
    }
    return false;
    }
    public static int getNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select number to remove");
        String line = scanner.nextLine();
        while(!isNumberGreaterEqualZero(line)){
            System.out.println("Incorrect argument passeed, Please give number greater or equal 0");
            scanner.nextLine();
        }
        return Integer.parseInt(line);
    }
    public static void removeTask(String[][] tab, int index){
        try{
            if(index < tab.length){
                tasks = ArrayUtils.remove(tab,index);
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println(e.getLocalizedMessage());
        }
    }
}



