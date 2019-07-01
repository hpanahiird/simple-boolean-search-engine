import java.io.File;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            if (file.isFile())
                System.out.println(file.length());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
