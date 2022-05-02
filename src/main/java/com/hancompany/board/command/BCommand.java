package com.hancompany.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BCommand {
	
	void execute(HttpServletRequest request, HttpServletResponse response); //인터페이스는 추상메소드, 제목만 있는 얘들 접근 지정자들을
}
