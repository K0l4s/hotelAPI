package com.hotel.hotelapi.service;

public interface IEmailService {
    boolean sendEmail(String from, String to, String subject, String body);
}
