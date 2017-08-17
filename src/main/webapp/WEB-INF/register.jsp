<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css"/>
<script src="/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="/css/style.css" type="text/css"/>


<script>
    $.validator.addMethod("checkUsername", function(value, element,params){
        //定义一个标志
        var flag = false;
        //value:输入的内容
        //element:被校验的元素对象
        //params：规则对应的参数值
        //目的：对输入的username进行ajax校验
        $.ajax({
            "async":false,
            "url":"/user/checkname",
            "type":"POST",
            "data":{"username":value},
            "dataType":"json",
            "success":function(data){
                flag = data.flag;
                System.out.print("flag"+flag);
            }
        });
        return flag;
    });

    $.validator.addMethod("checkCode", function(value, element,params){
        //定义一个标志
        var flag = false;
        //value:输入的内容
        //element:被校验的元素对象
        //params：规则对应的参数值
        var inputCode  = $('#imageCode').val();
        $.ajax({
            "async":false,
            "url":"/user/getStrCode",
            "type":"POST",
            "dataType":"json",
            "success":function(data){
                flag = data.flag;
                if (flag){
                    if (inputCode == data.strCode){
                        flag = true;
                    }else {
                        flag = false;
                    }
                }
            }
        });
        return flag;
    });


    $().ready(function () {
        $("#registerForm").validate({
            rules: {
                username: {
                    required:true,
                    checkUsername:true
                },
                imageCode: {
                    required: true,
                    checkCode: true
                },
                password:{
                    required:true,
                    rangelength:[6,12]
                },
                confirmpwd:{
                    required:true,
                    rangelength:[6,12],
                    equalTo:"#password"
                },
                email:{
                    required:true,
                    email:true
                },
                sex:{
                    required:true
                }
            },
            messages: {
                username: {
                    required: "用户名不能为空",
                    checkUsername:"用户名已存在"
                },
                imageCode: {
                    required: "验证码不能为空",
                    checkCode:"验证码不正确"
                },
                password:{
                    required:"密码不能为空",
                    rangelength:"密码长度6-12位"
                },
                confirmpwd:{
                    required:"密码不能为空",
                    rangelength:"密码长度6-12位",
                    equalTo:"两次密码不一致"
                },
                email:{
                    required:"邮箱不能为空",
                    email:"邮箱格式不正确"
                }
            },
            errorClass: "error",
            focusCleanup:true,
            debug:false
        });
    });

</script>

<style>
    body {
        margin-top: 20px;
        margin: 0 auto;
    }

    .carousel-inner .item img {
        width: 100%;
        height: 300px;
    }

    font {
        color: #3164af;
        font-size: 18px;
        font-weight: normal;
        padding: 0 10px;
    }

    .error {
        color: red;
    }
</style>

</head>
<body>
<!-- 引入header.jsp -->
<jsp:include page="header.jsp"></jsp:include>

<div class="container"
     style="width: 100%; background: url('/image/regist_bg.jpg');">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8"
             style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
            <font>会员注册</font>USER REGISTER
            <form id="registerForm" class="form-horizontal" action="/user/register" METHOD="post"
                  style="margin-top: 5px;">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="username" name="username"
                               placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="confirmpwd" name="confirmpwd"
                               placeholder="请输入确认密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-6">
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="请输入姓名">
                    </div>
                </div>
                <div class="form-group opt">
                    <label for="sex1" class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-6">
                        <label class="radio-inline"><input type="radio" name="sex" id="sex1" value="male">男</label>
                        <label class="radio-inline"><input type="radio" name="sex" id="sex2" value="female">女</label>
                        <label class="error" for="sex" style="display:none ">您没有第三种选择</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="date" class="col-sm-2 control-label">出生日期</label>
                    <div class="col-sm-6">
                        <input type="date" class="form-control" id="date" name="birthday" placeholder="格式“1980/12/31”">
                    </div>
                </div>

                <div class="form-group">
                    <label for="imageCode" class="col-sm-2 control-label">验证码</label>
                    <div class="col-sm-3">
                        <input type="text" id="imageCode" class="form-control" name="imageCode">

                    </div>
                    <div class="col-sm-2">
                        <img type="image" src="/user/getImage" id="codeImage" onclick="changeImageCode()"
                             style="cursor:pointer;"/>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" width="100" value="注册" name="submit"
                               style="background: url('/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>
<!-- 引入footer.jsp -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
    function changeImageCode() {
        $('#codeImage').attr('src', '/user/getImage?abc=' + Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题
    }
</script>
</html>




