package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.SubscriptionsModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DataGenerateSubscription {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateSubscription(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    public void generateSubscriptions(int i) throws SQLException {

        int subscriptionFrequencyOfCost = selectSubscriptionFrequencyOfCost();
        int randomIndex = selectRandomIndex();
        int subscriptionType = selectSubscriptionType(randomIndex);
        double subscriptionCost = selectSubscriptionCost(randomIndex, subscriptionFrequencyOfCost);

        String subscriptionTitle = selectSubscriptionTitle(randomIndex);
        String subscriptionDescription = selectSubscriptionDescription(randomIndex);
        String sql = "INSERT INTO SUBSCRIPTIONS(Title, Cost, Type, Description, Frequency_of_cost) VALUES (?, ?, ?, ?, ?)";

        SubscriptionsModel subscriptionsModel = new SubscriptionsModel(dbName, userName, password);

        try {
            PreparedStatement pstmt = subscriptionsModel.getConnection().prepareStatement(sql);

            pstmt.setString(1, subscriptionTitle);
            pstmt.setDouble(2, subscriptionCost);
            pstmt.setInt(3, subscriptionType);
            pstmt.setString(4, subscriptionDescription);
            pstmt.setInt(5, subscriptionFrequencyOfCost);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int selectSubscriptionFrequencyOfCost() {
        int[] subscriptionFrequencyOfCostsArray = {
                30, 365
        };

        Random random = new Random();
        int randomIndex = random.nextInt(subscriptionFrequencyOfCostsArray.length);

        return subscriptionFrequencyOfCostsArray[randomIndex];
    }

    private int selectRandomIndex() {
        int[] subscriptionTypesArray = {
                0, 1, 2
        };

        Random random = new Random();
        int randomIndex = random.nextInt(subscriptionTypesArray.length);

        return randomIndex;
    }

    private int selectSubscriptionType(int randomIndex) {
        int[] subscriptionTypesArray = {
                0, 1, 2
        };

        return subscriptionTypesArray[randomIndex];
    }

    private double selectSubscriptionCost(int randomIndex, int subscriptionFrequencyOfCost) {
        double[] subscriptionCostsMonthsArray = {
                0, 9.9, 19
        };

        double[] subscriptionCostsYearsArray = {
                0, 113, 220
        };

        if (subscriptionFrequencyOfCost == 30) {
            return subscriptionCostsMonthsArray[randomIndex];
        } else if (subscriptionFrequencyOfCost == 365) {
            return subscriptionCostsYearsArray[randomIndex];
        } else {
            return 0;
        }
    }

    private String selectSubscriptionTitle(int randomIndex) {
        String[] subscriptionTitlesArray = {
                "Free", "Starter", "Master"
        };

        return subscriptionTitlesArray[randomIndex];
    }

    private String selectSubscriptionDescription(int randomIndex) {
        String[] subscriptionDescriptionsArray = {
                "Free: Cette offre est idéale pour les personnes souhaitant tester nos services sans engagement. Elle offre des fonctionnalités limitées et un accès limité à nos événements.",
                "Starter: Un abonnement conçu pour ceux qui commencent à participer régulièrement à nos événements. Il offre un accès plus large à nos événements, et des réductions sur certains services.",
                "Master: Notre abonnement haut de gamme pour les personnes les plus dévouées. Offre un accès illimité à tous nos événements, des réductions spéciales, et un service client prioritaire."
        };

        return subscriptionDescriptionsArray[randomIndex];
    }
}
