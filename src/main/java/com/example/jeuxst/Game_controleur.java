package com.example.jeuxst;

import POO.Jedi;

import POO.Ranged;
import POO.Saber;
import javafx.animation.AnimationTimer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


import javafx.scene.control.Button;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;

public class Game_controleur implements Initializable {

    /* getlayoutX et getlayoutY= Ces méthodes renvoient les coordonnées du coin supérieur gauche de l'ImageView par rapport à son parent.*/
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


    ImageView itemImageview=new ImageView();

    Jedi personnage;
    Image imagePersonnage;



    Image itemImage;
    Saber saber;
    Ranged ranged;
    ImageView particule =new ImageView();

    ArrayList<ImageView> particulListe= new ArrayList();
    double tailleRanged = 8;

    double  tailleSaber =1.5;
    int numeroColor=1;

    boolean bolObs=false;


    private static final double G = 0.003d;
    private static final double vMarche = 0.018d;
    private static double vitesseY =0.0;

    double itemX;
    double itemY;

    boolean right = false;
    int rightAnimation=0;
    boolean left = false;
    int leftAnimation=0;
    boolean down =false;
    boolean up =false;
    boolean up1 =false;

    boolean n=false;

    boolean b =false;

    boolean jumpanimation =false;

    int jumpIteration = 0;

    boolean right1 = false;

    boolean nextScene= true;
    int sceneChangement=0;


    // Pour gérer les obstacles
    ArrayList<ImageView> imageViewList=new ArrayList<>();
    int nombreObstacle=3;   //changer le nombre d'obstacles + un if et ajoutée un affichage



    ArrayList<Double> widthDataObstacle=new ArrayList<>();
    ArrayList<Double> heightDataObstacle=new ArrayList<>();


    public Game_controleur( ) {



    }
    public void initia(String colort, String namet) {
        backGroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"marle0"+".png")));
      //  imageObstacle =new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"obstacle0"+".png")));
        backGround.setImage(  backGroundImage);
       // obstacle.setImage(imageObstacle);
        name = namet;
        color = colort;

        switch (color) {

            case"Orange" :
                numeroColor=3;
                break;
            case"Vert" :
                numeroColor=2;
                break;
            case"Bleu" :
                numeroColor=1;
                break;
            case"Violet" :
                numeroColor=4;
                break;

        }

        System.out.println(
                "le nom de votre personage est " + namet + " est la couleur de son sabre laser est " + colort
        );



         saber =new Saber(2.0, numeroColor,2.0);
         ranged = new Ranged(2.0,100,1);
        personnage = new Jedi(20, 2, 10, 14, "mario",saber);
        itemImage =new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +personnage.getItem().getDossier()+numeroColor+".png")));
          itemImageview.setImage(itemImage);

        itemY=  itemImageview.getImage().getHeight()/backGround.getImage().getHeight()*tailleSaber;
        itemX= itemImageview.getImage().getWidth()/backGround.getImage().getWidth()*tailleSaber;


            // itemImageview.setImage(personnage.getItem().getImage());


personnage.getHGX().set(0.1d);
personnage.getHGY().set(0.0d);




        for (int i=0;i<nombreObstacle;i++){

            Image image =new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/obstacle" + i + ".png")));
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageViewList.add(imageView);
            mainAnchorPane.getChildren().add(imageView);
          widthDataObstacle.add(imageView.getImage().getWidth()/backGround.getFitWidth());
          heightDataObstacle.add(imageView.getImage().getHeight()/backGround.getFitHeight());

        }
        System.out.println( imageViewList);

        mainAnchorPane.getChildren().add(itemImageview);

particulListe.add(particule);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        lol.setOnKeyPressed(e -> {
            switch(e.getCode()){

                case Q:
                case LEFT :
                    left = true;

                    break;
                case D:
                case RIGHT :
                    right = true;

                    break;
                case S:
                case DOWN :
                    down = true;
                    break;
                case Z:
                case UP :
                    up = true;
                    break;
                case N:
                    n=true;
                    break;
                case B:
                    b=true;
                    break;
                default:
                    break;

            }

        });
        lol.setOnKeyReleased(e -> {
            switch(e.getCode()){

                case Q:
                case LEFT :
                    left = false;

                    break;
                case D:
                case RIGHT :
                    right = false;

                    break;
                case S:
                case DOWN :
                    down = false;
                    break;

                case N:
                    n=false;

                    break;
                case B:
                    b=false;
                    break;

                default:
                    break;

            }

        });

        animationTimer.start();
    }



    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            Affichage.configBackground(backGround,backGround.getScene(),backGround.getParent());

            Affichage.configurer(pers, 0.046875d, 0.166666d, personnage.getHGX(),personnage.getHGY(),down);
            Affichage.configurerN(itemImageview, itemX, itemY, personnage.getItem().getHGX(), personnage.getItem().getHGY(), down);





            if(b){
                int number;
                b=false;
                if (personnage.getItem() instanceof Saber){

                    personnage.changeItem(ranged);
                }else{

                    personnage.changeItem(saber);
                }
                number=personnage.getItem().getId();


                itemImage =new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +personnage.getItem().getDossier()+ number+".png")));
                itemImageview.setImage(itemImage);
                itemY=  itemImageview.getImage().getHeight()/backGround.getImage().getHeight();
                itemX= itemImageview.getImage().getWidth()/backGround.getImage().getWidth();

                if(personnage.getItem() instanceof Ranged){

                    itemY=itemY/tailleRanged;
                    itemX=itemX/tailleRanged;
                }else{

                    itemY=itemY*tailleSaber;
                    itemX=itemX*tailleSaber;
                }
            }

          if(personnage.getItem() instanceof Ranged){

              personnage.getItem().getHGY().set(personnage.getHGY().get());
              if (right1) {itemImageview.setScaleX(1);
                  personnage.getItem().getHGX().set(personnage.getHGX().get() + 0.04);
                  itemImageview.setRotate(360);
              } else {
                  itemImageview.setScaleX(-1);
                  itemImageview.setRotate(-360);
                  personnage.getItem().getHGX().set(personnage.getHGX().get() - 0.07);
              }


          }else {


              personnage.getItem().getHGY().set(personnage.getHGY().get() - 0.08);
              if (right1) {
                  personnage.getItem().getHGX().set(personnage.getHGX().get() + 0.06);
                  itemImageview.setRotate(45);
              } else {
                  itemImageview.setRotate(-45);
                  personnage.getItem().getHGX().set(personnage.getHGX().get() - 0.06);
              }
          }

             if(n){
                 if (personnage.getItem() instanceof Ranged){
                 n=false;}
                 particule= personnage.getItem().Use(itemImageview,right1,down);
                 if(particule!=null){
                     particulListe.add(particule);
                 }


             }
            if(sceneChangement==0){

                bolObs = obstacleGravite(imageViewList.get(0), widthDataObstacle.get(0) / 2, heightDataObstacle.get(0) / 2, 0.7, 0.3) || obstacleGravite(imageViewList.get(2), widthDataObstacle.get(2), heightDataObstacle.get(2), 0.5, 0.66) || obstacleGravite(imageViewList.get(1), widthDataObstacle.get(1) / 2, heightDataObstacle.get(1) / 2, 0.2, 0.6);

            } else if (sceneChangement==1) {
                bolObs= obstacleGravite( imageViewList.get(1),widthDataObstacle.get(1)/2,heightDataObstacle.get(1)/2,0.15,0.3)||obstacleGravite( imageViewList.get(0),widthDataObstacle.get(0)/2,heightDataObstacle.get(0)/2,0.7,0.6)||            obstacleGravite( imageViewList.get(2),widthDataObstacle.get(2),heightDataObstacle.get(2),0.3,0.66);

            }else if (sceneChangement==2) {

                bolObs= obstacleGravite( imageViewList.get(2),widthDataObstacle.get(2),heightDataObstacle.get(2),0.2,0.66)||obstacleGravite( imageViewList.get(0),widthDataObstacle.get(0)/2,heightDataObstacle.get(0)/2,0.7,0.6)||obstacleGravite( imageViewList.get(1),widthDataObstacle.get(1)/2,heightDataObstacle.get(1)/2,0.3,0.66);

            }

            ImageView imageView2=particulListe.get(particulListe.size()-1);


            if(imageView2.getTranslateX()==800||imageView2.getTranslateX()==-800){

                particulListe.remove(particulListe.size()-1);
            }
            System.out.println(particulListe.size());








            //Affichage.configurer2(obstacle,LRatio,HRatio,0.1d,0.6d,backGround);

            if(pers.getBoundsInParent().getMaxX()>=backGround.getBoundsInParent().getMaxX() &&nextScene){
                sceneChangement ++;


                if(sceneChangement>2){ // je n'est pas encore fait de scene 3
                    sceneChangement=2;
                }
                //    System.out.println("up = "+sceneChangement);
                nextScene=false;
                backGroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"marle"+sceneChangement+".png")));
                // System.out.println("ta mère");
                backGround.setImage(backGroundImage);

                personnage.getHGX().set(0.0d);
                System.out.println("tu rentre ?");
            }
            if(pers.getBoundsInParent().getMinX()<backGround.getBoundsInParent().getMinX()){ // pour éviter de passer en dessous de 0, car il n'y a pas de scene -1
                sceneChangement --;


                if(sceneChangement<=0){
                    sceneChangement=0;
                }
                //  System.out.println("down = "+sceneChangement);
                personnage.getHGX().set(0.95d);

                backGroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"marle"+sceneChangement+".png")));
                //   System.out.println("ta mère");
                backGround.setImage(backGroundImage);
                System.out.println("tu rentre pas ?");

            }


        


            ////////////////////////////////////gravité:


            System.out.println(bolObs);
            if(!bolObs){

                if(pers.getBoundsInParent().getMinY() + pers.getBoundsInParent().getHeight() <= backGround.getBoundsInParent().getHeight() * 0.8d&&!jumpanimation ){
                    imageJump();
                    System.out.println("ou ici 5464312 ?");
                    personnage.getHGY().set(personnage.getHGY().get()  + vitesseY);

                }
                if(pers.getBoundsInParent().getMinY() + pers.getBoundsInParent().getHeight()  > backGround.getBoundsInParent().getHeight() * 0.8d &&!jumpanimation){
                    System.out.println("brerrrrrrrrr");
                    imageLanding();
                    personnage.getHGY().set((backGround.getFitHeight() * 0.8d) / backGround.getFitHeight()); // il faudra peut etre chnger ca

                    vitesseY = 0;
                    up1=false;
                }
            }





            if (left  && !down) {// Permet d'aller à gauche. Évite d'aller à gauche si l'on est accroupi.
                leftAnimation=personnage.walkAnimation("Left",leftAnimation,pers,up1);
                // Permet de savoir si l'on saute
                if(up1){ // si on saute, on avance un peu
                    personnage.getHGX().set(personnage.getHGX().get() - vMarche/2);

                }else{  // Sinon on avance normalement
                    personnage.getHGX().set(personnage.getHGX().get() - vMarche);}


                right1 = false;

            }

            if (right && !down) {// Permet d'aller à droite. Évite d'aller à droite si l'on est accroupi

               rightAnimation= personnage.walkAnimation("Right",rightAnimation,pers,up1);

                nextScene=true;
                // permet de savoir si l'on saute.
                if( up1){ //Si on saute l'on avance un peu.
                    personnage.getHGX().set(personnage.getHGX().get() +vMarche/2);

                }else{  // Sinon on avance normalement.
                    personnage.getHGX().set(personnage.getHGX().get() + vMarche);}

                right1 = true;

            }

            if (down ) { // pour s'accroupir
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
            if (up ) { // pour sauter


                jumpanimation=true; // Permet de mettre en place le saut. La valeur sera faux quand le saut sera terminée.
                if (right1) { //pour choisir la bonne orientation de l'image
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightJump.png")));
                } else {
                    imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftJump.png")));
                }
                pers.setImage(imagePersonnage);


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

                    personnage.getHGY().set(personnage.getHGY().get() +  vitesseY);
                }

            }

        }


        private void colision(ImageView obstacle ){
            for(int i =0 ;i<particulListe.size();i++) {
                if (particulListe.get(i).getLayoutX() + particulListe.get(i).getTranslateX() < obstacle.getBoundsInParent().getMaxX() && particulListe.get(i).getLayoutX() + particulListe.get(i).getTranslateX() > obstacle.getBoundsInParent().getMinX()) {
                    if (particulListe.get(i).getLayoutY() > obstacle.getBoundsInParent().getMinY() && particulListe.get(i).getLayoutY() < obstacle.getBoundsInParent().getMaxY()) {
                        mainAnchorPane.getChildren().remove(particulListe.get(i));
                        particulListe.remove(i);
                    }
                }
            }
        }

        private void imageLanding() {

            if (right1) {
                imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right.png")));
            } else {
                imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left.png")));
            }
            pers.setImage(imagePersonnage);
        }

        private void imageJump() {
            up=false;
            up1=true;
            if (right1) {
                imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightJump.png")));
            } else {
                imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftJump.png")));
            }
            pers.setImage(imagePersonnage);
            vitesseY += G;
        }

        public boolean obstacleGravite(ImageView imageViewObstacle,double LRatio,double HRatio,double hgx,double hgy){
           boolean bolObs1;

            colision(imageViewObstacle);
            Affichage.configurer2(imageViewObstacle, LRatio, HRatio, hgx, hgy);
            if(pers.getBoundsInParent().getMinX()<=imageViewObstacle.getBoundsInParent().getMaxX()&&pers.getBoundsInParent().getMaxX()>=imageViewObstacle.getBoundsInParent().getMinX() && pers.getBoundsInParent().getMaxY()<imageViewObstacle.getBoundsInParent().getMaxY() ) {

                bolObs1=true;

        }else{

                bolObs1=false;}
        if( bolObs1) {
            if (!pers.getBoundsInParent().intersects(imageViewObstacle.getBoundsInParent()) && !jumpanimation) {
                imageJump();
                System.out.println("ou ici?");
                personnage.getHGY().set(personnage.getHGY().get() + vitesseY);


            }
            if (pers.getBoundsInParent().intersects(imageViewObstacle.getBoundsInParent()) && !jumpanimation) {
                imageLanding();
                personnage.getHGY().set((imageViewObstacle.getBoundsInParent().getMinY() - pers.getBoundsInParent().getHeight()) / backGround.getFitHeight());
                System.out.println("tu es a l'intérieur ?");
                vitesseY = 0;
                up1 = false;
            }
        }
            if(pers.getBoundsInParent().getMaxX()>imageViewObstacle.getBoundsInParent().getMinX()&&pers.getBoundsInParent().getMinX()<imageViewObstacle.getBoundsInParent().getMaxX()&&pers.getBoundsInParent().getMaxY()>imageViewObstacle.getBoundsInParent().getMinY()&&pers.getBoundsInParent().getMinY()<imageViewObstacle.getBoundsInParent().getMaxY()){
                boolean gauche=pers.getBoundsInParent().getMaxX()>imageViewObstacle.getBoundsInParent().getMinX()&&pers.getBoundsInParent().getMaxX()<imageViewObstacle.getBoundsInParent().getMinX()+imageViewObstacle.getBoundsInParent().getMinX()*0.1;

                boolean droite=pers.getBoundsInParent().getMinX()<imageViewObstacle.getBoundsInParent().getMaxX()&&pers.getBoundsInParent().getMinX()>imageViewObstacle.getBoundsInParent().getMaxX()-imageViewObstacle.getBoundsInParent().getMinX()*0.1;

                if(gauche){
                    personnage.getHGX().set((imageViewObstacle.getBoundsInParent().getMinX()-pers.getBoundsInParent().getWidth())/backGround.getFitWidth());}

                if(droite) {
                    personnage.getHGX().set((imageViewObstacle.getBoundsInParent().getMaxX())/backGround.getFitWidth());

                }
                if(!droite&&!gauche &&pers.getBoundsInParent().getMaxY()>imageViewObstacle.getBoundsInParent().getMinY()+imageViewObstacle.getBoundsInParent().getHeight())
                {

                    personnage.getHGY().set((imageViewObstacle.getBoundsInParent().getMaxY())/backGround.getFitHeight());
                    jumpanimation = false;
                }

            }

return bolObs1;

        }

    };


}