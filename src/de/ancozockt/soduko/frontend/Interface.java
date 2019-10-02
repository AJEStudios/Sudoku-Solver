package de.ancozockt.soduko.frontend;

import de.ancozockt.soduko.backend.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Interface implements ActionListener {

    private JFrame frame;
    private ArrayList<JTextField> textFields;

    private JButton solve;
    private JButton reset;

    public Interface(){
        textFields = new ArrayList<>();

        frame = new JFrame(){
            public void paint(Graphics graphics){
                super.paint(graphics);

                Graphics2D graphics2D = (Graphics2D) graphics;
                graphics2D.setStroke(new BasicStroke(3));

                Line2D L1 = new Line2D.Double(35, 160, 520, 160);
                graphics2D.draw(L1);

                Line2D L2 = new Line2D.Double(35, 325, 520, 325);
                graphics2D.draw(L2);
                /*
                Line2D H1 = new Line2D.Float(30, 217, 515, 217);
                Line2D H2 = new Line2D.Float(30, 382, 515, 382);
                graphics2D.draw(H1);
                graphics2D.draw(H2);

                Line2D V1 = new Line2D.Float(195, 50, 195, 543);
                Line2D V2 = new Line2D.Float(360, 50, 360, 543);
                graphics2D.draw(V1);
                graphics2D.draw(V2);
                 */
            }
        };
        frame.setTitle("Sudoku Solver");
        frame.setLayout(null);
        frame.setSize(550, 650);


        int row = 0;
        int column = 0;
        for(int i = 0; i < 81; i++){
            if(row == 9){
                column++;
                row = 0;
            }

            JTextField field = new JTextField();

            field.setHorizontalAlignment(JTextField.CENTER);
            field.setBounds(25+(row*55), 25+(column*55), 50, 50);
            field.setFont(new Font("Arial", Font.BOLD, 25));

            frame.add(field);
            textFields.add(field);

            row++;
        }

        solve = new JButton("Solve");
        solve.setBounds(175, 525, 100, 50);
        solve.addActionListener(this);
        solve.setActionCommand("solve");
        frame.add(solve);

        reset = new JButton("Reset");
        reset.setBounds(280, 525, 100, 50);
        reset.addActionListener(this);
        reset.setActionCommand("reset");
        frame.add(reset);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("solve")){
            int i = 0;

            String[] fields = new String[81];
            for(JTextField field : textFields){
                field.setEditable(false);
                fields[i] = field.getText();
                i++;
            }

            new Sudoku(fields, textFields);

            solve.setEnabled(false);

        }else if(e.getActionCommand().equalsIgnoreCase("reset")){
            for(JTextField field : textFields){
                field.setText("");
                field.setEditable(true);
                field.setBackground(Color.WHITE);
            }

            solve.setEnabled(true);
        }
    }
}
