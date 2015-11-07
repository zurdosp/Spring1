package br.com.springframework.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Mobile implements BeanNameAware, 
	BeanClassLoaderAware, BeanFactoryAware, 
	InitializingBean, DisposableBean {

	private String mark;
	
	private String model;
	
	private String beanName = null;

	private int countSteps = 0;
	
	public void initMobile() {
		System.out.println(++countSteps + "- init-method, init a Mobile");
	}
	
	/**
	 * Makes the object aware of their bean name in a bean factory.
	 */
	@Override
	public void setBeanName(String name) {
		System.out.println("\n" + ++countSteps + "- BeanNameAware, " 
				+ name +" has been Initialized. Makes the object aware of their bean name in a bean factory." );		
		beanName = name;
	}

	/**
	 * Callback that allows a bean to be aware of the bean class loader; that is, 
	 * the class loader used by the present bean factory to load bean classes.
	 */
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println(++countSteps + "- ClassLoaderAware, "
				+ "parent class loader:"+classLoader.getParent().getClass().getName() + 
				" Gives the class loader used by the present bean factory to load bean classes.");
	}
		
	/**
	 * Or @private @Autowired BeanFactory beanFactory; in Spring 2.5+;
	 * Gives the bean access to the Bean Factory that created it.
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) 
			throws BeansException {
		System.out.println(++countSteps + "- BeanFactoryAware: " + beanFactory.getClass().getName() + " Gives the bean access to the Bean Factory that created it.");
		
	}
	
	/**
	 * React once all their properties have been set by a 
	 * BeanFactory: for example, to perform custom initialization, 
	 * or merely to check that all mandatory properties have been set.
	 * Called once all the Bean properties defined in the Configuration 
	 * file are set.
	 * Recommended user init-method
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(++countSteps + "- InitializingBean: afterPropertiesSet. React once all their properties have been set by a BeanFactory");		
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println(++countSteps + "- DisposableBean. Released the bean: " + beanName + " Called after Spring container is released the bean.");
		
	}
	
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
