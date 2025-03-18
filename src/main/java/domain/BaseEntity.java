package domain;

import java.time.LocalDateTime;

public class BaseEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void setUpdatedAt() {
        updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
