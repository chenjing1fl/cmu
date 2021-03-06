<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/10
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://cn.edu.cmu/uitag" prefix="dm" %>

<div >
    <form class="form-horizontal" role="form">
        <!-- #section:elements.form -->
        <div class="form-group">

            <label class="col-sm-1 control-label no-padding-right" for="condition1"> 会议编号: </label>
            <div class="col-sm-2">
                <input type="text"  id="condition1"  placeholder="请输入会议编号" class="col-xs-12" />
            </div>

            <label class="col-sm-2 control-label no-padding-right" for="condition2"> 主办单位/承办单位: </label>
            <div class="col-sm-2">
                <input type="text"  id="condition2"  placeholder="请输入主办单位或承办单位" class="col-xs-12" />
            </div>

            <label class="col-sm-2 control-label no-padding-right"  > 会议类型: </label>
            <div class="col-sm-2">
                <dm:list tabName="T_DM_HYLX" id="condition3" name="hylx" ></dm:list>
            </div>

            <div class="col-sm-1">
                <button class="btn btn-info btn-xs" id="query" type="button"> <i class="ace-icon fa fa-search "></i>
                    查询
                </button>
            </div>
        </div>

    </form>
    <div id="grid-pager"></div>

    <table id="grid-table"></table>

</div>

<!-- inline scripts related to this page -->
<script type="text/javascript">



    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


    $(function() {

        var parent_column = $(grid_selector).closest('[class*="col-"]');
        //resize to fit page size
        $(window).on('resize.jqGrid', function () {
            $(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
        })

        //侧边栏发生变化时重新设置宽度
        $(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
            if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
                //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
                setTimeout(function() {
                    $(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
                }, 0);
            }
        })

        //自定义 按钮
        var navBtns = [];

        var settings = {
            caption: "综合统计查询",
           /* data: grid_data,*/
            url:'hytj/list',
            colNames:["会议编号","会议名称","举行日期","地点","经费来源","会议类型", "是否执行","是否总结"],
            navBtns:navBtns,//自定义按钮
            pager:pager_selector,
            colModel:[
                {name:'hybh',index:'hybh',  },
                {name:'hymc',index:'hymc',  },

                {name:'jxrq',index:'jxrq',formatter:function(jxrq){
                        return new Date(jxrq).getYmd("yyyy-MM-dd");
                    }  },
                {name:'dd',index:'dd',  },
                {name:'jfly',index:'jfly',  },
                {name:'hylx',index:'hylx', formatter:function (hylx) {
                        return dmcache.getCode('t_dm_hylx',hylx);
                    } },
                {name:'sfzxjh',index:'sfzxjh',  formatter:function(sfzxjh, options, rowObject){
                    var sbid = rowObject.sbid;
                    if(sfzxjh == '1'){//查看申报信息
                        return "<a href='javascript:showSbInfo(\""+sbid+"\")' >已执行</a>";
                    }else{
                        return "未执行";
                    }

                 } },
                {name:'sfzj',index:'sfzj', formatter:function(sfzj, options, rowObject){
                        var zjid = rowObject.zjid;
                        if(sfzj == '1'){//查看总结信息
                            return "<a href='javascript:showZjInfo(\""+zjid+"\")' >已总结</a>";
                        }else{
                            return "未总结";
                        }
                }  },


            ]

        }


        //渲染jqGrid表格 ,包括渲染 分页信息
        $(grid_selector).tables(settings);



        //查询按钮添加事件
        $("#query").click(function(){
            refreshTable();
        });

    });

    function clearTable(){
        $(grid_selector).jqGrid('clearGridData');  //清空表格
    }

    function refreshTable(){
        $(grid_selector).jqGrid('setGridParam',{  // 重新加载数据
            postData:{
                'hybh':$("#condition1").val(),
                'hymc':$("#condition2").val(),
                'hylx':$("#condition3").val()
            },
            page:1
        }).trigger("reloadGrid");
    }


    //查看申报
    function showSbInfo(sbid){
        layer.newpage({
            area: ['1100px', ($(window).height()-10)+"px"],
            title:'查看会议申报信息',
            content:'hysb/toEdit?type=show&id='+sbid,
        });
    }

    //查看总结
    function showZjInfo(zjid){
        layer.newpage({
            area: ['900px', ($(window).height()-10)+"px"],
            title:'查看会议总结信息',
            content:'hyzj/toEdit?type=show&id='+zjid,
        });
    }

</script>