package com.raulquesada.appfutbol.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

/**
 * The type Select jornada dialog.
 */
public class SelectJornadaDialog extends DialogFragment {
    /**
     * The Value change listener.
     */
    private NumberPicker.OnValueChangeListener valueChangeListener;
    /**
     * The constant MIN_JORNADA.
     */
    private static final int MIN_JORNADA = 1;
    /**
     * The Max jornada.
     */
    private int maxJornada;
    /**
     * The Current jornada.
     */
    private int currentJornada;

    /**
     * Instantiates a new Select jornada dialog.
     *
     * @param maxJornada     the max jornada
     * @param currentJornada the current jornada
     */
    public SelectJornadaDialog(int maxJornada, int currentJornada) {
        this.maxJornada = maxJornada;
        this.currentJornada = currentJornada;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //NumberPIcker
        final NumberPicker numberPicker = new NumberPicker(getActivity());

        numberPicker.setMinValue(MIN_JORNADA);//set jornada minima
        numberPicker.setMaxValue(maxJornada);//set jornada máxima
        updateNumberPicker(numberPicker);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Calendario");
        builder.setMessage("Seleccionar Jornada");

        //Si pulsa OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                valueChangeListener.onValueChange(numberPicker,
                        numberPicker.getValue(), numberPicker.getValue());
                currentJornada = numberPicker.getValue();
                updateNumberPicker(numberPicker);
            }
        });

        //Si pulsa cancelar
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

    /**
     * Gets value change listener.
     *
     * @return the value change listener
     */
    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    /**
     * Sets value change listener.
     *
     * @param valueChangeListener the value change listener
     */
    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }

    /**
     * Update number picker.
     *
     * @param numberPicker the number picker
     */
    private void updateNumberPicker(NumberPicker numberPicker){
        numberPicker.setValue(currentJornada);
    }
}
