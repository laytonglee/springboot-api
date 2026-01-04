package kh.com.laytong.demo.Todo;

import java.time.LocalDateTime;

public class Todo {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime createdAt;

    public Todo() {
    }


    public Todo(Long id, String title, String description, boolean completed, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;
    }

}
