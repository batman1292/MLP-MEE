/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 *
 * @author 2IDR-PC
 */
public class ReadData {

    private int NumClassInput;
    private int NumClassOutput;
    private int NumData;
//    private double DataClassInput[];
//    private int DataClassOutput[];
    private double DataInput[][];
    private int DataOutput[][];
//    private ArrayList<Double> DataClassInput = new ArrayList<>();
//    private ArrayList<Integer> DataClassOutput = new ArrayList<>();
//    private ArrayList<ArrayList<Double>> DataInput = new ArrayList<>();
//    private ArrayList<ArrayList<Integer>> DataOutput = new ArrayList<>();

    public ReadData(String filename, int num_class_input, int num_class_output) throws FileNotFoundException, IOException {
        this.NumClassInput = num_class_input;
        this.NumClassOutput = num_class_output;
        this.NumData = 0;
        ReadingFile(filename);
    }

    public int getNumClassInput() {
        return this.NumClassInput;
    }

    public int getNumClassOutput() {
        return this.NumClassOutput;
    }

    public int getNumData() {
        return this.NumData;
    }

    private void ReadingFile(String filename) throws FileNotFoundException, IOException {
        String path = "D:\\BVH\\MEE\\AI\\Intiligent System\\task2\\AI_Assignment1(P'Jib)\\Ai_assignment1\\src\\ai_assignment1\\" + filename;
        File file = new File(path);
        setNumDataFormFile(filename);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int current_line = 0;
            while ((line = br.readLine()) != null) {
                String[] ary = line.split(",");
                for (int i = 0; i < ary.length; i++) {
                    if (i < this.NumClassInput) {
                        this.DataInput[current_line][i] = Double.parseDouble(ary[i]);
                    } else {
                        this.DataOutput[current_line][i-this.NumClassInput] = Integer.parseInt(ary[i]);
                    }
                }
                current_line++;
            }
            br.close();
            showDataArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setNumDataFormFile(String filename) throws FileNotFoundException, IOException {
        String path = "D:\\BVH\\MEE\\AI\\Intiligent System\\task2\\AI_Assignment1(P'Jib)\\Ai_assignment1\\src\\ai_assignment1\\" + filename;
        File file = new File(path);
        LineNumberReader lnr = new LineNumberReader(new FileReader(file));
        lnr.skip(Long.MAX_VALUE);
        this.NumData = lnr.getLineNumber() + 1;
        this.DataInput = new double [this.NumData][this.NumClassInput];
        this.DataOutput = new int [this.NumData][this.NumClassOutput];
        lnr.close();
    }
    
    private void showDataArray(){
        System.out.println("DataInput : ");
        for (int i = 0; i<this.NumData; i++){
            for (int j= 0; j<this.NumClassInput; j++){
                System.out.print(this.DataInput[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
        System.out.println("DataOutput : ");
        for (int i = 0; i<this.NumData; i++){
            for (int j= 0; j<this.NumClassOutput; j++){
                System.out.print(this.DataOutput[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }

}
