package ca.usherbrooke.notifius.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACde165c96457ecbb7608e016a3a454c70";
    public static final String AUTH_TOKEN = "725f5362975c14858ff47e0664e0ce11";

    public void sendSMS(String to, String text)
    {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(to), // to
                         new PhoneNumber("+14503006542"), // from
                         text)
                .create();

        System.out.println(message.getSid());
    }
}
