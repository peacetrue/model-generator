package com.github.peacetrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
* @author xiayx
*/
@Controller
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestService testService;

    /** 跳转列表页 */
    @GetMapping("/list")
    public String list(Model model) {
        logger.info("跳转列表页");
        logger.debug("model: {}", model);
        return "test/list";
    }

    /** 跳转详情页 */
    @GetMapping({"/detail", "/view", "/add", "/modify"})
    public String detail(Model model) {
        logger.info("跳转详情页");
        logger.debug("model: {}", model);
        return "test/detail";
    }

    /** 新增信息 */
    @ResponseBody
    @PostMapping
    public String add(TestAddDTO dto) {
        logger.info("新增信息");
        logger.debug("dto: {}", dto);
        dto.setOperatorId(SecurityContextHolder.getContext().getAuthentication().getName());
        return testService.add(dto).getId();
    }

    /** 分页查询信息 */
    @GetMapping
    @ResponseBody
    public Page<TestVO> query(TestQueryDTO dto, Pageable pageable) {
        logger.info("分页查询信息");
        logger.debug("dto: {}, pageable: {}", dto, pageable);
        return testService.query(dto, pageable);
    }

    /** 根据主键获取信息 */
    @ResponseBody
    @GetMapping(params = "id")
    public TestVO get(TestGetDTO dto) {
        logger.info("获取信息");
        logger.debug("dto: {}", dto);
        return testService.get(dto);
    }

    /** 修改信息 */
    @ResponseBody
    @PutMapping
    public void modify(TestModifyDTO dto) {
        logger.info("修改信息");
        logger.debug("dto: {}", dto);
        testService.modify(dto);
    }

    /** 删除信息 */
    @ResponseBody
    @DeleteMapping
    public void delete(TestDeleteDTO dto) {
        logger.info("删除信息");
        logger.debug("dto: {}", dto);
        testService.delete(dto);
    }

}
