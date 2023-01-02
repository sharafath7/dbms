import java.util.Locale;
import java.util.Scanner;
import java.sql.*;
class SQL_to_Java {
    static Scanner sc = new Scanner(System.in);
    static String userName;
    static String passWord;
    static void start() {
        System.out.println("Enter your UserName");
        userName= sc.nextLine();
        System.out.println("Enter your password");
        passWord=sc.nextLine();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306",userName,passWord);
        } catch (Exception e) {
            e.printStackTrace();
            start();
        }
    }
    static void use() throws SQLException {
        start();
        local create = new local(userName,passWord);
        System.out.println("Enter /h to help and bye to end ");
        System.out.print("SQL--> ");
        String option = sc.nextLine();
        while (!option.equals("bye")) {
            if (option.equalsIgnoreCase("show databases")) {
                create.showDatbases();
            }


            else if (option.equals("/h")) {
                System.out.println("Enter show databases to see all the databases in your sql");
                System.out.println("Enter create database to create the databases in your sql");
                System.out.println("Enter drop database name to delete the databases in your sql");
                System.out.println("Enter use database name to use the databases in your sql");
                System.out.println("Enter create table name to create table in the database you use in your sql");
                System.out.println("Enter show tables  to see table in the database you use in your sql");
                System.out.println("Enter describe table name to describe table in the database you use in your sql");
                System.out.println("Enter insert into table name to create table in the database you use in your sql");
                System.out.println("Enter select * from table see values in table in the database you use in your sql");
                System.out.println("Enter update table name to update table in the database you use in your sql");
            }



            else if (option.split(" ")[0].equalsIgnoreCase("CREATE") && option.split(" ")[1].equalsIgnoreCase("DATABASE") ){
                if (option.split(" ").length==3){
                    System.out.println(create.alter(option.split(" ")[2],1));
                }
                else {
                    System.out.println("Check your syntax.");
                }
            }


            else if (option.split(" ")[0].equalsIgnoreCase("DROP") && option.split(" ")[1].equalsIgnoreCase("DATABASE") ){
                if (option.split(" ").length==3){
                    System.out.println(create.alter(option.split(" ")[2],2));
                }
                else {
                    System.out.println("Check your syntax.");
                }
            }


            else if (option.split(" ")[0].equalsIgnoreCase("USE")){
                if (option.split(" ").length==2){
                    System.out.println(create.useage(option.split(" ")[1]));
                }else {
                    System.out.println("Check your syntax.");
                }
            }


            else if (option.split(" ")[0].equalsIgnoreCase("CREATE") && option.split(" ")[1].equalsIgnoreCase("TABLE") ){
                create.createTable(option);
            }


            else if (option.equalsIgnoreCase("show tables")) {
                create.showTable();
            }


            else if (option.split(" ")[0].equalsIgnoreCase("Describe")){
                if (option.split(" ").length==2){
                    create.descTable(option.split(" ")[1]);
                }
                else {
                    System.out.println("Check your syntax.");
                }
            }


            else if (option.split(" ")[0].equalsIgnoreCase("INSERT") && option.split(" ")[1].equalsIgnoreCase("INTO") ){
                create.insertIntoTable(option);
            }



            else if (option.split(" ")[0].equalsIgnoreCase("SELECT") &&option.split(" ")[1].equalsIgnoreCase("*") &&option.split(" ")[2].equalsIgnoreCase("FROM")) {
                create.select(option.split(" ")[3]);
            }


            else if (option.split(" ")[0].equalsIgnoreCase("UPDATE")) {
                create.update(option);
            }



            else {
                System.out.println("Check your syntax.");
            }
            System.out.print("SQL--> ");
            option = sc.nextLine();
        }
    }


    public static void main(String[] args) throws SQLException {
        use();
    }
}