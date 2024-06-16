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
        ArrayList<String[]> baseSheetChunks;
        ArrayList<String[]> titleHistorySheetChunks;



        try {
            baseSheetChunks = TSVReader.BasicSheetReader();
            titleHistorySheetChunks = TSVReader.readHistoryTitle();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("province_sheet_converter");





        while(!exit)
        {
            System.out.println("select the task");
            System.out.println("1. landed_title\t2. history/province\t3. localization\t4. adjust\t5. history/titles\t0. exit");
            option = sc.nextInt();
            switch (option)
            {
                case 1: {
                    TreeNode root = Noder.TreeNoder(baseSheetChunks);
                    LandedTitleCodeWriter.toLandedTitles(root);
                    break;
                }
                case 2: {
                    TreeNode root = Noder.TreeNoder(baseSheetChunks);
                    History.toHistory(root);
                    break;
                }
                case 3: {
                    Localization.Local(baseSheetChunks);
                    break;
                }
                case 4:{
                    TreeNode root = Noder.TreeNoder(baseSheetChunks);
                    rawCodeReader.codeReader(root);
                    Noder.TreeUpdater(root,baseSheetChunks);
                    LandedTitleCodeWriter.toLandedTitles(root);
                    History.toHistory(root);
                    BasicTSVWriter.toBasicTSV(root);
                    HistoryAttributeTSVWriter.HistoryTitleWriter(root);
                    break;
                }
                case 5: {
                    TreeNode root = Noder.TreeNoder(baseSheetChunks);
                    Noder.TreeNodeTitleHistoryUpdater(root,titleHistorySheetChunks);
                    HistoryAttributeCodeWriter.toCode(root);
                    break;
                }
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