package com.liuguilin.meet.im;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * FileName: IMSDK
 * Founder: 刘桂林
 * Create Date: 2019/3/25 22:56
 * Profile: IM所有操作
 */
public class IMSDK {

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static IMUser getCurrentUser() {
        IMUser user = BmobUser.getCurrentUser(IMUser.class);
        return user;
    }

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        IMUser user = getCurrentUser();
        if (user != null) {
            return true;
        }
        return false;
    }

    /**
     * 请求短信验证码
     * @param phone
     * @param listener
     */
    public static void requestSMSCode(String phone, QueryListener<Integer> listener) {
        BmobSMS.requestSMSCode(phone, "DataSDK", listener);
    }

    /**
     * 验证短信验证码
     * @param phone
     * @param code
     * @param listener
     */
    public static void verifySmsCode(String phone,String code, UpdateListener listener){
        BmobSMS.verifySmsCode(phone, code, listener);
    }

    /**
     * 手机号码重置密码
     * @param code
     * @param newPassword
     * @param listener
     */
    public static void resetPasswordBySMSCode(String code, String newPassword,UpdateListener listener){
        BmobUser.resetPasswordBySMSCode(code,newPassword,listener);
    }

    /**
     * 注册
     * @param userName
     * @param password
     * @param listener
     */
    public static void signUp(String userName, String password, SaveListener<Object> listener){
        IMUser user = new IMUser();
        user.setUsername(userName);
        user.setPassword(password);
        user.signUp(listener);
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param listener
     */
    public static void login(String userName, String password, SaveListener<IMUser> listener){
        IMUser user = new IMUser();
        user.setUsername(userName);
        user.setPassword(password);
        user.login(listener);
    }

    /**
     * 更新用户
     * @param imUser
     * @param listener
     */
    public static void updateUser(IMUser imUser,UpdateListener listener){
        imUser.update(listener);
    }

    /**
     * 退出登录
     */
    public static void exitUser(){
        BmobUser.logOut();
    }

    /**
     * 修改密码
     * @param oldPw
     * @param newPw
     * @param listener
     */
    public static void updatePassword(String oldPw,String newPw,UpdateListener listener){
        BmobUser.updateCurrentUserPassword(oldPw,newPw,listener);
    }
}
