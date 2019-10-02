package de.ancozockt.soduko.backend;

import de.ancozockt.soduko.backend.classes.FieldValue;
import de.ancozockt.soduko.backend.classes.SudokuCorner;
import de.ancozockt.soduko.backend.classes.SudokuField;
import de.ancozockt.soduko.backend.classes.SudokuLine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Sudoku {

    private ArrayList<SudokuCorner> corners;

    private ArrayList<SudokuLine> vlines;
    private ArrayList<SudokuLine> hlines;

    private ArrayList<SudokuField> fields;

    public Sudoku(String[] fieldvals, ArrayList<JTextField> fields){
        corners = new ArrayList<>();
        vlines = new ArrayList<>();
        hlines = new ArrayList<>();
        this.fields = new ArrayList<>();

        createFields(fieldvals);

        solveSudoku();
        if (checkLines() && checkCorners()) {
            printSoduko(fields);
        } else {
            int a = 0;
            for (SudokuField field : this.fields) {
                if (field.isGiven()) {
                    fields.get(a).setBackground(Color.RED);
                    fields.get(a).setText(field.getValue().getValue());
                }
                a++;
            }
        }
    }

    private void createFields(String[] fieldvals){

        /*
            Corners
         */

        SudokuCorner left_left = new SudokuCorner(); corners.add(left_left);
        SudokuCorner left_middle = new SudokuCorner(); corners.add(left_middle);
        SudokuCorner left_right = new SudokuCorner(); corners.add(left_right);

        SudokuCorner middle_left = new SudokuCorner(); corners.add(middle_left);
        SudokuCorner middle_middle = new SudokuCorner(); corners.add(middle_middle);
        SudokuCorner middle_right = new SudokuCorner(); corners.add(middle_right);

        SudokuCorner right_left = new SudokuCorner(); corners.add(right_left);
        SudokuCorner right_middle = new SudokuCorner(); corners.add(right_middle);
        SudokuCorner right_right = new SudokuCorner(); corners.add(right_right);

        /*
        Lines
         */

        SudokuLine V_1 = new SudokuLine();  SudokuLine H_1 = new SudokuLine(); vlines.add(V_1); hlines.add(H_1);
        SudokuLine V_2 = new SudokuLine();  SudokuLine H_2 = new SudokuLine(); vlines.add(V_2); hlines.add(H_2);
        SudokuLine V_3 = new SudokuLine();  SudokuLine H_3 = new SudokuLine(); vlines.add(V_3); hlines.add(H_3);
        SudokuLine V_4 = new SudokuLine();  SudokuLine H_4 = new SudokuLine(); vlines.add(V_4); hlines.add(H_4);
        SudokuLine V_5 = new SudokuLine();  SudokuLine H_5 = new SudokuLine(); vlines.add(V_5); hlines.add(H_5);
        SudokuLine V_6 = new SudokuLine();  SudokuLine H_6 = new SudokuLine(); vlines.add(V_6); hlines.add(H_6);
        SudokuLine V_7 = new SudokuLine();  SudokuLine H_7 = new SudokuLine(); vlines.add(V_7); hlines.add(H_7);
        SudokuLine V_8 = new SudokuLine();  SudokuLine H_8 = new SudokuLine(); vlines.add(V_8); hlines.add(H_8);
        SudokuLine V_9 = new SudokuLine();  SudokuLine H_9 = new SudokuLine(); vlines.add(V_9); hlines.add(H_9);

        /*
        Corner 1 -> 3
         */

        SudokuField one_one = new SudokuField(left_left, H_1, V_1, FieldValue.byValue(fieldvals[0]), (FieldValue.byValue(fieldvals[0]) != FieldValue.EMPTY)); left_left.addField(one_one); H_1.addField(one_one); V_1.addField(one_one); fields.add(one_one);
        SudokuField one_two = new SudokuField(left_left, H_1, V_2, FieldValue.byValue(fieldvals[1]), (FieldValue.byValue(fieldvals[1]) != FieldValue.EMPTY)); left_left.addField(one_two); H_1.addField(one_two); V_2.addField(one_two); fields.add(one_two);
        SudokuField one_three = new SudokuField(left_left, H_1, V_3, FieldValue.byValue(fieldvals[2]), (FieldValue.byValue(fieldvals[2]) != FieldValue.EMPTY)); left_left.addField(one_three); H_1.addField(one_three); V_3.addField(one_three); fields.add(one_three);
        SudokuField one_four = new SudokuField(left_middle, H_1, V_4, FieldValue.byValue(fieldvals[3]), (FieldValue.byValue(fieldvals[3]) != FieldValue.EMPTY)); left_middle.addField(one_four); H_1.addField(one_four); V_4.addField(one_four); fields.add(one_four);
        SudokuField one_five = new SudokuField(left_middle, H_1, V_5, FieldValue.byValue(fieldvals[4]), (FieldValue.byValue(fieldvals[4]) != FieldValue.EMPTY)); left_middle.addField(one_five); H_1.addField(one_five); V_5.addField(one_five); fields.add(one_five);
        SudokuField one_six = new SudokuField(left_middle, H_1, V_6, FieldValue.byValue(fieldvals[5]), (FieldValue.byValue(fieldvals[5]) != FieldValue.EMPTY)); left_middle.addField(one_six); H_1.addField(one_six); V_6.addField(one_six); fields.add(one_six);
        SudokuField one_seven = new SudokuField(left_right, H_1, V_7, FieldValue.byValue(fieldvals[6]), (FieldValue.byValue(fieldvals[6]) != FieldValue.EMPTY)); left_right.addField(one_seven); H_1.addField(one_seven); V_7.addField(one_seven); fields.add(one_seven);
        SudokuField one_eight = new SudokuField(left_right, H_1, V_8, FieldValue.byValue(fieldvals[7]), (FieldValue.byValue(fieldvals[7]) != FieldValue.EMPTY)); left_right.addField(one_eight); H_1.addField(one_eight); V_8.addField(one_eight); fields.add(one_eight);
        SudokuField one_nine = new SudokuField(left_right, H_1, V_9, FieldValue.byValue(fieldvals[8]), (FieldValue.byValue(fieldvals[8]) != FieldValue.EMPTY)); left_right.addField(one_nine); H_1.addField(one_nine); V_9.addField(one_nine); fields.add(one_nine);

        SudokuField two_one = new SudokuField(left_left, H_2, V_1, FieldValue.byValue(fieldvals[9]), (FieldValue.byValue(fieldvals[9]) != FieldValue.EMPTY)); left_left.addField(two_one); H_2.addField(two_one); V_1.addField(two_one); fields.add(two_one);
        SudokuField two_two = new SudokuField(left_left, H_2, V_2, FieldValue.byValue(fieldvals[10]), (FieldValue.byValue(fieldvals[10]) != FieldValue.EMPTY)); left_left.addField(two_two); H_2.addField(two_two); V_2.addField(two_two); fields.add(two_two);
        SudokuField two_three = new SudokuField(left_left, H_2, V_3, FieldValue.byValue(fieldvals[11]), (FieldValue.byValue(fieldvals[11]) != FieldValue.EMPTY)); left_left.addField(two_three); H_2.addField(two_three); V_3.addField(two_three); fields.add(two_three);
        SudokuField two_four = new SudokuField(left_middle, H_2, V_4, FieldValue.byValue(fieldvals[12]), (FieldValue.byValue(fieldvals[12]) != FieldValue.EMPTY)); left_middle.addField(two_four); H_2.addField(two_four); V_4.addField(two_four); fields.add(two_four);
        SudokuField two_five = new SudokuField(left_middle, H_2, V_5, FieldValue.byValue(fieldvals[13]), (FieldValue.byValue(fieldvals[13]) != FieldValue.EMPTY)); left_middle.addField(two_five); H_2.addField(two_five); V_5.addField(two_five); fields.add(two_five);
        SudokuField two_six = new SudokuField(left_middle, H_2, V_6, FieldValue.byValue(fieldvals[14]), (FieldValue.byValue(fieldvals[14]) != FieldValue.EMPTY)); left_middle.addField(two_six); H_2.addField(two_six); V_6.addField(two_six); fields.add(two_six);
        SudokuField two_seven = new SudokuField(left_right, H_2, V_7, FieldValue.byValue(fieldvals[15]), (FieldValue.byValue(fieldvals[15]) != FieldValue.EMPTY)); left_right.addField(two_seven); H_2.addField(two_seven); V_7.addField(two_seven); fields.add(two_seven);
        SudokuField two_eight = new SudokuField(left_right, H_2, V_8, FieldValue.byValue(fieldvals[16]), (FieldValue.byValue(fieldvals[16]) != FieldValue.EMPTY)); left_right.addField(two_eight); H_2.addField(two_eight); V_8.addField(two_eight); fields.add(two_eight);
        SudokuField two_nine = new SudokuField(left_right, H_2, V_9, FieldValue.byValue(fieldvals[17]), (FieldValue.byValue(fieldvals[17]) != FieldValue.EMPTY)); left_right.addField(two_nine); H_2.addField(two_nine); V_9.addField(two_nine); fields.add(two_nine);

        SudokuField three_one = new SudokuField(left_left, H_3, V_1, FieldValue.byValue(fieldvals[18]), (FieldValue.byValue(fieldvals[18]) != FieldValue.EMPTY)); left_left.addField(three_one); H_3.addField(three_one); V_1.addField(three_one); fields.add(three_one);
        SudokuField three_two = new SudokuField(left_left, H_3, V_2, FieldValue.byValue(fieldvals[19]), (FieldValue.byValue(fieldvals[19]) != FieldValue.EMPTY)); left_left.addField(three_two); H_3.addField(three_two); V_2.addField(three_two); fields.add(three_two);
        SudokuField three_three = new SudokuField(left_left, H_3, V_3, FieldValue.byValue(fieldvals[20]), (FieldValue.byValue(fieldvals[20]) != FieldValue.EMPTY)); left_left.addField(three_three); H_3.addField(three_three); V_3.addField(three_three); fields.add(three_three);
        SudokuField three_four = new SudokuField(left_middle, H_3, V_4, FieldValue.byValue(fieldvals[21]), (FieldValue.byValue(fieldvals[21]) != FieldValue.EMPTY)); left_middle.addField(three_four); H_3.addField(three_four); V_4.addField(three_four); fields.add(three_four);
        SudokuField three_five = new SudokuField(left_middle, H_3, V_5, FieldValue.byValue(fieldvals[22]), (FieldValue.byValue(fieldvals[22]) != FieldValue.EMPTY)); left_middle.addField(three_five); H_3.addField(three_five); V_5.addField(three_five); fields.add(three_five);
        SudokuField three_six = new SudokuField(left_middle, H_3, V_6, FieldValue.byValue(fieldvals[23]), (FieldValue.byValue(fieldvals[23]) != FieldValue.EMPTY)); left_middle.addField(three_six); H_3.addField(three_six); V_6.addField(three_six); fields.add(three_six);
        SudokuField three_seven = new SudokuField(left_right, H_3, V_7, FieldValue.byValue(fieldvals[24]), (FieldValue.byValue(fieldvals[24]) != FieldValue.EMPTY)); left_right.addField(three_seven); H_3.addField(three_seven); V_7.addField(three_seven); fields.add(three_seven);
        SudokuField three_eight = new SudokuField(left_right, H_3, V_8, FieldValue.byValue(fieldvals[25]), (FieldValue.byValue(fieldvals[25]) != FieldValue.EMPTY)); left_right.addField(three_eight); H_3.addField(three_eight); V_8.addField(three_eight); fields.add(three_eight);
        SudokuField three_nine = new SudokuField(left_right, H_3, V_9, FieldValue.byValue(fieldvals[26]), (FieldValue.byValue(fieldvals[26]) != FieldValue.EMPTY)); left_right.addField(three_nine); H_3.addField(three_nine); V_9.addField(three_nine); fields.add(three_nine);

        /*
        Corner 4->6
         */

        SudokuField four_one = new SudokuField(middle_left, H_4, V_1, FieldValue.byValue(fieldvals[27]), (FieldValue.byValue(fieldvals[27]) != FieldValue.EMPTY)); middle_left.addField(four_one); H_4.addField(four_one); V_1.addField(four_one); fields.add(four_one);
        SudokuField four_two = new SudokuField(middle_left, H_4, V_2, FieldValue.byValue(fieldvals[28]), (FieldValue.byValue(fieldvals[28]) != FieldValue.EMPTY)); middle_left.addField(four_two); H_4.addField(four_two); V_2.addField(four_two); fields.add(four_two);
        SudokuField four_three = new SudokuField(middle_left, H_4, V_3, FieldValue.byValue(fieldvals[29]), (FieldValue.byValue(fieldvals[29]) != FieldValue.EMPTY)); middle_left.addField(four_three); H_4.addField(four_three); V_3.addField(four_three); fields.add(four_three);
        SudokuField four_four = new SudokuField(middle_middle, H_4, V_4, FieldValue.byValue(fieldvals[30]), (FieldValue.byValue(fieldvals[30]) != FieldValue.EMPTY)); middle_middle.addField(four_four); H_4.addField(four_four); V_4.addField(four_four); fields.add(four_four);
        SudokuField four_five = new SudokuField(middle_middle, H_4, V_5, FieldValue.byValue(fieldvals[31]), (FieldValue.byValue(fieldvals[31]) != FieldValue.EMPTY)); middle_middle.addField(four_five); H_4.addField(four_five); V_5.addField(four_five); fields.add(four_five);
        SudokuField four_six = new SudokuField(middle_middle, H_4, V_6, FieldValue.byValue(fieldvals[32]), (FieldValue.byValue(fieldvals[32]) != FieldValue.EMPTY)); middle_middle.addField(four_six); H_4.addField(four_six); V_6.addField(four_six); fields.add(four_six);
        SudokuField four_seven = new SudokuField(middle_right, H_4, V_7, FieldValue.byValue(fieldvals[33]), (FieldValue.byValue(fieldvals[33]) != FieldValue.EMPTY)); middle_right.addField(four_seven); H_4.addField(four_seven); V_7.addField(four_seven); fields.add(four_seven);
        SudokuField four_eight = new SudokuField(middle_right, H_4, V_8, FieldValue.byValue(fieldvals[34]), (FieldValue.byValue(fieldvals[34]) != FieldValue.EMPTY)); middle_right.addField(four_eight); H_4.addField(four_eight); V_8.addField(four_eight); fields.add(four_eight);
        SudokuField four_nine = new SudokuField(middle_right, H_4, V_9, FieldValue.byValue(fieldvals[35]), (FieldValue.byValue(fieldvals[35]) != FieldValue.EMPTY)); middle_right.addField(four_nine); H_4.addField(four_nine); V_9.addField(four_nine); fields.add(four_nine);

        SudokuField five_one = new SudokuField(middle_left, H_5, V_1, FieldValue.byValue(fieldvals[36]), (FieldValue.byValue(fieldvals[36]) != FieldValue.EMPTY)); middle_left.addField(five_one); H_5.addField(five_one); V_1.addField(five_one); fields.add(five_one);
        SudokuField five_two = new SudokuField(middle_left, H_5, V_2, FieldValue.byValue(fieldvals[37]), (FieldValue.byValue(fieldvals[37]) != FieldValue.EMPTY)); middle_left.addField(five_two); H_5.addField(five_two); V_2.addField(five_two); fields.add(five_two);
        SudokuField five_three = new SudokuField(middle_left, H_5, V_3, FieldValue.byValue(fieldvals[38]), (FieldValue.byValue(fieldvals[38]) != FieldValue.EMPTY)); middle_left.addField(five_three); H_5.addField(five_three); V_3.addField(five_three); fields.add(five_three);
        SudokuField five_four = new SudokuField(middle_middle, H_5, V_4, FieldValue.byValue(fieldvals[39]), (FieldValue.byValue(fieldvals[39]) != FieldValue.EMPTY)); middle_middle.addField(five_four); H_5.addField(five_four); V_4.addField(five_four); fields.add(five_four);
        SudokuField five_five = new SudokuField(middle_middle, H_5, V_5, FieldValue.byValue(fieldvals[40]), (FieldValue.byValue(fieldvals[40]) != FieldValue.EMPTY)); middle_middle.addField(five_five); H_5.addField(five_five); V_5.addField(five_five); fields.add(five_five);
        SudokuField five_six = new SudokuField(middle_middle, H_5, V_6, FieldValue.byValue(fieldvals[41]), (FieldValue.byValue(fieldvals[41]) != FieldValue.EMPTY)); middle_middle.addField(five_six); H_5.addField(five_six); V_6.addField(five_six); fields.add(five_six);
        SudokuField five_seven = new SudokuField(middle_right, H_5, V_7, FieldValue.byValue(fieldvals[42]), (FieldValue.byValue(fieldvals[42]) != FieldValue.EMPTY)); middle_right.addField(five_seven); H_5.addField(five_seven); V_7.addField(five_seven); fields.add(five_seven);
        SudokuField five_eight = new SudokuField(middle_right, H_5, V_8, FieldValue.byValue(fieldvals[43]), (FieldValue.byValue(fieldvals[43]) != FieldValue.EMPTY)); middle_right.addField(five_eight); H_5.addField(five_eight); V_8.addField(five_eight); fields.add(five_eight);
        SudokuField five_nine = new SudokuField(middle_right, H_5, V_9, FieldValue.byValue(fieldvals[44]), (FieldValue.byValue(fieldvals[44]) != FieldValue.EMPTY)); middle_right.addField(five_nine); H_5.addField(five_nine); V_9.addField(five_nine); fields.add(five_nine);

        SudokuField six_one = new SudokuField(middle_left, H_6, V_1, FieldValue.byValue(fieldvals[45]), (FieldValue.byValue(fieldvals[45]) != FieldValue.EMPTY)); middle_left.addField(six_one); H_6.addField(six_one); V_1.addField(six_one); fields.add(six_one);
        SudokuField six_two = new SudokuField(middle_left, H_6, V_2, FieldValue.byValue(fieldvals[46]), (FieldValue.byValue(fieldvals[46]) != FieldValue.EMPTY)); middle_left.addField(six_two); H_6.addField(six_two); V_2.addField(six_two); fields.add(six_two);
        SudokuField six_three = new SudokuField(middle_left, H_6, V_3, FieldValue.byValue(fieldvals[47]), (FieldValue.byValue(fieldvals[47]) != FieldValue.EMPTY)); middle_left.addField(six_three); H_6.addField(six_three); V_3.addField(six_three); fields.add(six_three);
        SudokuField six_four = new SudokuField(middle_middle, H_6, V_4, FieldValue.byValue(fieldvals[48]), (FieldValue.byValue(fieldvals[48]) != FieldValue.EMPTY)); middle_middle.addField(six_four); H_6.addField(six_four); V_4.addField(six_four); fields.add(six_four);
        SudokuField six_five = new SudokuField(middle_middle, H_6, V_5, FieldValue.byValue(fieldvals[49]), (FieldValue.byValue(fieldvals[49]) != FieldValue.EMPTY)); middle_middle.addField(six_five); H_6.addField(six_five); V_5.addField(six_five); fields.add(six_five);
        SudokuField six_six = new SudokuField(middle_middle, H_6, V_6, FieldValue.byValue(fieldvals[50]), (FieldValue.byValue(fieldvals[50]) != FieldValue.EMPTY)); middle_middle.addField(six_six); H_6.addField(six_six); V_6.addField(six_six); fields.add(six_six);
        SudokuField six_seven = new SudokuField(middle_right, H_6, V_7, FieldValue.byValue(fieldvals[51]), (FieldValue.byValue(fieldvals[51]) != FieldValue.EMPTY)); middle_right.addField(six_seven); H_6.addField(six_seven); V_7.addField(six_seven); fields.add(six_seven);
        SudokuField six_eight = new SudokuField(middle_right, H_6, V_8, FieldValue.byValue(fieldvals[52]), (FieldValue.byValue(fieldvals[52]) != FieldValue.EMPTY)); middle_right.addField(six_eight); H_6.addField(six_eight); V_8.addField(six_eight); fields.add(six_eight);
        SudokuField six_nine = new SudokuField(middle_right, H_6, V_9, FieldValue.byValue(fieldvals[53]), (FieldValue.byValue(fieldvals[53]) != FieldValue.EMPTY)); middle_right.addField(six_nine); H_6.addField(six_nine); V_9.addField(six_nine); fields.add(six_nine);


        /*
        Corner R_L
         */

        SudokuField seven_one = new SudokuField(right_left, H_7, V_1, FieldValue.byValue(fieldvals[54]), (FieldValue.byValue(fieldvals[54]) != FieldValue.EMPTY)); right_left.addField(seven_one); H_7.addField(seven_one); V_1.addField(seven_one); fields.add(seven_one);
        SudokuField seven_two = new SudokuField(right_left, H_7, V_2, FieldValue.byValue(fieldvals[55]), (FieldValue.byValue(fieldvals[55]) != FieldValue.EMPTY)); right_left.addField(seven_two); H_7.addField(seven_two); V_2.addField(seven_two); fields.add(seven_two);
        SudokuField seven_three = new SudokuField(right_left, H_7, V_3, FieldValue.byValue(fieldvals[56]), (FieldValue.byValue(fieldvals[56]) != FieldValue.EMPTY)); right_left.addField(seven_three); H_7.addField(seven_three); V_3.addField(seven_three); fields.add(seven_three);
        SudokuField seven_four = new SudokuField(right_middle, H_7, V_4, FieldValue.byValue(fieldvals[57]), (FieldValue.byValue(fieldvals[57]) != FieldValue.EMPTY)); right_middle.addField(seven_four); H_7.addField(seven_four); V_4.addField(seven_four); fields.add(seven_four);
        SudokuField seven_five = new SudokuField(right_middle, H_7, V_5, FieldValue.byValue(fieldvals[58]), (FieldValue.byValue(fieldvals[58]) != FieldValue.EMPTY)); right_middle.addField(seven_five); H_7.addField(seven_five); V_5.addField(seven_five); fields.add(seven_five);
        SudokuField seven_six = new SudokuField(right_middle, H_7, V_6, FieldValue.byValue(fieldvals[59]), (FieldValue.byValue(fieldvals[59]) != FieldValue.EMPTY)); right_middle.addField(seven_six); H_7.addField(seven_six); V_6.addField(seven_six); fields.add(seven_six);
        SudokuField seven_seven = new SudokuField(right_right, H_7, V_7, FieldValue.byValue(fieldvals[60]), (FieldValue.byValue(fieldvals[60]) != FieldValue.EMPTY)); right_right.addField(seven_seven); H_7.addField(seven_seven); V_7.addField(seven_seven); fields.add(seven_seven);
        SudokuField seven_eight = new SudokuField(right_right, H_7, V_8, FieldValue.byValue(fieldvals[61]), (FieldValue.byValue(fieldvals[61]) != FieldValue.EMPTY)); right_right.addField(seven_eight); H_7.addField(seven_eight); V_8.addField(seven_eight); fields.add(seven_eight);
        SudokuField seven_nine = new SudokuField(right_right, H_7, V_9, FieldValue.byValue(fieldvals[62]), (FieldValue.byValue(fieldvals[62]) != FieldValue.EMPTY)); right_right.addField(seven_nine); H_7.addField(seven_nine); V_9.addField(seven_nine); fields.add(seven_nine);

        SudokuField eight_one = new SudokuField(right_left, H_8, V_1, FieldValue.byValue(fieldvals[63]), (FieldValue.byValue(fieldvals[63]) != FieldValue.EMPTY)); right_left.addField(eight_one); H_8.addField(eight_one); V_1.addField(eight_one); fields.add(eight_one);
        SudokuField eight_two = new SudokuField(right_left, H_8, V_2, FieldValue.byValue(fieldvals[64]), (FieldValue.byValue(fieldvals[64]) != FieldValue.EMPTY)); right_left.addField(eight_two); H_8.addField(eight_two); V_2.addField(eight_two); fields.add(eight_two);
        SudokuField eight_three = new SudokuField(right_left, H_8, V_3, FieldValue.byValue(fieldvals[65]), (FieldValue.byValue(fieldvals[65]) != FieldValue.EMPTY)); right_left.addField(eight_three); H_8.addField(eight_three); V_3.addField(eight_three); fields.add(eight_three);
        SudokuField eight_four = new SudokuField(right_middle, H_8, V_4, FieldValue.byValue(fieldvals[66]), (FieldValue.byValue(fieldvals[66]) != FieldValue.EMPTY)); right_middle.addField(eight_four); H_8.addField(eight_four); V_4.addField(eight_four); fields.add(eight_four);
        SudokuField eight_five = new SudokuField(right_middle, H_8, V_5, FieldValue.byValue(fieldvals[67]), (FieldValue.byValue(fieldvals[67]) != FieldValue.EMPTY)); right_middle.addField(eight_five); H_8.addField(eight_five); V_5.addField(eight_five); fields.add(eight_five);
        SudokuField eight_six = new SudokuField(right_middle, H_8, V_6, FieldValue.byValue(fieldvals[68]), (FieldValue.byValue(fieldvals[68]) != FieldValue.EMPTY)); right_middle.addField(eight_six); H_8.addField(eight_six); V_6.addField(eight_six); fields.add(eight_six);
        SudokuField eight_seven = new SudokuField(right_right, H_8, V_7, FieldValue.byValue(fieldvals[69]), (FieldValue.byValue(fieldvals[69]) != FieldValue.EMPTY)); right_right.addField(eight_seven); H_8.addField(eight_seven); V_7.addField(eight_seven); fields.add(eight_seven);
        SudokuField eight_eight = new SudokuField(right_right, H_8, V_8, FieldValue.byValue(fieldvals[70]), (FieldValue.byValue(fieldvals[70]) != FieldValue.EMPTY)); right_right.addField(eight_eight); H_8.addField(eight_eight); V_8.addField(eight_eight); fields.add(eight_eight);
        SudokuField eight_nine = new SudokuField(right_right, H_8, V_9, FieldValue.byValue(fieldvals[71]), (FieldValue.byValue(fieldvals[71]) != FieldValue.EMPTY)); right_right.addField(eight_nine); H_8.addField(eight_nine); V_9.addField(eight_nine); fields.add(eight_nine);

        SudokuField nine_one = new SudokuField(right_left, H_9, V_1, FieldValue.byValue(fieldvals[72]), (FieldValue.byValue(fieldvals[72]) != FieldValue.EMPTY)); right_left.addField(nine_one); H_9.addField(nine_one); V_1.addField(nine_one); fields.add(nine_one);
        SudokuField nine_two = new SudokuField(right_left, H_9, V_2, FieldValue.byValue(fieldvals[73]), (FieldValue.byValue(fieldvals[73]) != FieldValue.EMPTY)); right_left.addField(nine_two); H_9.addField(nine_two); V_2.addField(nine_two); fields.add(nine_two);
        SudokuField nine_three = new SudokuField(right_left, H_9, V_3, FieldValue.byValue(fieldvals[74]), (FieldValue.byValue(fieldvals[74]) != FieldValue.EMPTY)); right_left.addField(nine_three); H_9.addField(nine_three); V_3.addField(nine_three); fields.add(nine_three);
        SudokuField nine_four = new SudokuField(right_middle, H_9, V_4, FieldValue.byValue(fieldvals[75]), (FieldValue.byValue(fieldvals[75]) != FieldValue.EMPTY)); right_middle.addField(nine_four); H_9.addField(nine_four); V_4.addField(nine_four); fields.add(nine_four);
        SudokuField nine_five = new SudokuField(right_middle, H_9, V_5, FieldValue.byValue(fieldvals[76]), (FieldValue.byValue(fieldvals[76]) != FieldValue.EMPTY)); right_middle.addField(nine_five); H_9.addField(nine_five); V_5.addField(nine_five); fields.add(nine_five);
        SudokuField nine_six = new SudokuField(right_middle, H_9, V_6, FieldValue.byValue(fieldvals[77]), (FieldValue.byValue(fieldvals[77]) != FieldValue.EMPTY)); right_middle.addField(nine_six); H_9.addField(nine_six); V_6.addField(nine_six); fields.add(nine_six);
        SudokuField nine_seven = new SudokuField(right_right, H_9, V_7, FieldValue.byValue(fieldvals[78]), (FieldValue.byValue(fieldvals[78]) != FieldValue.EMPTY)); right_right.addField(nine_seven); H_9.addField(nine_seven); V_7.addField(nine_seven); fields.add(nine_seven);
        SudokuField nine_eight = new SudokuField(right_right, H_9, V_8, FieldValue.byValue(fieldvals[79]), (FieldValue.byValue(fieldvals[79]) != FieldValue.EMPTY)); right_right.addField(nine_eight); H_9.addField(nine_eight); V_8.addField(nine_eight); fields.add(nine_eight);
        SudokuField nine_nine = new SudokuField(right_right, H_9, V_9, FieldValue.byValue(fieldvals[80]), (FieldValue.byValue(fieldvals[80]) != FieldValue.EMPTY)); right_right.addField(nine_nine); H_9.addField(nine_nine); V_9.addField(nine_nine); fields.add(nine_nine);
    }

    private boolean solveSudoku(){
        for(int row = 0; row < 9; row++){
            for(int column = 0; column < 9; column++){
                SudokuLine line = vlines.get(row);
                if(!line.getFields().get(column).isGiven()) {
                    if (line.getFields().get(column).getValue() == FieldValue.EMPTY) {
                        SudokuField field = line.getFields().get(column);
                        for (FieldValue value : FieldValue.values()) {
                            if (isAllowed(value, field)) {
                                field.setValue(value);
                                if (solveSudoku()) {
                                    return true;
                                } else {
                                    field.setValue(FieldValue.EMPTY);
                                }
                            }
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isAllowed(FieldValue value, SudokuField field){
        ArrayList<FieldValue> values = new ArrayList<>();
        for(SudokuField corner : field.getCorner().getFields()){
            values.add(corner.getValue());
        }
        for(SudokuField vertical : field.getVertical().getFields()){
            values.add(vertical.getValue());
        }
        for(SudokuField horizontal : field.getHorizontal().getFields()){
            values.add(horizontal.getValue());
        }
        return !values.contains(value);
    }

    private void printSoduko(ArrayList<JTextField> fields){
        int a = 0;
        for(SudokuField field : this.fields){
            fields.get(a).setText(field.getValue().getValue());
            if(field.isGiven()) {
                fields.get(a).setBackground(Color.YELLOW);
            }
            a++;
        }
    }

    private boolean checkFields(ArrayList<SudokuField> fields){
        Integer[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        for(SudokuField field : fields){
            if(field.getValue() != FieldValue.EMPTY){
                int val = Integer.valueOf(field.getValue().getValue());
                values[val - 1] += 1;
            }
        }
        int covals = 0;
        for(int i : values){
            if(i == 1)
                covals++;
        }

        return covals==9;
    }

    private boolean checkCorners(){
        int correct = 0;
        for(SudokuCorner corner : corners){
            if(checkFields(corner.getFields()))
                correct++;
        }
        if(correct == 9)
            return true;

        return false;
    }

    private boolean checkLines(){
        int correct = 0;
        for(SudokuLine line : hlines){
            if(checkFields(line.getFields()))
                correct++;
        }
        for(SudokuLine line : vlines){
            if(checkFields(line.getFields()))
                correct++;
        }
        if(correct == 18)
            return true;

        return false;
    }

}
