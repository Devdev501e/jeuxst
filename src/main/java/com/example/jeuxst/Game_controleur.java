package com.example.jeuxst;

import POO.Jedi;

import POO.Ranged;
import POO.Saber;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleDoubleProperty;
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

    @FXML
    ImageView obstacle;

    ImageView imageViewObstacle ;

    ImageView itemImageview=new ImageView();

    Jedi personnage;
    Image imagePersonnage;



    Image itemImage;
    Saber saber;
    Ranged ranged;

    double tailleRanged = 8;

    double  tailleSaber =1.5;
    int numeroColor=1;

    boolean bolObs=false;



    private static final SimpleDoubleProperty HGX = new SimpleDoubleProperty();
    private static final SimpleDoubleProperty HGY = new SimpleDoubleProperty();

    private static final SimpleDoubleProperty HOX = new SimpleDoubleProperty();
    private static final SimpleDoubleProperty HOY = new SimpleDoubleProperty();

    public static final SimpleDoubleProperty IHGX =new SimpleDoubleProperty();
    public static final SimpleDoubleProperty IHGY=new SimpleDoubleProperty();

    private static final double G = 0.003d;
    private static final double vMarche = 0.018d;
    private static  double vitesseY =0.0;

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
    boolean left1 = false;



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
         ranged = new Ranged(2.0,50,1);
        personnage = new Jedi(20, 2, 10, 14, "mario",saber);
        itemImage =new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +personnage.getItem().getDossier()+numeroColor+".png")));
          itemImageview.setImage(itemImage);

        itemY=  itemImageview.getImage().getHeight()/backGround.getImage().getHeight()*tailleSaber;
        itemX= itemImageview.getImage().getWidth()/backGround.getImage().getWidth()*tailleSaber;


            // itemImageview.setImage(personnage.getItem().getImage());


HGX.set(0.1d);
HGY.set(0.0d);

        // position de l'obstacle :
        HOX.set(0.5d);
        HOY.set(0.7d);


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
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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

                case LEFT :
                    left = false;
                    break;
                case RIGHT :
                    right = false;
                    break;
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

            Affichage.configurer(pers, 0.046875d, 0.166666d, HGX,HGY,down);
            Affichage.configurerN(itemImageview, itemX, itemY, IHGX, IHGY, down);


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

              IHGY.set(HGY.get());
              if (right1) {itemImageview.setScaleX(1);
                  IHGX.set(HGX.get() + 0.04);
                  itemImageview.setRotate(360);
              } else {
                  itemImageview.setScaleX(-1);
                  itemImageview.setRotate(-360);
                  IHGX.set(HGX.get() - 0.07);
              }


          }else {


              IHGY.set(HGY.get() - 0.08);
              if (right1) {
                  IHGX.set(HGX.get() + 0.06);
                  itemImageview.setRotate(45);
              } else {
                  itemImageview.setRotate(-45);
                  IHGX.set(HGX.get() - 0.06);
              }
          }
            ImageView particule =new ImageView();
             if(n){
                 if (personnage.getItem() instanceof Ranged){
                 n=false;}
                 particule= personnage.getItem().Use(itemImageview,right1,HGX,HGY,IHGX,IHGY,down);

             }






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

                HGX.set(0.0d);
                System.out.println("tu rentre ?");
            }
            if(pers.getBoundsInParent().getMinX()<backGround.getBoundsInParent().getMinX()){ // pour éviter de passer en dessous de 0 car il n y a pas de scene -1
                sceneChangement --;


                if(sceneChangement<=0){
                    sceneChangement=0;
                }
                //  System.out.println("down = "+sceneChangement);
                HGX.set(0.95d);

                backGroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" +"marle"+sceneChangement+".png")));
                //   System.out.println("ta mère");
                backGround.setImage(backGroundImage);
                System.out.println("tu rentre pas ?");

            }

          /*
            /////////////boucle pour les obstacles
            for (int o=0;o<nombreObstacle;o++){

                double x ;
                double LRatio = (double) widthDataObstacle.get(o);
                double HRatio = (double) heightDataObstacle.get(o);
                if(o==0){
                    x=0.5d;

                }else{

                    x=0.8d;
                }
                ImageView imageView1 = (ImageView) imageViewList.get(o);


                Affichage.configurer2(imageView1, LRatio / 2, HRatio / 2,  x, 0.6d, backGround);


            System.out.println("imageView1.getY() = "+imageView1.getLayoutY());
            System.out.println("obstacle.getY() = "+obstacle.getLayoutY());


            System.out.println("imageView1.getFiteight() = "+imageView1.getLayoutY());
            System.out.println("obstacle.getFitHeight() = "+obstacle.getLayoutY());
            if(imageView1.getLayoutX()+imageView1.getFitWidth()>=pers.getLayoutX()&&imageView1.getLayoutX()-30<=pers.getLayoutX()&&pers.getLayoutY()+pers.getFitHeight()<imageView1.getLayoutY()+imageView1.getFitHeight()*1.2){

                    bolObs=true;
                    System.out.println("ca marche2 = "+imageView1.getFitHeight());
                    //     System.out.println("getlayoutY = "+obstacle.getLayoutY()+"obstacle.getFitHeight() = "+obstacle.getFitHeight()+"backGround.getFitheight()"+backGround.getFitHeight());

                }else{
                    System.out.println("ca marche pas2");
                    bolObs=false;
                }
                if(!bolObs){
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
                        //  System.out.println("gravité vitesse = "+vitesseY);
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
                }else {
                    if (pers.getLayoutY() + pers.getFitHeight() < imageView1.getLayoutY() && !jumpanimation) {
                        up = false;
                        up1 = true;
                        if (right1) {
                            imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightJump.png")));
                        } else {
                            imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftJump.png")));
                        }

                        pers.setImage(imagePersonnage);
                        vitesseY += G;
                        System.out.println("gravité vitesse = " + vitesseY);
                        HGY.set(HGY.get() + vitesseY);

                        System.out.println("tu rentre ?");

                    }
                    if (pers.getLayoutY() + pers.getFitHeight() >= imageView1.getLayoutY() && !jumpanimation) {

                        if (right1) {
                            imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right.png")));
                        } else {
                            imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left.png")));
                        }
                        pers.setImage(imagePersonnage);

                        HGY.set((imageView1.getLayoutY() - imageView1.getFitHeight() * 1.6) / backGround.getFitHeight());
                        //  System.out.println("HGY.set = "+(imageView1.getLayoutY()-imageView1.getImage().getHeight())/backGround.getImage().getHeight());
                        System.out.println((imageView1.getLayoutY() - imageView1.getFitHeight()) / backGround.getFitHeight());


                        vitesseY = 0;
                        // System.out.println("gravité bon = "+backGround.getFitHeight() * 0.8d);
                        up1 = false;
                    }

                } */

            ImageView imageView3 = imageViewList.get(2); //tuyau vert
            ImageView imageView1 =  imageViewList.get(1);
            ImageView imageView2 =  imageViewList.get(0);



            double LRatio;
            double HRatio;
            double hgx;
            double hgy;

            if ( M.abs(imageView1.getLayoutX()-pers.getLayoutX())< M.abs(imageView3.getLayoutX()-pers.getLayoutX())){

               // System.out.println("obstacle 1 ");
                imageViewObstacle= imageViewList.get(1);
                LRatio =  widthDataObstacle.get(1)/2;
                HRatio =  heightDataObstacle.get(1)/2;
                hgx=0.1d;
                hgy=0.6d;


          }else if( M.abs(imageView3.getLayoutX()-pers.getLayoutX())< M.abs(imageView2.getLayoutX()-pers.getLayoutX()))  {
                imageViewObstacle=imageViewList.get(2);
               LRatio =  widthDataObstacle.get(2);
                HRatio =  heightDataObstacle.get(2);
                hgx=0.4d;
                hgy=0.66d;

            }else{

                imageViewObstacle= imageViewList.get(0);
                LRatio =  widthDataObstacle.get(0)/2;
                HRatio =  heightDataObstacle.get(0)/2;
                hgx=0.6d;
                hgy=0.3d;



            }


            if (sceneChangement==0) {

                Affichage.configurer2(imageViewObstacle, LRatio, HRatio, hgx, hgy);
            }


           // pour vérifier si on est au dessu de l'obstacle


            if( imageViewObstacle.getBoundsInParent().getMaxY()>=pers.getBoundsInParent().getMaxY()&& imageViewObstacle.getBoundsInParent().getMinX()<pers.getBoundsInParent().getCenterX()&&pers.getBoundsInParent().getMaxY()< imageViewObstacle.getBoundsInParent().getMaxY()){

                bolObs=true;

            }else{
                bolObs=false;
            }

            if(pers.getBoundsInParent().getMaxX()>imageViewObstacle.getBoundsInParent().getMinX()&&pers.getBoundsInParent().getMinX()<imageViewObstacle.getBoundsInParent().getMaxX()&&pers.getBoundsInParent().getMaxY()>imageViewObstacle.getBoundsInParent().getMinY()&&pers.getBoundsInParent().getMinY()<imageViewObstacle.getBoundsInParent().getMaxY()){
             boolean gauche=pers.getBoundsInParent().getMaxX()>imageViewObstacle.getBoundsInParent().getMinX()&&pers.getBoundsInParent().getMaxX()<imageViewObstacle.getBoundsInParent().getMinX()+imageViewObstacle.getBoundsInParent().getMinX()*0.1;

             boolean droite=pers.getBoundsInParent().getMinX()<imageViewObstacle.getBoundsInParent().getMaxX()&&pers.getBoundsInParent().getMinX()>imageViewObstacle.getBoundsInParent().getMaxX()-imageViewObstacle.getBoundsInParent().getMinX()*0.1;

                if(gauche){
                    HGX.set((imageViewObstacle.getBoundsInParent().getMinX()-pers.getBoundsInParent().getWidth())/backGround.getFitWidth());}

                if(droite) {
                    HGX.set((imageViewObstacle.getBoundsInParent().getMaxX())/backGround.getFitWidth());

            }
                if(!droite&&!gauche)
                {

                    HGY.set((imageViewObstacle.getBoundsInParent().getMaxY())/backGround.getFitHeight());
                    jumpanimation = false;
                }

            }


            ////////////////////////////////////gravité:

            if(!bolObs){
                if(pers.getBoundsInParent().getMinY() + pers.getBoundsInParent().getHeight() <= backGround.getBoundsInParent().getHeight() * 0.8d&&!jumpanimation ){
                  up=false;
                    up1=true;
                    if (right1) {
                        imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightJump.png")));
                    } else {
                        imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftJump.png")));
                    }
                    pers.setImage(imagePersonnage);
                    vitesseY += G;
                    System.out.println("tas un problème");
                    HGY.set(HGY.get()  + vitesseY);

                }
                if(pers.getBoundsInParent().getMinY() + pers.getBoundsInParent().getHeight()  > backGround.getBoundsInParent().getHeight() * 0.8d &&!jumpanimation){

                    if (right1) {
                        imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right.png")));
                    } else {
                        imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left.png")));
                    }
                    pers.setImage(imagePersonnage);
                    HGY.set((backGround.getFitHeight() * 0.8d) / backGround.getFitHeight()); // il faudra peut etre chnger ca
                    System.out.println("oui");
                    vitesseY = 0;
                    up1=false;
                }
            }else{
                if(!pers.getBoundsInParent().intersects( imageViewObstacle.getBoundsInParent())&&!jumpanimation ){
                    up=false;
                    up1=true;
                    if (right1) {
                        imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_RightJump.png")));
                    } else {
                        imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_LeftJump.png")));
                    }

                    pers.setImage(imagePersonnage);
                    vitesseY += G;
                    HGY.set(HGY.get()  + vitesseY);


                }
                if( pers.getBoundsInParent().intersects( imageViewObstacle.getBoundsInParent())     &&!jumpanimation){
                    if (right1) {
                        imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Right.png")));
                    } else {
                        imagePersonnage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/" + personnage.getImageView() + "/" + personnage.getImageView() + "_Left.png")));
                    }
                    pers.setImage(imagePersonnage);
                    HGY.set((imageViewObstacle.getBoundsInParent().getMinY()-pers.getBoundsInParent().getHeight())/backGround.getFitHeight());

                    vitesseY = 0;
                    up1=false;
                }




            }

            if (left  && !down) {// Permet d'aller à gauche. Évite d'aller à gauche si l'on est accroupi.
                leftAnimation=personnage.walkAnimation("Left",leftAnimation,pers,up1);
                // Permet de savoir si l'on saute
                if(up1){ // si on saute on avance un peut
                    HGX.set(HGX.get() - vMarche/2);

                }else{  // Sinon on avance normalement
                    HGX.set(HGX.get() - vMarche);}


                right1 = false;
                left1 = true;
            }

            if (right && !down) {// Permet d'aller à droite. Évite d'aller à droite si l'on est accroupi

               rightAnimation= personnage.walkAnimation("Right",rightAnimation,pers,up1);

                nextScene=true;
                // permet de savoir si l'on saute.
                if( up1){ //Si on saute l'on avance un peu.
                    HGX.set(HGX.get() +vMarche/2);

                }else{  // Sinon on avance normalement.
                    HGX.set(HGX.get() + vMarche);}

                right1 = true;
                left1 = false;
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


                jumpanimation=true; // permet de mettre en place le saut. La valeur sera faux quand le saut sera terminée.
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

                    HGY.set(HGY.get() +  vitesseY);


                }






            }




        }

    };


}