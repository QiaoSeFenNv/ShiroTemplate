# 1、swagger 3.0.0 不支持springboot 2.6.x 以上的版本  具体看controller包下的configuration下的swagger代码
    解决：添加配置bean类

    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
            return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }
    
            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }
    
            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }


# 2、分模块循环依赖问题
    解决：梳理清楚依赖循环，一边controller包含service、dao、entity；service包含entity、dao；dao包含entity；entity包含utils；utls不包含任何依赖


# 3、突然疯狂扫描索引 停止等待
    3.1、完成控制台出现swagger地址和后端启动地址  添加完成！
    3.2、整合日志功能      日志就用molbok提供sl4g
    3.3、整合异常功能和vild工具类（已导入）
    