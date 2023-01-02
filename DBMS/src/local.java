import java.sql.*;
import java.util.ArrayList;

public class local {
    String userName ;
    String passWord;
    String mysqlUrl = "jdbc:mysql://localhost:3306";
    local(String userName , String passWord){
        this.userName=userName;
        this.passWord=passWord;
    }

    public void select(String toGet){

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(mysqlUrl,userName,passWord);
            Statement sat = conn.createStatement();
            ResultSet rset = sat.executeQuery("Describe "+toGet);
            int count = 0;
            ArrayList<String> arrayList = new ArrayList<>();
            while (rset.next()){
                arrayList.add(rset.getString(1));
                count++;
            }
            int space=20;
            Connection con = DriverManager.getConnection(mysqlUrl,userName,passWord);
            Statement sat1 = conn.createStatement();
            ResultSet rs = sat1.executeQuery("Select * from "+toGet);
            System.out.println("");
            while (rs.next())
            {
                for (int i=0; i<count; i++){
                    if(rs.getString(arrayList.get(i)).length()>space){
                        space=rs.getString(arrayList.get(i)).length();
                    }
                }
            }
            Statement st1 = conn.createStatement();
            ResultSet rs2 = st1.executeQuery("Select * from "+toGet);
            System.out.println("+"+"-".repeat((space+2)*count)+"+");
            System.out.print("| ");
            for (int i=0; i<count; i++){
                System.out.printf("%-"+space+"s |",(arrayList.get(i)));
            }
            System.out.println("");
            System.out.println("+"+"-".repeat((space+2)*count)+"+");
            while (rs2.next())
            {
                System.out.print("| ");
                for (int i=0; i<count; i++){
                    System.out.printf("%-"+space+"s |",rs2.getString(arrayList.get(i)));
                }
                System.out.println("");
            }
            System.out.println("+"+"-".repeat((space+2)*count)+"+");
            sat1.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public String alter(String name, int toExcute){
        try{
            Connection conn = DriverManager.getConnection(mysqlUrl,userName,passWord);
            Statement stmt = conn.createStatement();
            if (toExcute==1){
                String sql = "CREATE DATABASE "+name ;
                stmt.executeUpdate(sql);
                return ("Database created successfully...");
            } else if (toExcute==2) {
                String sql = "DROP DATABASE "+name;
                stmt.executeUpdate(sql);
                return ("Database dropped successfully...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Sorry , something went wrong";
    }

    public String useage(String option) throws SQLException {

        Connection con = DriverManager.getConnection(mysqlUrl,userName,passWord);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Show Databases");
        while(rs.next()) {
            if (rs.getString(1).equals(option)){
                mysqlUrl="jdbc:mysql://localhost:3306/"+option;
                return "Database changed.";
            }
        }return "Try again.";
    }




    public void showDatbases() {
        try {
            Connection con = DriverManager.getConnection(mysqlUrl,userName,passWord);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Show Databases");
            int a= 25;
            ArrayList<String> arrayList = new ArrayList<>();
            while(rs.next()) {
                if (rs.getString(1).length()>a){
                    a=rs.getString(1).length();
                }
                arrayList.add(rs.getString(1));
            }
            System.out.println("+"+"-".repeat(a+2)+"+");
            System.out.printf("| %-"+a+"s | %n","List of databases: ");
            System.out.println("+"+"-".repeat(a+2)+"+");
            for (int i=0; i<arrayList.size(); i++){
                System.out.printf("| %-"+a+"s | %n",arrayList.get(i));
            }
            System.out.println("+"+"-".repeat(a+2)+"+");
        }catch (Exception e){
            System.out.println("Sorry, something went wrong");
        }
    }
    public void descTable(String toShow){
        try {

            Connection con = DriverManager.getConnection(mysqlUrl,userName,passWord);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Describe "+toShow);
            System.out.println(" ");
            System.out.println("List of Tables: ");
            System.out.println("+------------------------------------------------------------------------------------------------------+");
            System.out.printf("| %-20s | %-15s | %-8s | %-10s | %-12s | %-20s | %n","Field","Type","Null","Key","Default","Extra");
            System.out.println("+------------------------------------------------------------------------------------------------------+");
            int count=0;
            while(rs.next()) {
                count++;
                System.out.printf("| %-20s | %-15s | %-8s | %-10s | %-12s | %-20s | %n",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            }
            System.out.println("+------------------------------------------------------------------------------------------------------+");
            System.out.println(count);
            System.out.println(" ");
        }catch (Exception e){
            System.out.println(e);
        }
    }



    public void createTable(String toDo){
        try{
            Connection conn = DriverManager.getConnection(mysqlUrl,userName,passWord);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(toDo);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void showTable(){
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            Connection con = DriverManager.getConnection(mysqlUrl,userName,passWord);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Show tables");
            int a=20;
            while(rs.next()) {
                arrayList.add(rs.getString(1));
                if (rs.getString(1).length()>a){
                    a=rs.getString(1).length();
                }
            }
            System.out.println("+"+"-".repeat(a+2)+"+");
            System.out.printf("| %-"+a+"s | %n","Tables ");
            System.out.println("+"+"-".repeat(a+2)+"+");
            for (int i=0; i<arrayList.size(); i++){
                System.out.printf("| %-"+a+"s | %n",arrayList.get(i));
            }
            System.out.println("+"+"-".repeat(a+2)+"+");
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void update(String toUpt){
        try(Connection conn = DriverManager.getConnection(mysqlUrl,userName,passWord);
            Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(toUpt);
            System.out.println("Update succesfull.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoTable(String toAdd){
        try{
            Connection conn = DriverManager.getConnection(mysqlUrl,userName,passWord);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(toAdd);
            System.out.println("Inserted records into the table...");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
