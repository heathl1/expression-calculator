import java.math.BigInteger;
import java.util.*;

public class Main {
    public static boolean isValidVar(String input) {
        return input.matches("[a-zA-Z]+");
    }

    public static void assignVariable(Map<String, BigInteger> variables, String input) {
        String[] assignment = input.split("\\s*=\\s*");
        //System.out.println(Arrays.toString(assignment));
        String variableName = assignment[0];
        if (isValidVar(variableName)) {
            if (assignment[1].matches("[a-zA-Z]+")) {
                assignment[1] = variables.get(assignment[1]).toString();
            }
            BigInteger variableValue = new BigInteger(assignment[1]);
            variables.put(variableName, variableValue);
        } else {
            System.out.println("Invalid identifier");
        }
        //System.out.println(variables);
    }
    public static String simplifyInput(String input) {
        input = input.replace("--", "+");
        input = input.replaceAll("\\++", "+");
        input = input.replace("+-", "-");
        input = input.replace("-+", "-");
        input = input.replaceAll("\\s+", "");
        return input;
    }

    public static String expressionType(String input) {
        if (input.matches("^\\s*[a-zA-Z]+\\s*=\\s*-?(?:\\d+|[a-zA-Z]+)\\s*$")) {
            return "ASSIGNMENT";
        }
        else {
            return "ARITHMETIC";
        }

    }

    public static void calculate(Deque<BigInteger> values, Deque<Character> ops) {
        char operator = ops.pop();
        BigInteger right = values.pop();
        BigInteger left = values.pop();

        BigInteger result = BigInteger.ZERO;

        switch (operator) {
            case '+':
                result = left.add(right);
                break;

            case '-':
                result = left.subtract(right);
                break;

            case '*':
                result = left.multiply(right);
                break;

            case '/':
                result = left.divide(right);
                break;
        }
        values.push(result);
    }


    public static void evaluateExpression(String input, Map<String, BigInteger> variables) {
        if (input.matches("[-?0-9]+")) {
            System.out.println((input.trim()));
            return;
        } else if ((input.matches("[a-zA-Z]+"))) {
            if (variables.containsKey(input)) {
                System.out.println(variables.get(input));
                return;
            } else {
                System.out.println("Unknown variable");
                return;
            }
        } else {
            String varString = input.replaceAll("[\\d\\s+-/*]", " ");
            String[] vars = varString.trim().split("\\s+");
            for (String var: vars) {
                if (variables.containsKey(var)) {
                    input = input.replace(var, variables.get(var).toString());
                }
            }
            input = simplifyInput(input);

            Deque<BigInteger> vals = new ArrayDeque<>();
            Deque<Character> ops = new ArrayDeque<>();

            Map<Character, Integer> priority = new HashMap<>();
            priority.put('+', 1);
            priority.put('-', 1);
            priority.put('*', 2);
            priority.put('/', 2);

            for (int i = 0; i < input.length(); i++) {
                char current = input.charAt(i);
                if (Character.isWhitespace(current)) {
                    continue;
                }
                if (Character.isDigit(current) || i == 0) {
                    StringBuilder num = new StringBuilder();
                    while (i < input.length() && Character.isDigit(input.charAt(i))) {
                        num.append(input.charAt(i));
                        i++;
                    }

                    vals.push(new BigInteger(num.toString()));
                    i--;
                } else if (current == '(') {

                    ops.push(current);
                } else if (current == ')') {

                    // Calculate everything inside ()
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        calculate(vals, ops);
                    }
                    if (ops.isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    ops.pop();
                } else if (priority.containsKey(current)) {

                    while (!ops.isEmpty()
                            && ops.peek() != '('
                            && priority.get(ops.peek()) >= priority.get(current)) {

                        calculate(vals, ops);
                    }

                    ops.push(current);
                } else {
                    throw new IllegalArgumentException();
                }

            }
            while (!ops.isEmpty()) {

                if (ops.peek() == '(') {
                    throw new IllegalArgumentException();
                }

                calculate(vals, ops);
            }


            // There should only be one value remaining
            if (vals.size() != 1) {
                throw new IllegalArgumentException();
            }

            System.out.println(vals.pop());
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean isRunning = true;
        Map<String, BigInteger> variables = new HashMap<>();
        while (isRunning) {
            input = scanner.nextLine().trim();
            if (input.equals(("/exit"))) {
                System.out.println("Bye!");
                isRunning = false;
            } else if (input.equals(("/help"))) {
                System.out.println("The program calculates the sum of numbers");
            } else if (input.matches("^/.*")) {
                System.out.println("Unknown command");
            } else if (input.isEmpty()) {
                continue;
            } else {
                try {
                    input = simplifyInput(input);
                    String expressionType = expressionType(input);
                    switch (expressionType) {

                        case "ASSIGNMENT":
                            assignVariable(variables, input);
                            break;
                        case "ARITHMETIC":
                            evaluateExpression(input, variables);
                            break;
                        default:
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("Invalid expression");
                }

            }
        }
    }
}
