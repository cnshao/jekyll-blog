package com.springms.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 链接Mysql数据库,通过JdbcTemplate编写数据库访问,而且支持事物处理机制。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 17/10/18
 *
 */
@SpringBootApplication
public class MsProviderUserMysqlJdbcTransactionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProviderUserMysqlJdbcTransactionalApplication.class, args);
		System.out.println("【【【【【【 链接MysqlJdbcTransactional数据库微服务 】】】】】】已启动.");
	}
}



/****************************************************************************************
 一、简单用户链接Mysql数据库微服务（通过JdbcTemplate编写数据访问，而且支持事物处理机制，使用 rollbackFor 属性，数据回滚）：

 1、给 insertUser 方法添加注解：@Transactional(propagation = Propagation.REQUIRED, isolation= Isolation.DEFAULT, rollbackFor = Exception.class)；
 2、insertUser 抛出 RollbackExceptionExtendsRuntimeException 异常；
 3、启动 springms-provider-user-mysql-jdbctemplate-transactional 模块服务，启动1个端口；
 4、在浏览器输入地址 http://localhost:8315/simplejdbc/list 可以看到所有用户信息成功的被打印出来；

 5、使用 REST Client 执行 "/simplejdbc/insertUser" 添加参数执行接口，结果数据没有入库，数据被回滚了；
 ****************************************************************************************/







/****************************************************************************************
 二、简单用户链接Mysql数据库微服务（通过JdbcTemplate编写数据访问，而且支持事物处理机制，使用 noRollbackFor 属性，数据回滚）：

 1、给 replaceUser 方法添加注解：@Transactional(noRollbackFor = BusinessExtendsException.class)；
 2、replaceUser 抛出 RuntimeException 异常；
 3、启动 springms-provider-user-mysql-jdbctemplate-transactional 模块服务，启动1个端口；
 4、在浏览器输入地址 http://localhost:8315/simplejdbc/list 可以看到所有用户信息成功的被打印出来；

 5、使用 REST Client 执行 "/simplejdbc/replaceUser" 添加参数执行接口，结果数据没有入库，数据被回滚了；
 ****************************************************************************************/







/****************************************************************************************
 三、简单用户链接Mysql数据库微服务（通过JdbcTemplate编写数据访问，而且支持事物处理机制，使用 noRollbackFor 属性，数据没有回滚）：

 1、给 replaceUser 方法添加注解：@Transactional(noRollbackFor = BusinessExtendsException.class)；
 2、replaceUser 抛出 BusinessSubExtendsException 异常；
 3、启动 springms-provider-user-mysql-jdbctemplate-transactional 模块服务，启动1个端口；
 4、在浏览器输入地址 http://localhost:8315/simplejdbc/list 可以看到所有用户信息成功的被打印出来；

 5、使用 REST Client 执行 "/simplejdbc/replaceUser" 添加参数执行接口，结果数据已经入库了，数据没有回滚了；

 注意：如果要使得 noRollbackFor 属性生效，注解中 @Transactional 必须得只有 noRollbackFor 属性，然后 noRollbackFor 的异常必须得是自己定义的异常，然后抛 RuntimeException 异常，这样我们才可以测出 noRollbackFor 回滚与不回滚的场景出来；
 ****************************************************************************************/







/****************************************************************************************
 四、简单用户链接Mysql数据库微服务（通过JdbcTemplate编写数据访问，而且支持事物处理机制，同时操作多个DAO文件入库，然后选择注解是否进行回滚数据）：

 这里就不做多的解释了，MovieServiceImpl 就是操作多个DAO文件入库，然后处理是否回滚数据的。

 注意：如果要使得 noRollbackFor 属性生效，注解中 @Transactional 必须得只有 noRollbackFor 属性，然后 noRollbackFor 的异常必须得是自己定义的异常，然后抛 RuntimeException 异常，这样我们才可以测出 noRollbackFor 回滚与不回滚的场景出来；

 rollbackFor 属性：抛出的异常是 rollbackFor 异常的子类时都会回滚数据；
 noRollbackFor 属性：抛出的异常是 noRollbackFor 异常的子类时不会回滚数据；抛出的异常不是 noRollbackFor 异常的子类时会回滚数据；
 ****************************************************************************************/







/****************************************************************************************
 资料文档：

 事物传播行为介绍:
 @Transactional(propagation=Propagation.REQUIRED)
 如果有事务, 那么加入事务, 没有的话新建一个(默认情况下)
 @Transactional(propagation=Propagation.NOT_SUPPORTED)
 容器不为这个方法开启事务
 @Transactional(propagation=Propagation.REQUIRES_NEW)
 不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
 @Transactional(propagation=Propagation.MANDATORY)
 必须在一个已有的事务中执行,否则抛出异常
 @Transactional(propagation=Propagation.NEVER)
 必须在一个没有的事务中执行,否则抛出异常(与Propagation.MANDATORY相反)
 @Transactional(propagation=Propagation.SUPPORTS)
 如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不
 用事务.

 事物超时设置:
 @Transactional(timeout=30) //默认是30秒
 事务隔离级别:
 @Transactional(isolation = Isolation.READ_UNCOMMITTED)
 读取未提交数据(会出现脏读, 不可重复读) 基本不使用
 @Transactional(isolation = Isolation.READ_COMMITTED)
 读取已提交数据(会出现不可重复读和幻读)
 @Transactional(isolation = Isolation.REPEATABLE_READ)
 可重复读(会出现幻读)
 @Transactional(isolation = Isolation.SERIALIZABLE)
 串行化
 @Transactional(propagation=Propagation.NESTED)  
 @Transactional (propagation = Propagation.REQUIRED,readOnly=true) //readOnly=true只读,不能
 更新,删除  
 @Transactional (propagation = Propagation.REQUIRED,timeout=30)//设置超时时间  
 @Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT)//设置数据库

 隔离级别
 @Transactional(noRollbackFor=Exception.class)//指定不回滚,遇到运行期例外
 @Transactional(rollbackFor=Exception.class) //指定回滚,需要捕获的例外

 ****************************************************************************************/




