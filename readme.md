# ruiji-Takeaway

黑马视频——瑞吉外卖

## day01

- 创建了springboot基本框架。

- 构建统一的返回值，位于common/R.java;

- 完成了login功能，使用过滤器优化login。
  
  - 过滤器：防止用户还未登陆就进入了index页面。具体流程如下：
    
    - 获取请求url。
    - 判断请求url中是否包含login，如果包含，说明是登录操作，放行。
    - 获取请求头中的令牌（token）。
    - 判断令牌是否存在，如果不存在，返回错误结果（未登录）。
    - 解析token，如果解析失败，返回错误结果（未登录）
    - 放行

## day02

- 完成了用户添加和查询请求；

- 由于用户添加要保证用户名不重复，因此引入全局异常,common/GlobalExceptionHandler.java;
  
  - ```java
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
        public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
            log.error(ex.getMessage());
            if(ex.getMessage().contains("Duplicate entry")){
                String[] split = ex.getMessage().split(" ");
                String msg = split[2] + "已存在";
                return R.error(msg);
            }
            return R.error("未知错误");
        }    
    ```

- 由于Json格式转换为java的对象出现格式错误问题，如Json中的Long精度小于java的Long精度，因此定义Json对象转换mapper；
  
  - ```java
        public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
        public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    
        public JacksonObjectMapper() {
            super();
            //收到未知属性时不报异常
            this.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    
            //反序列化时，属性不存在的兼容处理
            this.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    ```
    
            SimpleModule simpleModule = new SimpleModule()
                    .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                    .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                    .addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)))
        
                    .addSerializer(BigInteger.class, ToStringSerializer.instance)
                    .addSerializer(Long.class, ToStringSerializer.instance)
                    .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                    .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                    .addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        
            //注册功能模块 例如，可以添加自定义序列化器和反序列化器
            this.registerModule(simpleModule);
        }
    
    

- 完成了mybatis-plus的分页查询；
  
  - ```java
    @Bean
        public MybatisPlusInterceptor mybatisPlusInterceptor(){
            MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
            mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
            return mybatisPlusInterceptor;
        }
    ```

## day03

- 使用Mybatis-Plus 的TableField注解，完成公共字段自动填充，如：操作日期，操作用户等信息。
