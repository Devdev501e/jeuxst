package POO;
import com.example.jeuxst.Affichage;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.Objects;

public class Ranged extends Weapon{

    int amo;

    public Ranged (double damage, int amo,int id) {


       this.id=id;
       this.damage=damage;
       this.amo=amo;
       this.dossier="Ranged/";
    }

   @Override
    public ImageView Use(ImageView imageView, Boolean right1,boolean down) {
// peut-être quand va remplacer par une arrayliste d'imagview.
        SimpleDoubleProperty THGY=new SimpleDoubleProperty();
        SimpleDoubleProperty THGX=new SimpleDoubleProperty();

        AnchorPane rootNode = (AnchorPane) imageView.getScene().getRoot();


        ImageView imageView1=new ImageView();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/jeuxst/image/Ranged/2.png")));
        imageView1.setImage(image);
       
   if(this.amo>0){
        int currentIndex =  rootNode.getChildren().indexOf(imageView)-1;
        rootNode.getChildren().add( currentIndex,imageView1);
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), imageView1);

        THGY.set(this.getHGY().get()+imageView.getBoundsInParent().getHeight()/Affichage.getBackground().getBoundsInParent().getHeight()*0.13);
        double hRatio= imageView1.getImage().getHeight()/Affichage.getBackground().getImage().getHeight();
        double lRatio= imageView1.getImage().getWidth()/Affichage.getBackground().getImage().getWidth();
        if(right1) {
            THGX.set(this.HGX.get()+0.02);
            translateTransition.setToX(800); // Translation horizontale de 200 pixels
        }else{
            THGX.set(this.HGX.get()-0.02);
            translateTransition.setToX(-800);

        }
        Affichage.configurerN(imageView1,lRatio/14, hRatio/14, THGX,THGY,down);
        // Animation retour en arrière


        // Démarre l'animation


       translateTransition.play();

        translateTransition.setOnFinished(event -> {
            rootNode.getChildren().remove(imageView1);


        });


       amo--;}

   else{


   }

        return imageView1;


    }



    @Override
    public String getDossier(){

        return this.dossier;
    }
}
