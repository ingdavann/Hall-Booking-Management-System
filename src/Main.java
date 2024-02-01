import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void displayMenu(){
        System.out.println("==================== Welcome Hall Booking System Menu ======================");
        System.out.println("1.Booking");
        System.out.println("2.Hall");
        System.out.println("3.Showtime");
        System.out.println("4.Reboot Showtime");
        System.out.println("5.History");
        System.out.println("6.Exit");
        System.out.println("=============================================================================");
    }
    public static void dailyShowtime(){
        System.out.println("# Daily Showtime of CSTAD Hall");
        System.out.println("# A) Morning (10:00AM - 12:30PM)");
        System.out.println("# B) Afternoon (03:00PM - 5:30PM");
        System.out.println("# C) Night (07:00PM - 09:30PM");
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
        Title.displayTitle();
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

    // Option Case Method
    public static void menuOption(int rowHall, int colHall){
        String[][] hall1 = new String[rowHall][colHall];
        String[][] hall2 = new String[rowHall][colHall];
        String[][] hall3 = new String[rowHall][colHall];
        boolean  checkOption = true;
        Scanner menu = new Scanner(System.in);
        String pattern = "[1-9]+";
        String msg = "Sorry! please input positive number only";
        int rows =  0;
        int cols = 0;
        int optionCase = 0;
        do{
            displayMenu();
            System.out.print("-> Please choose option (1 to 6): ");
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
                            System.out.println("==================== Booking Hall ======================");
                            System.out.print("Enter your desired row " + "(1" + (rowHall > 1 ? "-" + colHall : "" ) + "): " );
                            rows = menu.nextInt();
                            if(rowHall < colHall || colHall <= 0 ){
                                System.out.println("--> Floor range start from " + "(1" + (rowHall > 1 ? "-" + colHall : "" ) + "): ");
                                checkOption = true;
                            }
                            else {
                                System.out.print("Enter your desired seater " + "(1" + (rowHall > 1 ? "-" + colHall : "") + "): ");
                                cols = menu.nextInt();
                                if ( cols > colHall || cols <= 0 ) {
                                    System.out.println("--> Room range start from " + "(1" + (rowHall > 1 ? "-" + colHall : "") + "): ");
                                    checkOption = true;
                                } else {
                                    if( hall1[ rowHall - 1 ][ colHall - 1 ] == null ){
                                        System.out.println("You have booked successfully");
                                    }else{
                                        System.out.println("You can not buy this condo, cause it already sold to someone!");
                                    }
                                }
                            }
                            checkOption = true;
                        }
                        case 2 -> {
                            System.out.println("==================== ## Hall ## ======================");
                            char seat = 65;
                            System.out.print("-> Please Choose Hall : ");
                            Scanner optionHall = new Scanner(System.in);
                            int hall = optionHall.nextInt();
                            if(hall <=0 || hall>3){
                                System.out.println("You can choose only Sixth...!");
                                checkOption = true;
                            }
                            switch (hall){
                                case 1 -> {
                                    System.out.println("==================== Hall A ============================");
                                    for (int i = 0; i < hall1.length; i++) {
                                        for (int j = 0; j < hall1[i].length; j++) {
                                            if (hall1[i][j] == null)
                                                System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + "::AV|\t");
                                            else
                                                System.out.print("|" + (char) (seat + i) +  "-" + (j + 1) +"::" + hall1[i][j] + "|\t");
                                        }
                                        System.out.println();
                                    }
                                }
                                case 2 -> {
                                    System.out.println("==================== Hall B ============================");
                                    for (int i = 0; i< hall2[i].length; i++){
                                        for (int j = 0; j < hall2[i].length; j++) {
                                            if (hall2[i][j] == null)
                                                System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + "::AV|\t");
                                            else
                                                System.out.print("|" + (char) (seat + i) +  "-" + (j + 1) +"::" + hall2[i][j] + "|\t");
                                        }
                                        System.out.println();
                                    }
                                }
                                case 3 -> {
                                    System.out.println("==================== Hall C ============================");
                                    for (int i = 0; i< hall3[i].length; i++){
                                        for (int j = 0; j < hall3[i].length; j++) {
                                            if (hall3[i][j] == null)
                                                System.out.print("|" + (char) (seat + i) + "-" + (j + 1) + "::AV|\t");
                                            else
                                                System.out.print("|" + (char) (seat + i) +  "-" + (j + 1) +"::" + hall3[i][j] + "|\t");
                                        }
                                        System.out.println();
                                    }
                                }
                            }
                            checkOption = true;
                        }
                        case 3 -> {
                            dailyShowtime();
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