package com.mofasa.ui;

import com.sun.istack.internal.Nullable;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class Slider extends JPanel {
    private JSlider slider;
    private JLabel label;
    Slider(int start,int end,int def,@Nullable ChangeListener listener){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        label = new JLabel(Integer.toString(def));
        slider = new JSlider(start,end,def);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.addChangeListener(changeEvent -> {label.setText(Integer.toString(slider.getValue()));});
        if(listener!=null)slider.addChangeListener(listener);
        add(label);
        add(slider);
    }
    public int getValue(){
        return slider.getValue();
    }
}
