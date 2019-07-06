import java.util.*;

class QueryExecutor {
    private InvertedIndex invertedIndex;
    private ArrayList<String> postfix;
    private String[] op = {"AND", "OR", "(", ")"};
    private List<String> operators = Arrays.asList(op);
    private List<String> queryTokens = new ArrayList<>();
    private PostingList queryResult;

    QueryExecutor(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    void execute(String query) {
        long queryStart = System.currentTimeMillis();
        parseQuery(query);
        runQuery();
        calculate_query_tf_idf();
        calculateCosines();
        long queryEnd = System.currentTimeMillis();
        System.out.println("results in: " + (queryEnd - queryStart) + " milliseconds");
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
        System.out.println("infix: " + infix);
        postfix = new ArrayList<>();
        queryTokens = new ArrayList<>();
        for (int i = 0; i < infix.size(); i++) {
            String current = infix.get(i);
            if (!operators.contains(current)) {
                queryTokens.add(current);
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
    }

    private void runQuery() {
        Stack<PostingList> postingOperands = new Stack<>();
        for (int i = 0; i < postfix.size(); i++) {
            String current = postfix.get(i);
            if (!operators.contains(current)) {
                postingOperands.push(invertedIndex.getPostingFor(current));
            } else if (current.equals("AND")) {
                postingOperands.push(and(postingOperands.pop(), postingOperands.pop()));
            } else if (current.equals("OR")) {
                postingOperands.push(or(postingOperands.pop(), postingOperands.pop()));
            }
        }
        queryResult = postingOperands.pop();
        System.out.println(queryResult);
    }

    private PostingList or(PostingList operand1, PostingList operand2) {
        PostingList result = new PostingList();
        result.addAll(operand1);
        for (int i = 0; i < operand2.size(); i++) {
            DocumentInfo current = operand2.get(i);
            if (!result.contains(current.getDocId())) {
                result.add(current);
            }
        }
        return result;
    }

    private PostingList and(PostingList operand1, PostingList operand2) {
        PostingList result = new PostingList();
        for (int i = 0; i < operand1.size(); i++) {
            DocumentInfo current = operand1.get(i);
            if (operand2.contains(current.getDocId())) {
                result.add(current);
            }
        }
        return result;
    }

    private HashMap<String, Double> kk = new HashMap<>();

    void calculate_query_tf_idf() {
        kk = new HashMap<>();
        for (int i = 0; i < queryTokens.size(); i++) {
            String current = queryTokens.get(i);
            double tfw = Math.log10(query_tf(current)) + 1;
            kk.put(current, tfw * invertedIndex.getTermIdf(current));
        }
        System.out.println("query tokens: " + kk);
    }

    HashMap<String, Double> documentCosine;
    void calculateCosines() {
        documentCosine = new HashMap<>();
        for (int i = 0; i < queryResult.size(); i++) {
            documentCosine.put(queryResult.get(i).getDocId(), cosine(queryResult.get(i).getDocId()));
        }

        System.out.println(documentCosine);
    }

    double cosine(String docId) {
        double result = 0;

        for (String key : kk.keySet()) {
            result += kk.get(key) * invertedIndex.get_tf_idf(key, docId);
        }


//        result =

        return result;
    }

    int query_tf(String token) {
        int c = 0;
        for (int i = 0; i < queryTokens.size(); i++) {
            if (queryTokens.get(i).equals(token))
                c++;
        }
        return c;
    }
}
