package EditData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will provide functionality such as reading in the data from a
 * specific CSV file based on the passed path in its constructor.
 *
 * @author Collin WIlliams
 */
public class CSVInput {
    private Scanner scan;
    private ArrayList<String> data = new ArrayList<>();

    /**
     * Constructs a CSVInput.
     */
    public CSVInput (String path){
        try {
            scan = new Scanner (new File(path));
            scan.useDelimiter(",");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        readFileData();
    }

    /**
     * Reads in and stores the information from the passed CSV file
     * into an <code>ArrayList<String><code> where each line of the csv is one element.
     */
    public void readFileData (){
        boolean skipHeader = true;
        while (scan.hasNext())
            if (skipHeader == false){
                data.add(scan.nextLine());
            } else {
                scan.nextLine();
                skipHeader = false;
            }


        scan.close();
    }

    /**
     * Returns the <code>ArrayList<String></code> of data contained in the CSV file.
     *
     * @return  <code>ArrayList<String></code> of data from CSV.
     */
    public ArrayList<String> getDataList() {
        return data;
    }

    /**
     * This main method test the this class but is not to be a part of or used in this program.
     * @param args
     */
//    public static void main (String[] args){
//        CSVInput c = new CSVInput("src/application/resources/ingredients.csv");
//        c.readFileData();
//        for (String el: c.getData()){
//            System.out.println(el);
//        }
//    }

}
