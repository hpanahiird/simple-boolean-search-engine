import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class QueryExecutor {
    private InvertedIndex invertedIndex;

    QueryExecutor(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    void execute(String query){
        parseQuery(query);
        System.out.println(query);
    }

    private void parseQuery(String query){
        List<String> splitted = new ArrayList<>();
        splitted.addAll(Arrays.asList(query.split("[\\s]+")));
        for (int i = splitted.size()-1;i>=0;i--){
            List list = Arrays.asList(splitted.get(i).split("((?<=\\()|(?=\\())|((?<=\\))|(?=\\)))"));
            splitted.remove(i);
            splitted.addAll(i,list);
        }
        System.out.println(splitted);
//        System.out.println(Arrays.toString(query.split("((?<=\\()|(?=\\())|( )|((?<=\\))|(?=\\)))")));
    }
}
