package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class Comments {
    @Schema(description = "общее количество комментариев")
    private int count;
    private List<Comment>results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comment> getResults() {
        return results;
    }

    public void setResults(List<Comment> results) {
        this.results = results;
    }

    public Comments(int count, List<Comment> results) {
        this.count = count;
        this.results = results;
    }
}
