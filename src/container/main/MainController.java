package container.main;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import container.countdown.CountDownTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    /*
    *   GLOBAL VARIABLES
     */
    @FXML private TabPane   tabPane;
    @FXML private JFXSlider sliderHrs;
    @FXML private JFXSlider sliderMin;
    @FXML private JFXSlider sliderSec;
    @FXML private Label     lbHours;
    @FXML private Label     lbMinutes;
    @FXML private Label     lbSeconds;
    @FXML private Label     lbScreen_ct;
    @FXML private JFXButton btnAction_ct;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {

        //=========== TAB_PANE ANIMATION EFFECT =================================//
        tabPane.getSelectionModel().selectedItemProperty().addListener((o, oldTab, newTab) ->
                new FadeIn(newTab.getContent()).play());

        //**************************************************************************//
        /*                                                                          */
        /*                          CountDown Timer Code                            */
        /*                                                                          */
        //**************************************************************************//
        ArrayList<JFXSlider> sliders = new ArrayList<>(
                Arrays.asList(
                        sliderHrs,
                        sliderMin,
                        sliderSec
                )
        );

        ArrayList<Label> labels_ct = new ArrayList<>(
                Arrays.asList(
                        lbHours,
                        lbMinutes,
                        lbSeconds,
                        lbScreen_ct
                )
        );

        ArrayList<Object> controls_ct = new ArrayList<>(
                Arrays.asList(
                        btnAction_ct,
                        null
                )
        );

        CountDownTimer cdTimer = new CountDownTimer(sliders, labels_ct, controls_ct);
        cdTimer.init__();
    }
}
