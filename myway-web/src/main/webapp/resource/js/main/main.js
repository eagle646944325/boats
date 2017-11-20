
var loginUser = ''; // 登录的用户信息
var objid = ''; // 登录的用户信息
$(function () {
    //菜单pjax事件绑定
    $(document).pjax('a[data-pjax]', '.pjax-container', {
        maxCacheLength: 0,
        cache: false,
        fragment: ".pjax-container",
        timeout: 8000
    });

    // 安全退出
    $("#loginOutBtn,#loginOutBtnPro").click(function () {
        swal({
            title: "确定退出吗?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false
        }, function () {
            $.ajax({
                type: "POST",
                url: "/rest/sys/loginout",
                dataType: "json",
                contentType : "application/json; charset=utf-8",
                success:function(result){
                    loginOut();
                },
                error : function(data) {
                    swal( "操作异常", '', 'error' );
                }
            });
        });
    });

    // 加载登录人员的信息
    getLoginUserInfo();

    // 默认跳转到主页
    pjaxUrl("/pages/home.html");

});

// 通过pjax插件加载需要的页面内容
function pjaxUrl ( url ){
    $.pjax({
        url: url,
        container: '.pjax-container',
        maxCacheLength:0,
        cache: false,
        fragment: ".pjax-container",
        timeout: 8000
    })
};

function getLoginUserInfo(){
    $.ajax({
        type: "POST",
        url: "/rest/sys/sessionuser",
        dataType: "json",
        contentType : "application/json; charset=utf-8",
        success:function(result){
            if(result['code']=='0'){
                loginUser = result['data'];
                if(strUtil.isEmpty(loginUser)){
                    loginOut();
                    return;
                }
                $("#userName").html(loginUser['userName']);
                $("#userOrg").html(loginUser['tSysOrg']['name']);
            }else{
                setTimeout('window.location.href = "/pages/index.html";', 3000);
                swal({
                    title: "系统登录过期，请重新登录",
                    text: "",
                    type: "success",
                    showCancelButton: false,
                    confirmButtonText: "是",
                    closeOnConfirm: false
                }, function () {
                    loginOut();
                });
            }
        },
        error : function(data) {
            swal( "登录异常请重新登录", '', 'error' );
            loginOut();
        }
    });
}

function loginOut() {
    removeAllCookie();
    window.location.href = "/pages/index.html";
}