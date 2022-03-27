package com.museum.user.backend.services;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ScheduleService {
    private final ThreadPoolTaskScheduler taskScheduler;
    private final MailService mailService;

    public ScheduleService(ThreadPoolTaskScheduler taskScheduler, MailService mailService) {
        this.taskScheduler = taskScheduler;
        this.mailService = mailService;
    }

    public void sendNotification(String email, Instant time, String message){
        taskScheduler.schedule(() -> mailService.sendReminderMail(email, message), time);
    }
}
