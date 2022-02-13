package model;

import enums.MessageSubject;
import lombok.Data;

@Data
public class Message {
    private MessageSubject subjectHeading;
    private String email;
    private String orderReference;
    private String message;

}
