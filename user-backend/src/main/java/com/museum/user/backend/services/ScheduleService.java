package com.museum.user.backend.services;

import com.museum.user.backend.model.entities.SessionEntity;
import com.museum.user.backend.repositories.SessionEntityRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ScheduleService {
    private final ThreadPoolTaskScheduler taskScheduler;
    private final MailService mailService;
    private final AuthorizationService authorizationService;
    private final SessionEntityRepository sessionRepository;

    public ScheduleService(ThreadPoolTaskScheduler taskScheduler, MailService mailService, AuthorizationService authorizationService, SessionEntityRepository sessionRepository) {
        this.taskScheduler = taskScheduler;
        this.mailService = mailService;
        this.authorizationService = authorizationService;
        this.sessionRepository = sessionRepository;
        removeExpiredSessions();
    }

    public void sendNotification(String email, Instant time, String message){
        taskScheduler.schedule(() -> mailService.sendReminderMail(email, message), time);
    }

    @Scheduled(cron = "0 */30 * * * *")
    private void removeExpiredSessions() {
        List<SessionEntity> sessionList = sessionRepository.findAll();
        sessionList.forEach(sessionEntity -> {
            if (sessionEntity.getValidUntil().before(Timestamp.from(Instant.now()))) {
                sessionRepository.deleteById(sessionEntity.getId());
            }
        });
        authorizationService.updateSessionList();
    }
}
