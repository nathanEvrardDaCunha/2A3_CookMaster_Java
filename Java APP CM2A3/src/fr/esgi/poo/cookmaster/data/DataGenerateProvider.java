package fr.esgi.poo.cookmaster.data;

import fr.esgi.poo.cookmaster.model.ProvidersModel;
import fr.esgi.poo.cookmaster.tools.CommonDataGenerator;
import fr.esgi.poo.cookmaster.tools.CommonSettings;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataGenerateProvider {

    private static final int PROVIDER_MIN_TYPE = 0;
    private static final int PROVIDER_MAX_TYPE = 3;
    private static final int RANDOM_INDEX = 0;
    private static final int PROVIDER_COST_MIN = 100;
    private static final int PROVIDER_COST_MAX = 300;
    private static final int PROVIDER_EVENT_MIN = 2;
    private static final int PROVIDER_EVENT_MAX = 50;

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateProvider(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateProviders() throws SQLException {
        ProvidersModel providersModel = new ProvidersModel(dbName, userName, password);

        int providerType;

        int randomIndex;
        String providerCompanyName;

        String providerLastname;
        String providerFirstname;

        do {
            providerType = CommonDataGenerator.selectRandomInt(PROVIDER_MIN_TYPE, PROVIDER_MAX_TYPE);

            randomIndex = CommonDataGenerator.selectRandomInt(RANDOM_INDEX, CommonSettings.ALL_ARRAY_SIZE);
            providerCompanyName = selectRandomProviderCompanyName(randomIndex);

            providerLastname = CommonDataGenerator.selectRandomLastname();
            providerFirstname = CommonDataGenerator.selectRandomFirstname();
        }while(providersModel.providerExists(providerFirstname, providerLastname, providerCompanyName, providerType));

        String providerDescription = selectRandomProviderDescription(randomIndex);
        int providerCost = CommonDataGenerator.selectRandomInt(PROVIDER_COST_MIN, PROVIDER_COST_MAX);
        int providerNumberOfEventsOrganised = CommonDataGenerator.selectRandomInt(PROVIDER_EVENT_MIN, PROVIDER_EVENT_MAX);

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
}
