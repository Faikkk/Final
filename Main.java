import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static final String[] menuOptions = { "Which field do you want to list?", "1. Item Id", "2. Name", "3. Category",
            "4. Price", "5. Old Price", "6. Sellable Online", "7. Link", "8. Other Colors", "9. Short Description",
            "10. Designer", "11. Depth", "12. Height", "13. Width",
            "Would you like to list it in ASCENDING or DESCENDING order?", "1. Ascending", "2. Descending",
            "Is there anything else that you want to do?",
            "If yes, choose another option from the list below!" };

    public static void printTheMenuOptions() {
        System.out.println(menuOptions[1]);
        System.out.println(menuOptions[2]);
        System.out.println(menuOptions[3]);
        System.out.println(menuOptions[4]);
        System.out.println(menuOptions[5]);
        System.out.println(menuOptions[6]);
        System.out.println(menuOptions[7]);
        System.out.println(menuOptions[8]);
        System.out.println(menuOptions[9]);
        System.out.println(menuOptions[10]);
        System.out.println(menuOptions[11]);
        System.out.println(menuOptions[12]);
        System.out.println(menuOptions[13]);
    }

    public static void initialMenu() {
        System.out.println("Welcome, User!");
        System.out.println("Please, choose one of the options by typing the required digit:");
        System.out.println("1) List the entities");
        System.out.println("2) Sort the entities");
        System.out.println("3) Search the entities");
        System.out.println("4) List column names");
        System.out.println("5) Filter entities");
        System.out.println("Additional:");
        System.out.println("6) Export the list of designers");
        System.out.println("7) Export the entities based on specific Category");
    }

    public static void secondaryMenu() {
        System.out.println("Do you want to display all the field or only selected ones?");
        System.out.println("1. all fields");
        System.out.println("2. selected fields");
    }

    public static void exportAtTheEnd() {
        System.out.println("Would you like to export the result?");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

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

        } catch (Exception e) { // CHANGE LATER
            e.printStackTrace();
        }

        initialMenu();

        Scanner sc = new Scanner(System.in);
        int input;
        do {
            input = sc.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Please, choose the action you want to perform by typing the required digit:");
                    System.out.println("0. List all entities");
                    System.out.println("1. List randomly selected 20 entities");
                    System.out.println("2. List top 20 entities");
                    System.out.println("3. List bottom 20 entities");
                    int second_input = sc.nextInt();

                    switch (second_input) {
                        case 0:

                            for (int i = 0; i < dataset.size(); i++) {
                                Dataset field = dataset.get(i);
                                System.out.printf("%5s %n", field);
                            }
                            System.out.println();
                            break;

                        case 1:
                            ListingRandomEntities(dataset, 0);
                            // secondaryMenu();
                            // int third_input = sc.nextInt();
                            // switch (third_input) {
                            // case 1:
                            // ListingRandomEntities(dataset, 0);
                            // break;

                            // case 2:
                            // System.out.println("Which field do you want to list?");
                            // printTheMenuOptions();
                            // break;

                            break;

                        case 2:
                            secondaryMenu();
                            int third_input = sc.nextInt();
                            switch (third_input) {
                                case 1:
                                    ListingTopEntities(dataset);
                                    break;

                                case 2:
                                    System.out.println("Which field do you want to list?");
                                    printTheMenuOptions();

                                    int given_input = sc.nextInt();
                                    switch (given_input) {

                                        case 1:
                                            for (int i = 0; i < 20; i++) {
                                                Integer field = dataset.get(i).getItem_id();
                                                System.out.println(field);

                                            }
                                            break;

                                        case 2:
                                            for (int i = 0; i < 20; i++) {
                                                String field = dataset.get(i).getName();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 3:
                                            for (int i = 0; i < 20; i++) {
                                                String field = dataset.get(i).getCategory();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 4:
                                            for (int i = 0; i < 20; i++) {
                                                Double field = dataset.get(i).getPrice();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 5:
                                            for (int i = 0; i < 20; i++) {
                                                String field = dataset.get(i).getOld_price();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 6:
                                            for (int i = 0; i < 20; i++) {
                                                Boolean field = dataset.get(i).getSellable_online();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 7:
                                            for (int i = 0; i < 20; i++) {
                                                String field = dataset.get(i).getLink();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 8:
                                            for (int i = 0; i < 20; i++) {
                                                Boolean field = dataset.get(i).getOther_colors();
                                                System.out.println(field);

                                            }
                                            break;

                                        case 9:
                                            for (int i = 0; i < 20; i++) {
                                                String field = dataset.get(i).getShort_description();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 10:
                                            for (int i = 0; i < 20; i++) {
                                                String field = dataset.get(i).getDesigner();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 11:
                                            for (int i = 0; i < 20; i++) {
                                                Integer field = dataset.get(i).getDepth();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 12:
                                            for (int i = 0; i < 20; i++) {
                                                Integer field = dataset.get(i).getHeight();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 13:
                                            for (int i = 0; i < 20; i++) {
                                                Integer field = dataset.get(i).getWidth();
                                                System.out.println(field);
                                            }
                                            break;
                                    }
                            }
                            break;

                        case 3:
                            secondaryMenu();

                            int fifth_input = sc.nextInt();
                            switch (fifth_input) {
                                case 1:
                                    ListingBottomEntities(dataset);
                                    break;

                                case 2:
                                    System.out.println("Which field do you want to list?");
                                    printTheMenuOptions();

                                    int sixth_input = sc.nextInt();
                                    switch (sixth_input) {
                                        case 1:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                Integer field = dataset.get(i).getItem_id();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 2:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                String field = dataset.get(i).getName();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 3:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                String field = dataset.get(i).getCategory();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 4:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                Double field = dataset.get(i).getPrice();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 5:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                String field = dataset.get(i).getOld_price();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 6:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                Boolean field = dataset.get(i).getSellable_online();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 7:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                String field = dataset.get(i).getLink();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 8:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                Boolean field = dataset.get(i).getOther_colors();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 9:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                String field = dataset.get(i).getShort_description();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 10:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                String field = dataset.get(i).getDesigner();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 11:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                Integer field = dataset.get(i).getDepth();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 12:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                Integer field = dataset.get(i).getHeight();
                                                System.out.println(field);
                                            }
                                            break;

                                        case 13:
                                            for (int i = dataset.size() - 20; i < dataset.size(); i++) {
                                                Integer field = dataset.get(i).getWidth();
                                                System.out.println(field);
                                            }
                                            break;
                                    }
                            }
                            System.out.println(menuOptions[17] + '\n' + menuOptions[18]);
                            printTheMenuOptions();
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Based on which field would you like to sort?");
                    printTheMenuOptions();
                    Comparator<Dataset> comparator;
                    int nth_input = sc.nextInt();
                    switch (nth_input) {
                        case 1:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            int another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getItem_id().compareTo(h2.getItem_id()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getItem_id().compareTo(h1.getItem_id()))
                                            .forEach(System.out::println);
                                    break;
                            }
                            break;

                        case 2:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getName().compareTo(h2.getName()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getName().compareTo(h1.getName()))
                                            .forEach(System.out::println);
                                    break;
                            }
                            break;

                        case 3:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getCategory().compareTo(h2.getCategory()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getCategory().compareTo(h1.getCategory()))
                                            .forEach(System.out::println);
                                    break;
                            }
                            break;

                        case 4:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getPrice().compareTo(h2.getPrice()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getPrice().compareTo(h1.getPrice()))
                                            .forEach(System.out::println);
                                    break;
                            }
                            break;

                        case 5:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getOld_price().compareTo(h2.getOld_price()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getOld_price().compareTo(h1.getOld_price()))
                                            .forEach(System.out::println);
                                    break;
                            }
                            break;

                        case 6:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getSellable_online()
                                                    .compareTo(h2.getSellable_online()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getSellable_online()
                                                    .compareTo(h1.getSellable_online()))
                                            .forEach(System.out::println);
                                    break;
                            }
                            break;

                        case 7:
                            comparator = (h1, h2) -> h1.getLink().compareTo(h2.getLink());
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            dataset.sort(comparator);
                            switch (another_input) {
                                case 1:
                                    System.out.println(dataset);
                                    break;

                                case 2:
                                    dataset.sort(comparator.reversed());
                                    System.out.println(dataset);
                                    break;
                            }
                            break;

                        case 8:
                            comparator = (h1, h2) -> h1.getOther_colors().compareTo(h2.getOther_colors());
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();

                            switch (another_input) {
                                case 1:
                                    dataset.sort(comparator);
                                    System.out.println(dataset);
                                    break;

                                case 2:
                                    dataset.sort(comparator.reversed());
                                    System.out.println(dataset);
                                    break;
                            }
                            break;

                        case 9:
                            comparator = (h1, h2) -> h1.getShort_description().compareTo(h2.getShort_description());
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            dataset.sort(comparator);
                            switch (another_input) {
                                case 1:
                                    System.out.println(dataset);
                                    break;

                                case 2:
                                    dataset.sort(comparator.reversed());
                                    System.out.println(dataset);
                                    break;
                            }
                            break;

                        case 10:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getDesigner().compareTo(h2.getDesigner()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getDesigner().compareTo(h1.getDesigner()))
                                            .forEach(System.out::println);
                                    break;
                            }
                            break;

                        case 11:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getDepth().compareTo(h2.getDepth()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getDepth().compareTo(h1.getDepth()))
                                            .forEach(System.out::println);
                                    break;
                            }
                            break;

                        case 12:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getHeight().compareTo(h2.getHeight()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getHeight().compareTo(h1.getHeight()))
                                            .forEach(System.out::println);
                                    break;
                            }
                            break;

                        case 13:
                            System.out.println(menuOptions[14] + '\n' + menuOptions[15] + '\n' + menuOptions[16]);
                            another_input = sc.nextInt();
                            switch (another_input) {
                                case 1:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h1.getWidth().compareTo(h2.getWidth()))
                                            .forEach(System.out::println);
                                    break;

                                case 2:
                                    dataset.stream()
                                            .sorted((h1, h2) -> h2.getWidth().compareTo(h1.getWidth()))
                                            .forEach(System.out::println);
                                    break;
                            }
                    }
                    // System.out.println("Would you like to a. sort again or b. list the already
                    // sorted entities?");
                    // String final_input = sc.nextLine();
                    // switch ('a') {
                    // case "1)": {
                    // System.out.println("Based on which entities you would like to sort?");
                    // printTheMenuOptions();
                    // }
                    // break;

                    // case "2)": {

                    // }
                    // }
                    // break;

                case 3:

                    System.out.println("Press 1 for Searching Item_ID\nPress 2 for Searching Name\nPress 3 for Searching Category\nPress 4 for Searching Price\nPress 5 for SearchingOld_Price\nPress 6 for Searching Sellable_Online\nPress 7 for SearchingLink\nPress 8 for Searching Other_Colors\nPress 9 for SearchingShort_Description\nPress 10 for Searching Designer\nPress 11 for SearchingDepth\nPress 12 for Searching Height\nPress 13 for Searching Width\n");
                    int index = sc.nextInt();
                    if (index == 1) {
                        System.out.println(
                                "Enter the value you want the Item_ID to be(with decimal point specifier): ");
                        dataset.stream()
                                .filter(products -> {
                                    return (products.getItem_id()) == sc.nextInt();
                                }).collect(Collectors.toList())
                                .forEach(System.out::println);
                    }

                    if (index == 2) {
                        System.out.println("Enter the String you want the Name contain");
                        sc.nextLine();
                        String cnt = sc.nextLine();
                        dataset.stream().filter(products -> {
                            return products.getName().contains(cnt);
                        }).collect(Collectors.toList())
                                .forEach(System.out::println);
                    }
                    if (index == 3) {
                        System.out.println("Enter the String you want the Category to contain:");
                        sc.nextLine();
                        String cnt = sc.nextLine();
                        dataset.stream().filter(products -> {
                            return products.getCategory().contains(cnt);
                        }).collect(Collectors.toList());
                    }
                    if (index == 4) {
                        System.out.println(
                                "Enter the value you want the Price to be(with decimal point specifier): ");
                        Double p = sc.nextDouble();
                        dataset.stream().filter(products -> {
                            return products.getPrice() == p;
                        }).collect(Collectors.toList());
                    }
                    if (index == 5) {
                        System.out.println("Enter the Old_Price you want search for: ");
                        sc.nextLine();
                        String search = sc.nextLine();
                        dataset.stream().filter(products -> {
                            return products.getOld_price().contains(search);
                        }).collect(Collectors.toList())
                                .forEach(System.out::println);
                    }
                    if (index == 6) {
                    System.out.println("Enter the Sellable_Online you want search for: ");
                    sc.nextLine();
                    Boolean s_o = sc.nextBoolean();
                    dataset.stream().filter(products -> {
                    return products.getSellable_online().equals(s_o);
                    }).collect(Collectors.toList())
                    .forEach(System.out::println);
                    }
                    if (index == 7) {
                    System.out.println("Enter the String you want Link to contain : ");
                    sc.nextLine();
                    String lk = sc.next();
                    dataset.stream().filter(products -> {
                    return products.getLink().contains(lk);
                    }).collect(Collectors.toList())
                    .forEach(System.out::println);
                    }
                    if (index == 8) {
                    System.out.println("Enter the Other_Colors you want search for: ");
                    sc.nextLine();
                    Boolean o_c = sc.nextBoolean();
                    dataset.stream().filter(products -> {
                    return products.getOther_colors().equals(o_c);
                    }).collect(Collectors.toList())
                    .forEach(System.out::println);
                    }

                    if (index == 9) {
                        System.out.println("Enter the Short_Description you want search for: ");
                        sc.nextLine();
                        String sd = sc.nextLine();
                        dataset.stream().filter(products -> {
                        return products.getShort_description().contains(sd);
                        }).collect(Collectors.toList())
                        .forEach(System.out::println);
                        }
    

                    if (index == 10) {
                    System.out.println("Enter the String you want the Designer contain");
                    sc.nextLine();
                    String cnt = sc.next();
                    dataset.stream().filter(products -> {
                    return products.getDesigner().contains(cnt);
                    }).collect(Collectors.toList());
                    }
                    if (index == 11) {
                    System.out.println(
                    "Enter the value you want the Depth to be: ");
                    Integer dp = sc.nextInt();
                    dataset.stream().filter(products -> {
                    return products.getDepth() == dp;
                    }).collect(Collectors.toList());
                    }
                    if (index == 12) {
                    System.out.println(
                    "Enter the value you want the Height to be: ");
                    Integer hg = sc.nextInt();
                    dataset.stream().filter(products -> {
                    return products.getHeight() == hg;
                    }).collect(Collectors.toList());
                    }
                    if (index == 13) {
                    System.out.println(
                    "Enter the value you want the Width to be: ");
                    Integer wd = sc.nextInt();
                    dataset.stream().filter(products -> {
                    return products.getWidth() == wd;
                    }).collect(Collectors.toList());
                    }
                    break;

                case 4:
                    System.out.println(column_headers);
                    System.out.println(menuOptions[17] + '\n' + menuOptions[18]);
                    break;

                // FILTER
                case 5:
                    System.out.println("Please, choose for which options you would like to filter:");
                    printTheMenuOptions();
                    index = sc.nextInt();
                    switch (index) {
                        case 1:
                            System.out.println(
                                    "Clear! Choose Filter option for Item_Id\n1 - Equal \n2 - Greater than\n3 - Less than\n4 - Greater and Equal to\n5 - Less and Equal to\n6-Null");
                            int fltr = sc.nextInt();

                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getItem_id().equals(cnt);
                                }).collect(Collectors.toList());

                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Item_ID_eq");

                            }

                            if (fltr == 2) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getItem_id() > cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Item_ID_gt");

                            }

                            if (fltr == 3) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getItem_id() < cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Item_ID_lt");

                            }

                            if (fltr == 4) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getItem_id() >= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Item_ID_ge");

                            }

                            if (fltr == 5) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getItem_id() <= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Item_ID_le");

                            }

                            if (fltr == 6) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getItem_id() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Item_ID_null");

                            }

                            break;

                        case 2:
                            System.out.println("Clear! Please Enter Filter option for Name\n1 - Contains\n2 - Null");
                            fltr = sc.nextInt();

                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                String ln = sc.next();
                                var st = dataset.stream().filter(product -> {
                                    return product.getName().equals(ln);
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(), "Name_contains");

                            }
                            if (fltr == 2) {
                                var st = dataset.stream().filter(product -> {
                                    return product.getName().length() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(), "Name_null");

                            }
                            break;

                        case 3:
                            System.out.println(
                                    "Clear! Please Enter Filter option for Category\n1 - Contains\n2 - Null");
                            fltr = sc.nextInt();

                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                String ln = sc.next();
                                var st = dataset.stream().filter(product -> {
                                    return product.getCategory().equals(ln);
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(), "Category_contains");


                            }
                            if (fltr == 2) {
                                var st = dataset.stream().filter(product -> {
                                    return product.getCategory().length() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(), "Category_null");

                            }
                            break;

                        case 4:
                            System.out.println(
                                    "Clear! Choose Filter option for Price\n1 - Equal \n2 - Greater than\n3 - Less than\n4 - Greater and Equal to\n5 - Less and Equal to\n6-Null");
                            fltr = sc.nextInt();

                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                Double cnt = sc.nextDouble();
                                var s = dataset.stream().filter(product -> {
                                    return product.getPrice() == cnt;
                                }).collect(Collectors.toList());

                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Price_eq");

                            }

                            if (fltr == 2) {
                                System.out.println("The String you want to contain is: ");
                                Double cnt = sc.nextDouble();
                                var s = dataset.stream().filter(product -> {
                                    return product.getPrice() > cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Price_gt");

                            }

                            if (fltr == 3) {
                                System.out.println("The String you want to contain is: ");
                                Double cnt = sc.nextDouble();
                                var s = dataset.stream().filter(product -> {
                                    return product.getPrice() < cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Price_lt");

                            }

                            if (fltr == 4) {
                                System.out.println("The String you want to contain is: ");
                                Double cnt = sc.nextDouble();
                                var s = dataset.stream().filter(product -> {
                                    return product.getPrice() >= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Price_ge");

                            }

                            if (fltr == 5) {
                                System.out.println("The String you want to contain is: ");
                                Double cnt = sc.nextDouble();
                                var s = dataset.stream().filter(product -> {
                                    return product.getPrice() <= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Price_le");

                            }

                            if (fltr == 6) {
                                System.out.println("The String you want to contain is: ");
                                Double cnt = sc.nextDouble();
                                var s = dataset.stream().filter(product -> {
                                    return product.getPrice() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Price_null");

                            }
                            break;

                        case 5:
                            System.out.println(
                                    "Clear Sir! Please Enter Filter option for Old_Price\n1 - Contains\n2 - Null");
                            fltr = sc.nextInt();

                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                String ln = sc.next();
                                var st = dataset.stream().filter(product -> {
                                    return product.getOld_price().contains(ln);
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(),"Old_Price_contains");

                            }
                            if (fltr == 2) {
                                var st = dataset.stream().filter(product -> {
                                    return product.getOld_price().length() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(),"Old_Price_null");

                                
                            }
                            break;

                        case 6:
                            System.out.println("Clear! Please Enter Filter option for Sellable_Online\n1 - Equal");
                            fltr = sc.nextInt();
                            if (fltr == 1) {
                                System.out.println("Enter True or False ");
                                Boolean ln = sc.nextBoolean();
                                var st = dataset.stream().filter(product -> {
                                    return product.getSellable_online().equals(ln);
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(),"Sellable_Online_contains");

                            }
                            break;

                        case 7:
                            System.out.println("Clear! Please Enter Filter option for Link\n1 - Contains\n2 - Null");
                            fltr = sc.nextInt();
                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                String ln = sc.next();
                                var st = dataset.stream().filter(product -> {
                                    return product.getLink().contains(ln);
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                            }
                            if (fltr == 2) {
                                var st = dataset.stream().filter(product -> {
                                    return product.getLink().length() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                            }
                            break;

                        case 8:
                            System.out.println("Please Enter Filter option for Other_Colors\n1 - Contains");
                            fltr = sc.nextInt();
                            if (fltr == 1) {
                                System.out.println("Enter True or False ");
                                Boolean ln = sc.nextBoolean();
                                var st = dataset.stream().filter(product -> {
                                    return product.getOther_colors().equals(ln);
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(),"Other_Colors_contains");

                            }
                            break;

                        case 9:
                            System.out.println(
                                    "Please Enter Filter option for Short_Description\n1 - Contains\n2 - Null");
                            fltr = sc.nextInt();
                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                String ln = sc.next();
                                var st = dataset.stream().filter(product -> {
                                    return product.getShort_description().contains(ln);
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(),"Short_Description_contains");

                            }
                            if (fltr == 2) {
                                var st = dataset.stream().filter(product -> {
                                    return product.getShort_description().length() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(),"Short_Description_null");
                                
                            }
                            break;

                        case 10:
                            System.out
                                    .println("Clear! Please Enter Filter option for Designer\n1 - Contains\n2 - Null");
                            fltr = sc.nextInt();

                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                String ln = sc.next();
                                var st = dataset.stream().filter(product -> {
                                    return product.getDesigner().contains(ln);
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(),"Designer_contains");

                            }
                            if (fltr == 2) {
                                var st = dataset.stream().filter(product -> {
                                    return product.getDesigner().length() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(st.toString());
                                exportingTheResult(st.toString(),"Designer_null");
                            }
                            break;

                        case 11:
                            System.out.println(
                                    "Clear Sir! Choose Filter option for Depth\n1 - Equal \n2 - Greater than\n3 - Less than\n4 - Greater and Equal to\n5 - Less and Equal to");
                            fltr = sc.nextInt();

                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getDepth() == cnt;
                                }).collect(Collectors.toList());

                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Depth_eq");

                            }

                            if (fltr == 2) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getDepth() > cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Depth_gt");

                            }

                            if (fltr == 3) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getDepth() < cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Depth_lt");

                            }

                            if (fltr == 4) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getDepth() >= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Depth_ge");

                            }

                            if (fltr == 5) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getDepth() <= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Depth_le");

                            }

                            if (fltr == 6) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getDepth() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Depth_le");

                            }
                            break;

                        case 12:
                            System.out.println(
                                    "Clear Sir! Choose Filter option for Height\n1 - Equal \n2 - Greater than\n3 - Less than\n4 - Greater and Equal to\n5 - Less and Equal to");
                            fltr = sc.nextInt();

                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getHeight() == cnt;
                                }).collect(Collectors.toList());

                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Height_eq");

                            }

                            if (fltr == 2) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getHeight() > cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Height_gt");

                            }

                            if (fltr == 3) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getHeight() < cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Height_lt");

                            }

                            if (fltr == 4) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getHeight() >= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Height_ge");

                            }

                            if (fltr == 5) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getHeight() <= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Height_le");

                            }

                            if (fltr == 6) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getHeight() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Height_le");

                            }
                            break;

                            case 13:
                            System.out.println(
                                    "Clear Sir! Choose Filter option for Width\n1 - Equal \n2 - Greater than\n3 - Less than\n4 - Greater and Equal to\n5 - Less and Equal to");
                            fltr = sc.nextInt();

                            if (fltr == 1) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getWidth() == cnt;
                                }).collect(Collectors.toList());

                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Width_eq");

                            }

                            if (fltr == 2) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getWidth() > cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Width_gt");

                            }

                            if (fltr == 3) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getWidth() < cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Width_lt");

                            }

                            if (fltr == 4) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getWidth() >= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Width_ge");

                            }

                            if (fltr == 5) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getWidth() <= cnt;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Width_le");

                            }

                            if (fltr == 6) {
                                System.out.println("The String you want to contain is: ");
                                Integer cnt = sc.nextInt();
                                var s = dataset.stream().filter(product -> {
                                    return product.getWidth() == 0;
                                }).collect(Collectors.toList());
                                System.out.println(s.toString());
                                exportingTheResult(s.toString(), "Width_le");

                            }
                            break;

                    }

                    break;

                case 6:
                    try {
                        FileWriter fileWriter = new FileWriter("List of Designers.txt");
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        for (Dataset ds : dataset) {
                            // replace all the strings, starting with a number of double quote
                            String properFormatting = ds.getDesigner().replaceAll("^(\\d+.*)|\".*\"|", "");
                            // remove the slash delimeter separating some of the designers
                            printWriter.println(properFormatting.replaceAll("/", "\n"));

                        }

                        printWriter.close();
                        System.out.println("The list has been exported...");
                    } catch (IOException e) { // CHANGE LATER
                        // TODO: handle exception
                    }
                     break;

                case 7:
                    printingDifferentCategories(dataset, "Bar furniture", "Bar furniture.csv");
                    printingDifferentCategories(dataset, "Beds", "Beds.csv");
                    printingDifferentCategories(dataset, "Bookcases & shelving units",
                            "Bookcases & shelving units.csv");
                    printingDifferentCategories(dataset, "Cabinets & cupboards", "Cabinets & cupboards.csv");
                    printingDifferentCategories(dataset, "CafÃ© furniture", "CafÃ© furniture.csv"); // BU ISHLEMIR
                    printingDifferentCategories(dataset, "Chairs", "Chairs.csv");
                    printingDifferentCategories(dataset, "Chests of drawers & drawer units",
                            "Chests of drawers & drawer units.csv");
                    printingDifferentCategories(dataset, "Children's furniture", "Children's furniture.csv");
                    printingDifferentCategories(dataset, "Nursery furniture", "Nursery furniture.csv");
                    printingDifferentCategories(dataset, "Outdoor furniture", "Outdoor furniture.csv");
                    printingDifferentCategories(dataset, "Room dividers", "Room dividers.csv");
                    printingDifferentCategories(dataset, "Sideboards, buffets & console tables",
                            "Sideboards, buffets & console tables.csv");
                    printingDifferentCategories(dataset, "Sofas & armchairs", "Sofas & armchairs.csv");
                    printingDifferentCategories(dataset, "Tables & desks", "Tables & desks.csv");
                    printingDifferentCategories(dataset, "Trolleys", "Trolleys.csv");
                    printingDifferentCategories(dataset, "TV & media furniture", "TV & media furniture.csv");
                    printingDifferentCategories(dataset, "Wardrobes", "Wardrobes.csv");
                    System.out.println("The category files have been exported.");
                    break;

            }

        } while (input < 10);
    }

    public static void printingDifferentCategories(List<Dataset> dataset, String category, String filename) {
        try {
            File f1 = new File("Files");
            f1.mkdir();
            File file = new File(f1, filename);

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print( "item_id" + "," + "name" + "," + "price" + "," + "old_price" + ","+ "sellable_online" 
                    + "," + "link" + "," + "other_colors" + "," + "short_description" + ","
                    + "designer" + "," + "depth" + "," + "height" + "," + "width");
            for (Dataset ds : dataset) {
                if (ds.getCategory().contains(category)) {

                    printWriter.print(ds.printStuff());
                }

            }
            printWriter.close();

        } catch (Exception e) { // CHANGE LATER
            // TODO: handle exception
        }
    }

    // IMPLEMENTATION OF FUNCTIONALITY 1 a)
    public static void ListingRandomEntities(List<Dataset> dataset, int state) {
        int count = 0;
        switch (state) {
            case 0:
                for (int i = 0; i < 20; i++) {
                    Collections.shuffle(dataset);
                    Dataset field = dataset.get(i);
                    count++;
                    System.out.printf("%5s %n", field);

                }
                System.out.println("Number of entities listed: " + count + '\n');
                break;

            case 1:
                for (int i = 0; i < 20; i++) {
                    Collections.shuffle(dataset);
                    Dataset field = dataset.get(i);
                    count++;
                    System.out.printf("%5s %n", field.getItem_id());
                }
                System.out.println("Number of entities listed: " + count + '\n');

        }
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

    public static void exportingTheResult(String string, String filename) {
        exportAtTheEnd();
        Scanner sc = new Scanner(System.in);
        int some_input = sc.nextInt();
        switch (some_input) {
            case 1:
                try {
                    String destination = filename;
                    FileOutputStream stream = new FileOutputStream(destination);
                    System.out.println("The file has been saved in " + destination);
                    System.setOut(new PrintStream(stream));
                    System.out.println(string);

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            case 2: {
                System.out.println(" ");
            }

        }
    }

}