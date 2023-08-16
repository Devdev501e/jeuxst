package com.example.jeuxst;



import javafx.beans.binding.Bindings;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;


//lol



public class Affichage {

    private static double RATIO = 52d/35d;
    private static ImageView background;



    public static void configBackground(ImageView iV, Scene s, Parent parent) {

        background = iV;

        When condition = Bindings.when((s.widthProperty().divide(s.heightProperty())).greaterThanOrEqualTo(RATIO));
        iV.fitWidthProperty().bind(condition.then(iV.fitHeightProperty().multiply(RATIO)).otherwise(s.widthProperty()));
        iV.fitHeightProperty().bind(condition.then(s.heightProperty()).otherwise(iV.fitWidthProperty().divide(RATIO)));

        parent.layoutXProperty().bind((s.widthProperty().subtract(iV.fitWidthProperty())).divide(2));
        parent.layoutYProperty().bind((s.heightProperty().subtract(iV.fitHeightProperty())).divide(2));

    }



    public static double Xconfigure(ImageView iV,double x,double y){
        Scene sne=  iV.getScene();
        double newX=0;
        double newy=0;

        newX =  x * (iV.getFitWidth() / iV.getFitHeight());
        newy =  y * (iV.getFitHeight() / iV.getFitWidth());
        System.out.println("ta mère ");
        // System.out.println("X AF= "+newX+"YAF = "+newy);
        //System.out.println("Xnom= "+x+"Ynom = "+y);


        return 2.00;
    }

    public static void configurer(ImageView iV, double LRatio, double HRatio, SimpleDoubleProperty hGX, SimpleDoubleProperty hGY,ImageView background, boolean down) {

        iV.fitHeightProperty().bind(background.fitHeightProperty().multiply(HRatio));
        iV.fitWidthProperty().bind(background.fitWidthProperty().multiply(LRatio));
        if (down) {
            SimpleDoubleProperty HGY2 =new SimpleDoubleProperty();
            HGY2.set(hGY.get()+LRatio);
            iV.layoutXProperty().bind(background.fitWidthProperty().multiply(hGX));
            iV.layoutYProperty().bind(background.fitHeightProperty().multiply( HGY2));


        }else{

            iV.layoutXProperty().bind(background.fitWidthProperty().multiply(hGX));
            iV.layoutYProperty().bind(background.fitHeightProperty().multiply(hGY));

        }
    }





}