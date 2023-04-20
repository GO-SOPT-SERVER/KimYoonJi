package sopt.org.SecondSeminar.domain;

import lombok.Getter;

@Getter
public class Posting {
    private Long id;
    private String title;
    private String context;
    private String writer;
    private String time;

    public Posting(String title, String context, String writer, String time) {
        this.title = title;
        this.context = context;
        this.writer = writer;
        this.time = time;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "\n" +
                "title: " + this.title + "\n" +
                "context: " + this.context + "\n" +
                "writer: " + this.writer + "\n" +
                "time: " + this.time;
    }
}
