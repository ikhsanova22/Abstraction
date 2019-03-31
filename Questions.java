package Game;

public interface Questions {

    public String getQuestion(int point);
    public boolean verifyAnswer(String correct);
    public String[] getVariants();


}
