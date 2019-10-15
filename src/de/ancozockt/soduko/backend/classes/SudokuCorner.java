package de.ancozockt.soduko.backend.classes;

import java.util.ArrayList;

//This is a corner
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
