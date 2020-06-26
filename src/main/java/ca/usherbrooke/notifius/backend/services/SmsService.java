/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService
{
    private final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Value("${twilio.account_sid}")
    public String account_sid;
    @Value("${twilio.account_token}")
    public String auth_token;
    @Value("${twilio.phone_number}")
    public String phoneNumber;

    public void sendSMS(String to, String text)
    {
        Twilio.init(account_sid, auth_token);
        Message message = Message
                .creator(new PhoneNumber(to), new PhoneNumber(phoneNumber), text)
                .create();
    }
}
