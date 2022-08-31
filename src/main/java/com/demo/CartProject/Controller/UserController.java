package com.demo.CartProject.Controller;


import com.demo.CartProject.Common.BaseResponse;
import com.demo.CartProject.Common.CommonCode;
import com.demo.CartProject.Common.CommonMessage;
import com.demo.CartProject.Entity.User;
import com.demo.CartProject.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/add")
    public BaseResponse<User> addUser(@RequestBody User param) {
        try {
            if (Objects.nonNull(param)) {
                userService.addUser(param);
                return new BaseResponse<>(CommonMessage.SAVED, CommonCode.SUCCESS, param);
            } else {
                return null;
            }
        } catch (Exception e) {
            return new BaseResponse<>(CommonMessage.NOT_SAVED, CommonCode.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/getAll")
    public BaseResponse<User> getAll() {
        List<User> userList = userService.getUsers();
        return new BaseResponse(CommonMessage.FOUND, CommonCode.SUCCESS, userList);
    }

    @GetMapping(value = "/getById/{id}")
    public BaseResponse<User> getById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return new BaseResponse<>(CommonMessage.FOUND, CommonCode.SUCCESS, user);
    }


    @PutMapping(value = "update/{id}")
    public BaseResponse<User> updateUser(@PathVariable("id") Long id, @RequestBody User param) {
        try {
            userService.updateUser(id, param);
            return new BaseResponse<>(CommonMessage.UPDATED, CommonCode.SUCCESS);
        } catch (RuntimeException e) {
            return new BaseResponse(CommonMessage.NOT_UPDATED,CommonCode.NOT_FOUND);
        } catch (Exception e){
            return new BaseResponse(CommonMessage.NOT_UPDATED,CommonCode.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "delete/{id}")
    public BaseResponse<User> deleteUser(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        try{
            userService.deleteUser(id);
            return new BaseResponse<>(CommonMessage.DELETED,CommonCode.SUCCESS);
        } catch (RuntimeException e){
            return new BaseResponse<>(CommonMessage.NOT_DELETED,CommonCode.BAD_REQUEST);
        } catch (Exception e){
            return new BaseResponse(CommonMessage.NOT_DELETED,CommonCode.NOT_FOUND);
        }

    }
}
