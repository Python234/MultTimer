package container.main;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import container.timers.boxing.BoxingController;
import container.timers.countdown.CountDownTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    /*
    *   GLOBAL VARIABLES
     */
    @FXML private Label          lbScreen_ct;
    @FXML private Label          lbScreen_bt;
    @FXML private Label          lbWorkTime;
    @FXML private Label          lbRestTime;
    @FXML private Label          lbRoundNumber;
    @FXML private Label          lbSeconds;
    @FXML private Label          lbMinutes;
    @FXML private Label          lbHours;
    @FXML private TabPane        tabPane;
    @FXML private JFXSlider      sliderHrs;
    @FXML private JFXSlider      sliderMin;
    @FXML private JFXSlider      sliderSec;
    @FXML private JFXButton      btnAction_ct;
    @FXML private JFXButton      btnBack;
    @FXML private JFXButton      btnAction_bt;
    @FXML private JFXButton      btnSave;
    @FXML private JFXButton      btnNew;
    @FXML private JFXButton      btnCancel;
    @FXML private JFXButton      btnSetting;
    @FXML private ScrollPane     scrollPane;
    @FXML private ColorPicker    cpRoundColor;
    @FXML private ColorPicker    cpWarningColor;
    @FXML private ColorPicker    cpRestColor;
    @FXML private JFXCheckBox    ckbAlertEndOfRest;
    @FXML private JFXCheckBox    ckbEnableVoice;
    @FXML private JFXComboBox<Object> cbProfileNames;
    @FXML private JFXComboBox<Object> cbNumberOfRounds;
    @FXML private JFXComboBox<Object> cbRoundTimeMin;
    @FXML private JFXComboBox<Object> cbRoundTimeSec;
    @FXML private JFXComboBox<Object> cbRestTimeMin;
    @FXML private JFXComboBox<Object> cbRestTimeSec;
    @FXML private JFXComboBox<Object> cbPeriodicMin;
    @FXML private JFXComboBox<Object> cbPeriodicSec;
    @FXML private JFXComboBox<Object> cbEndOfRoundMin;
    @FXML private JFXComboBox<Object> cbEndOfRoundSec;
    @FXML private JFXComboBox<Object> cbPrepareTimeMin;
    @FXML private JFXComboBox<Object> cbPrepareTimeSec;
    @FXML private JFXComboBox<Object> cbStartEndSound;
    @FXML private JFXComboBox<Object> cbRoundWarningSound;
    @FXML private JFXComboBox<Object> cbInnerPeriodicSound;
    @FXML private JFXComboBox<Object> cbEndOfRestSound;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {

        // *************************** TAB_PANE ANIMATION EFFECT ******************************* //
        tabPane.getSelectionModel().selectedItemProperty().addListener((o, oldTab, newTab)
                -> new FadeIn(newTab.getContent()).play());

        //**************************************************************************//
        /*                                                                          */
        /*                          CountDown Timer                                 */
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

        new CountDownTimer(sliders, labels_ct, controls_ct).__init();
        // =========================================================================== //


        //**************************************************************************//
        /*                                                                          */
        /*                              Boxing Timer                                */
        /*                                                                          */
        //**************************************************************************//
        ArrayList<Label> labels_bt = new ArrayList<>(
                Arrays.asList(
                        lbScreen_bt,
                        lbRoundNumber,
                        lbWorkTime,
                        lbRestTime
                )
        );

        ArrayList<JFXButton> buttons = new ArrayList<>(
                Arrays.asList(
                        btnSetting,
                        btnBack,
                        btnAction_bt,
                        btnCancel,
                        btnSave,
                        btnNew
                )
        );

        ArrayList<JFXCheckBox> checkBoxes_bt = new ArrayList<>(
                Arrays.asList(
                        ckbEnableVoice,
                        ckbAlertEndOfRest
                )
        );

        ArrayList<ColorPicker> colorPickers = new ArrayList<>(
                Arrays.asList(
                        cpRoundColor,
                        cpWarningColor,
                        cpRestColor
                )
        );

        ArrayList<JFXComboBox<Object>> comboBoxes = new ArrayList<>(
                Arrays.asList(
                        cbNumberOfRounds,
                        cbRoundTimeMin,
                        cbRoundTimeSec,
                        cbRestTimeMin,
                        cbRestTimeSec,
                        cbPrepareTimeMin,
                        cbPrepareTimeSec,
                        cbPeriodicMin,
                        cbPeriodicSec,
                        cbEndOfRoundMin,
                        cbEndOfRoundSec,
                        cbStartEndSound,
                        cbInnerPeriodicSound,
                        cbRoundWarningSound,
                        cbEndOfRestSound,
                        cbProfileNames
                )
        );

        new BoxingController(labels_bt, buttons, checkBoxes_bt, colorPickers, comboBoxes, scrollPane).__init();
        // =========================================================================== //
    }
}
