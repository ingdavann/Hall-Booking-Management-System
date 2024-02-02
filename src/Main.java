import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static String[] history = new String[50];
    public static int historyIndex = 0;
    public static void displayMenu(){
        System.out.println("==================== Welcome Hall Booking System Menu ======================");
        System.out.println("<1> Booking");
        System.out.println("<2> Hall");
        System.out.println("<3> Showtime");
        System.out.println("<4> Reboot Showtime");
        System.out.println("<5> History");
        System.out.println("<6> Exit");
        System.out.println("=============================================================================");
    }
    public static void dailyShowtime(){
        System.out.println("# Daily Showtime of CSTAD Hall");
        System.out.println("# 1) Morning (10:00AM - 12:30PM)");
        System.out.println("# 2) Afternoon (03:00PM - 5:30PM");
        System.out.println("# 3) Night (07:00PM - 09:30PM");
    }

    /* validate method user input*/
    public static boolean validateInput(String input, String pattern, String msg){
        Pattern pattern1 = Pattern.compile(pattern);
        Matcher matcher = pattern1.matcher(input);
        return  matcher.matches();
    }
    /* End validate method user input*/

    /* main method */
    public static void main(String[] args) {
        addHistory('a',1,"asd");
        Title.displayTitle();
        String[] history = new String[50];
        Scanner config = new Scanner(System.in);
        boolean checkConfig = true;
        String pattern = "[1-9]+";
        String msg = "Sorry! please input positive number only";
        int rows = 0 , cols = 0 ;
        int checkedRow = checkRows(config, pattern, msg, rows);
        int checkCol = checkCols(config, pattern, msg, cols);
        int  total = checkedRow * checkCol;
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("=> Number of Rows: " + checkedRow + " Rows");
        System.out.println("=> Number of Seaters per row: " + checkCol + " Seaters");
        System.out.println("Total Seaters: " + total + " Seaters");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        menuOption(checkedRow, checkCol);
    }
    /* End main method */
    private static int checkRows (Scanner config, String pattern, String msg, int rows){
        do {
            System.out.println("# Setting: Set row and seater per row");
            System.out.print("--> Please set row in hall : ");
            String rowInHall = config.nextLine();
            if (validateInput(rowInHall, pattern, msg)) {
                rows = Integer.parseInt(rowInHall);
                break;
            }
            else {
                System.out.println(msg);
            }
        } while (true);
        return rows;
    }
    private static int checkCols (Scanner config, String pattern, String msg, int cols){
        do {
            System.out.print("--> Please set number of seaters per row : ");
            String totalSeatInRow = config.nextLine();
            if (validateInput(totalSeatInRow, pattern, msg)) {
                cols = Integer.parseInt(totalSeatInRow);
                System.out.println("======= You have successfully set up the hall =======");
                break;
            }
            else {
                System.out.println(msg);
            }
        }while (true);
        return cols;
    }

    // method for introduce booking process
    private static void introduceMsg(){
        System.out.println("# INSTRUCTION");
        System.out.println("# Single : C-1");
        System.out.println("# Multiple (Separate by comma) : C-1, C-2 ");
    }
    private static void initHall(String[][] hall){
        for(int i= 0; i<hall.length; i++){
            for (int j =0; j<hall[i].length; j++){
                hall[i][j] = "AV";
            }
        }
    }

    private static void showAllHall(String[][] hall){
        char seat = 65;
        for (int i = 0; i<hall.length; i++){
            for (int j = 0; j< hall[i].length; j++){
                System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + ":: " + hall[i][j] + "|\t");
            }
            System.out.println();
        }
    }

    private static void bookingSeat(String[][] hall, Scanner input ){
        System.out.print("Enter seats : ");
        String userInput = input.nextLine();
        System.out.print("Enter userID : ");
        String[] seatsArray = userInput.split(",");
        String userID = input.nextLine();
        String[] inputSplit = userInput.split("-");
        String getUserResult = "";
        String addedHistory = "";
        char letterResult = 0;
        int number = 0;

        for (String seat : seatsArray){
            String seatSplit = seat.replaceAll("-","");
            letterResult = seatSplit.charAt(0);
            char charNumber = seatSplit.charAt(1);
            number = Character.getNumericValue(charNumber);

            char seats = 65;
            for (int i = 0; i < hall.length; i++) {
                for (int j = 0; j < hall[i].length; j++) {
                    if (hall[i][j] == null) {
                        System.out.print("|" + (char) (seats + i) + "-" + (j + 1) + ":: " + hall[i][j] + "|\t");
                    }
                    if(letterResult == (char) (seats + i) && number == (j+1)){
                        hall[i][j] = "BO";
                        // Add History
                        addedHistory = addHistory(letterResult,number,userID);
                        if(historyIndex < history.length){
                            history[historyIndex] = addedHistory;
                            historyIndex ++;
                        }
                    }
                }
            }
        }
        System.out.println(" Booking Successfully...!");
    }

    private static String addHistory(char letter,int number, String userID) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

        // Format the LocalDateTime using the formatter
        String formattedDateTime = date.format(formatter);
        String seat = letter + "-" + number;
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
        return String.format("\n" +
                "\n# Seat : %s" +
                "\n# User ID : %s" +
                "\n# Date : %s",seat,userID,formattedDateTime);

    }
    // End method for introduce booking process

    // Option Case Method
    public static void menuOption(int rowHall, int colHall ){

        String[][] hall1 = new String[rowHall][colHall];
        String[][] hall2 = new String[rowHall][colHall];
        String[][] hall3 = new String[rowHall][colHall];
        initHall(hall1);
        initHall(hall2);
        initHall(hall3);
        String[][] newHall = new String[rowHall][colHall];

        boolean  checkOption = true;
        Scanner menu = new Scanner(System.in);
        String pattern = "[1-9]+";
        String msg = "Sorry! please input positive number only";
        int optionCase = 0;
        char seat = 65;
        int hall=0;
        int userId = 0;

        do{
            displayMenu();
            System.out.print("-> Please choose menu : ");
            String option = menu.nextLine();
            if(validateInput(option,pattern,msg)){
                optionCase = Integer.parseInt(option);
                if (optionCase > 6){
                    System.out.println("Sorry! You can choose only Sixth...!");
                    checkOption = true;
                }
                else {
                    switch (optionCase){
                        case 1 ->{
                            boolean checkHall = true;
                            do{
                                System.out.println("==================== Start Booking Hall ======================");
                                dailyShowtime();
                                System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                                System.out.print("Please select show time ( 1 | 2 | 3 ) : ");
                                String optionHall = menu.nextLine();
                                if (validateInput(optionHall, pattern, msg)){
                                    hall = Integer.parseInt(optionHall);
                                    if(hall > 4){
                                        System.out.println("Sorry! You can choose only Third...!");
                                        checkHall = true;
                                    }else {
                                        int j = 0;
                                        int i = 0;
                                        switch (hall){
                                            case 1 -> {
                                                System.out.println("====================  Hall #1 ======================");
                                                for (i = 0; i < hall1.length; i++) {
                                                    for (j = 0; j < hall1[i].length; j++) {
                                                        if (hall1[i][j] != null)
                                                            System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + ":: "+hall1[i][j]+"|\t");
                                                    }
                                                    System.out.println();
                                                }
                                                introduceMsg();
                                                bookingSeat(hall1,menu);
                                            }
                                            case 2 -> {
                                                System.out.println("====================  Hall #2 ======================");
                                                for (i = 0; i < hall2.length; i++) {
                                                    for (j = 0; j < hall2[i].length; j++) {
                                                        if (hall2[i][j] != null)
                                                            System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + ":: "+hall2[i][j]+"|\t");
                                                    }
                                                    System.out.println();
                                                }
                                                introduceMsg();
                                                bookingSeat(hall2,menu);
                                            }
                                            case 3 -> {
                                                System.out.println("====================  Hall #3 ======================");
                                                for (i = 0; i < hall3.length; i++) {
                                                    for (j = 0; j < hall3[i].length; j++) {
                                                        if (hall3[i][j] != null)
                                                            System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + ":: "+hall3[i][j]+"|\t");
                                                    }
                                                    System.out.println();
                                                }
                                                introduceMsg();
                                                bookingSeat(hall3,menu);
                                            }
                                        }
                                    }
                                }
                                else {
                                    System.out.println(msg);
                                }
                                checkHall = false;
                            }while(checkHall);
                        }
                        case 2 -> {
                            System.out.println("# Show All Hall");
                            System.out.println("====================  Hall #1 ======================");
                            showAllHall(hall1);
                            System.out.println("====================  Hall #2 ======================");
                            showAllHall(hall2);
                            System.out.println("====================  Hall #3 ======================");
                            showAllHall(hall3);
                        }
                        case 3 -> {
                            dailyShowtime();
                        }
                        case 4 ->{
                            System.out.println("==================== Rebooting Hall ====================");
                            initHall(hall1);
                            initHall(hall2);
                            initHall(hall3);
                            System.out.println(" -> Reboot successfully...!");
                        }
                        case 5 -> {
                            System.out.println("==================== Show History ====================");
                            for(int i = 0; i<history.length; i++){
                                if(history[i] != null){
                                    System.out.println(history[i]);
                                }
                            }
                            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        }
                        case 6 -> {
                            System.exit(0);
                        }
                    }
                }
            }
            else {
                System.out.println(msg);
            }
        }while(checkOption);
    }
}