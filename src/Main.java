import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InvertedIndex invertedIndex = new InvertedIndex();
        ArrayList<String> documents= new ArrayList<>();
        try {
            File file = new File("input.txt");
            if (file.isFile()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    documents.add(line);
//                    invertedIndex.indexDocument(line);
                }
                for (int i = 0; i < documents.size(); i++) {
                    InvertedIndex.setN(documents.size());
                    invertedIndex.indexDocument(documents.get(i));
                }
                invertedIndex.print();
            }
            System.out.println("Welcome to Simple Boolean Search Engine!");
            System.out.println("please enter your query and hit enter to start search.");
            System.out.println("to exit just type \"exit!!\" and hit enter.");
            QueryExecutor executor = new QueryExecutor(invertedIndex);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Query: ");
            String query = scanner.nextLine();
            while (!"exit!!".equals(query)){
                executor.execute(query);
                System.out.print("Query: ");
                query = scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
