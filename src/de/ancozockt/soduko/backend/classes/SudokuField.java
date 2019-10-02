package de.ancozockt.soduko.backend.classes;

import java.util.ArrayList;

public class SudokuField {

    private SudokuCorner corner;
    private SudokuLine horizontal;
    private SudokuLine vertical;

    private FieldValue value;
    private boolean given;

    private boolean solved;

    private ArrayList<FieldValue> posibilitys;
    private ArrayList<FieldValue> tempposibilitys;

    public SudokuField(SudokuCorner corner, SudokuLine horizontal, SudokuLine vertical, FieldValue value, boolean given){
        this.corner = corner;
        this.horizontal = horizontal;
        this.vertical = vertical;

        this.value = value;
        this.given = given;

        this.posibilitys = new ArrayList<>();
        this.tempposibilitys = new ArrayList<>();
    }

    public boolean isValid(){
        ArrayList<FieldValue> values = new ArrayList<>();
        for(SudokuField field : corner.getFields()){
            values.add(field.getValue());
        }
        for(SudokuField field : vertical.getFields()){
            values.add(field.getValue());
        }
        for(SudokuField field : horizontal.getFields()){
            values.add(field.getValue());
        }
        return !values.contains(getValue());
    }

    public void checkPosiblitys(){
        ArrayList<FieldValue> tempposibilitys = new ArrayList<>();
        for(FieldValue value : FieldValue.values()){
            if(value != FieldValue.EMPTY)
                tempposibilitys.add(value);
        }
        for(SudokuField field : corner.getFields()){
            if(field.isGiven()){
                tempposibilitys.remove(field.getValue());
            }
        }
        for(SudokuField field : vertical.getFields()){
            if(field.isGiven()){
                tempposibilitys.remove(field.getValue());
            }
        }
        for(SudokuField field : horizontal.getFields()){
            if(field.isGiven()){
                tempposibilitys.remove(field.getValue());
            }
        }
        posibilitys = tempposibilitys;
        if(posibilitys.size() == 1){
            value = posibilitys.get(0);
            given = true;
        }
    }

    public void checkTempPosiblitys(boolean corners, boolean lines){
        ArrayList<FieldValue> tempposibilitys = new ArrayList<>();
        for(FieldValue value : FieldValue.values()){
            if(value != FieldValue.EMPTY)
                tempposibilitys.add(value);
        }

        if(!isGiven()) {
            for (SudokuField field : corner.getFields()) {
                if (corners || field.isGiven())
                    tempposibilitys.remove(field.getValue());
            }
            for (SudokuField field : vertical.getFields()) {
                if (lines || field.isGiven())
                    tempposibilitys.remove(field.getValue());
            }
            for (SudokuField field : horizontal.getFields()) {
                if (lines || field.isGiven())
                    tempposibilitys.remove(field.getValue());
            }
        }
        this.tempposibilitys = tempposibilitys;
    }

    public boolean isGiven() {
        return given;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public FieldValue getValue() {
        return value;
    }

    public void setValue(FieldValue value) {
        this.value = value;
    }

    public ArrayList<FieldValue> getPosibilitys() {
        return posibilitys;
    }

    public ArrayList<FieldValue> getTempposibilitys() {
        return tempposibilitys;
    }

    public SudokuLine getHorizontal() {
        return horizontal;
    }

    public SudokuCorner getCorner() {
        return corner;
    }

    public SudokuLine getVertical() {
        return vertical;
    }
}
