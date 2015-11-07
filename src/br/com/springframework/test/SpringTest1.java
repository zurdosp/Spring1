package br.com.springframework.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest1 {

	public static void main(String[] args) {
		ApplicationContext applicationContext = 
					new ClassPathXmlApplicationContext("beans.xml");
		Person person = (Person) applicationContext.getBean("person");
		
		person.talk();
		System.out.println("Calling ConfigurableApplicationContext close()...");
		((ConfigurableApplicationContext)applicationContext).close();
	}
}