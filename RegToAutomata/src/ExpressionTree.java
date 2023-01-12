import java.util.Stack;

public class ExpressionTree {
    public Node root;

    private static boolean isOperator(Character c){
        String operators = "|*.";
        return operators.contains(c.toString());
    }
    public ExpressionTree(String regex){
        Stack<Node> stack = new Stack<Node>();
        Node temp;
        for(int i = 0 ; i < regex.length() ; i++){
            Character c = regex.charAt(i);
            if(!isOperator(c)){
                temp = new Node(c);
                stack.push(temp);
            }else{
                if(c == '*'){
                    temp = new Node(c);
                    temp.left = stack.pop();
                    stack.push(temp);
                }else{
                    temp = new Node(c);
                    temp.right = stack.pop();
                    temp.left = stack.pop();
                    stack.push(temp);
                }

            }
        }
        root = stack.pop();
    }
}
