class TermInfo {
    private String term;
    private int documentFrequency;

    TermInfo(String term) {
        this.term = term;
        documentFrequency = 1;
    }

    void increment() {
        documentFrequency++;
    }

    String getTerm() {
        return term;
    }

    int getDocumentFrequency() {
        return documentFrequency;
    }
}
