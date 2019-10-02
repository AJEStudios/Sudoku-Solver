package de.ancozockt.soduko.backend.classes;

import java.util.ArrayList;

public class SudokuCorner {

    private ArrayList<SudokuField> fields;

    public SudokuCorner(){
        this.fields = new ArrayList<>();
    }

    public ArrayList<SudokuField> getFields() {
        return fields;
    }

    public void addField(SudokuField field){
        fields.add(field);
    }
}
