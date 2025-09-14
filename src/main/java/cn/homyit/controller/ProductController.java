package cn.homyit.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.homyit.Dto.ProductSpecDto;
import cn.homyit.entity.Product;
import cn.homyit.entity.ProductSku;
import cn.homyit.entity.ProductSpec;
import cn.homyit.entity.Result;
import cn.homyit.service.ProductService;
import cn.homyit.service.ProductSpecService;
import cn.homyit.service.ProductSpecSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@SaIgnore
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询全部商品
     *
     * @return
     */
    @GetMapping("/list")
    public List<Product> listAll() {
        return productService.listAll();
    }

    /**
     * 获取某个商品
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public Result getProductById(Long id) {
        return productService.getProductById(id);
    }

    /**
     * 获取活动商品
     *
     * @return
     */
    @GetMapping("/active")
    public List<Product> getActiveProducts() {
        return productService.getActiveProducts();
    }

    /**
     * 价格升序排列
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}/price/asc")
    public List<Product> getProductsByCategoryPriceAsc(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryAndSort(categoryId, "price", "ASC");
    }


    /**
     * 价格降序排列
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}/price/desc")
    public List<Product> getProductsByCategoryPriceDesc(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryAndSort(categoryId, "price", "DESC");
    }

    /**
     * 销量升序排列
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}/sales/asc")
    public List<Product> getProductsByCategorySalesAsc(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryAndSort(categoryId, "sales", "ASC");
    }

    /**
     * 销量降序排列
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}/sales/desc")
    public List<Product> getProductsByCategorySalesDesc(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryAndSort(categoryId, "sales", "DESC");
    }


    /**
     * 根据分类ID获取商品列表
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/by-category")
    public List<Product> getProductsByCategory(@RequestParam Long categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }
    @Autowired
    private ProductSpecService productSpecService;

    /**
     * 添加规格
     * @param dto
     * @return
     */
    @PostMapping("/spec/add")
    public Result addSpec(@RequestBody ProductSpecDto dto) {
        return productSpecService.addSpec(dto);

    }

    /**
     * 查询规格
     * @param productId
     * @return
     */
    @GetMapping("/spec/list/{productId}")
    public List<ProductSpecDto> listSpecs(@PathVariable Long productId) {
        List<ProductSpecDto> specs = productSpecService.listSpecs(productId);
        return specs != null ? specs : new ArrayList<>();
    }
    @Autowired
    private ProductSpecSkuService productSkuService;

    /**
     * 自动生成SKU
     * @param productId
     * @return
     */
    @PostMapping("/sku/generate/{productId}")
    public Result<String> generateSku(@PathVariable Long productId) {
        productSkuService.generateSku(productId);
        return Result.success("SKU生成成功");
    }


    /**
     * 查询SKU
     * @param productId
     * @return
     */
    @GetMapping("/sku/list/{productId}")
    public List<ProductSku> listSku(@PathVariable Long productId) {
        return productSkuService.listSku(productId);
    }


}
