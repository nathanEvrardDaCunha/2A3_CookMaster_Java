package fr.esgi.poo.cookmaster.model;

public class RentModel extends Model{

    public RentModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "rent";
    }
}
