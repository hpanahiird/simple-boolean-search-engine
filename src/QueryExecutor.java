public class QueryExecutor {
    InvertedIndex invertedIndex;

    public QueryExecutor(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    void execute(String query){
        System.out.println(query);
    }
}
