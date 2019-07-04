import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class QueryExecutor {
    private InvertedIndex invertedIndex;
//    private Stack<ArrayList<String>> postingOperands;
    private ArrayList<String> postfix;
    String[] op = {"AND", "OR", "(", ")"};
    List<String> operators = Arrays.asList(op);

    QueryExecutor(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
//        postingOperands = new Stack<>();
    }

    void execute(String query) {
        parseQuery(query);
        runQuery();
//        System.out.println(query);
    }

    private void parseQuery(String query) {
        List<String> infix = new ArrayList<>();
        infix.addAll(Arrays.asList(query.split("[\\s]+")));
        for (int i = infix.size() - 1; i >= 0; i--) {
            List<String> list = Arrays.asList(infix.get(i).split("((?<=\\()|(?=\\())|((?<=\\))|(?=\\)))"));
            infix.remove(i);
            infix.addAll(i, list);
        }
        Stack<String> stack = new Stack<>();
        postfix = new ArrayList<>();
        for (int i = 0; i < infix.size(); i++) {
            String current = infix.get(i);
            if (!operators.contains(current)) {
                postfix.add(current);
            } else if (current.equals("(")) {
                stack.push(current);
            } else if (current.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("("))
                    postfix.add(stack.pop());

                if (!stack.isEmpty() && !stack.peek().equals("(")) {
//                    return "Invalid Expression"; // invalid expression
                    System.out.println("Invalid Expression1");
                } else
                    stack.pop();
            } else // an operator is encountered
            {
//                while (!stack.isEmpty()) {
//                    if (stack.peek().equals("(")) {
////                        return "Invalid Expression";
//                        System.out.println("Invalid Expression2");
//                    }
//                    postfix.add(stack.pop());
//                }
                stack.push(current);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
//                return "Invalid Expression";
                System.out.println("Invalid Expression3");
            }
            postfix.add(stack.pop());
        }
        System.out.println(postfix);
//        postingOperands.push(invertedIndex.getPostingFor(infix.get(0)));
//        ArrayList result = invertedIndex.getPostingFor(infix.get(0));
//        System.out.println(result);
//        System.out.println(Arrays.toString(query.split("((?<=\\()|(?=\\())|( )|((?<=\\))|(?=\\)))")));
    }

    private void runQuery() {
        Stack<ArrayList<String>> postingOperands = new Stack<>();
        for (int i = 0; i < postfix.size(); i++) {
            String current = postfix.get(i);
            if (!operators.contains(current)){
                postingOperands.push(invertedIndex.getPostingFor(current));
            }else if (current.equals("AND")){
                postingOperands.push(and(postingOperands.pop(),postingOperands.pop()));
            }else if (current.equals("OR")){
                postingOperands.push(or(postingOperands.pop(),postingOperands.pop()));
            }
        }
        ArrayList result = postingOperands.pop();
        System.out.println(result);
    }

    private ArrayList<String> or(ArrayList<String> operand1, ArrayList<String> operand2){
        ArrayList<String> result = new ArrayList<>();
        return result;
    }

    private ArrayList<String> and(ArrayList<String> operand1, ArrayList<String> operand2){
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < operand1.size(); i++) {
            String current = operand1.get(i);
            if (operand2.contains(current)){
                result.add(current);
            }
        }
        return result;
    }
}
