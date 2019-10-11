$(function () {
    //使用datagrid显示区域
    $('#dis').datagrid({
        title: "广告类型信息",
        url: 'getAllTbContentCategory',  //服务器地址
        pagination: true,  //启用分页
        pageList: [3, 6, 9, 15, 20], //设置每页大小
        pageSize: 3, //每页三条
        sortName: 'id',
        remoteSort: false,
        sortOrder: 'desc',
        columns: [[
            {field: "ck", checkbox: "true", width: 100, align: 'left'},
            {field: 'id', title: '广告类型编号', width: 100, align: 'left'},
            {field: 'name', title: '广告类型名称', width: 100, align: 'left'},
            {
                field: 'opt', title: '操作', width: 100, align: 'left',
                formatter: function (value, row, index) {
                    //格式化单元格函数，有3个参数：
                    //     value：字段的值。
                    //     rowData：行数据。
                    //     rowIndex：行索引。
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:DelContentCategory(" + row.id + ")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    // return "<a href=\"javascript:DelContentCategory(" + row.id + ")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>" + "                 " +
                    //     "<a href=\"javascript:SearchStreet(" + row.id + ")\">查看街道</a>";

                }
            }
        ]]
    });
});


//(对话框的存储按钮)添加
function SaveDialog() {
    //添加的表单提交
    //使用easyui提交表单
    $('#addDialogForm').form('submit', {
        url: "addContentCategory",
        success: function (data) {  //注意:返回的是json字符串
            //将json字符串转化为json对象
            data = $.parseJSON(data);
            if (data.result == 1) {
                //清空form
                $('#addDialogForm').form("clear")
                //添加成功关闭对话框
                $("#AddDialog").dialog("close");
                //实现datagrid的刷新
                $('#dis').datagrid('reload');
                $.messager.alert('提示框', '添加成功！噢耶!!', 'info');
            } else {
                $.messager.alert('提示框', '添加失败！^_^', 'info');
            }
        }
    });

}

//打开对话框
function Add() {
    $("#AddDialog").dialog('open').dialog('setTitle', ">>>>添加区域");
}

//(对话框的取消按钮)(共用)
function CloseDialog(id) {
    $("#" + id).dialog('close');
    //实现datagrid的刷新
    $('#dis').datagrid('reload');
}

//打开修改对话框
function ModifyBySelect() {
    //获取datagrid选中行  返回的数组
    var SelectRows = $("#dis").datagrid('getSelections');
    if (SelectRows.length != 1) {
        $.messager.alert('提示框', '你还没有选中行，或者选择了多行.', 'info');
        return;
    }
    //打开窗口
    $("#upDialog").dialog("open").dialog("setTitle", ">>>>修改广告类型信息");
    //发送异步请求获取对象进行回显
    var row = SelectRows[0];
    $.post("getSingleContentCategory",
        {"id": row.id},
        function (data) {
            //回显
            $('#upDialogForm').form('load', data);
        }, "json");
}

//修改的(对话框的更新按钮)
function UpDialog() {
    //添加的表单提交
    //使用easyui提交表单
    $('#upDialogForm').form('submit', {
        url: "updateContentCategory",
        success: function (data) {  //注意:返回的是json字符串
            //将json字符串转化为json对象
            data = $.parseJSON(data);
            if (data.result == 1) {
                //关闭对话框
                $("#upDialog").dialog("close");
                //实现datagrid的刷新
                $('#dis').datagrid('reload');
                $.messager.alert('提示框', '修改成功！噢耶!!', 'info');
            } else {
                $.messager.alert('提示框', '修改成功！噢耶!!', 'info');
            }
        }
    });

}

//删除操作(批量删除)  删除广告信息
function DelContentCategory() {
    //获取datagrid选中行  返回的数组
    var SelectRows = $("#dis").datagrid('getSelections');
    if (SelectRows.length == 0) {
        $.messager.alert('提示框', '你还没有选中行，请选择要删除的行.', 'info');
        return;
    }
    //打开窗口
    $.messager.confirm('提示框', '确认删除吗?',
        function (r) {
            if (r) {
                var ids = [];
                for (var i = 0; i < SelectRows.length; i++) {
                    ids.push({name: 'ids', value: SelectRows[i].id});
                }
                $.post("delContentCategoryOneOrList",
                    ids,
                    function (data) {
                        if (data.result > 0) {

                            //实现datagrid的刷新
                            $('#dis').datagrid('reload');
                            $.messager.alert('提示框', '您已删除成功了！噢耶!!', 'info');
                        } else {
                            $.messager.alert('提示框', '删除失败了,请重试！噢耶!!', 'info');

                        }
                    }, "json");
            }else {
                //实现datagrid的刷新
                $('#dis').datagrid('reload');
            }
        });
}
//
// //删除单条   删除广告信息
// function DelContentCategory(id) {
//     //打开窗口
//     $.messager.confirm('提示框', '确认删除吗?',
//         function (r) {
//             if (r) {
//                 $.post("delContentCategory",
//                     {"id": id},
//                     function (data) {
//                         if (data.result > 0) {
//
//                             //实现datagrid的刷新
//                             $('#dis').datagrid('reload');
//                             $.messager.alert('提示框', '您已删除' + data.result + '条记录！噢耶!!', 'info');
//                         } else {
//                             $.messager.alert('提示框', '删除失败了,请重试！噢耶!!', 'info');
//                         }
//                     }, "json");
//             }
//         });
// }


//**************************************街道管理***********************************************************************
//显示区域下的街道
function SearchStreet(id) {
    $("#ShowStreet").dialog('open').dialog('setTitle', ">>>>街道信息");

    //把获取的区域id填充到添加的文本框中用于添加
    $("#districtId").val(id);

    //使用datagrid显示区域
    $('#streetdis').datagrid({
        title: "街道信息",
        url: 'getStreet?id=' + id,  //服务器地址
        pagination: true,  //启用分页
        pageList: [3, 6, 9, 15, 20], //设置每页大小
        pageSize: 3, //每页三条
        columns: [[
            {field: "ck", checkbox: "true", width: 100, align: 'left'},
            {field: 'id', title: '编号', width: 100, align: 'left'},
            {field: 'name', title: '街道名称', width: 100, align: 'left'},
            {
                field: 'opt', title: '操作', width: 100, align: 'left',
                formatter: function (value, row, index) {
                    //格式化单元格函数，有3个参数：
                    //     value：字段的值。
                    //     rowData：行数据。
                    //     rowIndex：行索引。
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:DeleteStreet(" + row.id + ")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>"


                }
            }
        ]]
    });
}


//(对话框的存储按钮)添加
function addStreet() {

    //取值
    var name = $("#name").val();
    var districtId = $("#districtId").val();
    //发送异步请求
    $.post("addStreet",
        {"name": name, "districtId": districtId},
        function (data) {
            if (data.result == 1) {
                //清空文本框 并获得焦点
                document.getElementById('name').value = "";
                document.getElementById('name').focus();
                //实现datagrid的刷新
                $('#streetdis').datagrid('reload');
                $.messager.alert('提示框', '添加成功！噢耶!!', 'info');
            } else {
                $.messager.alert('提示框', '添加失败！^_^', 'info');
            }
        }, "json");

}


//打开修改对话框
function UpdateStreet() {
    //获取datagrid选中行  返回的数组
    var SelectRows = $("#streetdis").datagrid('getSelections');
    if (SelectRows.length != 1) {
        $.messager.alert('提示框', '你还没有选中行，或者选择了多行.', 'info');
        return;
    }
    //打开窗口
    $("#upStreetDialog").dialog("open").dialog("setTitle", ">>>>修改街道信息");
    //发送异步请求获取对象进行回显
    var row = SelectRows[0];
    $.post("getSingleStreet",
        {"id": row.id},
        function (data) {
            //回显
            $('#upStreetDialogForm').form('load', data);
        }, "json");
}

//修改的(对话框的更新按钮)
function UpStreetDialog() {
    //添加的表单提交
    //使用easyui提交表单
    $('#upStreetDialogForm').form('submit', {
        url: "updateStreet",
        success: function (data) {  //注意:返回的是json字符串
            //将json字符串转化为json对象
            data = $.parseJSON(data);
            if (data.result == 1) {
                //关闭对话框
                $("#upStreetDialog").dialog("close");
                //实现datagrid的刷新
                $('#streetdis').datagrid('reload');
                $.messager.alert('提示框', '修改成功！噢耶!!', 'info');
            } else {
                $.messager.alert('提示框', '修改成功！噢耶!!', 'info');
            }
        }
    });
}


//删除操作(批量删除)
function DeleteStreetList() {
    //获取datagrid选中行  返回的数组
    var SelectRows = $("#streetdis").datagrid('getSelections');
    if (SelectRows.length == 0) {
        $.messager.alert('提示框', '你还没有选中行，请选择要删除的行.', 'info');
        return;
    }
    //打开窗口
    $.messager.confirm('提示框', '确认删除吗?',
        function (r) {
            if (r) {
                var ids = [];
                for (var i = 0; i < SelectRows.length; i++) {
                    ids.push({name: 'ids', value: SelectRows[i].id});
                }
                $.post("deleteMoreStreet",
                    ids,
                    function (data) {
                        if (data.result > 0) {

                            //实现datagrid的刷新
                            $('#streetdis').datagrid('reload');
                            $.messager.alert('提示框', '您已删除成功了！噢耶!!', 'info');
                        } else {
                            $.messager.alert('提示框', '删除失败了,请重试！噢耶!!', 'info');
                        }
                    }, "json");
            }
        });
}

//删除单条   删除街道的
function DeleteStreet(id) {
    //打开窗口
    $.messager.confirm('提示框', '确认删除吗?',
        function (r) {
            if (r) {
                $.post("deleteStreet",
                    {"id": id},
                    function (data) {
                        if (data.result > 0) {

                            //实现datagrid的刷新
                            $('#streetdis').datagrid('reload');
                            $.messager.alert('提示框', '您已删除' + data.result + '条记录！噢耶!!', 'info');
                        } else {
                            $.messager.alert('提示框', '删除失败了,请重试！噢耶!!', 'info');
                        }
                    }, "json");
            }
        });
}




