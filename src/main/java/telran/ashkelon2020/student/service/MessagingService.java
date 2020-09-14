package telran.ashkelon2020.student.service;

import telran.ashkelon2020.student.dto.MessageDto;

public interface MessagingService {
	
	MessageDto sendMessage(String message);
	
}