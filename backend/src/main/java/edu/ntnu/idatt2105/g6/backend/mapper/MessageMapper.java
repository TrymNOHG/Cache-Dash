package edu.ntnu.idatt2105.g6.backend.mapper;

import edu.ntnu.idatt2105.g6.backend.dto.MessageDTO;

import java.sql.Timestamp;
import java.util.Date;

public class MessageMapper {

    public void hello() {
        MessageDTO messageDTO = new MessageDTO(1L, "Leon",
                "Hi", new Timestamp(new Date().getTime()));

    }

}
