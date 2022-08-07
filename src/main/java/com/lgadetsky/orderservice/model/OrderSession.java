package com.lgadetsky.orderservice.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Сущность сессии пользователя")
public class OrderSession {
	@Schema(description = "Счетчик сессий", example = "1000")
	private int id;
	@Schema(description = "Уникальный идентификатор сессии", example = "1234-AAFF-BB55-DD22")
	private String sessionId;
	@Schema(description = "Время открытия сессии", example = "2022-08-08 00:00:00")
	private Date startTime;
	@Schema(description = "Время действия сессии в минутах", example = "1440")
	private int timeoutMinutes;
}
