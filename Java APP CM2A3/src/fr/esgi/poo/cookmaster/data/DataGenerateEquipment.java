package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.EquipmentsModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DataGenerateEquipment {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateEquipment(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateEquipments(int i) throws SQLException{

        String equipmentTitle = selectEquipmentTitle();
        String equipmentBrand = selectEquipmentBrand();

        int equipmentCost = selectEquipmentCost(equipmentTitle, equipmentBrand);
        int equipmentType = selectEquipmentType(equipmentTitle, equipmentBrand);

        String sql = "INSERT INTO EQUIPMENTS(Title, Type, Cost, Brand, Id_1) VALUES (?, ?, ?, ?, ?)";

        EquipmentsModel equipmentsModel = new EquipmentsModel(dbName, userName, password);

        try {
            PreparedStatement pstmt = equipmentsModel.getConnection().prepareStatement(sql);

            pstmt.setString(1, equipmentTitle);
            pstmt.setInt(2, equipmentType);
            pstmt.setInt(3, equipmentCost);
            pstmt.setString(4, equipmentBrand);
            pstmt.setInt(5, i);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int selectRandomIndex(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    private int selectEquipmentCost(String equipmentTitle, String equipmentBrand) {
        EquipmentsModel equipmentsModel = new EquipmentsModel(dbName, userName, password);
        try {
            return equipmentsModel.equipmentCostExist(equipmentTitle, equipmentBrand);
        } catch (SQLException e) {
            return selectRandomIndex(1000);
        }
    }

    private int selectEquipmentType(String equipmentTitle, String equipmentBrand) {
        EquipmentsModel equipmentsModel = new EquipmentsModel(dbName, userName, password);
        try {
            return equipmentsModel.equipmentTypeExist(equipmentTitle, equipmentBrand);
        } catch (SQLException e) {
            return selectRandomIndex(5);
        }
    }


    private String selectEquipmentTitle(){
        String[] equipmentTitlesArray = {
                "COUTEAU A TRANCHER", "COUTEAU A DECOUPER", "COUTEAU A PAIN", "COUTEAU DE CHEF", "COUTEAU D'OFFICE",
                "POELE", "CASSEROLE", "FAITOUT", "MOULE A GATEAU", "BATTEUR ELECTRIQUE", "ROBOT CULINAIRE",
                "PLAN DE TRAVAIL", "RAPE A FROMAGE", "PRESSE-AGRUMES", "MOULE A TARTES", "SALADIER",
                "THERMOMETRE DE CUISINE", "CUIT-VAPEUR", "GRILLE-PAIN", "BOUILLOIRE", "CAFETIERE",
                "FOUET", "PLAT A FOUR", "PLANCHA", "COUPE-LEGUMES", "MACHINE A PATES",
                "HACHOIR A VIANDE", "GRILL", "MIXEUR", "ESSOREUSE A SALADE", "SOUPIERE"
        };

        int randomIndex = selectRandomIndex(15);
        return equipmentTitlesArray[randomIndex];
    }

    private String selectEquipmentBrand(){
        String[] equipmentBrandsArray = {
                "KITCHENAID", "BREVILLE", "CUISINART", "HAMILTON BEACH", "WILFA", "BOSCH", "BLACK+DECKER",
                "THERMADOR", "LE CREUSET", "ALL-CLAD", "ZWILLING J.A. HENCKELS", "WMF", "GLOBAL",
                "VICTORINOX", "MAC KNIVES", "ZEROLL", "OXO", "WHIRLPOOL", "PANASONIC", "SHARP",
                "SMEG", "MOULINEX", "DE'LONGHI", "BRAUN", "PHILIPS", "RUSSELL HOBBS", "KRUPS",
                "SIEMENS", "AEG", "ELECTROLUX"
        };

        int randomIndex = selectRandomIndex(15);
        return equipmentBrandsArray[randomIndex];
    }


}
