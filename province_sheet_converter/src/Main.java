import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import subCode.*;
import subCode.Noder;


public class Main {
    public static void main(String[] args) {

        int option;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        ArrayList<String[]> LineChuncks;



        try {
            LineChuncks = TSVReader.Reader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("province_sheet_converter");





        while(!exit)
        {
            System.out.println("select the task");
            System.out.println("1. landed_title\t2. history\t3. localization\t4. adjust\t0. exit");
            option = sc.nextInt();
            switch (option)
            {
                case 1: {
                    TreeNode root = Noder.TreeNoder(LineChuncks);
                    toText.toLandedTitles(root);
                    break;
                }
                case 2: {
                    TreeNode root = Noder.TreeNoder(LineChuncks);
                    History.toHistory(root);
                    break;
                }
                case 3:
                    Localization.Local(LineChuncks);
                    break;
                case 4:
                    TreeNode root = Noder.TreeNoder(LineChuncks);
                    rawCodeReader.codeReader(root);
                    Noder.TreeUpdater(root,LineChuncks);
                    toText.toLandedTitles(root);
                    History.toHistory(root);
                    CSVWriter.toCSV(root);
                    break;
                case 0:
                    System.out.println("exiting");
                    exit = true;
                    break;
                default:
                    System.out.println("Undeveloped or incorrect interaction");
            }
        }
    }
}