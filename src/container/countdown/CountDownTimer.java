package container.countdown;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;

public class CountDownTimer {

    private final JFXSlider sliderHrs;
    private final JFXSlider sliderMin;
    private final JFXSlider sliderSec;
    private final Label     lbHours;
    private final Label     lbMinutes;
    private final Label     lbSeconds;
    private final Label     lbScreen;
    private final JFXButton btnAction;

    private final Timeline  timer;

    private Integer         hours;
    private Integer         minutes;
    private Integer         seconds;
    private Integer         mSec;
    private BooleanProperty isRunning;

    public CountDownTimer(ArrayList<JFXSlider> sliders,
                          ArrayList<Label>     labels,
                          ArrayList<Object>    controls)
    {
        sliderHrs = sliders.get(0);
        sliderMin = sliders.get(1);
        sliderSec = sliders.get(2);
        lbHours   = labels.get(0);
        lbMinutes = labels.get(1);
        lbSeconds = labels.get(2);
        lbScreen  = labels.get(3);
        btnAction = (JFXButton) controls.get(0);

        timer     = new Timeline();
        hours     = 0;
        minutes   = 0;
        seconds   = 0;
        mSec      = 0;
        isRunning = new SimpleBooleanProperty();
    }

    public void init__() {
        sliderHrs.setValue(0);
        sliderMin.setValue(0);
        sliderSec.setValue(0);

        sliderHrs.valueProperty().addListener((o, ov, nv) -> lbHours.setText(format(Math.round(nv.floatValue()))));
        sliderMin.valueProperty().addListener((o, ov, nv) -> lbMinutes.setText(format(Math.round(nv.floatValue()))));
        sliderSec.valueProperty().addListener((o, ov, nv) -> lbSeconds.setText(format(Math.round(nv.floatValue()))));

        isRunning.addListener((o, ov, nv) -> runningStateChanged(nv));
        isRunning.set(false);

        btnAction.setOnAction(this::onActionEvent);

        KeyFrame frame = new KeyFrame(Duration.millis(10), this::onTimeTick);
        timer.getKeyFrames().add(frame);
        timer.setCycleCount(Timeline.INDEFINITE);
    }


    private void onActionEvent(ActionEvent event) {

        hours   = Integer.valueOf(lbHours.getText());
        minutes = Integer.valueOf(lbMinutes.getText());
        seconds = Integer.valueOf(lbSeconds.getText());

        // if time is zero do nothing
        if (hours == 0 && minutes == 0 && seconds == 0) return;

        if (isRunning.get()) { stopCountDown(); }
        else { timer.playFromStart(); isRunning.set(true); }

        event.consume();
    }

    private void onTimeTick(ActionEvent event) {
        if (hours == 0 && minutes == 0 && seconds == 0 && mSec == 0) soundAlarm();
        if (mSec == 0) {
            if (seconds > 0) {
                seconds--;
                mSec    = 99;
            }
            else if (minutes > 0) {
                minutes--;
                seconds = 59;
                mSec    = 99;
            }
            else if (hours > 0){
                hours--;
                minutes = 59;
                seconds = 59;
                mSec    = 99;
            }
        }
        else mSec--;

        lbScreen.setText(format(hours) + ":" + format(minutes) + ":" + format(seconds) + ":" + format(mSec));
        event.consume();
    }

    private void runningStateChanged(boolean newState) {
        btnAction.setText(newState ? "Stop" : "Start");

        sliderHrs.setDisable(newState);
        sliderMin.setDisable(newState);
        sliderSec.setDisable(newState);
    }

    private String format(Integer integer) {
        if (integer < 10) return "0" + integer;
        return String.valueOf(integer);
    }

    private void soundAlarm() { stopCountDown(); }

    private void stopCountDown() {
        timer.stop();
        isRunning.set(false);
        lbScreen.setText("00:00:00:00");
    }
}
