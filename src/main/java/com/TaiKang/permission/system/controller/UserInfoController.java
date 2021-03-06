package com.TaiKang.permission.system.controller;

import com.TaiKang.permission.system.bean.UserInfo;
import com.TaiKang.permission.system.service.UserInfoService;
import com.TaiKang.permission.utils.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
@Api(value = "用户CRUD操作", description = "用户信息的的增删查改等")
public class UserInfoController {
    public final static Logger _log = LoggerFactory.getLogger(UserInfoController.class);


    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户查询.
     *
     * @return
     */
    @RequestMapping(value = "/userList",method = RequestMethod.POST)
//    @RequiresPermissions("userInfo:view")//权限管理;
    @ApiOperation(value = "查询全部用户信息", notes = "")
    public ResponseMessage userInfo() {
        List<UserInfo>userList=userInfoService.findAll();
        return ResponseMessage.ok(userList);
    }

    /**
     * 用户添加;
     *
     * @return
     */
    @RequestMapping(value = "/doUserAdd",method = RequestMethod.POST)
//    @RequiresPermissions("userInfo:add")//权限管理;
    @ApiOperation(value = "添加用户信息", notes = "")
    public ResponseMessage userInfoAdd(@RequestBody UserInfo userInfo) {
        _log.info("添加用户入参:{}",userInfo);
        if (userInfo == null) {
            return null;
        }
        boolean b = userInfoService.addUserInfo(userInfo);
        if (b){
            return ResponseMessage.ok("添加成功");
        }
        return ResponseMessage.error("网络故障,请稍后重试或联系技术人员");
    }

    /**
     * 用户删除;
     *
     * @return
     */
    @RequestMapping(value = "/userDel",method = RequestMethod.POST)
//    @RequiresPermissions("userInfo:del")//权限管理;
    @ApiOperation(value = "删除用户信息", notes = "")
    public ResponseMessage userDel(@RequestBody UserInfo userInfo) {
        if (userInfo==null){
            return null;
        }
        boolean b = userInfoService.removeUserInfo(userInfo.getUserId());
        if (b){
            return ResponseMessage.ok("删除成功");
        }
        return ResponseMessage.error("网络故障,请稍后重试或联系技术人员");
    }

    /**
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/userUpdate",method = RequestMethod.POST)
    public ResponseMessage userUpdate(@RequestBody UserInfo userInfo){
        if (userInfo==null){
            return null;
        }
        boolean b = userInfoService.editorUserInfo(userInfo);
        if (b){
            return ResponseMessage.ok("更新成功");
        }
        return ResponseMessage.error("网络故障,请稍后重试或联系技术人员");
    }

    @RequestMapping(value = "/getOne" ,method = RequestMethod.POST)
    public ResponseMessage getOne(@RequestBody UserInfo userInfo){
        if (null==userInfo){
            return null;
        }

        UserInfo user = userInfoService.findUserbyId(userInfo.getUserId());
       return ResponseMessage.ok(user);

    }
}