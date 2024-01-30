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

    public static void configHall() {
        boolean checkConfig = false;
        do {
//            int totalSeatInRow = 0;
            String regex = "-?[1-9]\\d*|0\n";
            Scanner config = new Scanner(System.in);

            System.out.println("# Setting: Set row and seater per row");
            System.out.print("--> Please set row in hall : ");
            String rowInHall = config.nextLine();

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(rowInHall);


            if (matcher.matches()) {
                Integer toRow = Integer.valueOf(rowInHall);
                if (toRow <= 0) {
                    System.out.println("Row can not zero or negative...!");
                    checkConfig = true;
                }
                else{
                    System.out.print("--> Please set number of seaters per row : ");
                    String totalSeatInRow = config.nextLine();
                    Integer toSeater = Integer.valueOf(totalSeatInRow);
                    if(toSeater <= 0){
                        System.out.println("Seater can not zero or negative...!");
                        checkConfig = true;
                    }
                    else{
                        System.out.println("======= You have successfully set up the hall =======");
                        Integer total;
                        total = toRow * toSeater;
                        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println("=> Number of Rows: " + toRow + " Rows");
                        System.out.println("=> Number of Seaters per row: " + toSeater + " Seaters");
                        System.out.println("Total Seaters: " + total + " Seaters");
                        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        checkConfig = false;
                    }
                    String[][] hall1 = new String[toRow][toSeater];
                    String[][] hall2 = new String[toRow][toSeater];
                    String[][] hall3 = new  String[toRow][toSeater];
                }
            } else {
                System.out.println("You can not input text, number only");
                checkConfig = true;
            }
        } while (checkConfig);
    }
    public static void menuOption(){
        boolean  checkOption = false;
        Scanner menu = new Scanner(System.in);
        int rows =  0;
        int cols = 0;
        do{
            displayMenu();
            String regex = "-?[1-9]\\d*|0\n";
            System.out.print("-> Please choose option (1 to 6): ");
            String option = menu.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(option);
            if(matcher.matches()){
                Integer optionCase = Integer.valueOf(option);
                if(optionCase<=0 || optionCase>6){
                    System.out.println("You can choose only Sixth...!");
                    checkOption = true;
                }
                else {
                    switch (optionCase){
                        case 1 ->{
                            System.out.println("==================== Booking Hall ======================");
//                            System.out.print("Enter your desired floor " + "(1" + (rowInHall > 1 ? "-" + totalSeatInRow : "" ) + "): " );
//                            rows = menu.nextInt();
//                            if(rowInHall < totalSeatInRow || totalSeatInRow <= 0 ){
//                                System.out.println("--> Floor range start from " + "(1" + (rowInHall > 1 ? "-" + totalSeatInRow : "" ) + "): ");
//                                checkOption = true;
//                            }
                        }
                        case 2 -> {
                            System.out.println("Hello");
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
                System.out.println("You can not input text, number only");
                checkOption = true;
            }
        }while(checkOption);

    }
    public static void main(String[] args) {
        Title.displayTitle();
        configHall();
        menuOption();
    }



}