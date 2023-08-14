package com.example.jeuxst;

import POO.Jedi;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game_controleur implements Initializable { //yes
    String color;
    String name;
    @FXML
    AnchorPane mainAnchorPane;
    @FXML
    ImageView pers;

    @FXML
    ImageView backGround;
    Image backGroundImage;

    @FXML
    Button lol;

    Jedi personnage;
    Image imagePersonnage;
    double déplacementY = 365;
    double déplacementX = 20;
    double vitesse = 20;
    double fallY =0;


    public  double g =0.9;

    boolean right = false;
    int rightAnimation=0;
    boolean left = false;
    int leftAnimation=0;
    boolean down =false;
    boolean up =false;
    boolean up1 =false;

    boolean jumpanimation =false;

    int jumpIteration = 0;

    boolean right1 = false;
    boolean left1 = false;


    private Parent group;

private Scene scene;

public void fitCordinate(ImageView iV,double x,double y){


    Scene sne=  iV.getScene();
    double newX=0;
    double newy=0;

    newX =  x * (iV.getFitWidth() / iV.getFitHeight());
    newy =  y * (iV.getFitHeight() / iV.getFitWidth());
   this.déplacementY=newy;
   this.déplacementX=newX;
   iV.setLayoutY(déplacementY);
   iV.setLayoutX(déplacementX);
}
    public Game_controleur( ) {



    }
    public void initia(String colort, String namet) {
        backGroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"marle"+".png")));
        backGround.setImage(  backGroundImage);
        name = namet;
        color = colort;
        System.out.println(
                "le nom de votre personage est " + namet + " est la couleur de son sabre laser est " + colort
        );


        personnage = new Jedi(20, 2, 10, 2, 14, "mario");

// Ajoutez le nœud représentant le personnage à votre conteneur

        backGroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"marle"+".png")));





        pers.setLayoutY(déplacementY);
        pers.setLayoutX(déplacementX);
    }
    public void initializeData(Scene scene) {





        // Now you can use these references in your configBackground method

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* if (pers.getLayoutY() + pers.getFitHeight() <= 100) {
            TranslateTransition transition = new TranslateTransition(Duration.seconds(3), pers);
            transition.setByY(-50); // Déplacer le bouton de 100 pixels horizontalement



            // Démarrer la transition
            transition.play();
        }*/






         //Affichage.configBackgroundResponsive(backGround.getScene(),backGround);


       // backGround.setImage(backGroundImage);

    // Affichage.configBackground(backGround,g);

       lol.setOnKeyPressed(e -> {
            switch(e.getCode()){

                case LEFT :
                    left = true;

                    break;
                case RIGHT :
                    right = true;

                    break;
                case DOWN :
                    down = true;
                    break;
                case UP :
                    up = true;
                    break;

                default:
                    break;

            }

        });
        lol.setOnKeyReleased(e -> {
            switch(e.getCode()){

                case LEFT :
                    left = false;
                    break;
                case RIGHT :
                   right = false;
                    break;
                case DOWN :
                    down = false;
                    break;



                default:
                    break;

            }

        });

       animationTimer.start();
    }

    @FXML
    public void show(KeyEvent event) throws IOException {
        System.out.println("X= " + pers.getLayoutX() + "Y= " + pers.getLayoutY());
        System.out.println("pers.getLayoutY() = "+pers.getLayoutY()+"déplacementY = "+déplacementY);
        System.out.println(pers.getFitHeight());


               //déplacementY-=5;


               // déplacementY-=100;
              //  pers.setLayoutY(déplacementY);
               // transition.setFromY(100);
              //  transition.setToY(0);

               // transition.play();


    }

    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
          /*  if (isJumping) {
                // Mise à jour de la position du personnage
                currentY -= jumpSpeed;
                imageView.setTranslateY(currentY);

                // Vérification si la hauteur maximale du saut est atteinte
                if (currentY <= initialY - jumpHeight) {
                    isJumping = false;
                }
            } else {
                // Mise à jour de la position du personnage en descendant
                if (currentY < initialY) {
                    currentY += gravity;
                    imageView.setTranslateY(currentY);
                }




            }*/
            backGround.getScene().widthProperty().addListener((observable, oldWidth, newWidth) ->fitCordinate(pers,déplacementX,déplacementY)); //Affichage.Xconfigure(pers,déplacementX,déplacementY));
            backGround.getScene().heightProperty().addListener((observable, oldHeight, newHeight) -> fitCordinate(pers,déplacementX,déplacementY));//Affichage.Xconfigure(pers,déplacementX,déplacementY));




           Affichage.configBackground(backGround,backGround.getScene(),backGround.getParent());
            Affichage.configurer(pers, 0.046875d, 0.166666d,déplacementX, déplacementY);
          //  Affichage.configBackgroundResponsive(backGround.getScene(),backGround);
                 backGround.getParent();
            //gravité:
            if(pers.getLayoutY() + pers.getFitHeight() <= backGround.getFitHeight() * 0.8d&&!jumpanimation ){
                up=false;
                up1=true;
                if (right1) {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightJump.png")));
                } else {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftJump.png")));
                }
                pers.setImage(imagePersonnage);
               déplacementY+= fallY;
               // System.out.println("backGround.getFitHeight() * 0.8d = "+backGround.getFitHeight() * 0.8d);
                System.out.println(déplacementY);
               pers.setLayoutY(déplacementY);
                fallY += g;
            }

            if(pers.getLayoutY() + pers.getFitHeight() > backGround.getFitHeight() * 0.8d ){

                if (right1) {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right.png")));
                } else {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left.png")));
                }
                pers.setImage(imagePersonnage);
                fallY=0;
               // System.out.println("gravité bon = "+backGround.getFitHeight() * 0.8d);
                up1=false;
            }

            if (left  && !down) {// Permet d'aller à gauche. Évite d'aller à gauche si l'on est accroupi.
             if(!up1) {
                 if(leftAnimation>=0 &&leftAnimation<=3){
                     imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left.png")));
                     leftAnimation+=1;
                 }else if (leftAnimation>=4 &&leftAnimation<=7){
                     imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left1.png")));
                     leftAnimation+=1;
                 }else if (leftAnimation>=8 &&leftAnimation<=11){
                     imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left2.png")));
                     leftAnimation+=1;
                 }else if (leftAnimation>=12 &&leftAnimation<=15){
                     imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left1.png")));
                     leftAnimation+=1;
                 }else if (leftAnimation>=16){
                     imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left3.png")));
                     leftAnimation=0;
                 }
                 pers.setImage(imagePersonnage);
             }
             // Permet de savoir si l'on saute
            if( jumpanimation ||pers.getLayoutY() + pers.getFitHeight() <= backGround.getFitHeight() * 0.8d){ // si on saute on avance un peut
                 déplacementX -= 7;

             }else{  // Sinon on avance normalement
                déplacementX += -vitesse;}

                pers.setLayoutX(déplacementX);
                right1 = false;
                left1 = true;
            }

            if (right && !down) {// Permet d'aller à droite. Évite d'aller à droite si l'on est accroupi
                if(!up1) {

                   if(rightAnimation>=0&&rightAnimation<=3){
                       imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right.png")));
                       rightAnimation+=1;
                   } else if (rightAnimation>=4&&rightAnimation<=7) {
                       imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right1.png")));
                       rightAnimation+=1;

                   } else if (rightAnimation>=8&&rightAnimation<=11) {
                       imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right2.png")));
                       rightAnimation+=1;



                   }else if (rightAnimation>=12&&rightAnimation<=15) {
                       imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right1.png")));
                       rightAnimation=0;

                   }else if (rightAnimation>=16) {
                       imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right3.png")));
                       rightAnimation=0;

                   }

                    pers.setImage(imagePersonnage);
                }
               // permet de savoir si l'on saute.
               if( jumpanimation ||pers.getLayoutY() + pers.getFitHeight() <= backGround.getFitHeight() * 0.8d){ //Si on saute l'on avance un peu.
                    déplacementX += 7;

                }else{  // Sinon on avance normalement.
                   déplacementX += vitesse;}
                pers.setLayoutX(déplacementX);
                right1 = true;
                left1 = false;
            }

            if (down) { // pour s'accroupir
                if (right1) { //permet de choisir la bonne orientation de l'image.
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightCrouch.png")));
                } else {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftCrouch.png")));
                }

                if(right){
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightCrouch.png")));

                }else if (left) {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftCrouch.png")));

                }
                pers.setImage(imagePersonnage); // permet le déplacement du personnage


            }
            if (up) { // pour sauter
             jumpanimation=true; // permet de mettre en place le saut. La valeur sera faux quand le saut sera terminée.
                if (right1) { //pour choisir la bonne orientation de l'image
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightJump.png")));
                } else {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftJump.png")));
                }
                pers.setImage(imagePersonnage);

               up=true; // permet de revenir dans la condition up pour continuer le saut.





                déplacementY-=15; //vitesse du saut
                jumpIteration+=10; //timer du saut
                if (jumpIteration>=130){

                    jumpIteration=0;
                    jumpanimation=false;
                    up=false;// permet de ne plus revenir dans la condition up pour arreter le saut.
                }else{

                pers.setLayoutY(déplacementY);}

                //déplacementY-=5;


                // déplacementY-=100;
                //  pers.setLayoutY(déplacementY);
                // transition.setFromY(100);
                //  transition.setToY(0);

                // transition.play();



            }




        }

    };


}

