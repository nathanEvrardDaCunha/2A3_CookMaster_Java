package fr.esgi.cookmaster_java_2a3.model;

import java.sql.*;

public class EquipmentsModel extends Model {

    private ResultSet equipment;

    public EquipmentsModel(String dbName, String userName, String password) {
        super(dbName, userName, password);
    }

    public void loadEquipmentById(int id) {
        try {
            ResultSet resultSet = this.executeQuery("SELECT * FROM equipments WHERE Id = " + id);
            if (resultSet.next()) {
                this.equipment = resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getEquipmentId() {
        try {
            return this.equipment.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getEquipmentTitle() {
        try {
            return this.equipment.getString("Title");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getEquipmentType() {
        try {
            return this.equipment.getInt("Type");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getEquipmentCost() {
        try {
            return this.equipment.getInt("Cost");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getEquipmentBrand() {
        try {
            return this.equipment.getString("Brand");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getEquipmentEventId() {
        try {
            return this.equipment.getInt("Id_1");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int equipmentCostExist(String equipmentTitle, String equipmentBrand) throws SQLException {
        String query ="SELECT Cost FROM equipments WHERE Title = ? AND Brand = ?";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, equipmentTitle);
        stmt.setString(2, equipmentBrand);

        ResultSet rs = stmt.executeQuery();
        return rs.getInt("Cost");
    }

    public int equipmentTypeExist(String equipmentTitle, String equipmentBrand) throws SQLException {
        String query ="SELECT Type FROM equipments WHERE Title = ? AND Brand = ?";
        PreparedStatement stmt = getConnection().prepareStatement(query);
        stmt.setString(1, equipmentTitle);
        stmt.setString(2, equipmentBrand);

        ResultSet rs = stmt.executeQuery();
        return rs.getInt("Type");
    }

    @Override
    public String getTableName() {
        return "equipments";
    }
}
