mybatis入口为SqlSessionFactoryBuilder，通过Resources工具类导入mybatis-config.xml资源文件
SqlSessionFactory(DefaultSqlSession)的构建依赖于Configuration核心类
Configuration的构建过程在XMLConfigBuilder完成，xml的解析过程由其父类BaseBuilder完成
依次解析xml文件中的
properties：
	从property元素、resource/url、env然后设置到Configuration对应variables中
settings：

typeAliases：
	可通过包(@Alias)、类定义，由TypeAliasRegistry解析到TYPE_ALIASES变量中，别名的使用应该不止在sql阶段做类型映射
	别名的使用在其它地方都有使用到
plugins：
	通过实现Interceptor接口，结合@Intercepts定义插件使用时期，设计方式可以学习，动态代理的合理使用
	Interceptor Plugin、MapperProxy MapperProxyFactory设计非常巧妙
objectFactory：
	通过class创建类的工厂类
objectWrapperFactory:
	类的包装器
reflectorFactory:
	Reflector，将类的字段、getter/setter等信息缓存，
environments:
	主要对数据库、事务进行初始化
databaseIdProvider:
	针对不同数据库环境来匹配对应的sql，通过数据库名称，定义对应的值，在mapper.xml中属性databaseId
typeHandlers:
	定义javaType,jdbcType匹配关系，在设置参数与解释结果集时使用
mappers:
	通过package(xml接口同名)、resource(xml)、class(接口)、url(file:xml)
	XMLMapperBuilder.parse (分别对mapper.xml中对应的select|update|insert|delete...等元素的解析)
	具体的解析过程在XMLStatementBuilder中，	通过MapperBuilderAssistant完成，贯穿整个解析过程。
	在configuration中添加IncompleteResultMap、IncompleteStatement(通过对应的Resolver解析)
    在configuration中添加mapper接口（MapperRegistry通过为每个mapper接口实现代理工厂，每次调用mapper方法其实是通过代理工厂生成对应的代理类）

其实以上所有工作都是为了构造Configuration对象


