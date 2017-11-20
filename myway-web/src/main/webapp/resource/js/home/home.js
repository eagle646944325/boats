/**
 * 分页加载器
 */
var membershipQuery = (function() {
    var obj = {};
    var pageBar = $("#pageBar");
    var tableBody = $("#dataList");
    var queryPageUtil = new PageUtils( pageBar, tableBody);

    obj.sexKey = {
        '1' : '男',
        '2' : '女'
    };
    obj.statusKey = {
        '0' : '已注销',
        '1' : '正常',
        '2' : '暂停使用'
    };
    obj.orgKey = {
        '1' : '系统管理一组',
        '2' : '运维一组',
        '3' : '研发一组',
        '4' : '运维二组',
        '5' : '研发二组',
        '6' : '运维三组',
        '7' : '研发三组'
    };
    obj.roleKey = {
        '1' : '系统管理',
        '2' : '运维人员',
        '3' : '研发人员'
    };

    obj.data = [];//当前列数据
    obj.type = 'msisdn';//当前搜索条件，默认为phone手机号
    obj.loadQuery = function(page, removeIfEmpty) {
        // debugger;
        queryPageUtil.loadPage(page, '/sys/listuser',
            {
                'orgId'   : $("#orgId option:selected").val(),
                'roleId'  : $("#roleId option:selected").val(),
                'sex'     : $("#sex option:selected").val(),
                'status'     : $("#status option:selected").val(),
                'msisdn'  : (obj.type == 'msisdn') ? $("#searchText").val() : '',
                'wx'      : (obj.type == 'wx') ? $("#searchText").val() : '',
                'qq'      : (obj.type == 'qq') ? $("#searchText").val() : '',
                'userName'        : (obj.type == 'userName' ) ? $("#searchText").val() : '',
                'searchStartTime' : $("#searchStartTime").val(),
                'searchEndTime'   : $("#searchEndTime").val()
            },
            function (jsonResponse) {
                tableBody.html('');
                if(tools.isNull(jsonResponse['data'])){
                    pageBar.html('');
                    return;
                }
                var pagination = jsonResponse['data'];
                if (pagination['list'] && pagination['list'].length > 0) {
                    obj.data = pagination['list'];
                    $(pagination['list']).each(function (index, item) {
                        var html = '<tr class="animated fadeIn">';
                            html += '<td>' + item.userName + '</td>';
                            html += '<td>' + item.realName + '</td>';
                        html += '<td>' + item.msisdn + '</td>';
                        html += '<td>' + item.wx + '</td>';
                        html += '<td>' + item.qq + '</td>';
                        html += '<td>' + obj.sexKey[item.sex] + '</td>';
                        html += '<td>' + item.roleId + '</td>';
                        html += '<td>' + obj.orgKey[item.orgId] + '</td>';
                        html += '<td>' + obj.statusKey[item.status] + '</td>';
                        html += '<td>' + tools.getFormatTime( item.createTime, 'yyyy-MM-dd hh:mm' ) + '</td>';
                            html += '</tr>';
                            $(html).appendTo(tableBody);
                    });
                    tableBody.parent().trimTextLength();
                    tools.tooltipInit();
                }
            }, null, removeIfEmpty
        );
    };

    /**
     * 改变搜索类型
     * @param that
     * @param type
     */
    obj.changeShow = function( that, type ){
        obj.type = type;
        $("#innerShow").html( $(that).html() );
        $("#searchText").val( '').focus();
    };

    return obj;
})();

// 首次加载
$(document).ready(function() {
    membershipQuery.loadQuery(1);
    $('.input-daterange').datepicker({
        keyboardNavigation: false,
        forceParse: false,
        autoclose: true,
        format: "yyyy-mm-dd"
    });
});