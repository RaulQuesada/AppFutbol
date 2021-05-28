package com.raulquesada.appfutbol.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SelectJornadaDialog extends DialogFragment {
    private NumberPicker.OnValueChangeListener valueChangeListener;
    private static final int MIN_JORNADA = 1;
    private int maxJornada;
    private int currentJornada;

    public SelectJornadaDialog(int maxJornada, int currentJornada) {
        this.maxJornada = maxJornada;
        this.currentJornada = currentJornada;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final NumberPicker numberPicker = new NumberPicker(getActivity());

        numberPicker.setMinValue(MIN_JORNADA);
        numberPicker.setMaxValue(maxJornada);
        updateNumberPicker(numberPicker);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Calendario");
        builder.setMessage("Seleccionar Jornada");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
                currentJornada = numberPicker.getValue();
                updateNumberPicker(numberPicker);
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
            }
        });

        builder.setView(numberPicker);
        return builder.create();
    }

    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
    private void updateNumberPicker(NumberPicker numberPicker){
        numberPicker.setValue(currentJornada);
    }
}
