import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class QueryExecutor {
    private InvertedIndex invertedIndex;
    private Stack<ArrayList<String>> postingOperands;

    QueryExecutor(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
        postingOperands = new Stack<>();
    }

    void execute(String query){
        parseQuery(query);
        runQuery();
        System.out.println(query);
    }

    private void parseQuery(String query){
        List<String> splitted = new ArrayList<>();
        splitted.addAll(Arrays.asList(query.split("[\\s]+")));
        for (int i = splitted.size()-1;i>=0;i--){
            List<String> list = Arrays.asList(splitted.get(i).split("((?<=\\()|(?=\\())|((?<=\\))|(?=\\)))"));
            splitted.remove(i);
            splitted.addAll(i,list);
        }
        postingOperands.push(invertedIndex.getPostingFor(splitted.get(0)));
//        ArrayList result = invertedIndex.getPostingFor(splitted.get(0));
//        System.out.println(result);
//        System.out.println(Arrays.toString(query.split("((?<=\\()|(?=\\())|( )|((?<=\\))|(?=\\)))")));
    }

    private void runQuery(){
        ArrayList result = postingOperands.pop();
        System.out.println(result);
    }
}
