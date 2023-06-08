package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.ProvidersModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DataGenerateProvider {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateProvider(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateProviders(int i) throws SQLException {
        ProvidersModel providersModel = new ProvidersModel(dbName, userName, password);

        int providerType;

        int randomIndex;
        String providerCompanyName;

        String providerLastname;
        String providerFirstname;

        do {
            providerType = selectRandomProviderType();

            randomIndex = selectRandomIndex(15);
            providerCompanyName = selectRandomProviderCompanyName(randomIndex);

            providerLastname = selectRandomProviderLastname();
            providerFirstname = selectRandomProviderFirstname();
        }while(providersModel.providerExists(providerFirstname, providerLastname, providerCompanyName, providerType));

        String providerDescription = selectRandomProviderDescription(randomIndex);
        int providerCost = selectRandomProviderCost(50, 130);
        int providerNumberOfEventsOrganised = selectRandomProviderNumberOfEventsOrganised();

        String sql = "INSERT INTO PROVIDERS(Firstname, Lastname, Type, Provider_compagny_name, Cost, Description, Number_of_events_organised) VALUES (?, ?, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement pstmt = providersModel.getConnection().prepareStatement(sql);

            pstmt.setString(1, providerFirstname);
            pstmt.setString(2, providerLastname);
            pstmt.setInt(3, providerType);
            pstmt.setString(4, providerCompanyName);
            pstmt.setInt(5, providerCost);
            pstmt.setString(6, providerDescription);
            pstmt.setInt(7, providerNumberOfEventsOrganised);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int selectRandomProviderType() {
        int random = (int) (Math.random() * 3);
        return random;
    }

    private int selectRandomProviderCost(int min, int max) {
        int random = (int) (Math.random() * (max - min + 1) + min);
        return random;
    }

    private int selectRandomProviderNumberOfEventsOrganised() {
        int random = (int) (Math.random() * 25);
        return random;
    }

    private int selectRandomIndex(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    private String selectRandomProviderCompanyName(int randomIndex) {
        String[] providerCompanyNamesArray = {
                "CUISINE DELIGHTS", "GOURMET GRILL", "SIZZLING CHEFS", "KITCHEN KINGS", "FOODIE FANTASIA", "FLAVOURFUL FEASTS", "EPICUREAN EATERY",
                "COOK'S CASTLE", "DELICIOUS DISHES", "CULINARY CREATIONS", "PALATE PLEASERS", "GASTRONOMIC GOODS", "FOOD FUSION", "DELIGHTFUL DINING", "CHEF'S CHOICE",
                "DISHY DESIGNS", "TASTE TREASURES", "SAVORY SPECIALTIES", "EXQUISITE EATS", "DELECTABLE DELIGHTS", "FOOD FANTASY", "PALATE PARADISE", "DISH DELIGHTS",
                "TASTY TREATS", "GOURMET GALORE", "FEASTFUL FLAVOURS", "CULINARY CRAFT", "PANTRY PROS", "KITCHEN KREATIONS", "FOODIE'S FEST"
        };

        return providerCompanyNamesArray[randomIndex];
    }

    private String selectRandomProviderDescription(int randomIndex){

        /*
            METTRE LES DESCRIPTIONS EN ANGLAIS
         */

        String[] providerDescriptionsArray = {
                "Création de repas délicieux avec une touche d'authenticité.",
                "Pionniers de la cuisine gourmet et des recettes innovantes.",
                "Ustensiles de cuisine de qualité pour le chef moderne.",
                "Expériences culinaires exquises pour les passionnés de cuisine.",
                "Fourniture des meilleurs ingrédients pour vos chefs-d'œuvre culinaires.",
                "Livraison de joie à travers la nourriture, une assiette à la fois.",
                "Votre partenaire dans la cuisine - faire de la cuisine un plaisir.",
                "Créations savoureuses, cuisinées à la perfection.",
                "Ingrédients frais et savoureux pour votre cuisine.",
                "Plats délicieux, préparés avec amour et passion.",
                "Passionné par la fourniture d'expériences culinaires exceptionnelles.",
                "Maîtrise de l'art de la cuisine gourmet.",
                "Qualité inégalée dans les ustensiles de cuisine et les fournitures de cuisine.",
                "Transformation de simples ingrédients en plats extraordinaires.",
                "Rendre les repas mémorables avec nos offres gourmet.",
                "Votre partenaire de confiance dans tout ce qui est culinaire.",
                "Améliorer votre expérience culinaire avec nos produits premium.",
                "Pour l'amour de la nourriture et de la cuisine.",
                "Vous apporte les saveurs les plus fines du monde entier.",
                "Dédié à fournir des solutions culinaires de qualité.",
                "Pour le cuisinier moderne - ustensiles de cuisine pratiques et élégants.",
                "Apporter la joie de cuisiner dans tous les foyers.",
                "Pour le chef en vous - fournitures de cuisine de haute qualité.",
                "Cuisiner devient facile et amusant avec nos produits innovants.",
                "Propager le bonheur, un plat à la fois.",
                "Votre source de référence pour la fine cuisine et les innovations culinaires.",
                "Soutenir votre voyage culinaire avec nos produits de qualité.",
                "Célébrer la joie de la nourriture et de la cuisine.",
                "Fournir une qualité et un service inégalés dans l'industrie culinaire.",
                "Améliorer l'expérience culinaire avec notre sélection gourmet."
        };

        return providerDescriptionsArray[randomIndex];
    }

    private String selectRandomProviderLastname(){
        String[] providerLastnameArray = {
                "RAMSAY", "CHILD", "BOCUSE", "LAWSON", "OLIVER", "STEWART", "RAY", "BATALI",
                "HERMÉ", "KELLER", "DUCASSE", "ROBUCHON", "FIERI", "BOULUD", "PUCK", "BLUMENTHAL",
                "REDZEPI", "ADRIÀ", "BOTTURA", "BOURDAIN", "CRENN", "PÉPIN", "AUGUSTE", "SAFFITZ",
                "OTTOLENGHI", "PIERREWHITE", "FLAY", "DE LAURENTIIS", "GARTEN", "SAMUELSSON"
        };

        int randomIndex = selectRandomIndex(15);
        return providerLastnameArray[randomIndex];
    }

    private String selectRandomProviderFirstname(){
        String[] providerLastnameArray = {
                "Gordon", "Julia", "Paul", "Nigella", "Jamie", "Martha", "Rachel", "Mario", "Pierre",
                "Thomas", "Alain", "Joël", "Guy", "Daniel", "Wolfgang", "Heston", "René",
                "Ferran", "Massimo", "Anthony", "Dominique", "Jacques", "Georges",
                "Claire", "Yotam", "Marco", "Bobby", "Giada", "Ina", "Marcus"
        };

        int randomIndex = selectRandomIndex(15);
        return providerLastnameArray[randomIndex];
    }
}
