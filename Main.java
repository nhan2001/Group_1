import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main implements ActionListener {

    /* create User interface by GUI object */

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button;
    private JFrame frame;
    private JPanel panel;
    public Main(){
        /* this is GUI constructor with frame, button and panel*/
        frame = new JFrame();

        button = new JButton("Data");
        button.addActionListener(aa -> {
            Scanner sc = new Scanner(System.in);

            /* date format */
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");

            Date date = null;
            System.out.println("===============================");
            try {
                /* input star day */
                System.out.print("Enter star day(dd-mm-yyy): ");
                date = df.parse(sc.nextLine());
            } catch (Exception e){
            }

            /* print the star day */
            System.out.println("Star day: " + df1.format(date));

            /* convert star day into milisec */
            long dateInms = date.getTime();

            /* add the time range (7 days in milisec) */
            Date endDay = new Date(dateInms + (7 * 24 * 60 * 60 * 1000));
            System.out.println("End day: " + df1.format(endDay));
            System.out.println("===============================");
        });

        button1 = new JButton("Summary");

        button2 = new JButton("Tabular Display");
        button2.addActionListener(this);

        button3 = new JButton("Chart Display");

        panel = new JPanel();
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));

        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Java Project");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        /* this will let all the element in the table to be align to the right when FALSE and align to the left when TRUE*/
        boolean leftJustifiedRows = false;
        //the result that print to the console. Use two-dimensional array and each sub-array is a collum.
        String[][] table = new String[][]{{"iso_code", "continent", "location", "date ", "new_cases", "new_deaths", "population"},
                {"AFG", "Asia", "Afghanistan", "12/30/2020", "55", "2", "38928341"},
                {"ALB", "Europe", "Albania", "12/17/2020", "787", "15", "2877800"},
                {"DZA", "Africa", "Algeria", "9/4/2020", "304", "10", "43851043"},
                {"AND", "Europe", "Andorra", "6/15/2020", "0", "0", "77265"},
                {"AGO", "Africa", "Angola", "5/4/2020", "0", "0", "32866268"},
                {"ATG", "North America", "Antigua and Barbuda", "4/13/2020", "2", "0", "97928"}
        };



        // calculate the length of the column

        Map<Integer,Integer> columnLengths =  new HashMap<>();

        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null){
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()){
                columnLengths.put(i, a[i].length());
            }
        }));

        final StringBuilder formatString =  new StringBuilder("");
        String flag;
        flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s"));
        formatString.append("|\n");

        // print the table to console
        Stream.iterate(0, (i -> i < table.length), (i -> ++i)).
                forEach(a -> System.out.printf(formatString.toString(), table[a]));
    }
}