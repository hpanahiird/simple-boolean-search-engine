import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvertedIndex {
    private List<String> dictionary;//my dictionary
    private List<List<String>> postings;//my postings

    public InvertedIndex() {
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

        for (String token :
                tokens) {
            int index = dictionary.indexOf(token);
            if (index != -1) {
                postings.get(index).add(docId);
            } else {
                addNewTerm(token, docId);
            }
        }

        System.out.println(docId + ":" + tokens.length);
    }

    private void addNewTerm(String term, String docId) {
        dictionary.add(term);
        List<String> posting = new ArrayList<>();
        posting.add(docId);
        postings.add(posting);
    }

    void print() {
        for (int i = 0; i < dictionary.size(); i++) {
            System.out.println(dictionary.get(i)+":"+postings.get(i).toString());
        }
    }
}
