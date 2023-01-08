package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import start.structure.RessourcesAccess;
import start.structure.stockage.Session;

import java.io.IOException;

public class VueChoixModeJeu {
    
    private static final VueJeu vueJeu = new VueJeu();

	public void affichageVueChoixModeJeu(Stage stage) throws IOException {
        ;
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1280, 720);
        scene.getStylesheets().add(String.valueOf(RessourcesAccess.class.getResource("css/style.css")));

        Label labelChoixModeJeu = new Label("Choix du mode de jeu");
        labelChoixModeJeu.getStyleClass().add("nameGame");
        labelChoixModeJeu.setLayoutX(125);
        labelChoixModeJeu.setLayoutY(40);
        labelChoixModeJeu.setFont(new javafx.scene.text.Font("Goldman", 40));
        labelChoixModeJeu.setTextFill(javafx.scene.paint.Color.WHITE);

        Button demarrerPartie = new Button("Mode Classic");
        demarrerPartie.getStyleClass().add("buttonEcran");
        demarrerPartie.setLayoutX(541);
        demarrerPartie.setLayoutY(350);

        Button demarrerInfinit = new Button("Mode Infini");
        demarrerInfinit.getStyleClass().add("buttonEcran");
        demarrerInfinit.setLayoutX(554);
        demarrerInfinit.setLayoutY(430);

        Button buttonRetour = new Button("Retour");
        buttonRetour.getStyleClass().add("buttonEcran");
        buttonRetour.setLayoutX(570);
        buttonRetour.setLayoutY(510);

        buttonRetour.setOnAction(event -> {
            VueMenu vueMenu = new VueMenu();
            vueMenu.demarrerMenu(stage);
        });

        demarrerPartie.setOnMouseClicked(event -> {
            try {
                vueJeu.demarrerJeu(stage, "Normal");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        demarrerInfinit.setOnMouseClicked(event -> {
            try {
                vueJeu.demarrerJeu(stage, "Infini");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        pane.getChildren().addAll(demarrerPartie, demarrerInfinit, buttonRetour, labelChoixModeJeu);
        
        stage.setScene(scene);
        stage.show();

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            System.out.println("Fermeture de Koala Rock");
            Label alerte = new Label("Voulez vous vraiment \n" + "quitter le jeu ?");
            alerte.getStyleClass().add("LabelError");
            alerte.setLayoutX(520);
            alerte.setLayoutY(250);

            Rectangle rectangle = new Rectangle();
            rectangle.setX(500);
            rectangle.setY(200);
            rectangle.setWidth(300);
            rectangle.setHeight(200);

            rectangle.setArcHeight(50);
            rectangle.setArcWidth(50);
            rectangle.setFill(Color.BLACK);
            rectangle.setEffect(new DropShadow(10, Color.WHITE));

            Button oui = new Button("Oui");
            oui.getStyleClass().add("btnGrey");
            oui.setLayoutX(520);
            oui.setLayoutY(325);

            Button non = new Button("Non");
            non.getStyleClass().add("btnRed");
            non.setLayoutX(720);
            non.setLayoutY(325);

            oui.setOnAction(e -> {
                System.out.println("Deconnexion de l'utilisateur");
                Session.getInstance().disconnect();
                System.out.println("Fermeture du jeu");
                System.exit(0);
            });
            non.setOnAction(e -> {
                pane.getChildren().removeAll(oui, non, rectangle, alerte);
            });
            pane.getChildren().addAll(rectangle, oui, non, alerte);
        });
        stage.setOnCloseRequest(event -> {
            event.consume();
            System.out.println("Fermeture de Koala Rock");
            Label alerte = new Label("Voulez vous vraiment \n" + "quitter le jeu ?");
            alerte.getStyleClass().add("LabelError");
            alerte.setLayoutX(520);
            alerte.setLayoutY(250);

            Rectangle rectangle = new Rectangle();
            rectangle.setX(500);
            rectangle.setY(200);
            rectangle.setWidth(300);
            rectangle.setHeight(200);

            rectangle.setArcHeight(50);
            rectangle.setArcWidth(50);
            rectangle.setFill(Color.BLACK);
            rectangle.setEffect(new DropShadow(10, Color.WHITE));

            Button oui = new Button("Oui");
            oui.getStyleClass().add("btnGrey");
            oui.setLayoutX(520);
            oui.setLayoutY(325);

            Button non = new Button("Non");
            non.getStyleClass().add("btnRed");
            non.setLayoutX(720);
            non.setLayoutY(325);

            oui.setOnAction(e -> {
                System.out.println("Deconnexion de l'utilisateur");
                Session.getInstance().disconnect();
                System.out.println("Fermeture du jeu");
                System.exit(0);
            });
            non.setOnAction(e -> {
                pane.getChildren().removeAll(oui, non, rectangle, alerte);
            });
            pane.getChildren().addAll(rectangle, alerte, oui, non);
        });
    }
}
