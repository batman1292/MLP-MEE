package ai_assignment1;

/**
 *
 * @author Jeep
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class RunGraph extends ApplicationFrame implements ActionListener {

    static XYSeriesCollection dataset = new XYSeriesCollection();
    /**
     * The time series data.
     */
    static ArrayList<Perceptron> perceptron = new ArrayList<>();
    static ArrayList<Double> Weight00 = new ArrayList<>();
    static ArrayList<Double> Weight01 = new ArrayList<>();
    static ArrayList<Double> Weight02 = new ArrayList<>();
    static ArrayList<Double> Weight10 = new ArrayList<>();
    static ArrayList<Double> Weight11 = new ArrayList<>();
    static ArrayList<Double> Weight12 = new ArrayList<>();
    static ArrayList<Double> Weight20 = new ArrayList<>();
    static ArrayList<Double> Weight21 = new ArrayList<>();
    static ArrayList<Double> Weight22 = new ArrayList<>();
    static ArrayList<Double> errorGraph = new ArrayList<>();
    static int PerceptronInLv[] = {2, 1};
    static int TrainingSet[][] = {{0, 0, 0},
        {0, 1, 1},
        {1, 0, 1},
        {1, 1, 0}};
    static double ErrorValue;
    static double sose;                                     // sose = Sum of mean Square Error
    static double ExpectValue;                              // Expect Value of Output
    static int epoch = 0;                                       // count epoch
    static int setGraph;
    static Perceptron p0 = new Perceptron(0, 0);                // Initial Perceptron #0 in lv.0 
    static Perceptron p1 = new Perceptron(1, 0);                // Initial Perceptron #1 in lv.0 
    static Perceptron p2 = new Perceptron(2, 1);                // Initial Perceptron #2 in lv.1 
    static JFreeChart chart;
    static XYSeries s1 = new XYSeries("Weight 00");
    static XYSeries s2 = new XYSeries("Weight 01");
    static XYSeries s3 = new XYSeries("Weight 02");
    static XYSeries s4 = new XYSeries("Weight 10");
    static XYSeries s5 = new XYSeries("Weight 11");
    static XYSeries s6 = new XYSeries("Weight 12");
    static XYSeries s7 = new XYSeries("Weight 20");
    static XYSeries s8 = new XYSeries("Weight 21");
    static XYSeries s9 = new XYSeries("Weight 22");
    static XYSeries s21 = new XYSeries("Error Mean Square");
    static RunGraph demo;
    static RunGraph demo2;
    static int i = 0;
    static int j = 0;
    static JButton button = new JButton("");
    static JButton button2 = new JButton("");
    static JButton start = new JButton("START");
    static boolean boolcheck = false;
    /**
     * Constructs a new demonstration application.
     *
     * @param title the frame title.
     */
    public RunGraph(String title, int set) {
        super(title);   // set title bar
        this.setGraph = set;
        XYDataset dataset = null;
        dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        button.setActionCommand("ADD_DATA");    //create button 
        button.addActionListener(this);
        button2.setActionCommand("ADD_DATA_TWO");
        button2.addActionListener(this);
        start.setActionCommand("START");    //create button 
        start.addActionListener(this);
        JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        content.add(start, BorderLayout.SOUTH);
        chartPanel.setPreferredSize(new java.awt.Dimension(683, 384));
        setContentPane(content);
    }

    private JFreeChart createChart(XYDataset dataset) {
        if (setGraph == 1) {
            chart = ChartFactory.createXYLineChart(
                    "Weight per Epoch",// title
                    "Epoch",// x-axis label
                    "Weight",// y-axis label
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,// create legend?
                    false,// generate tooltips?
                    false);// generate URLs?
            chart.setBackgroundPaint(Color.white);
            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);
            plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            XYItemRenderer r = plot.getRenderer();
            if (r instanceof XYLineAndShapeRenderer) {
                XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
                renderer.setShapesFilled(Boolean.TRUE);
                renderer.setShapesVisible(true);
            }
        } else if (setGraph == 2) {
            chart = ChartFactory.createXYLineChart(
                    "Error mean square per Epoch", // title
                    "Epoch", // x-axis label
                    "Error Mean Square", // y-axis label
                    dataset, // data
                    PlotOrientation.VERTICAL,
                    true, // create legend?
                    false, // generate tooltips?
                    false // generate URLs?
                    );
            chart.setBackgroundPaint(Color.white);
            XYPlot plot = (XYPlot) chart.getPlot(); //Set property of chart
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);
            plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
            plot.setDomainCrosshairVisible(true);
            plot.setRangeCrosshairVisible(true);
            XYItemRenderer r = plot.getRenderer();
            if (r instanceof XYLineAndShapeRenderer) {
                XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
                renderer.setShapesFilled(Boolean.TRUE);
                renderer.setShapesVisible(true);
            }
        }
        return chart;
    }

    private static XYDataset createDataset() {
        if (setGraph == 1) {
            XYSeriesCollection dataset = new XYSeriesCollection();
            s1.add(i, Weight00.get(i));     //put xy point to dataset
            s2.add(i, Weight01.get(i));
            s3.add(i, Weight02.get(i));
            s4.add(i, Weight10.get(i));
            s5.add(i, Weight11.get(i));
            s6.add(i, Weight12.get(i));
            s7.add(i, Weight20.get(i));
            s8.add(i, Weight21.get(i));
            s9.add(i, Weight22.get(i));

            dataset.addSeries(s1);
            dataset.addSeries(s2);
            dataset.addSeries(s3);
            dataset.addSeries(s4);
            dataset.addSeries(s5);
            dataset.addSeries(s6);
            dataset.addSeries(s7);
            dataset.addSeries(s8);
            dataset.addSeries(s9);
            i++;
            return dataset;
        }
        if (setGraph == 2) {
            XYSeriesCollection dataset = new XYSeriesCollection();
            s21.add(j, errorGraph.get(j));
            dataset.addSeries(s21);
            j++;
            return dataset;
        }
        return null;
    }

    public void actionPerformed(ActionEvent e) {    // event for add new data to chart

       
        if (e.getActionCommand().equals("START")) {
            boolcheck = true;
            //System.out.println("dddddddddddddddddddddddddddd");
        }

        if (boolcheck==true) {
            if (e.getActionCommand().equals("ADD_DATA")) {  //Graph 1

                s1.add(i, Weight00.get(i));
                s2.add(i, Weight01.get(i));
                s3.add(i, Weight02.get(i));
                s4.add(i, Weight10.get(i));
                s5.add(i, Weight11.get(i));
                s6.add(i, Weight12.get(i));
                s7.add(i, Weight20.get(i));
                s8.add(i, Weight21.get(i));
                s9.add(i, Weight22.get(i));
                i = i + 1;
            } else if (e.getActionCommand().equals("ADD_DATA_TWO")) {   // Graph2

                s21.add(j, errorGraph.get(j));
                j = j + 1;

            }
        }

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));  // Create output file
        perceptron.add(p0);                                  // add Perceptron to ArrayList
        perceptron.add(p1);
        perceptron.add(p2);
        do {                 // Loop until Sum Error mean square < 0.01
            sose = 0;
            for (int i = 0; i < 4; i++) {        // Loop to train 4 training data per epoch
                ExpectValue = TrainingSet[i][2];        // Set Expect Value
                perceptron.get(0).SetInput(TrainingSet[i][0], TrainingSet[i][1]);   // Set input to neuron
                perceptron.get(1).SetInput(TrainingSet[i][0], TrainingSet[i][1]);
                perceptron.get(0).GetOutput();
                perceptron.get(1).GetOutput();
                perceptron.get(2).SetInput(p0.GetOutputValue(), p1.GetOutputValue());    // Set input to output neuron
                perceptron.get(2).GetOutput();
                err();                                          // Method to calculate and set Error Value
                LastRamp();                                     // Method to calcultae the ramp of output neuron
                ramp();                                         // Method to calculate ramp of another neuron except output neuron
                AdjWeight();                                    // Method to adjust weight for neuron
                System.out.println(perceptron.get(0).GetInput(1) + "   " + perceptron.get(1).GetInput(2) + "  =  " + perceptron.get(2).GetOutputValue());
                sose = (sose + Math.pow(ErrorValue, 2));      // Calculate sum of error square
            }                                               // end of Loop 1 epoch (4 training data)
            sose = sose / 4;                     // Calculate sum of error mean square
            for (int j = 0; j < 3; j++) {           // Loop for print Weight on input[i] for every neuron
                System.out.println("input " + j + "  :  " + p0.GetPresentWeight(j) + "   " + p1.GetPresentWeight(j) + "   " + p2.GetPresentWeight(j));
            }
            System.out.println("error mean square = " + sose);
            epoch++;
            System.out.println("epoch = " + epoch);

            Weight00.add(p0.GetPresentWeight(0));   //put weight into arraylist
            Weight10.add(p1.GetPresentWeight(0));
            Weight20.add(p2.GetPresentWeight(0));
            Weight01.add(p0.GetPresentWeight(1));
            Weight11.add(p1.GetPresentWeight(1));
            Weight21.add(p2.GetPresentWeight(1));
            Weight02.add(p0.GetPresentWeight(2));
            Weight12.add(p1.GetPresentWeight(2));
            Weight22.add(p2.GetPresentWeight(2));
            errorGraph.add(sose);
            WriteFile(out);

        } while (sose > 0.01);

        out.close();    //closefile

        RunGraph demo = new RunGraph("Weight per Epoch", 1);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

        RunGraph demo2 = new RunGraph("Error per Epoch", 2);
        demo2.pack();
        RefineryUtilities.centerFrameOnScreen(demo2);
        demo2.setVisible(true);

        for (int i = 0; i < epoch; i++) {   //auto click button
            button.doClick(20);
            button2.doClick(20);
        }


    }

    public static void err() {   // Method to calculate error value
        ErrorValue = 0;
        ErrorValue = ExpectValue - perceptron.get((perceptron.size() - 1)).GetOutputValue();
    }

    public static void LastRamp() {   // Method to Calculate the ramp of the output neuron
        perceptron.get((perceptron.size() - 1)).SetRamp(ErrorValue * (perceptron.get((perceptron.size() - 1)).GetOutputValue()) * (1 - perceptron.get((perceptron.size() - 1)).GetOutputValue()));
        // last ramp = Error Value * Output * (1 - Output)
    }

    public static void ramp() {   // Method to Calculate ramp for neuron except output neuron

        for (int i = perceptron.get((perceptron.size() - 1)).Getlevel() - 1; i >= 0; i--) {
            int Snumber = 0;                                                    // Snumber of first perceptron in Lv.
            while (perceptron.get(Snumber).Getlevel() != i) {
                Snumber++;           // Find Snumber of First perceptron in Lv.
            }
            for (int j = 0; j < PerceptronInLv[i]; j++) // Set ramp for every perceptron
            {
                perceptron.get(Snumber + j).SetRamp(perceptron.get(Snumber + j).GetOutputValue() * (1 - perceptron.get(Snumber + j).GetOutputValue()) * SOP(i + 1, j + 1));
                // Calculate ramp
            }
        }
    }

    public static double SOP(int NextLv, int NumberOfInput) // Find Sum of Product to calculate ramp
    {
        double sum = 0;
        int Snumber = 0;
        while (perceptron.get(Snumber).Getlevel() != NextLv) {
            Snumber++;          // Find Snumber of First perceptron in NextLv.
        }
        for (int i = 0; i < PerceptronInLv[NextLv]; i++) {
            sum = sum + perceptron.get(Snumber + i).GetRamp() * perceptron.get(Snumber + i).GetPresentWeight(NumberOfInput);
            // Sum of Product = Sum of (ramp * weight)
        }
        return sum;
    }

    public static void AdjWeight() {    // Method for adjust weight to every neuron
        double temp[] = new double[3];
        for (int i = 0; i <= perceptron.get((perceptron.size() - 1)).GetSnum(); i++) {  // Loop for adjust weight to every neuron
            for (int j = 0; j < 3; j++) {   // Loop for adjust every weight in the neuron
                temp[j] = perceptron.get(i).GetPresentWeight(j) + 0.2 * (perceptron.get(i).GetPresentWeight(j) - perceptron.get(i).GetPastWeight(j)) + (0.9 * perceptron.get(i).GetRamp() * perceptron.get(i).GetInput(j));
            }   // temp keep value of new weight in the neuron
            perceptron.get(i).SetWeight(temp);   // Set weight for the neuron
        }
    }

    public static void WriteFile(BufferedWriter out) {  //Write output in to textfile
        try {
            out.write("Period : " + epoch);
            out.newLine();
            for (int j = 0; j < 3; j++) {
                out.write("     weight " + "0" + j + "  :  " + p0.GetPresentWeight(j));
                out.write("     weight " + "1" + j + "  :  " + p1.GetPresentWeight(j));
                out.write("     weight " + "2" + j + "  :  " + p2.GetPresentWeight(j));
                out.newLine();
            }
            out.write("     error mean square = " + sose + "\n");
            out.newLine();
        } catch (IOException e) {
        }
    }
}
