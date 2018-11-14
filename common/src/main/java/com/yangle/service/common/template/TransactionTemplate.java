package com.yangle.service.common.template;

import com.yangle.service.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * yishao
 * 事物模板
 * @param <T>
 */
public abstract class TransactionTemplate<T> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected abstract T process() throws BizException;

	/**
	 * 名称规则参见 com.mogujie.raptor.support.spring.DataSourceScanner#registerTransactionManager
	 * @param txName
	 * @return
	 */
	public T execute(String txName) {
//		PlatformTransactionManager platformTransactionManager = SpringContextHolder.getBean(txName);
//		// 事务操作
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		TransactionStatus txStatus = platformTransactionManager.getTransaction(def);
//		try {
//			T result = process();
//			platformTransactionManager.commit(txStatus);
//			return result;
//		} catch (BizException e) {
//			logger.error("on error when execute transaction:", e);
//			platformTransactionManager.rollback(txStatus);
//			throw e;
//		}catch (Exception e){
//			logger.error("on error when execute transaction:", e);
//			platformTransactionManager.rollback(txStatus);
//			throw e;
//		}
		return null;
	}
}
