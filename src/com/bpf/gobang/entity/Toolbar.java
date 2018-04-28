package com.bpf.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Toolbar {
	private static Toolbar toolbar = null;
	
	private Toolbar() {
		init();
	}
	
	//提供一个全局的静态方法
    public static Toolbar getToolbar(){
        if(toolbar == null){
            synchronized(Toolbar.class){
                if(toolbar == null){
                	toolbar = new Toolbar();
                }
            }
        }
        return toolbar;
    }
    
    //返回按钮图片位置
	private String BACK_IMAGE_URL;
	//返回按钮图片（大）位置
	private String BIG_BACK_IMAGE_URL;
	//重玩按钮图片位置
	private String RESTART_IMAGE_URL;
	//重玩按钮图片（大）位置
	private String BIG_RESTART_IMAGE_URL;
	//重玩按钮图片位置
	private String REGRET_IMAGE_URL;
	//重玩按钮图片（大）位置
	private String BIG_REGRET_IMAGE_URL;
	//工具栏背景图片
	private String TOOLBAR_BACKGROUND_IMAGE_URL;
	//提示图片位置
	private String PROMPT_IMAGE_URL;
	//提示图片（大）位置
	private String BIG_PROMPT_IMAGE_URL;
	//当前按钮
    private String CURRENT_BUTTON;
	
	public String getBACK_IMAGE_URL() {
		return BACK_IMAGE_URL;
	}
	
	public String getBIG_BACK_IMAGE_URL() {
		return BIG_BACK_IMAGE_URL;
	}
	
	public String getRESTART_IMAGE_URL() {
		return RESTART_IMAGE_URL;
	}

	public String getBIG_RESTART_IMAGE_URL() {
		return BIG_RESTART_IMAGE_URL;
	}

	public String getREGRET_IMAGE_URL() {
		return REGRET_IMAGE_URL;
	}

	public String getBIG_REGRET_IMAGE_URL() {
		return BIG_REGRET_IMAGE_URL;
	}

	public String getTOOLBAR_BACKGROUND_IMAGE_URL() {
		return TOOLBAR_BACKGROUND_IMAGE_URL;
	}

	public String getPROMPT_IMAGE_URL() {
		return PROMPT_IMAGE_URL;
	}

	public String getBIG_PROMPT_IMAGE_URL() {
		return BIG_PROMPT_IMAGE_URL;
	}

	public String getCURRENT_BUTTON() {
		return CURRENT_BUTTON;
	}

	public void setCURRENT_BUTTON(String cURRENT_BUTTON) {
		CURRENT_BUTTON = cURRENT_BUTTON;
	}

	/**
	 * <p>Title: init</p>
	 * <p>Description: 该类初始化方法，创建该类实例时，从配置文件中获取值赋给成员变量</p>
	 */
	public void init() {
		InputStream inputStream = null;
		try {
			Properties properties = new Properties();
			inputStream = new FileInputStream("cfg/cfg.properties");
			properties.load(inputStream);
			
			PROMPT_IMAGE_URL = properties.getProperty("prompt_image_url");
			BIG_PROMPT_IMAGE_URL = properties.getProperty("big_prompt_image_url");
			BACK_IMAGE_URL = properties.getProperty("back_image_url");
			BIG_BACK_IMAGE_URL = properties.getProperty("big_back_image_url");
			RESTART_IMAGE_URL = properties.getProperty("restart_image_url");
			BIG_RESTART_IMAGE_URL = properties.getProperty("big_restart_image_url");
			REGRET_IMAGE_URL = properties.getProperty("regret_image_url");
			BIG_REGRET_IMAGE_URL = properties.getProperty("big_regret_image_url");
			TOOLBAR_BACKGROUND_IMAGE_URL = properties.getProperty("toolbar_background_image_url");
			
			CURRENT_BUTTON = "";
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
