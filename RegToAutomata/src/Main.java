import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String expression = getExpression();
        expression = PolishFormConverter.addConcatenationSymbol(expression);
        if(!PolishFormConverter.isRegularExpressionCorrect(expression)){
            return;
        }
        System.out.println("Expresie : " + expression); System.out.println("Polish form : " + PolishFormConverter.convertToPolishForm(expression));
        ExpressionTree expressionTree = new ExpressionTree(PolishFormConverter.convertToPolishForm(expression));
        System.out.println();
        inorder(expressionTree.root);
        // if validate expression
        /*int choice = 9;
        while(choice != 0){
            Scanner myScanner = new Scanner(System.in);
            showMenu();
            choice = myScanner.nextInt();
            System.out.println();
            switch(choice){
                case 1: break;
                case 2: System.out.println("Expresie : " + expression); System.out.println("Polish form : " + PolishFormConverter.convertToPolishForm(expression));break;
                case 3: break;
                case 0: break;
                default:break;
            }
        }*/

    }
    private static void inorder(Node node){
        if(node == null){
            return;
        }
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }
    private static String getExpression(){
        String expression = new String();
        try {
            File file = new File("C:\\Users\\stefan\\IdeaProjects\\RegToAutomata\\src\\RegularExpression.txt");
            Scanner myReader = new Scanner(file);
            expression = myReader.nextLine();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return expression;
    }

    private static void showMenu(){
        System.out.println();
        System.out.println("1 - Print automata");
        System.out.println("2 - Print expression");
        System.out.println("3 - Check word");
        System.out.println("0 - Exit");
        System.out.println();
    }
}