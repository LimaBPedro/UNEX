package mvc.model;

public class Player {
    private String name;
    private String color;
    private Integer score;

    public String getName(){
        return this.name;
    }

    public void  setName(String name){
        this.name = name;
    }

    public String getColor(){
        return this.color;
    }

    public void  setColor(String color){
        this.color = color;
    }

    public Integer getScore(){
        return this.score;
    }

    public void  setScore(Integer score){
        this.score = score;
    }
}
