// 登录页面
$(function () {
    $("#loginBtn").click(function () {
        var userName = $("#userName").val();
        var password = $("#password").val();
        if(strUtil.isEmpty(userName) || strUtil.isEmpty(password)){
            swal( "请输入登录用户名和密码", '', 'error' );
            return;
        }else{
            var args = {
                "userName":userName,
                "password":password
            };
            $.ajax({
                type: "POST",
                url: "/rest/sys/login",
                dataType: "json",
                contentType : "application/json; charset=utf-8",
                data: JSON.stringify(args),
                success:function(result){
                    if(result['code']=='0'){
                        window.location.href = "/pages/main.html";
                    }else{
                        swal( result['msg'], '', 'error' );
                        return;
                    }
                },
                error : function(data) {
                    swal( "操作异常", '', 'error' );
                }
            });
        }
    });
});