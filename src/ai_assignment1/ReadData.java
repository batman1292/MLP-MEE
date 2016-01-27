/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author 2IDR-PC
 */
public class ReadData {

    private int NumClassInput;
    private int NumClassOutput;
    private int NumData;
    private LinkedList<Double> DataClassInput = new LinkedList<>();
    private LinkedList<Integer> DataClassOutput = new LinkedList<>();
    private LinkedList<LinkedList<Double>> DataInput = new LinkedList<>();
    private LinkedList<LinkedList<Integer>> DataOutput = new LinkedList<>();

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

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                this.DataClassInput.clear();
                this.DataClassOutput.clear();
                this.NumData++;
                String[] ary = line.split(",");
                for (int i = 0; i < ary.length + 1; i++) {
//                    System.out.println(ary[i]);
                    if (i < this.NumClassInput) {
//                        System.out.println(Double.parseDouble(ary[i]));
                        this.DataClassInput.addLast(Double.parseDouble(ary[i]));
                    } else if (i == ary.length) {
                        this.DataInput.addLast(this.DataClassInput);
                        this.DataOutput.addLast(this.DataClassOutput);
                    } else {
                        this.DataClassOutput.addLast(Integer.parseInt(ary[i]));
                    }
                }
            }
            System.out.println(this.DataInput.toString());
            System.out.println(this.DataOutput.toString());
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
