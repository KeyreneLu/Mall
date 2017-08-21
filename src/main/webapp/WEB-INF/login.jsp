<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css"/>
    <script src="/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/js/layer/layer.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="/css/style.css" type="text/css"/>

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }

        .container .row div {
            /* position:relative;
                         float:left; */

        }

        font {
            color: #666;
            font-size: 22px;
            font-weight: normal;
            padding-right: 17px;
        }

        .error{
            color: red;
        }
    </style>
    <script>
        $.validator.addMethod("checkname", function(value, element,params){
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
                    flag = !data.flag;
                    System.out.print("flag"+flag);
                }
            });
            return flag;
        });

        $.validator.addMethod("checkCode", function (value, element, params) {
            //定义一个标志
            var flag = false;
            //value:输入的内容
            //element:被校验的元素对象
            //params：规则对应的参数值
            var inputCode = $('#code').val();
            $.ajax({
                "async": false,
                "url": "/user/getStrCode",
                "type": "POST",
                "dataType": "json",
                "success": function (data) {
                    flag = data.flag;
                    if (flag) {
                        if (inputCode == data.strCode) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                    }
                }
            });
            return flag;
        });


        $().ready(function () {
            $("#loginform").validate({
                rules: {
                    code: {
                        required: true,
                        checkCode: true
                    },
                    username:{
                        required:true,
                        checkname:true
                    },
                    password:{
                        required:true
                    }
                },
                messages: {
                    code: {
                        required: "验证码不能为空",
                        checkCode: "验证码不正确"
                    },
                    username:{
                        required:"用户名不能为空",
                        checkname:"用户名不存在"
                    },
                    password:{
                        required:"密码不能为空"
                    }
                },
                errorClass: "error",
                focusCleanup: true,
                debug: false
            })
        })
    </script>
</head>
<body>

<!-- 引入header.jsp -->
<jsp:include page="header.jsp"></jsp:include>


<div class="container"
     style="width: 100%; height: 460px; background: #FF2C4C url('/images/loginbg.jpg') no-repeat;">
    <div class="row">
        <div class="col-md-7">
            <!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
        </div>

        <div class="col-md-5">
            <div
                    style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
                <font>会员登录</font>USER LOGIN
                <div>&nbsp;</div>
                <form class="form-horizontal" id="loginform" action="" method="post">
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="code" class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="code" name="code"
                                   placeholder="请输入验证码">
                        </div>
                        <div class="col-sm-3">
                            <img type="image" src="/user/getImage" id="codeImage" onclick="changeImageCode()"
                                 style="cursor:pointer;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label> <input type="checkbox"> 自动登录
                                </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
                                    type="checkbox"> 记住用户名
                            </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input id="login" type="submit" width="100" value="登录" name="submit"
                                   style="background: url('/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 引入footer.jsp -->
<jsp:include page="footer.jsp"></jsp:include>
<script>
    jQuery(document).ready(function () {
//回车事件绑定
        document.onkeydown = function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 13) {
                $('#login').click();
            }
        };
    });

    $('#login').click(function () {

        var username = $('#username').val();
        var password = $('#password').val();

        data = {username:username,password:password};

        $.ajax({
            url:"/user/login",
            data:data,
            type:"post",
            dataType:"json",
            success:function (result) {
                if(result && result.code == 200){
                    layer.msg(result.msg);
                    window.location.href="/cart";
                }else {
                    layer.msg(result.msg);
                }
            }
        })

    })
</script>
</body>

<script type="text/javascript">
    function changeImageCode() {
        $('#codeImage').attr('src', '/user/getImage?abc=' + Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题
    }
</script>
</html>