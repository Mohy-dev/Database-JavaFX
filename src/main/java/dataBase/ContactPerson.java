package dataBase;

import java.sql.Date;

public class ContactPerson {

    private int id;
    private String name;
    private String nick_name;
//    private String address;
//    private String home_phone;
//    private String work_phone;
    private String cell_phone;
    private String email;
//    private Date birthday;
//    private String web_site;
//    private String profession;

    public ContactPerson(int id,
    String name,
    String nick_name,
    String email,
    String cell_phone){
        this.id = id;
        this.name = name;
        this.nick_name = nick_name;
        this.email = email;
        this.cell_phone = cell_phone;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getNick_name(){
        return nick_name;
    }

    public String getEmail(){
        return email;
    }

    public String getCell_phone(){
        return cell_phone;
    }
}

