import java.util.ArrayList;

public class TermList extends ArrayList<TermInfo> {
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size(); i++) {
            if (o.equals(this.get(i).getTerm())){
                return i;
            }
        }
        return -1;
    }
}
