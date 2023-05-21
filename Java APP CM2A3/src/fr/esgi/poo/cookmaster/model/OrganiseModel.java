package fr.esgi.poo.cookmaster.model;

public class OrganiseModel extends Model{

    public OrganiseModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "organise";
    }
}
