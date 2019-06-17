package com.easybuy.other;


import com.github.bingoohuang.patchca.background.SingleColorBackgroundFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.AbstractCaptchaService;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;

import java.awt.*;
import java.util.Random;

/*
 *该类的功能以及特点描述：电影订票系统 产生验证码
 *该类是否被编译测试：否
 *@see(与之相关联的类)：   来源：
 *                     中间：
 *                     去处：
 *开发公司或单位：成007个人
 *版权：成007

 *@author(作者)：成007
 *@since（该文件使用的jdk）：JDK1.8
 *@version（版本）：1.0
 *@数据库查询方式：mysql+hibernate
 *@date(开发日期)：2018/6/20
 *改进：
 *最后更改日期：
 */
public class CaptchaService extends AbstractCaptchaService {

	private static final Random random = new Random();
	
	/**
	 * 不可定制的验证码服务(默认)
	 */
	public CaptchaService() {
		
		// 文本内容
		wordFactory = new CaptchaWordFactory();
		
		// 字体
		fontFactory = new RandomFontFactory();
		
		// 效果
		textRenderer = new BestFitTextRenderer();
		
		// 背景
		backgroundFactory = new SingleColorBackgroundFactory();
		
		// 字体颜色
		colorFactory = new SingleColorFactory(new Color(25, 60, 170));
		
		// 样式(曲线波纹加干扰线)
		//0到4 之间生产随机数
		int i = random.nextInt(5) ;
		switch (i) {
			case 0:
				filterFactory = new CurvesRippleFilterFactory(colorFactory);
				break;
			case 1:
				filterFactory = new DoubleRippleFilterFactory();
				break;
			case 2:
				filterFactory = new WobbleRippleFilterFactory();
				break;
			case 3:
				filterFactory = new DiffuseRippleFilterFactory();
				break;
			case 4:
				filterFactory = new MarbleRippleFilterFactory();
				break;
		}
		
		// 图片宽高
		width = 150;
		height = 50;
	}
	
	/**
	 * 可以定制的验证码服务(宽度和高度)
	 * @param width
	 * @param height
	 */
	public CaptchaService(int width, int height) {
		
		// 文本内容
		wordFactory = new CaptchaWordFactory();
		
		// 字体
		fontFactory = new RandomFontFactory();
		
		// 效果
		textRenderer = new BestFitTextRenderer();
		
		// 背景
		backgroundFactory = new SingleColorBackgroundFactory();
		
		// 字体颜色
		colorFactory = new SingleColorFactory(new Color(25, 60, 170));
		
		// 样式(曲线波纹加干扰线)
		filterFactory = new CurvesRippleFilterFactory(colorFactory);
		
		// 图片宽高
		this.width = width;
		this.height = height;
	}
	
	/**
	 * 可以定制的验证码服务(默认值,宽度和高度)
	 * @param defaultValue
	 * @param width
	 * @param height
	 */
	public CaptchaService(String defaultValue, int width, int height) {
		
		// 文本内容
		wordFactory = new CaptchaWordFactory(defaultValue);
		
		// 字体
		fontFactory = new RandomFontFactory();
		
		// 效果
		textRenderer = new BestFitTextRenderer();
		
		// 背景
		backgroundFactory = new SingleColorBackgroundFactory();
		
		// 字体颜色
		colorFactory = new SingleColorFactory(new Color(25, 60, 170));
		
		// 样式(曲线波纹加干扰线)
		filterFactory = new CurvesRippleFilterFactory(colorFactory);
		
		// 图片宽高
		this.width = width;
		this.height = height;
	}
}