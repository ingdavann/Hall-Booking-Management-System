//import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Regex {
//
//    public static void main(String[] args) {
//        String regex = "\\\\d+[0-9]+";
//        System.out.println("Input:");
//        Scanner scanner = new Scanner(System.in);
//        String option = scanner.nextLine();
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(option);
//
//        if (matcher.matches()){
//            Integer integer = Integer.valueOf(option);
//            System.out.println(integer);
//            System.out.println("Number");
//        }
//        else {
//            System.out.println("Text");
//        }
//    }
//
//}

public class Regex{
    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age = age;
    }

    public Regex(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static void eat(){

    }

    public static void main(String[] args) {

    }
}


