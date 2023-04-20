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

## day04

- 完成了文件上传和下载功能。
  
  - 文件路径：在配置文件中定义保存路径，用@value来注入到basePath变量中，后续动态使用。
  
  - 文件上传：首先获取文件名后缀，然后使用uuid重新生成文件名，防止文件名重复。
    
    ```java
    //原始文件名
            String originalFilename = file.getOriginalFilename();//abc.jpg
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    
            //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;//dfsdfdfd.jpg
    ```
  
  - 文件下载：

- 完成了新增菜品功能：
  
  - 完成菜品信息分页展示功能：
    
    - 使用Dto类对现有的类进行扩展：如菜品dish类，扩展了所属菜品类型的名称以及其可选择的口味list。
    
    - ```java
      public class DishDto extends Dish {
      
          private List<DishFlavor> flavors = new ArrayList<>();
      
          private String categoryName;
      
          private Integer copies;
      }public class DishDto extends Dish {
      ```
  
  - 完成根据id查询菜品信息和对应的口味信息：
    
    - 利用BeanUtils.copyProperties对对象进行拷贝。
    
    - 学会使用lambda表达式。
