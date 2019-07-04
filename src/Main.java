import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InvertedIndex invertedIndex = new InvertedIndex();
        try {
            File file = new File("input.txt");
            if (file.isFile()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    invertedIndex.indexDocument(line);
                }
                invertedIndex.print();
            }
            System.out.println("Welcome to Simple Boolean Search Engine!");
            System.out.println("please enter your query and hit enter to start search.");
            System.out.println("to exit just type \"exit!!\" and hit enter.");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Query: ");
            String query = scanner.nextLine();
            while (!"exit!!".equals(query)){
                System.out.println(query);
                System.out.print("Query: ");
                query = scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
