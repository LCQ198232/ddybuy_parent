$(function () {
    //使用datagrid显示区域
    $('#dis').datagrid({
        title: "广告类型信息",
        url: 'getAllContent',  //服务器地址
        pagination: true,  //启用分页
        pageList: [3, 6, 9, 15, 20], //设置每页大小
        pageSize: 3, //每页三条
        sortName: 'id',
        remoteSort: false,
        sortOrder: 'desc',
        columns: [[
            {field: "ck", checkbox: "true", width: 100, align: 'left'},
            {field: 'id', title: '广告编号', width: 100, align: 'left'},
            {field: 'title', title: '广告标题', width: 100, align: 'left'},
            {field: 'url', title: 'URL', width: 100, align: 'left',
                formatter: function (value, row, index) {
                    //格式化单元格函数，有3个参数：
                    //     value：字段的值。
                    //     rowData：行数据。
                    //     rowIndex：行索引。
                    //发送异步请求Ajax
                    return "<a href="+row.url+" target=\"_blank\">"+row.url+"</a>";
                }},
            {field: 'status', title: '广告状态', width: 100, align: 'left'},
            {field: 'sortOrder', title: '展示顺序', width: 100, align: 'left'},
            {field: 'opt', title: '操作', width: 100, align: 'left',
                formatter: function (value, row, index) {
                    //格式化单元格函数，有3个参数：
                    //     value：字段的值。
                    //     rowData：行数据。
                    //     rowIndex：行索引。
                    //发送异步请求Ajax
                    return "<a href=\"javascript:DelContent()\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>"+
                        "      <a href="+ row.pic +" target=\"_blank\">预览</a>";

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
    location.href="tbContent.html";
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
    $("#upDialog").dialog("open").dialog("setTitle", ">>>>修改广告信息");
    //发送异步请求获取对象进行回显
    var row = SelectRows[0];
    $.post("getSingleContent",
        {"id": row.id},
        function (data) {
            //回显
            $('#upDialogForm').form('load', data);
        }, "json");

    //从服务器中获取数据，并显示到下拉列表
    // 1.使用jquery发送异步请求获取数据(json)，通过js循环操作option
    // 2.利用easyui绑定数据
    $('#categoryid').combobox({
        url:'getCategory',
        valueField:'id',
        textField:'name'
    });

}

//修改的(对话框的更新按钮)
function UpDialog() {
    //添加的表单提交
    //使用easyui提交表单
    $('#upDialogForm').form('submit', {
        url: "updateContent",
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
                $.messager.alert('提示框', '修改失败！噢耶!!', 'info');
            }
        }
    });

}

//删除操作(批量删除)  删除广告信息
function DelContent() {
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
                $.post("delContentOneOrList",
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




