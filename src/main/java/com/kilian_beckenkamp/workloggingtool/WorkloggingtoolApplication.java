package com.kilian_beckenkamp.workloggingtool;

import com.kilian_beckenkamp.workloggingtool.frameManagement.FrameManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;

@SpringBootApplication
@EnableJpaRepositories
public class WorkloggingtoolApplication {

	public static JFrame frame = new JFrame();


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WorkloggingtoolApplication.class, args);

		FrameManager fm = context.getBean(FrameManager.class);
		fm.showStartPanel();
	}

}
