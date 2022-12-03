import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class App {

    public static void main(String[] args) {
        Connection con = connect();
        // String currentDirectory = System.getProperty("user.dir");
        // fileReader(this.currentDirectory);
        // csvReader(currentDirectory);
        // insertUserToDB(con);
        List<User> userList = readUserFromDB(con);
        userList = sortUserList(userList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        for (User user : userList) {
            try {
                String json = ow.writeValueAsString(user);
                System.out.println(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<User> sortUserList(List<User> userList) {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getMrms_username().compareTo(u2.getMrms_username());
            }
        });
        return userList;
    }

    public static List<User> readUserFromDB(Connection con) {
        List<User> userList = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            String stringQuery = "SELECT * FROM mrms_user";
            ResultSet result = stmt.executeQuery(stringQuery);
            while (result.next()) {
                User user = new User();
                user.setMrms_user_id(result.getInt(1));
                user.setMrms_username(result.getString(2));
                user.setMrms_password(result.getString(3));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static void insertUserToDB(Connection con) {
        try {
            String newUsername = "bambang";
            String newPassword = "12345";
            int userLength = readUserFromDB(con).size();
            String stringQuery = "INSERT INTO mrms_user (mrms_user_id,mrms_username,mrms_password) VALUES (?,?,?);";
            PreparedStatement ps = con.prepareStatement(stringQuery);

            // set the preparedstatement parameters
            ps.setInt(1, (userLength + 1));
            ps.setString(2, newUsername);
            ps.setString(3, newPassword);

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }

    public static Connection connect() {
        Connection conn = null;
        final String url = "jdbc:postgresql://localhost/mrms";
        final String user = "postgres";
        final String password = "";
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void csvReader(String currentDirectory) {
        CSVReader cr = new CSVReader(currentDirectory);
        List<Industry> industryList = cr.readCSVAsIndustryList("csv-sample.csv");
        System.out.println("Halllo" + industryList.size());
        for (Industry i : industryList) {
            try {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(i);
                System.out.println(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileReader(String currentDirectory) {
        FileReadWrite<Integer> rw = new FileReadWrite<>(currentDirectory);
        List<Integer> inputList = new ArrayList<>();
        inputList.add(3);
        inputList.add(1);
        inputList.add(2);
        inputList = rw.sortIntegerList(inputList);

        rw.writeFile(inputList, "output.txt");
        rw.readFile("output.txt");
        rw.duplicateFile("output.txt", "output2.txt");
    }
}
