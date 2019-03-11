package com.springms.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置服务端ClientServer对配置文件内容进行RSA加解密。<br/>
 *
 * 配置服务服务端Server应用入口（设置配置服务端文件 RSA 非对称加解密）。<br/>
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 17/10/18
 *
 */
@SpringBootApplication
@EnableConfigServer
public class MsConfigServerEncryptRsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsConfigServerEncryptRsaApplication.class, args);
        System.out.println("【【【【【【 ConfigServerEncryptRsa微服务 】】】】】】已启动.");
    }
}


/****************************************************************************************
 * 配置服务的路劲规则：
 *
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 ****************************************************************************************/





/****************************************************************************************
 application.yml 涉及到的链接文件内容展示如下：

 http://git.oschina.net/ylimhhmily/OpenSource_CustomCircleLineProgressBar/blob/master/application.yml
 profile: profile-default

 http://git.oschina.net/ylimhhmily/OpenSource_CustomCircleLineProgressBar/blob/master/foobar-dev.yml
 profile: profile-dev

 http://git.oschina.net/ylimhhmily/OpenSource_CustomCircleLineProgressBar/blob/master/foobar-stg1rsa.yml
 profile: '{cipher}AQAnkS1BpmB6Obu/Hg3qeXjDyvakHMIwFUVasKax0BIYHkc50ZRF7kcDLpG1o/iwhY8aAVyPGJXGnU7r
 1Su4NKAkQAHX6+yJq3hWd6N2GloQOIgMjjDc4cockGgxG+yoIFT1ggJ+kbzzMR6TDnPYZ3uDBLsngH0c
 9VkEaagpIcGW+2wCAu5KLq/Zh7m2oq65L4illCpPqOwbfvyiFwCpwU/0MH+QXC0+lPu/yXsxLILwRrh9
 7ZpWduQEDjMznMjSSpkbbeniilHjkUVWXsi4w36f194YN4abl3Lvv+pSzUMA72lGxIl7y50AbaeqyNM8
 ju8OKL0yDMmgmfTdxiVCK9DQIfaZHJeN9A5BEllzT5aOUATTsXtTTVSvL3+2RrcMIXw='

 http://git.oschina.net/ylimhhmily/OpenSource_CustomCircleLineProgressBar/blob/master/foobar-stg2rsa.properties
 profile={cipher}AQBF1BU5+/8EvHkJdoXFvYmYt8K5QvuyTbBl7rwg0G49QSV4IPDDarPFr/10zzcepV8UHpbVHQ9vMJAV6WCefmzMh0YWAPwRsLOgJIgfpbkPacRoVSvwqYEhHshNNQHNOjWT84BDBXiKXcnPeOhnMNUOiB7M05VBZRVwdUuHBN/Zb/L9vxnQLTlwALS1TNfd3JUL7S61oz4JBf/c5FoQUPx/JawUz/uEwi337GCEkFmKacC8fF+cbjLOzsdtHkxrHZtz8QesDCwanwpZl8KbLTzeiU03uAj60qYBaoCYm+A19z+07SXHL0KKhoWp5TcABDv5HY5Bv1astZVp7r+YFAwh/xYnHBYeUwBvmbjTJMYCJEuFNuWr35RhJWJSrAuI1eE=
 ****************************************************************************************/






/****************************************************************************************
 一、配置服务端ClientServer对配置文件内容进行RSA加解密（设置配置服务端文件 RSA 非对称加解密）：

 1、注解：EnableConfigServer
 2、打开windows命令窗口，执行命令：
    >keytool -genkeypair -alias mytestkey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass aaaaa888 -keystore server-rsa.jks -storepass paic1234

    执行完后，正常情况下在会执行命令的目录下生成 server-rsa.jks 文件；

 3、编辑 application.yml 文件，注意填写 encrypt.keyStore 属性字段值；
 4、启动 springms-config-server-encrypt-rsa 模块服务，启动1个端口；

 5、生成配置文件内容，打开windows命令窗口，执行命令：
     >curl.exe localhost:8265/encrypt -d foobar-stg1rsa
     AQAnkS1BpmB6Obu/Hg3qeXjDyvakHMIwFUVasKax0BIYHkc50ZRF7kcDLpG1o/iwhY8aAVyPGJXGnU7r
     1Su4NKAkQAHX6+yJq3hWd6N2GloQOIgMjjDc4cockGgxG+yoIFT1ggJ+kbzzMR6TDnPYZ3uDBLsngH0c
     9VkEaagpIcGW+2wCAu5KLq/Zh7m2oq65L4illCpPqOwbfvyiFwCpwU/0MH+QXC0+lPu/yXsxLILwRrh9
     7ZpWduQEDjMznMjSSpkbbeniilHjkUVWXsi4w36f194YN4abl3Lvv+pSzUMA72lGxIl7y50AbaeqyNM8
     ju8OKL0yDMmgmfTdxiVCK9DQIfaZHJeN9A5BEllzT5aOUATTsXtTTVSvL3+2RrcMIXw=

     >curl.exe localhost:8265/encrypt -d foobar-stg2rsa
     AQBF1BU5+/8EvHkJdoXFvYmYt8K5QvuyTbBl7rwg0G49QSV4IPDDarPFr/10zzcepV8UHpbVHQ9vMJAV
     6WCefmzMh0YWAPwRsLOgJIgfpbkPacRoVSvwqYEhHshNNQHNOjWT84BDBXiKXcnPeOhnMNUOiB7M05VB
     ZRVwdUuHBN/Zb/L9vxnQLTlwALS1TNfd3JUL7S61oz4JBf/c5FoQUPx/JawUz/uEwi337GCEkFmKacC8
     fF+cbjLOzsdtHkxrHZtz8QesDCwanwpZl8KbLTzeiU03uAj60qYBaoCYm+A19z+07SXHL0KKhoWp5TcA
     BDv5HY5Bv1astZVp7r+YFAwh/xYnHBYeUwBvmbjTJMYCJEuFNuWr35RhJWJSrAuI1eE=

     将这两个值进行保存到配置文件，也就是我们的Git仓库中的配置文件；

 6、在浏览器输入地址 http://localhost:8265/foobar-default.yml 正常情况下会输出配置文件的内容（内容为：profile: profile-default）；
 7、在浏览器输入地址 http://localhost:8265/foobar-dev.yml 正常情况下会输出配置文件的内容（内容为：profile: profile-dev）；
 8、在浏览器输入地址 http://localhost:8265/foobar-stg1rsa.yml 正常情况下会输出配置文件的内容（内容为：profile: foobar-stg1rsa）；
 9、在浏览器输入地址 http://localhost:8265/foobar-stg2rsa.yml 正常情况下会输出配置文件的内容（内容为：profile: foobar-stg2rsa）；
 10、在浏览器输入地址 http://localhost:8265/foobar-stg2rsa.properties 正常情况下会输出配置文件的内容（内容为：profile: foobar-stg2rsa）；

 总结一：一切都正常打印，说明 SpringCloud 的解密已经能正确的完成了，但是注意加密内容保存到 properties 文件的时候，要将回车换行符去掉保存，不然将获取不到正确值；

 11、修改 application.yml 文件，将 encrypt.keyStore 属性值随便改下，改成比如 encrypt.secret: aaaaaaaaaaa
 12、停止并重启 springms-config-server-encrypt-rsa 模块服务，启动1个端口；

 13、在浏览器输入地址 http://localhost:8265/foobar-default.yml 正常情况下会输出配置文件的内容（内容为：profile: profile-default）；
 14、在浏览器输入地址 http://localhost:8265/foobar-dev.yml 正常情况下会输出配置文件的内容（内容为：profile: profile-dev）；
 15、在浏览器输入地址 http://localhost:8265/foobar-stg1rsa.yml 不能正常获取配置文件内容（内容为：invalid: profile: <n/a> profile: profile-default）；
 16、在浏览器输入地址 http://localhost:8265/foobar-stg2rsa.yml 不能正常获取配置文件内容（内容为：invalid: profile: <n/a> profile: profile-default）；
 17、在浏览器输入地址 http://localhost:8265/foobar-stg2rsa.properties 不能正常获取配置文件内容（内容为：invalid: profile: <n/a> profile: profile-default）；

 总结二：由此可见 encrypt.keyStore 经过赋值生成配置文件内容后，就不能轻易改变，一旦改变的话，那么原本正常的内容值将获取不到了；
 ****************************************************************************************/






