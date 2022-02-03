package dataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;

public class ContactDAO  {

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String USER = "root";
    static final String PASS = "1234";
    static final String serverName = "localhost";
    static final String dbms = "mysql";
    static final String portNumber = "3306";
    static final String dataBaseName = "addressbook";
    static final String utf8 = "?characterEncoding=utf8";
    static final String tableNAme = "contact";
    static Connection con;
    static Statement st;

//    "jdbc:mysql://localhost:3306/addressbook?characterEncoding=utf8","root","1234"

    static void connection() {
        try {
            Class.forName(DRIVER);
            Properties connectionProps = new Properties();
            connectionProps.put("user", USER);
            connectionProps.put("password", PASS);
            con = DriverManager.getConnection("jdbc:" + dbms + "://" +
                            serverName +
                            ":" + portNumber + "/" + dataBaseName + utf8,
                    connectionProps);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't Connect to database");
        }
    }

    static void ends() {
        try {
            con.close();
        }catch (SQLException e){
            System.out.println("Can't quit");
        }
    }


    public static List<ContactPerson> getContacts() {
        List<ContactPerson> selectSet = new ArrayList<>();
        try {
            String selectAllQuery = "select * from " + tableNAme;
            Statement st = con.createStatement();
            ResultSet select = st.executeQuery(selectAllQuery);
            while(select.next()) {
                int id = select.getInt("id");
                String name = select.getString("name");
                String nick_name = select.getString("nick_name");
                String email = select.getString("email");
                String cell_phone = select.getString("cell_phone");
                ContactPerson obj = new ContactPerson(id, name, nick_name, email,cell_phone);
                selectSet.add(obj);
            }
            select.close();
            return selectSet;
        } catch (SQLException e)
        {
            System.out.println("Can't execute fetching data");
            return null;
        }
    }

    static void deleteData(int id) throws SQLException {
        try {
            String deleteQuery = "delete from contact where id=" + id + ";";
            Statement st = con.createStatement();
            st.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't delete data");
        }
    }

    static void InsertData(ContactPerson insert) throws SQLException{
        try {
            String insertQuery = "insert into contact (id, name, nick_name, cell_phone, email) VALUES ('" +
                    insert.getId() + "','" +
                    insert.getName() + "','" +
                    insert.getNick_name() + "','" +
                    insert.getCell_phone() + "','" +
                    insert.getEmail() + "')" + ";";
            Statement st = con.createStatement();
            st.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't Insert data, try another id");
        }
    }

    static void updateData(ContactPerson update) throws SQLException{
        try {
            String updateQuery = "update contact set " +
                    "name = '" + update.getName() + "''," +
                    "nick_name = '" + update.getNick_name() + "'," +
                    "cell_phone = '" + update.getCell_phone() + "'," +
                    "email = '" + update.getEmail() + " where id = " + update.getId() + ";";
            Statement st = con.createStatement();
            st.executeUpdate(updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't update data");
        }
    }

}

