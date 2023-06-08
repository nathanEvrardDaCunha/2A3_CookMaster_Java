package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.ProductsModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGenerateProduct {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateProduct(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateProducts(int i) throws SQLException {

        int productCategory = selectRandomCategory();
        String productTitle = selectRandomTitle(productCategory);
        String productDescription = selectRandomDescription(productCategory);

        int productCost = selectRandomProductCost(productCategory);
        int productStockState = selectRandomStockState();

        String sql = "INSERT INTO PRODUCTS(Title, Cost, Description, Category, Stock_state) VALUES (?, ?, ?, ?, ?)";

        ProductsModel productsModel = new ProductsModel(dbName, userName, password);

        try {
            PreparedStatement pstmt = productsModel.getConnection().prepareStatement(sql);

            pstmt.setString(1, productTitle);
            pstmt.setInt(2, productCost);
            pstmt.setString(3, productDescription);
            pstmt.setInt(4, productCategory);
            pstmt.setInt(5, productStockState);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int selectRandomCategory(){
        int randomInt = (int) (Math.random() * 3);
        return randomInt;
    }

    private String selectRandomTitle(int productCategory){
        String[] typeOfKnifeTitle = {
                "Couteau de chef",
                "Couteau Santoku",
                "Couteau à pain",
                "Couteau à désosser",
                "Couteau d'office",
                "Couteau à éplucher",
                "Couteau à fromage",
                "Couteau à huitres",
                "Couteau à jambon",
                "Couteau à fileter"
        };

        String[] typeOfPanTitle = {
                "Poêle à frire",
                "Sautoir",
                "Casserole",
                "Marmite",
                "Wok",
                "Sauteuse",
                "Faitout",
                "Cocotte en fonte",
                "Poêle à crêpes",
                "Poêle à grillades"
        };

        String[] typeOfBakewareTitle = {
                "Moule à gâteau",
                "Moule à tarte",
                "Moule à muffins",
                "Plaque à biscuits",
                "Moule à pain",
                "Moule à brioche",
                "Moule à madeleines",
                "Moule à cannelés",
                "Moule à kouglof",
                "Moule à savarin"
        };

        if(productCategory == 0){
            int randomIndex = (int) (Math.random() * typeOfKnifeTitle.length);
            return typeOfKnifeTitle[randomIndex];
        } else if(productCategory == 1){
            int randomIndex = (int) (Math.random() * typeOfPanTitle.length);
            return typeOfPanTitle[randomIndex];
        } else {
            int randomIndex = (int) (Math.random() * typeOfBakewareTitle.length);
            return typeOfBakewareTitle[randomIndex];
        }
    }

    private String selectRandomDescription(int productCategory){
        String[] typeOfKnifeDescription = {
                "Couteau conçu pour une coupe précise et confortable.",
                "Couteau robuste, idéal pour la préparation quotidienne des aliments.",
                "Couteau professionnel pour une utilisation en cuisine.",
                "Couteau durable et tranchant pour une coupe efficace.",
                "Conçu pour faciliter le travail en cuisine.",
                "Couteau polyvalent pour diverses tâches culinaires.",
                "Couteau à la pointe fine pour une coupe détaillée.",
                "Manche ergonomique pour une prise en main confortable.",
                "Acier inoxydable pour une longue durée de vie.",
                "Couteau bien équilibré pour une utilisation facile et sûre."
        };

        String[] typeOfPanDescription = {
                "Poêle à revêtement antiadhésif pour une cuisson facile.",
                "Distribue la chaleur uniformément pour une cuisson optimale.",
                "Poignée résistante à la chaleur pour une manipulation sûre.",
                "Poêle durable pour une utilisation quotidienne.",
                "Idéale pour sauter, frire et cuire à la vapeur.",
                "Facile à nettoyer et va au lave-vaisselle.",
                "Conçu pour une performance culinaire supérieure.",
                "Capacité généreuse pour cuisiner pour toute la famille.",
                "Idéal pour une variété de plats.",
                "Base épaisse pour une meilleure rétention de la chaleur."
        };

        String[] typeOfBakewareDescription = {
                "Moule antiadhésif pour un démoulage facile.",
                "Distribue la chaleur uniformément pour une cuisson parfaite.",
                "Idéal pour la cuisson de diverses pâtisseries.",
                "Robuste et durable pour une utilisation à long terme.",
                "Facile à nettoyer et va au lave-vaisselle.",
                "Conçu pour une performance de cuisson supérieure.",
                "Moule à la capacité généreuse pour les grands gâteaux.",
                "Parfait pour le pain maison, les tartes, les muffins et plus encore.",
                "Convient à tous types de fours.",
                "Offre un excellent démoulage pour une présentation parfaite."
        };

        if(productCategory == 0){
            int randomIndex = (int) (Math.random() * typeOfKnifeDescription.length);
            return typeOfKnifeDescription[randomIndex];
        } else if(productCategory == 1){
            int randomIndex = (int) (Math.random() * typeOfPanDescription.length);
            return typeOfPanDescription[randomIndex];
        } else {
            int randomIndex = (int) (Math.random() * typeOfBakewareDescription.length);
            return typeOfBakewareDescription[randomIndex];
        }
    }

    private int selectRandomProductCost(int productCategory){
        if(productCategory == 0){
            int randomIndex = (int) (Math.random() * (50 - 10) + 10);
            return randomIndex;
        } else if(productCategory == 1){
            int randomIndex = (int) (Math.random() * (35 - 20) + 20);
            return randomIndex;
        } else {
            int randomIndex = (int) (Math.random() * (15 - 3) + 3);
            return randomIndex;
        }
    }

    private int selectRandomStockState(){
        int randomIndex = (int) (Math.random() * 200);
        return randomIndex;
    }
}
