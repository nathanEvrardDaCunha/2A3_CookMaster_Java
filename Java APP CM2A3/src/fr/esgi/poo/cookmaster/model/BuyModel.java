package fr.esgi.poo.cookmaster.model;

public class BuyModel extends Model{

    public BuyModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "buy";
    }
}
