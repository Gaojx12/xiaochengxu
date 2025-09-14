package cn.homyit.handler;

import cn.homyit.entity.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    /**
     * 运行时异常处理
     *
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        return Result.fail(e.getMessage());
    }

    /**
     * 业务异常处理
     *
     * @param e
     * @return
     */
//    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
//    public Result handleBusinessException(BusinessException e) {
//        return Result.failure(e.getMessage(), null);
//    }

    /**
     * 参数校验异常处理
     *
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(errorMessage);
    }

    /**
     * 参数校验异常处理
     *
     * @param e
     * @return
     */
//    @org.springframework.web.bind.annotation.ExceptionHandler(HandlerMethodValidationException.class)
//    public Result handleValidationException(HandlerMethodValidationException e) {
//        // 遍历所有的BindException，获取验证错误信息
////        for (ParameterValidationResult result : e.getAllValidationResults()) {
////            // 获取FieldError列表
////            List<FieldError> fieldErrors = result.getMethodParameter().get..getFieldErrors();
////            for (FieldError fieldError : fieldErrors) {
////                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
////            }
////        }
//
////        String errorMessage = e.getMessage(); //.getFieldError().getDefaultMessage();
////        e.getAllValidationResults().get(0).getMethodParameter().
//        return Result.failure("数据验证失败！");
////        return new Result<>(400, errorMessage, null);
//    }


}
