package cn.homyit.Dto;

import lombok.Data;

import java.util.List;
@Data
public class ProductSpecDto {
        private Long id;
        private Long productId;
        private String specName; // 规格名，例如 "内存"
        private List<String> specValues; // ["8GB", "16GB"]
}
