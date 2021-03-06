<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/13
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://cn.edu.cmu/uitag" prefix="dm" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>

    <meta name="description" content="Common form elements and layouts"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="assets/css/font-awesome.css"/>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="assets/css/jquery-ui.custom.css"/>
    <link rel="stylesheet" href="assets/css/jquery-ui.css"/>
    <link rel="stylesheet" href="assets/css/ui.jqgrid.css"/>
    <link rel="stylesheet" href="assets/css/chosen.css"/>
    <link rel="stylesheet" href="assets/css/bootstrap-datepicker3.css"/>

    <!-- text fonts -->
    <link rel="stylesheet" href="assets/css/ace-fonts.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-part2.css" class="ace-main-stylesheet"/>
    <link rel="stylesheet" href="assets/css/ace-ie.css"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <![endif]-->

    <!--[if lte IE 8]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.js"></script>
    <![endif]-->


</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <form class="form-horizontal" id="form" role="form">
                            <input type="hidden" name="hysb.sbid" value="${hysb.sbid}"/>
                            <input type="hidden" name="hysb.jhid" value="${hysb.jhid}"/>
                            <input type="hidden" name="hysb.status" id="status" value="${hysb.status}"/>
                            <!-- #section:elements.form -->


                            <div class="form-group">

                                <label class="col-xs-2 control-label "> 会议编号: </label>
                                <div class="col-xs-4">
                                    <input type="text" readonly="readonly" disabled="disabled" name="hysb.hybh" value="${hysb.hybh}" id="hybh" readonly="readonly" placeholder="会议编号保存时自动生成" class="col-xs-12"/>
                                </div>
                                <label class="col-xs-2 control-label "> 已在属地公安部门备案 </label>
                                <div class="col-xs-4">
                                    <dm:list name="hysb.sfbb" type="radio" value="${hysb.sfbb}" id="sfbb" tabName="t_dm_yn" readonly="readonly" disabled="disabled"/>
                                </div>
                            </div>

                            <div class="form-group">

                                <label class="col-xs-2 control-label "> 会议名称: </label>

                                <div class="col-xs-4">
                                    <input type="text" readonly="readonly" disabled="disabled" name="hysb.hymc" value="${hysb.hymc}" id="hymc" placeholder="会议名称" class="col-xs-12"/>
                                </div>
                                <label class="col-xs-2 control-label "> 会议名称(英文): </label>
                                <div class="col-xs-4">
                                    <input type="text" readonly="readonly" disabled="disabled" name="hysb.hymcEn" value="${hysb.hymcEn}" id="hymcEn" placeholder="会议名称（English）" class="col-xs-12"/>
                                </div>

                            </div>


                            <div class="form-group">
                                <label class="col-xs-2 control-label "> 主办单位: </label>
                                <div class="col-xs-4">
                                    <input type="text" readonly="readonly" disabled="disabled" name="hysb.zbdw" value="${hysb.zbdw}" id="zbdw" placeholder="主办单位" class="col-xs-12"/>
                                </div>
                                <label class="col-xs-2 control-label "> 承办单位: </label>

                                <div class="col-xs-4">
                                    <input type="text" readonly="readonly" disabled="disabled" name="hysb.cbdw" value="${hysb.cbdw}" id="cbdw" placeholder="承办单位" class="col-xs-12"/>
                                </div>

                            </div>


                            <div class="form-group">
                                <label class="col-xs-2 control-label "> 举行日期: </label>
                                <div class="col-xs-4">
                                    <input class="form-control date-picker" name="hysb.jxrq" readonly="readonly" disabled="disabled" value="<fmt:formatDate value="${hysb.jxrq}" pattern="yyyy-MM-dd"/>" id="jbrq" type="text"
                                           data-date-format="yyyy-mm-dd"/>
                                </div>

                                <label class="col-xs-2 control-label "> 经费来源: </label>
                                <div class="col-xs-4">
                                    <input type="text" readonly="readonly" disabled="disabled" name="hysb.jfly" value="${hysb.jfly}" id="jfly" placeholder="经费来源" class="col-xs-12"/>
                                </div>

                            </div>

                            <div class="form-group">

                                <label class="col-xs-2 control-label "> 地点: </label>

                                <div class="col-xs-10">
                                    <input type="text" readonly="readonly" disabled="disabled" name="hysb.dd" value="${hysb.dd}" id="dd" placeholder="地点" class="col-xs-12"/>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-xs-2 control-label "> 负责人姓名: </label>
                                <div class="col-xs-4">
                                    <input type="text" readonly="readonly" disabled="disabled" name="hysb.fzrxm" value="${hysb.fzrxm}" id="fzrxm" placeholder="负责人姓名" class="col-xs-12"/>
                                </div>
                                <label class="col-xs-2 control-label "> 负责人电话: </label>
                                <div class="col-xs-4">
                                    <input type="text" readonly="readonly" disabled="disabled" name="hysb.fzrdh" value="${hysb.fzrdh}" id="fzrdh" placeholder="负责人电话" class="col-xs-12"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2 control-label "> 会议类型: </label>
                                <div class="col-xs-4">
                                    <dm:list name="hysb.hylx" value="${hysb.hylx}" type="select" id="hylx"  tabName="t_dm_hylx" readonly="readonly" disabled="disabled"/>
                                </div>
                                <div class="col-xs-6">

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2 control-label "> 会议基本信息: </label>
                                <div class="col-xs-10">
                                    <textarea class="form-control limited" name="hysb.hyjbxx" id="hyjbxx" readonly="readonly" disabled="disabled"
                                              placeholder="会议基本信息" maxlength="500">${hysb.hyjbxx}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2 control-label "> 会议规模: </label>
                                <div class="col-xs-10">
                                    <textarea class="form-control limited" name="hysb.hygm" id="hygm" placeholder="会议规模" readonly="readonly" disabled="disabled"
                                              maxlength="500">${hysb.hygm}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2 control-label "> 会议背景及必要性: </label>
                                <div class="col-xs-10">
                                    <textarea class="form-control limited" name="hysb.bjjbyx" id="bjjbyx" readonly="readonly" disabled="disabled"
                                              placeholder="会议背景及必要性" maxlength="500">${hysb.bjjbyx}</textarea>
                                </div>
                            </div>
                            <hr/>
                            <div class="form-group">
                                <div class="col-xs-2">
                                    参会人员姓名
                                </div>
                                <div class="col-xs-2">
                                    国籍
                                </div>
                                <div class="col-xs-3">
                                    单位
                                </div>
                                <div class="col-xs-3">
                                    职务
                                </div>
                                <div class="col-xs-2">
                                    操作
                                </div>
                            </div>


                            <c:if test="${sbRymds!=null && sbRymds.size()>0}">
                                <c:forEach items="${sbRymds}" var="ry">
                                    <div class="form-group cy">
                                        <div class="col-xs-2">
                                            <input type="text" readonly="readonly" disabled="disabled" inp="xm" name="chrys[@].xm" value="${ry.xm}"  placeholder="姓名" class="col-xs-12"/>
                                        </div>
                                        <div class="col-xs-3">
                                            <input type="text" readonly="readonly" disabled="disabled" inp="gj" name="chrys[@].gj" value="${ry.gj}" placeholder="国籍" class="col-xs-12"/>
                                        </div>
                                        <div class="col-xs-4">
                                            <input type="text" readonly="readonly" disabled="disabled" inp="dw" name="chrys[@].dw" value="${ry.dw}" placeholder="单位" class="col-xs-12"/>
                                        </div>
                                        <div class="col-xs-3">
                                            <input type="text" readonly="readonly" disabled="disabled" inp="zw" name="chrys[@].zw" value="${ry.zw}" placeholder="职务" class="col-xs-12"/>
                                        </div>

                                    </div>
                                </c:forEach>
                            </c:if>
                             <div id="btns" class="col-md-offset-3 col-md-9" style = "text-align:right;">
                                <hr/>
                                <button class="btn btn-info btn-sm" id="close" type="button">
                                    <i class="ace-icon fa fa-close bigger-110"></i>
                                    关闭
                                </button>
                            </div>
                           &nbsp;&nbsp;&nbsp;
                        </form>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->


</div><!-- /.main-container -->
<div id="template" style="display: none">

    <div class="form-group cy">
        <div class="col-xs-2">
            <input type="text" readonly="readonly" disabled="disabled" inp="xm" name="chrys[@].xm" placeholder="姓名" class="col-xs-12"/>
        </div>
        <div class="col-xs-2">
            <input type="text" readonly="readonly" disabled="disabled" inp="gj" name="chrys[@].gj" placeholder="国籍" class="col-xs-12"/>
        </div>
        <div class="col-xs-3">
            <input type="text" readonly="readonly" disabled="disabled" inp="dw" name="chrys[@].dw" placeholder="单位" class="col-xs-12"/>
        </div>
        <div class="col-xs-3">
            <input type="text" readonly="readonly" disabled="disabled" inp="zw" name="chrys[@].zw" placeholder="职务" class="col-xs-12"/>
        </div>
        <div class="col-xs-2">
            <button class='btn btn-danger btn-mini' onclick='deleteCy(this); return false;'><i
                    class='ace-icon fa fa-trash-o  '>删除</i></button>
            <button class='btn btn-info btn-mini' onclick='appendCy(); return false;'><i
                    class='ace-icon fa fa-plus '>添加</i></button>
        </div>
    </div>
</div>

<script src='assets/js/jquery.js'></script>

<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/chosen.jquery.js"></script>
<script src="assets/js/date-time/bootstrap-datepicker.js"></script>
<script src="assets/js/typeahead.jquery.js"></script>
<script src="assets/js/ace/elements.typeahead.js"></script>

<script src="assets/js/jqGrid/jquery.jqGrid.js"></script>
<script src="assets/js/jqGrid/i18n/grid.locale-cn.js"></script>

<!-- jqueryValidate验证框架-->
<script src="assets/js/jqvalidate/jquery.validate.min.js"></script>
<script src="assets/js/jqvalidate/messages_zh.js"></script>
<!--弹出层 -->
<script src="assets/js/layer/layer.js"></script>

<!--自定义js -->
<script src="assets/project/js/common-window.js"></script>


<!-- ace scripts -->


<script>


    $(function () {
        $("#close").click(function(){

            closeLayer();

        });


    });



</script>
</body>
</html>
