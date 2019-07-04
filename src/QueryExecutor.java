class QueryExecutor {
    private InvertedIndex invertedIndex;

    QueryExecutor(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    void execute(String query){
        System.out.println(query);
    }
}
