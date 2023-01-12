public class Transition {
    private Character startState;
    private Character endState;
    private Character letter;

    public Transition(Character startState, Character endState, Character letter){
        this.startState = startState;
        this.endState = endState;
        this.letter = letter;
    }


    public Character getStartState() {
        return startState;
    }

    public Character getEndState(){
        return endState;
    }

    public Character getLetter(){
        return letter;
    }
}
