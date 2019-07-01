import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            if (file.isFile()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
