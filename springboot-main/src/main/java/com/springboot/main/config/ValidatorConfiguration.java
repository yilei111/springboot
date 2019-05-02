package com.springboot.main.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * hibernate-validator 配置
 *         通常的校验会验证所有属性并返回一个错误消息集合，而快速失败模式通常按顺序验证到第一个字段不符合验证要求时，就可以直接拒绝请求了。
 *         Hibernate Validator有以下两种验证模式：
 *         1、普通模式（默认） 会校验完所有的属性，然后返回所有的验证失败信息 。2、快速失败返回模式 只要有一个验证失败，则返回此条验证失败信息
 * @author Mr yi
 */
@Configuration
public class ValidatorConfiguration {
	@Bean
	public Validator validator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
				.addProperty("hibernate.validator.fail_fast", "true").buildValidatorFactory();//true-快速失败返回模式； false-普通模式
		Validator validator = validatorFactory.getValidator();
		return validator;
	}
}
