package com.example.jeuxst;

import POO.Jedi;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game_controleur implements Initializable {
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



    private static SimpleDoubleProperty HGX = new SimpleDoubleProperty();
    private static SimpleDoubleProperty HGY = new SimpleDoubleProperty();
    private static double G = 0.003d;
    private static double vMarche = 0.02d;




    private static double vitesseY =0.0;






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



    boolean nextScene= true;
    int sceneChangement=0;

    public void fitCordinate(ImageView iV,double x,double y){


  /*  Scene sne=  iV.getScene();
    double newX=0;
    double newy=0;

    newX =  x * (iV.getFitWidth() / iV.getFitHeight());
    newy =  y * (iV.getFitHeight() / iV.getFitWidth());
   this.déplacementY=newy;
   this.déplacementX=newX;
   iV.setLayoutY(déplacementY);
   iV.setLayoutX(déplacementX);*/
    }
    public Game_controleur( ) {



    }
    public void initia(String colort, String namet) {
        backGroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"marle0"+".png")));
        backGround.setImage(  backGroundImage);
        name = namet;
        color = colort;
        System.out.println(
                "le nom de votre personage est " + namet + " est la couleur de son sabre laser est " + colort
        );


        personnage = new Jedi(20, 2, 10, 2, 14, "mario");

// Ajoutez le nœud représentant le personnage à votre conteneur




        HGX.set(0.1d);
        HGY.set(0.0d);


        System.out.println("HGY = "+HGY);
        System.out.println("HGX = "+HGX);
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

            if(pers.getLayoutX()+pers.getFitWidth()>=backGround.getFitWidth() &&nextScene){
                sceneChangement ++;
                if(sceneChangement>2){ // j'ai pas encore fait de scene 3
                    sceneChangement=2;
                }
                nextScene=false;
                backGroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"marle"+sceneChangement+".png")));
                System.out.println("ta mère");
                backGround.setImage(backGroundImage);

                HGX.set(0.0d);
                HGY.set(0.9d);
            }
            if(pers.getLayoutX()+pers.getFitWidth()<=0){ // pour éviter de passer en dessous de 0 car il n y a pas de scene -1
                sceneChangement --;
                if(sceneChangement<=0){
                    sceneChangement=0;
                }
                HGX.set(1.0d);
                HGY.set(0.9d);
                backGroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"marle"+sceneChangement+".png")));
                System.out.println("ta mère");
                backGround.setImage(backGroundImage);


            }



            Affichage.configBackground(backGround,backGround.getScene(),backGround.getParent());

            Affichage.configurer(pers, 0.046875d, 0.166666d, HGX, HGY, backGround,down);

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
                vitesseY += G;
                System.out.println("gravité vitesse = "+vitesseY);
                HGY.set(HGY.get()  + vitesseY);




            }

            if(pers.getLayoutY() + pers.getFitHeight() > backGround.getFitHeight() * 0.8d &&!jumpanimation){

                if (right1) {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right.png")));
                } else {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left.png")));
                }
                pers.setImage(imagePersonnage);
                HGY.set((backGround.getFitHeight() * 0.8d - personnage.getFitHeight()) / backGround.getFitHeight()); // il faudra peut etre chnger ca
                vitesseY = 0;
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
                    HGX.set(HGX.get() - vMarche/2);

                }else{  // Sinon on avance normalement
                    HGX.set(HGX.get() - vMarche);}


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
                    nextScene=true;
                    pers.setImage(imagePersonnage);
                }
                // permet de savoir si l'on saute.
                if( jumpanimation ||pers.getLayoutY() + pers.getFitHeight() <= backGround.getFitHeight() * 0.8d){ //Si on saute l'on avance un peu.
                    HGX.set(HGX.get() +vMarche/2);

                }else{  // Sinon on avance normalement.
                    HGX.set(HGX.get() + vMarche);}

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
                up=true; // permet de revenir dans la condition up pour continuer le saut.

                jumpanimation=true; // permet de mettre en place le saut. La valeur sera faux quand le saut sera terminée.
                if (right1) { //pour choisir la bonne orientation de l'image
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightJump.png")));
                } else {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftJump.png")));
                }
                pers.setImage(imagePersonnage);


                System.out.println("avant le saut = "+vitesseY);
                vitesseY =-0.03d;


                //vitesse du saut
                jumpIteration+=5; //timer du saut
                if (jumpIteration>=100){
                    vitesseY =0.0;
                    jumpIteration=0;
                    jumpanimation=false;
                    up=false;// permet de ne plus revenir dans la condition up pour arreter le saut.
                }else{
                    vitesseY +=0.009d;
                    System.out.println(vitesseY);

                    HGY.set(HGY.get() +  vitesseY);

                    System.out.println(HGY.get());
                    System.out.println("apres le moin = ");
                    System.out.println(HGY.get() -  vitesseY);
                }


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
