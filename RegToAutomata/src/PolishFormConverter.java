import java.util.Stack;

public class PolishFormConverter {

    private static boolean isAlpha(Character c){
        if((c >= 'a' && c <= 'z') || (c >= 'A') && c <= 'Z'){
            return true;
        }
        return false;
    }
    private static boolean isOperator(Character c){
        String operators = "|*.";
        return operators.contains(c.toString());
    }

    private static int priority(Character c){
        if(c == '*') return 3; //kleene star
        if(c == '.') return 2; //concatenation
        if(c == '|') return 1; //union
        return 0;
    }

    public static String addConcatenationSymbol(String s){
        String newString = new String("");
        for(int i = 0; i < s.length(); i++){
            newString = newString + s.charAt(i);
            if(i < s.length() - 1){
                Character c1 = s.charAt(i);
                Character c2 = s.charAt(i+1);
                if((isAlpha(c1) || c1 == ')' || c1 == '*') && (isAlpha(c2) || c2 == '(')){
                    newString = newString + '.';
                }


            }
        }
        newString = '(' + newString + ')';
        return newString;
    }

    public static boolean isRegularExpressionCorrect(String s){
        int openBraces = 0;
        if(isOperator(s.charAt(0))){
            return false;
        }
        if(isOperator(s.charAt(s.length()-1)) && s.charAt(s.length()-1) != '*'){
            return false;
        }
        for(int i = 0; i < s.length(); i++){
            Character c = s.charAt(i);
            if(!isAlpha(c) && c != '(' && c != ')' && !isOperator(c)){
                return false;
            }
            if(c == '('){
                openBraces++;
            } else if ( c == ')') {
                openBraces--;
            }
            if(openBraces < 0)
                return false;
            if(i < s.length() - 1){
                if(isOperator(c) && isOperator(s.charAt(i+1)) && c!='*'){
                    return false;
                }
            }
        }
        if(openBraces != 0)
            return false;

        return true;
    }
    public static String convertToPolishForm(String s){
        Stack<Character> stack = new Stack<Character>();
        String polishForm = new String("");
        Character assist;
        for(int i = 0; i < s.length(); i++){
            Character c = s.charAt(i);
            if(isAlpha(c)){
                polishForm = polishForm + c;
            }
            if(isOperator(c)){
                int prio = priority(c);
                while(!stack.isEmpty()){
                    assist = stack.peek();
                    if(isOperator(assist) && priority(assist) >= prio){
                        polishForm = polishForm + assist;
                        stack.pop();
                    }else{
                        break;
                    }
                }
                stack.push(c);
                continue;
            }
            if(c == '('){
                stack.push(c);
                continue;
            }
            if(c == ')'){
                while(stack.peek() != '('){
                    polishForm = polishForm + stack.peek();
                    stack.pop();
                }
                stack.pop();
                continue;
            }
        }
        return polishForm;
    }
}
