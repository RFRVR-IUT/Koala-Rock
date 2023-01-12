package start.structure.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import start.structure.RessourcesAccess;
import start.structure.metier.entite.AuthPlayer;
import start.structure.metier.manager.PlayerManager;
import start.structure.stockage.Security;
import start.structure.stockage.Session;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class VueConnexion {


    public void affichageVueConnexion(Stage stage) throws IOException {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1280, 720);
        scene.getStylesheets().add(String.valueOf(RessourcesAccess.class.getResource("css/style.css")));

        // CONNEXION //
        Label label = new Label("Connexion");
        label.getStyleClass().add("labelConnexion");
        label.setLayoutX(200);
        label.setLayoutY(50);

        Label labelPseudo = new Label("Pseudo");
        labelPseudo.getStyleClass().add("LabelConnexionField");
        labelPseudo.setLayoutX(275);
        labelPseudo.setLayoutY(250);

        Label labelMotDePasse = new Label("Mot de passe");
        labelMotDePasse.getStyleClass().add("LabelConnexionField");
        labelMotDePasse.setLayoutX(275);
        labelMotDePasse.setLayoutY(350);

        TextField textFieldPseudo = new TextField();
        textFieldPseudo.getStyleClass().add("TextFieldConnexion");
        textFieldPseudo.setLayoutX(275);
        textFieldPseudo.setLayoutY(275);

        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("TextFieldConnexion");
        passwordField.setLayoutX(275);
        passwordField.setLayoutY(375);

        Button buttonConnexion = new Button("Se connecter");
        buttonConnexion.getStyleClass().add("btnGrey");
        buttonConnexion.setLayoutX(285);
        buttonConnexion.setLayoutY(450);

        // Inscription //
        Label labelInscription = new Label("Inscription");
        labelInscription.getStyleClass().add("labelConnexion");
        labelInscription.setLayoutX(720);
        labelInscription.setLayoutY(50);

        Label labelPseudoInscription = new Label("Pseudo");
        labelPseudoInscription.getStyleClass().add("LabelConnexionField");
        labelPseudoInscription.setLayoutX(790);
        labelPseudoInscription.setLayoutY(150);

        Label labelMotDePasseInscription = new Label("Mot de passe");
        labelMotDePasseInscription.getStyleClass().add("LabelConnexionField");
        labelMotDePasseInscription.setLayoutX(790);
        labelMotDePasseInscription.setLayoutY(250);

        Label labelMotDePasseInscription2 = new Label("Confirmation");
        labelMotDePasseInscription2.getStyleClass().add("LabelConnexionField");
        labelMotDePasseInscription2.setLayoutX(790);
        labelMotDePasseInscription2.setLayoutY(350);

        TextField textFieldPseudoInscription = new TextField();
        textFieldPseudoInscription.getStyleClass().add("TextFieldConnexion");
        textFieldPseudoInscription.setLayoutX(790);
        textFieldPseudoInscription.setLayoutY(175);

        PasswordField passwordFieldInscription = new PasswordField();
        passwordFieldInscription.getStyleClass().add("TextFieldConnexion");
        passwordFieldInscription.setLayoutX(790);
        passwordFieldInscription.setLayoutY(275);

        PasswordField passwordFieldInscription2 = new PasswordField();
        passwordFieldInscription2.getStyleClass().add("TextFieldConnexion");
        passwordFieldInscription2.setLayoutX(790);
        passwordFieldInscription2.setLayoutY(375);

        Button buttonInscription = new Button("S'inscrire");
        buttonInscription.getStyleClass().add("btnGrey");
        buttonInscription.setLayoutX(810);
        buttonInscription.setLayoutY(450);

        Button buttonRetour = new Button("Retour");
        buttonRetour.getStyleClass().add("btnGrey");
        buttonRetour.setLayoutX(645);
        buttonRetour.setLayoutY(620);

        Label labelErreur = new Label();
        labelErreur.getStyleClass().add("LabelConnexionField");
        labelErreur.setLayoutX(340);
        labelErreur.setLayoutY(580);

        Button menu = new Button("Menu");
        menu.getStyleClass().add("btnGrey");
        menu.setLayoutX(545);
        menu.setLayoutY(620);

        // Retour menu
        menu.setOnAction(event -> {
            VueMenu vueMenu = new VueMenu();
            vueMenu.demarrerMenu(stage);
        });

        // Inscription
        buttonInscription.setOnAction(event -> {
            //if error -> labelErreur.setText("Erreur");
            labelErreur.setText("");
            if (PlayerManager.getInstance().getPlayer(textFieldPseudoInscription.getText()) == null) {
                if (textFieldPseudoInscription.getText().equals("") || passwordFieldInscription.getText().equals("") || passwordFieldInscription2.getText().equals("")) {
                    labelErreur.setText("Veuillez remplir tous les champs d'incription");
                } else if (!passwordFieldInscription.getText().equals(passwordFieldInscription2.getText())) {
                    labelErreur.setText("Les mots de passe ne correspondent pas");
                    passwordFieldInscription.setText("");
                    passwordFieldInscription2.setText("");
                } else {
                    try {
                        PlayerManager.getInstance().createPlayer(textFieldPseudoInscription.getText(), passwordFieldInscription.getText());
                        Session.getInstance().connect(textFieldPseudoInscription.getText());
                        textFieldPseudoInscription.setText("");
                        passwordFieldInscription.setText("");
                        passwordFieldInscription2.setText("");
                        VueMenu vueMenu = new VueMenu();
                        vueMenu.demarrerMenu(stage);
//                        labelErreur.setText("Inscription réussie");
                    } catch (Exception e) {
                        labelErreur.setText("Erreur lors de l'inscription");
                    }
                }
            } else {
                labelErreur.setText("Ce pseudo est déjà utilisé");
            }
        });

        // Connexion
        buttonConnexion.setOnAction(event -> {
            labelErreur.setText("");
            if (textFieldPseudo.getText().equals("") || passwordField.getText().equals("")) {
                labelErreur.setText("Veuillez remplir tous les champs d'incription");
            } else {
                if (PlayerManager.getInstance().getPlayer(textFieldPseudo.getText()) == null) {
                    labelErreur.setText("Ce pseudo n'existe pas");
                } else {
                    AuthPlayer authPlayer = PlayerManager.getInstance().getPlayer(textFieldPseudo.getText());
                    try {
                        if (Security.checkPassword(passwordField.getText(), authPlayer.getSalt(), authPlayer.getHashedPassword())) {
                            if (Session.getInstance().isConnected()) {
                                Session.getInstance().disconnect();
                            }
                            Session.getInstance().connect(textFieldPseudo.getText());
                            textFieldPseudo.setText("");
                            passwordField.setText("");
                            VueMenu vueMenu = new VueMenu();
                            vueMenu.demarrerMenu(stage);
                        } else {
                            labelErreur.setText("Mot de passe incorrect");
                            passwordField.setText("");
                        }
                    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        // Retour
        buttonRetour.setOnAction(event -> {
            VueParametre vueParametre = new VueParametre();
            try {
                vueParametre.affichageVueParametre(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Lors du click sur le bouton quitter de la fenetre (affichage confirmation)
        stage.setOnCloseRequest(event -> {
            event.consume();
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
                Session.getInstance().disconnect();
                System.exit(0);
            });
            non.setOnAction(e -> {
                pane.getChildren().removeAll(oui, non, rectangle, alerte);
            });
            pane.getChildren().addAll(rectangle, alerte, oui, non);
        });

        pane.setStyle("-fx-border-color: white ; -fx-border-width: 10px ; -fx-background-color: black ; -fx-background-radius: 10px ;");
        pane.getChildren().addAll(label, labelPseudo, labelMotDePasse, textFieldPseudo, passwordField, buttonConnexion, labelInscription, labelPseudoInscription, labelMotDePasseInscription, labelMotDePasseInscription2, textFieldPseudoInscription, passwordFieldInscription, passwordFieldInscription2, buttonInscription, buttonRetour, labelErreur, menu);
        stage.setTitle("Connexion");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
}
