/**
 * 分页加载器
 */
var editmemberjs = (function() {
    var obj = {};
    var pageBar = $("#pageBar");
    var tableBody = $("#dataList");
    var queryPageUtil = new PageUtils( pageBar, tableBody);


    obj.loadQuery = function( removeIfEmpty) {
        queryPageUtil.ajaxRequest('/member/findMember',
            {
                'id'   : objid
            },
            function (jsonResponse) {
                var pagination = jsonResponse['data'];
                if (pagination!=null) {
                    obj.data = pagination;
                    $("#editPhone").append(pagination.phone);
                    $("#editPhone1").append(pagination.phone);
                    $("#editmemberName").append(pagination.memberName);
                    $("#editstartTime").val(tools.getFormatTime(pagination.startTime, 'yyyy-MM-dd' ) );
                    $("#editendTime").val(tools.getFormatTime( pagination.endTime, 'yyyy-MM-dd' ) );

                    $("#editremark").val(pagination.remark);

                    $("#editgrade").val(pagination.grade);
                    $("#editstatus").val(pagination.status);
                    $("#editprice").val(pagination.price);



                    tableBody.parent().trimTextLength();
                    tools.tooltipInit();
                }
            }, null, removeIfEmpty
        );
    };




    $("#editmemberButtion").click(function () {
        var id = objid;
        var editstartTime=$("#editstartTime").val( );
        var editendTime=$("#editendTime").val();
        var editremark =$("#editremark").val();
        var editgrade =$("#editgrade").val();
        var editstatus= $("#editstatus").val();
        var editprice= $("#editprice").val();
debugger;
        if(strUtil.isEmpty(id) ){
            swal( "id不能为空", '', 'error' );
            return;
        }else{



            queryPageUtil.ajaxRequest('/member/editMember',
                    {
                        'id'   : objid,
                         "searchStartTime":editstartTime,
                        "grade":editgrade,
                        "status":editstatus,
                        "price":editprice,
                        "remark":editremark,
                         "searchEndTime":editendTime
                    },
                    function (jsonResponse) {
                        debugger;
                        if(jsonResponse['code']=='0'){
                            pjaxUrl( "/pages/member.html");
                        }else{
                            swal( jsonResponse['msg'], '', 'error' );
                            return;
                        }
                    }, null
            );




            // var args = {
            //     'id'   : objid
            //     //"startTime":editstartTime,
            //     // "grade":editgrade,
            //     // "status":editstatus,
            //     // "price":editprice,
            //     // "remark":editremark
            //    // "endTime":editendTime
            // };
            // $.ajax({
            //     type: "POST",
            //     url: "/member/findMember",
            //     dataType: "json",
            //     contentType : "application/json; charset=utf-8",
            //     data: JSON.stringify(args),
            //     success:function(result){
            //         debugger;
            //         if(result['code']=='0'){
            //             window.location.href = "/pages/main.html";
            //         }else{
            //             swal( result['msg'], '', 'error' );
            //             return;
            //         }
            //     },
            //     error : function(data) {
            //         swal( "操作异常", '', 'error' );
            //     }
            // });
        }
    });


    return obj;
})();

// 首次加载
$(document).ready(function() {


    editmemberjs.loadQuery(objid);
    $('.input-daterange').datepicker({
        keyboardNavigation: false,
        forceParse: false,
        autoclose: true,
        format: "yyyy-mm-dd"
    });
});