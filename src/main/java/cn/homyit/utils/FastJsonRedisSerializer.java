//package cn.homyit.utils;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.parser.ParserConfig;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//
/// **
// * Redis使用FastJson序列化
// */
//public class FastJsonRedisSerializer<T> implements RedisSerializer<T>
//{
//
//    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
//
//    private final Class<T> clazz;
//
//    static
//    {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//    }
//
//    public FastJsonRedisSerializer(Class<T> clazz)
//    {
//        super();
//        this.clazz = clazz;
//    }
//
//    @Override
//    public byte[] serialize(T t) throws SerializationException
//    {
//        if (t == null)
//        {
//            return new byte[0];
//        }
//        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
//    }
//
//    @Override
//    public T deserialize(byte[] bytes) throws SerializationException
//    {
//        if (bytes == null || bytes.length <= 0)
//        {
//            return null;
//        }
//        String str = new String(bytes, DEFAULT_CHARSET);
//
//        return JSON.parseObject(str, clazz);
//    }
//
//
//    protected JavaType getJavaType(Class<?> clazz)
//    {
//        return TypeFactory.defaultInstance().constructType(clazz);
//    }
//}
//
//
package cn.homyit.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Redis使用FastJson序列化
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        // 使用 JSON.toJSONBytes 替代 JSON.toJSONString
        return JSON.toJSONBytes(t, JSONWriter.Feature.WriteClassName);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        // 使用 JSON.parseObject 直接解析字节数组
        return JSON.parseObject(bytes, clazz, JSONReader.Feature.SupportAutoType);
    }

    protected JavaType getJavaType(Class<?> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}