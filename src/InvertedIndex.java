import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvertedIndex {
    private List<String> dictionary;//my dictionary
    private List<List<String>> postings;//my postings

    public InvertedIndex(){
        dictionary = new ArrayList<>();
        postings = new ArrayList<>();
    }

    void indexDocument(String document) {
        //first we normalize document
        //only normalizing step in this program is only make them lower case
        String normalized = document.toLowerCase();

        //split document tokens by space
        String[] splited = normalized.split(" ");

        //first element of each line is document id
        String docId = splited[0];

        //rest are tokens
        String[] tokens = Arrays.copyOfRange(splited, 1, splited.length);

        System.out.println(docId + ":"+tokens.length);
    }
}
