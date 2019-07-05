class DocumentInfo {
    private String docId;
    private int frequency;
    private double tfw = 0;

    DocumentInfo(String docId) {
        this.docId = docId;
        frequency = 1;
        tfw = Math.log10(frequency) + 1;
    }

    String getDocId() {
        return docId;
    }

    void occured() {
        frequency++;
        tfw = Math.log10(frequency) + 1;
    }

    int getFrequency() {
        return frequency;
    }

    double getTfw() {
        return tfw;
    }
}
