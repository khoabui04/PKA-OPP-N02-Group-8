package com.phenikaa.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Tiêu đề thông báo không được để trống")
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String message;
    
    @Enumerated(EnumType.STRING)
    private NotificationType type = NotificationType.INFO;
    
    @Enumerated(EnumType.STRING)
    private NotificationPriority priority = NotificationPriority.NORMAL;
    
    @Column(name = "is_read")
    private Boolean isRead = false;
    
    @Column(name = "target_user_id")
    private Long targetUserId;
    
    @Column(name = "target_user_type")
    @Enumerated(EnumType.STRING)
    private UserType targetUserType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Librarian createdBy;
    
    @Column(name = "related_entity_id")
    private Long relatedEntityId;
    
    @Column(name = "related_entity_type")
    private String relatedEntityType;
    
    @Column(name = "action_url")
    private String actionUrl;
    
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "read_at")
    private LocalDateTime readAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Business methods
    public void markAsRead() {
        this.isRead = true;
        this.readAt = LocalDateTime.now();
    }
    
    public boolean isExpired() {
        return expiresAt != null && LocalDateTime.now().isAfter(expiresAt);
    }
    
    public boolean isActive() {
        return !isRead && !isExpired();
    }
    
    public enum NotificationType {
        INFO("Thông tin"),
        WARNING("Cảnh báo"),
        ERROR("Lỗi"),
        SUCCESS("Thành công"),
        OVERDUE("Quá hạn"),
        REMINDER("Nhắc nhở"),
        SYSTEM("Hệ thống");
        
        private final String displayName;
        
        NotificationType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    public enum NotificationPriority {
        LOW("Thấp"),
        NORMAL("Bình thường"),
        HIGH("Cao"),
        URGENT("Khẩn cấp");
        
        private final String displayName;
        
        NotificationPriority(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    public enum UserType {
        READER("Độc giả"),
        LIBRARIAN("Thủ thư");
        
        private final String displayName;
        
        UserType(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}