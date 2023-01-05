package Final;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Dataset> dataset = new ArrayList<>();
        String line;
        String column_headers = null;
        String item_idSTRING = null;
        String name = null;
        String priceSTRING = null;
        String old_price = null;
        String sellable_onlineSTRING = null;
        String link = null;
        String other_colorsSTRING = null;
        String short_description = null;
        String designer = null;
        String depthSTRING = null;
        String heightSTRING = null;
        String widthSTRING = null;

        try (BufferedReader br = new BufferedReader(new FileReader("ikea.csv"));) {
            column_headers = br.readLine();

            while ((line = br.readLine()) != null) {
                String data[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                String number_of_itemSTRING = data[0];
                Integer number_of_item = Integer.parseInt(number_of_itemSTRING);

                item_idSTRING = data[1];
                Integer item_id = Integer.parseInt(item_idSTRING);

                name = data[2];
                String category = data[3];

                priceSTRING = data[4];
                Double price = Double.parseDouble(priceSTRING);

                old_price = data[5];

                sellable_onlineSTRING = data[6];
                Boolean sellable_online = Boolean.parseBoolean(sellable_onlineSTRING);

                link = data[7];

                other_colorsSTRING = data[8];
                Boolean other_colors = Boolean.parseBoolean(other_colorsSTRING);

                short_description = data[9];
                designer = data[10];

                depthSTRING = data[11];
                Integer depth = data[11] == "" ? 0 : Integer.parseInt(depthSTRING);

                heightSTRING = data[12];
                Integer height = data[12] == "" ? 0 : Integer.parseInt(heightSTRING);

                widthSTRING = data[13];
                Integer width = data[13] == "" ? 0 : Integer.parseInt(widthSTRING);

                Dataset datasetOne = new Dataset(number_of_item, item_id, name, category,
                        price, old_price, sellable_online, link, other_colors, short_description, designer, depth,
                        height, width);
                dataset.add(datasetOne);

            }

            // TO PRINT ALL OF THE ENTITIES:

            // for (int i = 0; i < dataset.size(); i++) {
            // Dataset current = dataset.get(i);
            // System.out.printf("%5s %n", current);
            // }
            // System.out.println();

            //

        } catch (Exception e) { // CHANGE LATER
            e.printStackTrace();
        }

        System.out.println("Welcome, User!");
        System.out.println("Please, choose one of the options by typing the required digit:");
        System.out.println("1) List the entities");
        System.out.println("2) Sort the entities");
        System.out.println("3) Search the entities");
        System.out.println("4) List column names");
        System.out.println("5) Filter entities");

        Scanner sc = new Scanner(System.in);
        int input;
        do {
            input = sc.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Please, choose the action you want to perform by typing the required digit:");
                    System.out.println("1. List randomly selected 20 entities");
                    System.out.println("2. List top 20 entities");
                    System.out.println("3. List bottom 20 entities");
                    int second_input = sc.nextInt();

                    switch (second_input) {
                        case 1:
                            // TO BE DONE

                        case 2:
                            System.out.println("Do you want to display all the field or only selected ones>");
                            System.out.println("1. all fields");
                            System.out.println("2. selected fields");
                            int third_input = sc.nextInt();
                            switch (third_input) {
                                case 1:
                                    ListingTopEntities(dataset);
                                    break;

                                case 2:
                                    System.out.println("Which field do you want to list?");
                                    System.out.println("1. Item Id");
                                    System.out.println("2. Name");
                                    System.out.println("3. Category");
                                    System.out.println("4. Price");
                                    System.out.println("5. Old Price");
                                    System.out.println("6. Sellable Online");
                                    System.out.println("7. Link");
                                    System.out.println("8. Other Colors");
                                    System.out.println("9. Short Description");
                                    System.out.println("10. Designer");
                                    System.out.println("11. Depth");
                                    System.out.println("12. Height");
                                    System.out.println("13. Width");

                                    int fourth_input = sc.nextInt();
                                    switch (fourth_input) {
                                        case 1:
                                            for (int i = 0; i < 20; i++) {
                                                Integer current = dataset.get(i).getItem_id();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 2:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getName();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 3:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getCategory();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 4:
                                            for (int i = 0; i < 20; i++) {
                                                Double current = dataset.get(i).getPrice();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 5:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getOld_price();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 6:
                                            for (int i = 0; i < 20; i++) {
                                                Boolean current = dataset.get(i).getSellable_online();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 7:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getLink();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 8:
                                            for (int i = 0; i < 20; i++) {
                                                Boolean current = dataset.get(i).getOther_colors();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 9:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getShort_description();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 10:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getDesigner();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 11:
                                            for (int i = 0; i < 20; i++) {
                                                Integer current = dataset.get(i).getDepth();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 12:
                                            for (int i = 0; i < 20; i++) {
                                                Integer current = dataset.get(i).getHeight();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 13:
                                            for (int i = 0; i < 20; i++) {
                                                Integer current = dataset.get(i).getWidth();
                                                System.out.println(current);
                                            }
                                            break;
                                    }
                            }
                            break;

                        case 3:
                            System.out.println("Do you want to display all the field or only selected ones>");
                            System.out.println("1. all fields");
                            System.out.println("2. selected fields");
                            int fifth_input = sc.nextInt();
                            switch (fifth_input) {
                                case 1:
                                    ListingBottomEntities(dataset);
                                    break;


                                case 2:
                                    System.out.println("Which field do you want to list?");
                                    System.out.println("1. Item Id");
                                    System.out.println("2. Name");
                                    System.out.println("3. Category");
                                    System.out.println("4. Price");
                                    System.out.println("5. Old Price");
                                    System.out.println("6. Sellable Online");
                                    System.out.println("7. Link");
                                    System.out.println("8. Other Colors");
                                    System.out.println("9. Short Description");
                                    System.out.println("10. Designer");
                                    System.out.println("11. Depth");
                                    System.out.println("12. Height");
                                    System.out.println("13. Width");

                                    int sixth_input = sc.nextInt();
                                    switch (sixth_input) {
                                        case 1:
                                            for (int i = 0; i < 20; i++) {
                                                Integer current = dataset.get(i).getItem_id();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 2:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getName();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 3:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getCategory();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 4:
                                            for (int i = 0; i < 20; i++) {
                                                Double current = dataset.get(i).getPrice();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 5:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getOld_price();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 6:
                                            for (int i = 0; i < 20; i++) {
                                                Boolean current = dataset.get(i).getSellable_online();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 7:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getLink();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 8:
                                            for (int i = 0; i < 20; i++) {
                                                Boolean current = dataset.get(i).getOther_colors();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 9:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getShort_description();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 10:
                                            for (int i = 0; i < 20; i++) {
                                                String current = dataset.get(i).getDesigner();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 11:
                                            for (int i = 0; i < 20; i++) {
                                                Integer current = dataset.get(i).getDepth();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 12:
                                            for (int i = 0; i < 20; i++) {
                                                Integer current = dataset.get(i).getHeight();
                                                System.out.println(current);
                                            }
                                            break;

                                        case 13:
                                            for (int i = 0; i < 20; i++) {
                                                Integer current = dataset.get(i).getWidth();
                                                System.out.println(current);
                                            }
                                            break;
                                    }
                            }
                            break;
                    }

                    System.out.println("Is there anything else that you want to do?");
                    System.out.println("If yes, choose another option from the list above!");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println(column_headers);
                    System.out.println("Is there anything else that you want to do?");
                    System.out.println("If yes, choose another option from the list above!");
                    break;
            }

        } while (input < 6);
    }

    // IMPLEMENTATION OF FUNCTIONALITY 1 b)
    public static void ListingTopEntities(List<Dataset> dataset) {
        int count = 0;
        for (int i = 0; i < 20; i++) {
            Dataset top_twenty = dataset.get(i);
            count++;
            System.out.printf("%5s %n", top_twenty);
            System.out.println("Number of entities listed: " + count + '\n');

        }
    }

    // IMPLEMENTATION OF FUNCTIONALITY 1 c)
    public static void ListingBottomEntities(List<Dataset> dataset) {
        int count = 0;
        for (int i = dataset.size() - 20; i < dataset.size(); i++) {
            count++;
            Dataset top_twenty = dataset.get(i);
            System.out.printf("%5s %n", top_twenty);
            System.out.println("Number of entities listed: " + count + '\n');

        }
    }

}
