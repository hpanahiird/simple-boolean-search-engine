import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InvertedIndex {
    private List<String> dictionary;//my dictionary
    private List<PostingList> postings;//my postings

    InvertedIndex() {
        dictionary = new ArrayList<>();
        postings = new ArrayList<>();
    }

    void indexDocument(String document) {
        //first we normalize document
        //only normalizing step in this program is only make them lower case
        String normalized = document.toLowerCase();

        //split document tokens by space
        String[] splited = normalized.split("[ ,.]+");

        //first element of each line is document id
        String docId = splited[0];

        //rest are tokens
        String[] tokens = Arrays.copyOfRange(splited, 1, splited.length);

        for (String token :
                tokens) {
            int index = dictionary.indexOf(token);
            if (index != -1) {
                int documentIndex = postings.get(index).indexOf(docId);
                if (documentIndex < 0)
                    postings.get(index).add(new DocumentInfo(docId));
                else
                    postings.get(index).get(documentIndex).occured();

            } else {
                addNewTerm(token, docId);
            }
        }

    }

    private void addNewTerm(String term, String docId) {
        dictionary.add(term);
        PostingList posting = new PostingList();
        posting.add(new DocumentInfo(docId));
        postings.add(posting);
    }

    void print() {
        System.out.println("inverted index:");
        for (int i = 0; i < dictionary.size(); i++) {
            PostingList current = postings.get(i);
            System.out.print(dictionary.get(i) + ": [");
            for (int j = 0; j < current.size(); j++) {
                System.out.print("<"+current.get(j).getDocId()+":"+current.get(j).getTfw()+">,");
            }
            System.out.println("]");
        }
    }

    PostingList getPostingFor(String term) {
        int index = dictionary.indexOf(term);
        if (index >= 0)
            return postings.get(index);
        else
            return new PostingList();
    }
}
