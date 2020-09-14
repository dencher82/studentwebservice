package telran.ashkelon2020.student.service.messaging;

import org.springframework.stereotype.Service;

import telran.ashkelon2020.student.dto.MessageDto;
import telran.ashkelon2020.student.service.MessagingService;

@Service
public class ViberService implements MessagingService{
	
	@Override
	public MessageDto sendMessage(String message) {
		return MessageDto.builder()
				.messager("Viber")
				.payload(message)
				.build();
	}
	
}
