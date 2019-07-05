import java.util.ArrayList;

public class PostingList extends ArrayList<DocumentInfo> {
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size(); i++) {
            if (o.equals(this.get(i).getDocId())){
                return i;
            }
        }
        return -1;
    }
}
