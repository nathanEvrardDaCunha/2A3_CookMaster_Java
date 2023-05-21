package fr.esgi.poo.cookmaster.model;

public class GenerateModel extends Model{
    public GenerateModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    @Override
    public String getTableName() {
        return "generate";
    }
}
