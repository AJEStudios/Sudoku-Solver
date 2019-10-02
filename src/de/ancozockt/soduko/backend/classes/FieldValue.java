package de.ancozockt.soduko.backend.classes;


public enum FieldValue {

    EMPTY("-"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9");

    String value;

    FieldValue(String value){
        this.value = value;
    }

    public static FieldValue byValue(String val){
        FieldValue response = EMPTY;
        for(FieldValue value : values()){
            try {
                if (Integer.valueOf(val).equals(Integer.valueOf(value.getValue()))) {
                    response = value;
                }
            }catch (NumberFormatException ignored){}
        }
        return response;
    }

    public String getValue() {
        return value;
    }
}
