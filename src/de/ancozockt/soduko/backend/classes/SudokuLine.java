package de.ancozockt.soduko.backend.classes;

import java.util.ArrayList;

public class SudokuLine {

    private ArrayList<SudokuField> fields;

    public SudokuLine(){
        this.fields = new ArrayList<>();
    }

    public ArrayList<SudokuField> getFields() {
        return fields;
    }

    public void addField(SudokuField field){
        fields.add(field);
    }
}
