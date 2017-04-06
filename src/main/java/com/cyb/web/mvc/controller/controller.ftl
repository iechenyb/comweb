package ${packageName};

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cyb.web.base.controller.BaseController;
import ${servicePackageName}
/**
 *  @author ${author}
    @date ${date}
 */
@Controller
@RequestMapping("${basePath}")
public class ${modelName}Controller extends BaseController {
   @Resource(name="${varModelName}Service")
   ${modelName}Service ${varModelName}Service;
}