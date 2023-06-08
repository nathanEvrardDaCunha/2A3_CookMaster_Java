package fr.esgi.poo.cookmaster.main;

import fr.esgi.poo.cookmaster.model.EventsLocationModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DataGenerateEventLocation {

    private final String dbName;
    private final String userName;
    private final String password;

    public DataGenerateEventLocation(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }

    void generateEventLocations(int i) throws SQLException{
        int eventLocationCost = selectRandomEventLocationCost(0, 1300);
        String eventLocationAddress = selectRandomEventLocationAddress();
        String eventLocationPostalCode = selectRandomEventLocationPostalCode();
        String eventLocationCity = selectRandomEventLocationCity();

        String sql = "INSERT INTO EVENT_LOCATIONS(Address, Postal_code, City, Cost) VALUES (?, ?, ?, ?)";

        EventsLocationModel eventsLocationModel = new EventsLocationModel(dbName, userName, password);

        try {
            PreparedStatement pstmt = eventsLocationModel.getConnection().prepareStatement(sql);

            pstmt.setString(1, eventLocationAddress);
            pstmt.setString(2, eventLocationPostalCode);
            pstmt.setString(3, eventLocationCity);
            pstmt.setInt(4, eventLocationCost);


            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int selectRandomEventLocationCost(int min, int max){
        return (int) (Math.random() * (max - min)) + min;
    }

    private int selectRandomIndex(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private String selectRandomEventLocationAddress(){
        String[] eventLocationArray = {
                "Rue des Orfèvres", "Avenue de la République", "Boulevard de la Liberté",
                "Rue des Dames", "Avenue du Général Leclerc", "Boulevard Saint-Germain",
                "Rue de la Paix", "Avenue de Wagram", "Boulevard Haussmann",
                "Rue du Bac", "Avenue des Champs-Élysées", "Boulevard Montmartre",
                "Rue de Rivoli", "Avenue de l'Opéra", "Boulevard Saint-Michel",
                "Rue du Faubourg Saint-Antoine", "Avenue Foch", "Boulevard de Magenta",
                "Rue de Vaugirard", "Avenue George V", "Boulevard Malesherbes",
                "Rue de Passy", "Avenue Montaigne", "Boulevard de la Chapelle",
                "Rue La Fayette", "Avenue de la Bourdonnais", "Boulevard de Sébastopol",
                "Rue du Cherche-Midi", "Avenue de Clichy", "Boulevard de la Villette",
                "Rue Saint-Antoine", "Avenue de Friedland", "Boulevard de la Madeleine"
        };

        int randomIndex = selectRandomIndex(0, 15);
        return eventLocationArray[randomIndex] + " " + selectRandomIndex(0, 150);
    }

    private String selectRandomEventLocationCity() {

        /*
            Faire en sorte que la ville soit bien dans le bon code postal
         */

        String[] eventCityArray = {
                "Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Montpellier",
                "Strasbourg", "Bordeaux", "Lille", "Rennes", "Reims", "Le Havre", "Saint-Étienne",
                "Toulon", "Grenoble", "Dijon", "Angers", "Nîmes", "Villeurbanne", "Le Mans",
                "Aix-en-Provence", "Clermont-Ferrand", "Brest", "Limoges", "Tours", "Amiens",
                "Perpignan", "Metz", "Besançon", "Boulogne-Billancourt", "Orléans", "Mulhouse",
                "Rouen", "Caen", "Nancy", "Saint-Denis", "Saint-Denis", "Argenteuil", "Montreuil",
                "Roubaix", "Dunkerque", "Tourcoing", "Nanterre", "Avignon", "Créteil", "Poitiers",
                "Versailles", "Courbevoie", "Vitry-sur-Seine", "Colombes", "Pau", "Aulnay-sous-Bois",
                "Asnières-sur-Seine", "Rueil-Malmaison", "Saint-Pierre", "Antibes", "Saint-Maur-des-Fossés",
                "Champigny-sur-Marne", "La Rochelle", "Aubervilliers", "Calais", "Cannes", "Le Tampon",
                "Béziers", "Colmar", "Bourges", "Drancy", "Mérignac", "Saint-Nazaire", "Valence",
                "Ajaccio", "Issy-les-Moulineaux", "Noisy-le-Grand", "Villeneuve-d'Ascq", "Quimper",
                "Antony", "Troyes", "Neuilly-sur-Seine", "La Seyne-sur-Mer", "Les Abymes", "Lorient",
                "Sarcelles", "Pessac", "Ivry-sur-Seine", "Cergy", "Clichy", "Niort", "Chambéry", "Montauban",
                "Vénissieux", "Beauvais", "Hyères", "Charleville-Mézières", "Cholet", "Chelles", "Meaux",
                "Épinay-sur-Seine", "Saint-André", "La Roche-sur-Yon", "Bondy", "Levallois-Perret", "Issy-les-Moulineaux",
                "Évry-Courcouronnes", "Arles", "Valenciennes", "Cagnes-sur-Mer", "Bobigny", "Corbeil-Essonnes",
                "Saint-Quentin", "Pantin", "Maisons-Alfort", "Chalon-sur-Saône", "Meudon", "Fontenay-sous-Bois",
                "Châteauroux", "Saint-Joseph", "Narbonne", "Saint-Louis", "Saint-Paul", "Albi", "Villejuif",
        };

        int randomIndex = selectRandomIndex(0, 15);
        return eventCityArray[randomIndex];
    }

    private String selectRandomEventLocationPostalCode(){
        return String.format("%05d", selectRandomIndex(10000, 100000));
    }
}
