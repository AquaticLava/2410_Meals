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
     *
     * @param type - "ingredients" if the csv has ingredients or "recipes" if it's the recipes csv
     */
    public CSVInput (String path, String type){
        try {
            scan = new Scanner (new File(path));
            if (type.equals("ingredients")) {
                scan.useDelimiter(",");
                readFileDataIngredients();
            } else if (type.equals("recipes")) {
                scan.useDelimiter("#");
                readFileDataRecipes();
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }

    /**
     * Reads in and stores the information from the passed CSV file
     * into an <code>ArrayList<String><code> where each line of the csv is one element.
     */
    public void readFileDataRecipes (){
        StringBuilder sb = new StringBuilder();

        scan.nextLine();//Skip header
        while (scan.hasNext()) {
            sb.append(scan.nextLine()).append(" ");
        }
        data.add(sb.toString());
        scan.close();
    }

    /**
     * Reads in and stores the information from the passed CSV file
     * into an <code>ArrayList<String><code> where each line of the csv is one element.
     */
    public void readFileDataIngredients (){
        boolean skipHeader = true;
        while (scan.hasNext())
            if (!skipHeader){
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

//    /**
//     * This main method test this class but is not to be a part of or used in this program.
//     * @param args
//     */
//    public static void main (String[] args){
//        CSVInput c = new CSVInput("src/application/resources/ingredients.csv");
//        c.readFileData();
//        for (String el: c.getData()){
//            System.out.println(el);
//        }
//    }

}
