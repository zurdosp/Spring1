package br.com.springframework.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyPostProcessor implements BeanPostProcessor {

	/**
	 * Factory hook that allows for custom modification of new bean instances before initialization, 
	 * after BeanName, ClassLoader and BeanFactory aware.
	 */
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("** BeanPostProcessor, Before Initialization " + beanName + " one per Bean, "
				+ "Spring container init instantiating, configuring, and initializing Bean");
		return bean;  
	}

	/**
	 * Factory hook that allows for custom modification of new bean before instances initialization, 
	 * after BeanName, ClassLoader and BeanFactory aware and ini-method call.
	 */
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.	println("** BeanPostProcessor After Initialization " + beanName + " one per Bean, "
				+ "Spring container finish instantiating, configuring, and initializing Bean");
		return bean; 
	}

}
