package dataBase;


public class ContactPerson {

    private final int id;
    private final String name;
    private final String nick_name;
    private final String cell_phone;
    private final String email;

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

