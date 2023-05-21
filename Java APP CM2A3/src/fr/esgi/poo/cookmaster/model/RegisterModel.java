package fr.esgi.poo.cookmaster.model;

public class RegisterModel extends Model{

    public RegisterModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "register";
    }
}
