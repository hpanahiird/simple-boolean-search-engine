import java.io.*;

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
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
