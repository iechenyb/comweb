package com.cyb.web.swagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.web.swagger.bean.Person;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Controller
@EnableSwagger
@RequestMapping("/user-test")
@Api(value = "ApiController", description = "用户相关api")
public class ApiController {
 
	@RequestMapping(value="/get",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据id获取用户对象", httpMethod = "GET", 
	//produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
	//response = String.class, 
	notes = "随便说点啥")
	/*@ApiImplicitParams({
       // @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Integer", paramType = "path"),
	})*/
	@ApiResponses(value = {  
	          @ApiResponse(code = 400, message = "No Name Provided")  
	 })
	public String get(
			@RequestParam(value = "id", required = true) @ApiParam(value = "唯一id") Integer id  ){
        System.out.println("get"+id);
        return ("hello:"+id);
    }
	@ResponseBody 
    @RequestMapping(value="/add/{id}",method=RequestMethod.POST)
    public String post(@PathVariable("id") Integer id){
        System.out.println("post"+id);
        return "/hello";
    }
	@ResponseBody 
    @RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
    public String put(@PathVariable("id") Integer id){
        System.out.println("put"+id);
        return "/hello";
    }
	@ResponseBody 
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        System.out.println("delete"+id);
        return "/hello";
    }
    @ResponseBody
    @ApiOperation(value="json参数传递", notes="json参数传递note")  
    //@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")  
    @RequestMapping(value="/addUser", method=RequestMethod.POST)  
    public String postUser(@RequestBody @ApiParam(value = "唯一id") Person user) {  
        return user.getName();  
    } 
}
