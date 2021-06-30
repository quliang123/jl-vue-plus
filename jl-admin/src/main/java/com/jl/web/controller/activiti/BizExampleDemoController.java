package com.jl.web.controller.activiti;//package com.jl.web.controller.activiti;
//
//import com.alibaba.fastjson.JSON;
//import com.jl.activiti.domain.BizExampleDemo;
//import com.jl.activiti.domain.bo.BizExampleDemoAddBo;
//import com.jl.activiti.domain.bo.BizExampleDemoEditBo;
//import com.jl.activiti.domain.bo.BizExampleDemoQueryBo;
//import com.jl.activiti.domain.vo.BizExampleDemoVo;
//import com.jl.activiti.service.IBizExampleDemoService;
//import com.jl.activiti.service.IProcessService;
//import com.jl.common.annotation.Log;
//import com.jl.common.core.controller.BaseController;
//import com.jl.common.core.domain.AjaxResult;
//import com.jl.common.core.page.TableDataInfo;
//import com.jl.common.enums.BusinessType;
//import com.jl.common.utils.poi.ExcelUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 示例DemoController
// *
// * @author jl
// * @date 2021-05-18
// */
//@Api(value = "示例Demo控制器", tags = {"示例Demo管理"})
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
//@RestController
//@RequestMapping("/example/demo")
//public class BizExampleDemoController extends BaseController {
//
//    private final IBizExampleDemoService iBizExampleDemoService;
//
//    private final IProcessService iProcessService;
//
//    /**
//     * 查询示例Demo列表
//     */
//    @ApiOperation("查询示例Demo列表")
//    @PreAuthorize("@ss.hasPermi('system:demo:list')")
//    @GetMapping("/list")
//    public TableDataInfo<BizExampleDemoVo> list(@Validated BizExampleDemoQueryBo bo) {
//        return iBizExampleDemoService.queryPageList(bo);
//    }
//
//    /**
//     * 导出示例Demo列表
//     */
//    @ApiOperation("导出示例Demo列表")
//    @PreAuthorize("@ss.hasPermi('system:demo:export')")
//    @Log(title = "示例Demo", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult<BizExampleDemoVo> export(@Validated BizExampleDemoQueryBo bo) {
//        List<BizExampleDemoVo> list = iBizExampleDemoService.queryList(bo);
//        ExcelUtil<BizExampleDemoVo> util = new ExcelUtil<BizExampleDemoVo>(BizExampleDemoVo.class);
//        return util.exportExcel(list, "示例Demo");
//    }
//
//    /**
//     * 获取示例Demo详细信息
//     */
//    @ApiOperation("获取示例Demo详细信息")
//    @PreAuthorize("@ss.hasPermi('system:demo:query')")
//    @GetMapping("/{id}")
//    public AjaxResult<BizExampleDemoVo> getInfo(@NotNull(message = "主键不能为空")
//                                                  @PathVariable("id") Long id) {
//        return AjaxResult.success(iBizExampleDemoService.queryById(id));
//    }
//
//    /**
//     * 新增示例Demo
//     */
//    @ApiOperation("新增示例Demo")
//    @PreAuthorize("@ss.hasPermi('system:demo:add')")
//    @Log(title = "示例Demo", businessType = BusinessType.INSERT)
//    @PostMapping()
//    public AjaxResult<Void> add(@Validated @RequestBody BizExampleDemoAddBo bo) {
//        return toAjax(iBizExampleDemoService.insertByAddBo(bo) ? 1 : 0);
//    }
//
//    /**
//     * 修改示例Demo
//     */
//    @ApiOperation("修改示例Demo")
//    @PreAuthorize("@ss.hasPermi('system:demo:edit')")
//    @Log(title = "示例Demo", businessType = BusinessType.UPDATE)
//    @PutMapping()
//    public AjaxResult<Void> edit(@Validated @RequestBody BizExampleDemoEditBo bo) {
//        return toAjax(iBizExampleDemoService.updateByEditBo(bo) ? 1 : 0);
//    }
//
//    /**
//     * 删除示例Demo
//     */
//    @ApiOperation("删除示例Demo")
//    @PreAuthorize("@ss.hasPermi('system:demo:remove')")
//    @Log(title = "示例Demo" , businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
//                                       @PathVariable Long[] ids) {
//        return toAjax(iBizExampleDemoService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
//    }
//
//	/**
//	 * 提交申请
//	 */
//	@Log(title = "示例Demo", businessType = BusinessType.UPDATE)
//	@PostMapping( "/submitApply/{id}")
//	@ResponseBody
//	public AjaxResult submitApply(@PathVariable Long id, String variablesStr) {
//		try {
//			System.out.println("variables: " + variablesStr);
//			Map<String, Object> vars = (Map<String, Object>) JSON.parse(variablesStr);
//			String[] users = vars.get("users").toString().split(",");
//			BizExampleDemo bizExampleDemo = iBizExampleDemoService.getById(id);
//			Map<String, Object> variables = new HashMap<>();
//			if (users.length > 0) {
//				Object value =  Arrays.asList(users);
//				variables.put("users", value);
//			}
//			Boolean gytd = "1".equals(vars.get("gytd").toString());
//			variables.put("gytd", gytd);
//			iProcessService.submitApply(bizExampleDemo, "exampleDemo", variables);
//			iBizExampleDemoService.updateById(bizExampleDemo);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return error("提交申请出错：" + e.getMessage());
//		}
//		return success();
//	}
//}
