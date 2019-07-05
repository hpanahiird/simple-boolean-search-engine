class DocumentInfo {
    private String docId;
    private int frequency;

    DocumentInfo(String docId) {
        this.docId = docId;
        frequency = 1;
    }

    String getDocId() {
        return docId;
    }

    void occured(){
        frequency++;
    }

    public int getFrequency() {
        return frequency;
    }
}
