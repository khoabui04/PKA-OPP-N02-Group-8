package com.phenikaa.library.service;

import com.phenikaa.library.model.Notification;
import com.phenikaa.library.model.Borrowing;
import com.phenikaa.library.model.Librarian;
import com.phenikaa.library.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    // CRUD Operations
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    
    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }
    
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
    
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
    
    // Business Logic
    public List<Notification> getNotificationsByUser(Long userId, Notification.UserType userType) {
        return notificationRepository.findByTargetUserIdAndTargetUserType(userId, userType);
    }
    
    public List<Notification> getUnreadNotificationsByUser(Long userId, Notification.UserType userType) {
        return notificationRepository.findByTargetUserIdAndTargetUserTypeAndIsReadFalse(userId, userType);
    }
    
    public Long getUnreadNotificationsCount(Long userId, Notification.UserType userType) {
        return notificationRepository.countUnreadNotifications(userId, userType);
    }
    
    public List<Notification> getUnreadNotifications() {
        return notificationRepository.findByIsReadFalse();
    }
    
    public List<Notification> getRecentNotifications(int limit) {
        return notificationRepository.findRecentNotifications(Pageable.ofSize(limit));
    }
    
    @Transactional
    public void markAsRead(Long notificationId) {
        notificationRepository.findById(notificationId)
            .ifPresent(notification -> {
                notification.markAsRead();
                notificationRepository.save(notification);
            });
    }
    
    @Transactional
    public void markAllAsReadForUser(Long userId, Notification.UserType userType) {
        List<Notification> unreadNotifications = getUnreadNotificationsByUser(userId, userType);
        unreadNotifications.forEach(Notification::markAsRead);
        notificationRepository.saveAll(unreadNotifications);
    }
    
    // Notification Creation Methods
    public Notification createBorrowingNotification(Borrowing borrowing, String action) {
        Notification notification = new Notification();
        
        switch (action) {
            case "BORROWED":
                notification.setTitle("Sách đã được mượn");
                notification.setMessage(String.format("Bạn đã mượn sách '%s' thành công. Ngày trả: %s", 
                    borrowing.getBook().getTitle(), borrowing.getDueDate()));
                notification.setType(Notification.NotificationType.SUCCESS);
                break;
                
            case "RETURNED":
                notification.setTitle("Sách đã được trả");
                notification.setMessage(String.format("Bạn đã trả sách '%s' thành công.", 
                    borrowing.getBook().getTitle()));
                notification.setType(Notification.NotificationType.SUCCESS);
                break;
                
            case "RENEWED":
                notification.setTitle("Gia hạn thành công");
                notification.setMessage(String.format("Sách '%s' đã được gia hạn đến %s", 
                    borrowing.getBook().getTitle(), borrowing.getDueDate()));
                notification.setType(Notification.NotificationType.INFO);
                break;
        }
        
        notification.setTargetUserId(borrowing.getReader().getId());
        notification.setTargetUserType(Notification.UserType.READER);
        notification.setRelatedEntityId(borrowing.getId());
        notification.setRelatedEntityType("BORROWING");
        notification.setPriority(Notification.NotificationPriority.NORMAL);
        
        return notificationRepository.save(notification);
    }
    
    public Notification createOverdueNotification(Borrowing borrowing) {
        Notification notification = new Notification();
        notification.setTitle("Sách quá hạn");
        notification.setMessage(String.format("Sách '%s' đã quá hạn %d ngày. Vui lòng trả sách sớm để tránh phạt.", 
            borrowing.getBook().getTitle(), borrowing.getDaysOverdue()));
        notification.setType(Notification.NotificationType.WARNING);
        notification.setPriority(Notification.NotificationPriority.HIGH);
        notification.setTargetUserId(borrowing.getReader().getId());
        notification.setTargetUserType(Notification.UserType.READER);
        notification.setRelatedEntityId(borrowing.getId());
        notification.setRelatedEntityType("BORROWING");
        
        return notificationRepository.save(notification);
    }
    
    public Notification createDueSoonNotification(Borrowing borrowing) {
        Notification notification = new Notification();
        notification.setTitle("Sách sắp hết hạn");
        notification.setMessage(String.format("Sách '%s' sẽ hết hạn vào %s. Vui lòng chuẩn bị trả sách.", 
            borrowing.getBook().getTitle(), borrowing.getDueDate()));
        notification.setType(Notification.NotificationType.REMINDER);
        notification.setPriority(Notification.NotificationPriority.NORMAL);
        notification.setTargetUserId(borrowing.getReader().getId());
        notification.setTargetUserType(Notification.UserType.READER);
        notification.setRelatedEntityId(borrowing.getId());
        notification.setRelatedEntityType("BORROWING");
        
        return notificationRepository.save(notification);
    }
    
    public Notification createSystemNotification(String title, String message, 
                                               Notification.NotificationType type,
                                               Notification.NotificationPriority priority,
                                               Librarian createdBy) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setPriority(priority);
        notification.setCreatedBy(createdBy);
        
        return notificationRepository.save(notification);
    }
    
    public Notification createUserNotification(String title, String message,
                                             Long targetUserId, Notification.UserType userType,
                                             Notification.NotificationType type,
                                             Librarian createdBy) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setTargetUserId(targetUserId);
        notification.setTargetUserType(userType);
        notification.setCreatedBy(createdBy);
        notification.setPriority(Notification.NotificationPriority.NORMAL);
        
        return notificationRepository.save(notification);
    }
    
    // Cleanup methods
    @Transactional
    public void cleanupExpiredNotifications() {
        List<Notification> allNotifications = notificationRepository.findAll();
        List<Notification> expiredNotifications = allNotifications.stream()
            .filter(Notification::isExpired)
            .toList();
        
        notificationRepository.deleteAll(expiredNotifications);
    }
    
    @Transactional
    public void cleanupOldReadNotifications(int daysOld) {
        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(daysOld);
        List<Notification> allNotifications = notificationRepository.findAll();
        List<Notification> oldReadNotifications = allNotifications.stream()
            .filter(n -> n.getIsRead() && n.getReadAt() != null && n.getReadAt().isBefore(cutoffDate))
            .toList();
        
        notificationRepository.deleteAll(oldReadNotifications);
    }
}