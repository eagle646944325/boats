/**
 * 分页加载器
 */
var membershipQuery = (function() {
    var obj = {};
    var pageBar = $("#pageBar");
    var tableBody = $("#dataList");
    var queryPageUtil = new PageUtils( pageBar, tableBody);

    obj.grade = {
        '0' : '普通会员',
        '1' : '黄金会员',
        '2' : '黑金会员'
    };
    obj.statusKey = {
        '0' : '已注销',
        '1' : '正常',
        '2' : '暂停使用'
    };
    obj.data = [];//当前列数据

    obj.loadQuery = function(page, removeIfEmpty) {
        debugger;
        queryPageUtil.loadPage(page, '/member/listMember',
            {
                'phone' : $("#searchPhone").val(),
                'memberName'   : $("#searchMemberName").val()
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
                            html += '<td>' + item.phone + '</td>';
                            html += '<td>' + item.memberName + '</td>';
                            html += '<td>' + tools.getFormatTime( item.registTime, 'yyyy-MM-dd hh:mm' ) + '</td>'
                            html += '<td>' + obj.grade[item.grade] + '</td>';
                            html += '<td>' + obj.statusKey[item.status] + '</td>';
                            html += '<td>  <span class="m-r-sm text-muted welcome-message" onclick="editMember('+item.id+')">  编辑</span><span class="m-r-sm text-muted welcome-message" onclick="viewMember('+item.id+')">  查看</span></td>';
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

function editMember(id){
    objid=id;
    pjaxUrl("/pages/editMember.html");
};

function viewMember(id){
    alert(id);
};