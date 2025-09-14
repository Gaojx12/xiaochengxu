package cn.homyit.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//分页插件
@Configuration
@MapperScan("cn.homyit.mapper")
public class MpConfig {

    @Bean
    public MybatisPlusInterceptor pageInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();   //定义MP拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());  //添加具体的拦截器
        return interceptor;
    }
}

