package cn.homyit.controller;

import cn.homyit.entity.Result;
import cn.homyit.utils.TencentCOSUploadFileUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result ResultuploadPhoto(@RequestParam("file") MultipartFile file) {
        String fileUrl = TencentCOSUploadFileUtil.uploadFile(file);
        if (fileUrl != null) {
            return Result.success("获取成功", fileUrl);
        } else {
            return Result.fail("获取失败");
        }
    }
}
