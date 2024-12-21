package com.samilemir.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	
	private String eStatic;
	
	private MessageType messageType;
	
	public String prepErrorMessage() {
		StringBuilder build =new StringBuilder();
		build.append(messageType.getMessage());
		if(this.eStatic!=null) {
			build.append(""+eStatic);
		}
		return build.toString();
	}
	

}
