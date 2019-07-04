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

    void execute(String query) {
        parseQuery(query);
        runQuery();
        System.out.println(query);
    }

    private void parseQuery(String query) {
        List<String> infix = new ArrayList<>();
        String[] op = {"AND", "OR", "(", ")"};
        List<String> operators = Arrays.asList(op);
        infix.addAll(Arrays.asList(query.split("[\\s]+")));
        for (int i = infix.size() - 1; i >= 0; i--) {
            List<String> list = Arrays.asList(infix.get(i).split("((?<=\\()|(?=\\())|((?<=\\))|(?=\\)))"));
            infix.remove(i);
            infix.addAll(i, list);
        }
        Stack<String> stack = new Stack<>();
        ArrayList<String> postfix = new ArrayList<>();
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
        postingOperands.push(invertedIndex.getPostingFor(infix.get(0)));
//        ArrayList result = invertedIndex.getPostingFor(infix.get(0));
//        System.out.println(result);
//        System.out.println(Arrays.toString(query.split("((?<=\\()|(?=\\())|( )|((?<=\\))|(?=\\)))")));
    }

    private void runQuery() {
        ArrayList result = postingOperands.pop();
        System.out.println(result);
    }

    private void or(ArrayList<String> operand1, ArrayList<String> operand2){

    }

    private void and(ArrayList<String> operand1, ArrayList<String> operand2){

    }
}
