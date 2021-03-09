package com.project.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GenerateErrorCodeService {
	public String generateAlfaNumCode() { // codeType: X000X
		String code;
		String alfa1 = this.generateAlfaCode();
		String alfa2 = this.generateAlfaCode();
		int num = this.generateNumCode();
		
		code = alfa1 + num + alfa2;
		
		return code;
	}
	
	private String generateAlfaCode() {
		char letter;
		int position;
		String alfa = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		Random randomLet = new Random();
		
		position = (int) (randomLet.nextDouble() * alfa.length()-1);
		
		letter = alfa.charAt(position);
		
		return Character.toString(letter);
	}
	
	private int generateNumCode() {
		int num;
		Random randomNum = new Random();
		
		num = (int) (randomNum.nextDouble() * 999 + 100);
		
		return num;
	}
}
