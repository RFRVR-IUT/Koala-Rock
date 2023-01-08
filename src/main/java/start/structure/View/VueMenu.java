package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import start.structure.RessourcesAccess;
import start.structure.stockage.Session;

import java.io.IOException;
import java.util.Objects;

public class VueMenu {

    private VueChoixModeJeu vueChoixModeJeu = new VueChoixModeJeu();

    public void demarrerMenu(Stage stage) {
        stage.setTitle("Koala Rock");
        stage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        borderPane.setCenter(pane);

        pane.setStyle("-fx-border-color: green ;");
        borderPane.setStyle("-fx-background-color: transparent ;");


        Scene scene = new Scene(borderPane, 1280, 720);
        scene.getStylesheets().add(String.valueOf(RessourcesAccess.class.getResource("css/style.css")));

        stage.setScene(scene);
        stage.show();

/////////// BOUTON ///////////
        Label nameGame = new Label("Koala Rock");
        nameGame.getStyleClass().add("nameGame");
        nameGame.setLayoutX(355);
        nameGame.setLayoutY(100);

        Button demarrerPartie = new Button("Jouer");
        demarrerPartie.getStyleClass().add("buttonEcran");
        demarrerPartie.setLayoutX(590);
        demarrerPartie.setLayoutY(280);

        Button parametre = new Button("Paramètres");
        parametre.getStyleClass().add("buttonEcran");
        parametre.setLayoutX(555);
        parametre.setLayoutY(380);

        Button quitter = new Button("Quitter");
        quitter.getStyleClass().add("buttonEcran");
        quitter.setLayoutX(585);
        quitter.setLayoutY(480);


        ///////// IMAGE ///////////
        Label menuScreen = new Label();
        Image image = new Image(Objects.requireNonNull(RessourcesAccess.class.getResourceAsStream("menu/ImageMenu.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(720);
        imageView.setFitWidth(1280);
        menuScreen.setGraphic(imageView);

        Label labelError = new Label();
        labelError.getStyleClass().add("LabelError");
        labelError.setLayoutX(510);
        labelError.setLayoutY(250);

        ///////// COPYRIGHT ///////////
        Label copyRight = new Label("© 2023 Koala Rock");
        copyRight.getStyleClass().add("copyRight");
        copyRight.setLayoutX(1050);
        copyRight.setLayoutY(590);

        Label copyRightName = new Label("Réalisé par : Célyan, Joris, Killian, Simon, Valentin");
        copyRightName.getStyleClass().add("copyRightName");
        copyRightName.setLayoutX(922);
        copyRightName.setLayoutY(620);


        pane.getChildren().add(menuScreen);
        pane.getChildren().add(labelError);
        pane.getChildren().addAll(demarrerPartie);
        pane.getChildren().addAll(parametre, quitter);
        pane.getChildren().add(nameGame);
        pane.getChildren().addAll(copyRight, copyRightName);


        /**
         * Permet de lancer le jeu en mode classique
         * @param event
         */
        demarrerPartie.setOnMouseClicked(event -> {
            try {
                vueChoixModeJeu.affichageVueChoixModeJeu(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }); 

        /**
         * Permet d'ouvrir la fenêtre de paramétrage
         * @param event
         */
        parametre.setOnMouseClicked(event -> {
            VueParametre vueParametre = new VueParametre();
            try {
                vueParametre.affichageVueParametre(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        quitter.setOnMouseClicked(event -> {
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
