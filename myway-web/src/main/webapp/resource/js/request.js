/**
 * http方法
 */
var http = (function () {
    var object = {};//内部方法访问对象
    var isDevelop = true;   //开发环境开关
    var apiPerfix = '/rest';

    /**
     * GET封装
     * @param url
     * @param data
     * @param successCallBack
     * @param errorCallBack
     * @param async
     */
    object.get = function (url, data, successCallBack, errorCallBack, async) {
        object.ajaxUtil(url, data, successCallBack, errorCallBack, async, 'GET');
    };

    /**
     * POST封装
     * @param url
     * @param data
     * @param successCallBack
     * @param errorCallBack
     * @param async
     */
    object.post = function (url, data, successCallBack, errorCallBack, async) {
        object.ajaxUtil(url, data, successCallBack, errorCallBack, async, 'POST');
    };

    /**
     * AJAX执行封装
     * @param url
     * @param data
     * @param successCallBack
     * @param errorCallBack
     * @param async
     * @param type
     */
    object.ajaxUtil = function (url, data, successCallBack, errorCallBack, async, type) {
        Pace.restart();
        if (async != false) {
            async = true;
        }
        $.ajax({
            url: apiPerfix + url,
            data: data,
            async: async,
            type: type,
            dataType: 'JSON',
            success: function (res) {
                //返回码判断
                switch (parseInt(res.statusCode)) {
                    case 0:
                        break;
                    default :
                        break;
                }

                if (typeof successCallBack == 'function') {
                    successCallBack(res);
                }
            },
            error: function (XMLHttpRequest) {
                //请求出错时调用。传入 XMLHttpRequest 对象，描述错误类型的字符串以及一个异常对象
                console.log(XMLHttpRequest);
                debugger;
                if (XMLHttpRequest.responseJSON) {
                    //系统自定义返回码判断
                    var errorCode = parseInt(XMLHttpRequest.responseJSON.code);
                    var errorMsg = XMLHttpRequest.responseJSON.msg || '操作异常';
                    try {
                        switch (errorCode) {
                            case 401:
                                tools.toastr(errorMsg, '401错误', 'error');
                                setTimeout("window.location.href = '/pages/index.html'", 2000);
                                break;
                            case 404:
                                tools.toastr(errorMsg, '404错误', 'error');
                                //tools.href('/pages/404.html');
                                break;
                            case 500:
                                tools.toastr(errorMsg, '500错误', 'error');
                                //tools.href('/pages/500.html');
                                break;
                            case 502:
                                tools.toastr(errorMsg, '502错误', 'error');
                                //tools.href('/pages/502.html');
                                break;
                            case 503:
                                tools.toastr(errorMsg, '503错误', 'error');
                                //tools.href('/pages/503.html');
                                break;
                            default:
                                tools.toastr('操作异常', '', 'error');
                                break;
                        }
                    }
                    catch (e) {
                        //tools.toastr("异常！", '', 'error')
                    }
                }
                else {
                    try {
                        switch (XMLHttpRequest.status) {
                            case 401:
                                tools.toastr('未登录', '401错误', 'error');
                                setTimeout("window.location.href = '/pages/index.html'", 2000);
                                break;
                            case 404:
                                tools.toastr('操作异常', '404错误', 'error');
                                //tools.href('/pages/404.html');
                                break;
                            case 500:
                                tools.toastr('操作异常', '500错误', 'error');
                                //tools.href('/pages/500.html');
                                break;
                            case 502:
                                tools.toastr('操作异常', '502错误', 'error');
                                //tools.href('/pages/502.html');
                                break;
                            case 503:
                                tools.toastr('操作异常', '503错误', 'error');
                                //tools.href('/pages/503.html');
                                break;
                            default:
                                tools.toastr('操作异常', '', 'error');
                                break;
                        }
                    }
                    catch (e) {
                        tools.toastr("网络异常！", '', 'error')
                    }
                }

                if (typeof errorCallBack == 'function') {
                    errorCallBack(XMLHttpRequest);
                }
            }
        })
    };

    /**
     * 域名设置
     * @param url
     * @returns {string}
     */
    object.getFullUrl = function (url) {
        if (url && tools.isString(url)) {
            return window.location.protocol + '//' + window.location.host + url;
        }
        else {
            return window.location.protocol + '//' + window.location.host;
        }
    };
    return object;
})();


