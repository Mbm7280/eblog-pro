package com.echo.config.api;


public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),

    FAILED(500, "操作失败"),

    VALIDATE_FAILED(404, "参数检验失败"),

    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    FORBIDDEN(403, "没有相关权限"),

    PLEASE_APPORTION_PERMISSIONS(405, "请先分配权限"),

    RETURN_VALUE_IS_NULL(990, "返回值为空，请稍后重试"),


    THE_USERNAME_HAS_REGISTERED(1001, "该用户名已注册！"),


    THE_RESOURCE_CATEGORY_QUERY_FAILED(1002, "获取所有资源分类失败，请稍后重试！"),

    THE_RESOURCE_CATEGORY_ADD_FAILED(1003, "添加资源分类失败，请稍后重试！"),


    THE_RESOURCE_ADD_FAILED(1004, "添加资源失败，请稍后重试！"),

    THE_RESOURCE_DEL_FAILED(1005, "删除资源失败，请稍后重试！"),

    THE_RESOURCE_QUERY_FAILED(1006, "查询资源失败，请稍后重试！"),


    THE_MENU_QUERY_FAILED(1007, "查询菜单失败，请稍后重试！"),

    THE_MENU_DELETE_FAILED(1008, "删除菜单失败，请稍后重试！"),

    THE_ROLE_QUERY_FAILED(1009, "查询角色失败，请稍后重试！"),

    THE_ROLE_UPDATE_FAILED(1010, "更新角色失败，请稍后重试！"),


    THE_AUTHORIZED_FAILED(1011, "验证失败，请先登录！"),

    THE_USER_IS_NOT_EXIST(1012, "用户不存在，请重新输入！"),

    THE_OLD_PASSWORD_IS_WRONG(1013, "旧密码错误，请检查后输入！"),

    THE_PASSWORD_UPDATE_FAILED(1014, "旧密码修改失败，请稍后重试！"),

    THE_ROLE_DELETE_FAILED(1015, "删除角色失败，请稍后重试！"),


    THE_MENU_HIDDEN_FAILED(1016, "隐藏菜单失败，请稍后重试！"),


    THE_RESOURCE_UPDATE_FAILED(1017, "修改资源失败，请稍后重试！"),

    THE_USER_QUERY_FAILED(1018, "查询用户失败，请稍后重试！"),

    THE_USER_DELETE_FAILED(1019, "删除用户失败，请稍后重试！"),

    THE_USER_UPDATE_FAILED(1020, "更新用户信息失败，请稍后重试！"),

    THE_USER_NEED_ALLOW_RESOURCES(1021, "请为该用户分配权限后重试！"),

    THE_ARTICLE_IS_NOT_EXIST(1040, "文章不存在，请稍后再试！"),

    THE_CATEGORY_IS_NOT_EXIST(1041, "分类不存在，请稍后再试！"),

    THE_FRIENDLINK_IS_NOT_EXIST(1042, "友链不存在，请稍后再试！"),


    THE_COMMENT_QUERY_FAILED(1043, "查询评论失败，请稍后重试！"),





    ;

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
