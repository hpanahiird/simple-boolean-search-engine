class TermInfo {
    private String term;
    private int documentFrequency;
    private double idf;

    TermInfo(String term) {
        this.term = term;
        documentFrequency = 1;
        idf = Math.log10(InvertedIndex.N/documentFrequency);
    }

    void increment() {
        documentFrequency++;
        idf = Math.log10(InvertedIndex.N/documentFrequency);
    }

    String getTerm() {
        return term;
    }

    int getDocumentFrequency() {
        return documentFrequency;
    }

    double getIdf() {
        return idf;
    }
}
