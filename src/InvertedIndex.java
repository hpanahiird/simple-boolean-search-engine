import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InvertedIndex {
    private TermList dictionary;//my dictionary
    private List<PostingList> postings;//my postings

    static int N = 0;

    InvertedIndex() {
        dictionary = new TermList();
        postings = new ArrayList<>();
    }

    static void setN(int n) {
        N = n;
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
                if (documentIndex < 0) {
                    postings.get(index).add(new DocumentInfo(docId));
                    dictionary.get(index).increment();
                } else
                    postings.get(index).get(documentIndex).occured();

            } else {
                addNewTerm(token, docId);
            }
        }

    }

    private void addNewTerm(String term, String docId) {
        dictionary.add(new TermInfo(term));
        PostingList posting = new PostingList();
        posting.add(new DocumentInfo(docId));
        postings.add(posting);
    }

    void print() {
        System.out.println("inverted index:");
        for (int i = 0; i < dictionary.size(); i++) {
            PostingList current = postings.get(i);
            System.out.print("<" + dictionary.get(i).getTerm() + ":" + dictionary.get(i).getIdf() + ">: [");
            for (int j = 0; j < current.size(); j++) {
                System.out.print("<" + current.get(j).getDocId() + ":" + current.get(j).getTfw() + ">,");
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

    double get_tf_idf(String term, String docId) {
        double tf_idf;
        int termIndex = dictionary.indexOf(term);
        if (termIndex < 0)
            return 0;
        TermInfo termInfo = dictionary.get(termIndex);
        PostingList postingList = postings.get(termIndex);
        int documentIndex = postingList.indexOf(docId);
        if (documentIndex < 0)
            return 0;
        DocumentInfo documentInfo = postingList.get(documentIndex);

        tf_idf = documentInfo.getTfw() * termInfo.getIdf();

        return tf_idf;
    }

    double getTermIdf(String term) {
        double idf;
        int index = dictionary.indexOf(term);
        if (index >= 0)
            idf = dictionary.get(index).getIdf();
        else
            idf = 0;
        return idf;
    }
}
