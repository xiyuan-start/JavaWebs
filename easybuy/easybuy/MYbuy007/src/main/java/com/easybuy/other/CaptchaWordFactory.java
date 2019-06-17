package com.easybuy.other;


import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.github.bingoohuang.patchca.word.WordBean;


public class CaptchaWordFactory extends RandomWordFactory {

	private String defaultValue = "";
	
	private boolean isDefaultState = false; // 默认为非固定值状态
	
	public CaptchaWordFactory() {
		
		// 文本范围和长度
		characters = "abcdefskmnwx23456789";
		minLength = 4;
		maxLength = 4;
	}
	
	public CaptchaWordFactory(String defaultValue) {
		
		this.defaultValue = defaultValue;
		this.isDefaultState = true; //开启绑定值状态 
	}
	
	public WordBean getNextWord() {


		if(isDefaultState) {
			return new WordBean("","","");
		} else {
			return super.getNextWord();
		}
	}
}