package br.com.springframework.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class Person implements BeanNameAware, 
		BeanClassLoaderAware, BeanFactoryAware, 
		InitializingBean, DisposableBean {
	
	private String name;
	
	private String address;
	
	private String beanName = null;
	
	private int countSteps = 0;

	public void talk() {
		System.out.println("\n ** I said hello! ** \n");		
	}
	
	/**
	 * Makes the object aware of their bean name in a bean factory.
	 */	
	@Override
	public void setBeanName(String name) {
		System.out.println(++countSteps + " - BeanNameAware: " 
				+ name +" has been Initialized. Makes the object aware of their bean name in a bean factory." );
		beanName = name;
		}
	
	/**
	 * Callback that allows a bean to be aware of the bean class loader; that is, 
	 * the class loader used by the present bean factory to load bean classes.
	 */
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println(++countSteps + " - ClassLoaderAware, "
				+ "parent class loader:"+classLoader.getParent().getClass().getName() + 
				" Gives the class loader used by the present bean factory to load bean classes.");
	}

	/**
	 *  Or @private @Autowired BeanFactory beanFactory; in Spring 2.5+;
	 * Gives the bean access to the Bean Factory that created it.
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) 
			throws BeansException {
		System.out.println(++countSteps + " - BeanFactoryAware: Be aware of their owning BeanFactory.");
		
	}

	/**
	 * React once all their properties have been set by a 
	 * BeanFactory: for example, to perform custom initialization, 
	 * or merely to check that all mandatory properties have been set.
	 * Called once all the Bean properties defined in the Configuration 
	 * file are set. 
	 * Called after all bean properties have been set.
	 * Recommended user init-method
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(++countSteps + " - InitializingBean: afterPropertiesSet");		
	}

	/**
	 * Called after Spring container is released the bean.
	 * recommended to use  destroy-method 
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println(++countSteps + "- DisposableBean destroy(): Released the bean: " + beanName + " Called after Spring container is released the bean.");
		
	}
	
	public void defaultDestroy() throws Exception {
		System.out.println(++countSteps + "- default-destroy: Destroying bean: " + beanName + " Default called to destroy Bean.");
		
	}	

	public void defaultInit() throws Exception {
		System.out.println(++countSteps + "- default-init or initPerson: Initializing bean: " + beanName + " Default called to initialize Bean.");
	}	
	
	public void initPerson() {
		System.out.println(++countSteps + " - initPerson: init a Person ou default-init");
	}	
	
	@PostConstruct	   
	public void initIt() throws Exception {
		System.out.println(++countSteps + " - @PostConstruct: Init method after properties are set");
	}

	@PreDestroy
	public void cleanUp() throws Exception {
		System.out.println(++countSteps + " @PreDestroy: Spring Clean Up! Employee is cleaned up now.");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
