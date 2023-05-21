package fr.esgi.poo.cookmaster.model;

public class PrintModel extends Model{

    public PrintModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "print";
    }
}
