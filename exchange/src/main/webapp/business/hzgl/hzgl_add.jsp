<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://cn.edu.cmu/uitag" prefix="dm" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <base href="<%=basePath%>" >
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="description" content="Common form elements and layouts" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.css" />
    <link rel="stylesheet" href="assets/css/font-awesome.css" />

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="assets/css/jquery-ui.custom.css" />
    <!-- text fonts -->
    <link rel="stylesheet" href="assets/css/ace-fonts.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="assets/css/jquery-ui.custom.css" />
    <link rel="stylesheet" href="assets/css/jquery-ui.css" />
    <link rel="stylesheet" href="assets/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="assets/css/chosen.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-datepicker3.css" />
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-part2.css" class="ace-main-stylesheet" />
    <link rel="stylesheet" href="assets/css/ace-ie.css" />
    <![endif]-->

    <!--[if lte IE 9]>
    <![endif]-->

    <!--[if lte IE 8]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.js"></script>
    <![endif]-->
</head>

<body class="no-skin">
<div class="main-container" id="main-container">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal" id="form" role="form">

                            <div class="form-group">
                                <label class="col-xs-2 control-label "  > 姓名: </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="xm"  value="" placeholder="请输入姓名"    class="col-xs-12" />
                                </div>
                                <label class="col-xs-2 control-label "  > 性别: </label>

                                <div class="col-xs-4">
                                    <dm:list tabName="T_DM_XB" type="radio" id="gender" name="gender"  placeholder="请选择性别" ></dm:list>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2 control-label "  > 护照号码: </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="hzhm"  value=""  placeholder="请输入护照号码"  class="col-xs-12" />
                                </div>
                                <label class="col-xs-2 control-label "  > 国籍: </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="gj"  value=""  placeholder="请选择国籍"  class="col-xs-12" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2 control-label "  > 出生日期: </label>
                                <div class="col-xs-4">
                                    <input class="form-control date-picker" id="id-date-picker-1" name="birthday" value=""
                                           type="text" data-date-format="yyyy-mm-dd" />

                                </div>
                                <label class="col-xs-2 control-label "  > 出生地点: </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="csdd"  value="" placeholder="请输入签发日期"   class="col-xs-12" />
                                </div>

                            </div>
                            <div class="form-group">
                                <label class="col-xs-2 control-label "  > 签发日期: </label>
                                <div class="col-xs-4">
                                    <input class="form-control date-picker" id="id-date-picker-5" name="qfrq" placeholder="请选择签发日期" value=""
                                           type="text" data-date-format="yyyy-mm-dd" />
                                </div>
                                <label class="col-xs-2 control-label "  > 签发地点: </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="qfdd"  value="" placeholder="请输入签发地点"    class="col-xs-12" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2 control-label "  > 有效期至: </label>
                                <div class="col-xs-4">
                                    <input class="form-control date-picker" id="id-date-picker-10"  name="yxqz"  value=""
                                           type="text" data-date-format="yyyy-mm-dd" />
                                </div>
                                <label class="col-xs-2 control-label "  > 发证机关: </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="fzjg"  value=""  placeholder="请输入发证机关"  class="col-xs-12" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2 control-label "  > 借出时间:</label>
                                <div class="col-xs-4">
                                    <input class="form-control date-picker" id="id-date-picker-8" placeholder="请选择借出时间" name="jcsj"  value=""
                                           type="text" data-date-format="yyyy-mm-dd" />
                                </div>
                                <label class="col-xs-2 control-label "  > 归还时间:</label>
                                <div class="col-xs-4">
                                    <input class="form-control date-picker" id="id-date-picker-9"  name="ghsj"  value=""
                                           type="text" data-date-format="yyyy-mm-dd" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-2 control-label "  > 证件类型   : </label>
                                <div class="col-xs-4">
                                    <dm:list tabName="t_dm_tzlb" type="select" id="hzlx"  name="hzlx"   ></dm:list>
                                </div>
                                <label class="col-xs-2 control-label "  > 护照状态: </label>
                                <div class="col-xs-4">
                                    <dm:list tabName="t_dm_hzzt" type="select" id="status"  name="status"   ></dm:list>
                                </div>
                            </div>


                             <div class="form-group">
                                <label class="col-xs-2 control-label "  > 美国多次往返签证号码   : </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="dcwfqzhmMg"  value=""    class="col-xs-12" />
                                </div>
                                <label class="col-xs-2 control-label "  > 欧洲多次往返签证号码: </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="dcwfqzhmOz"  value=""    class="col-xs-12" />
                                </div>
                            </div>

                             <div class="form-group">
                                <label class="col-xs-2 control-label "  > 加拿大多次往返签证号码   : </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="dcwfqzhmJnd"  value=""    class="col-xs-12" />
                                </div>
                                <label class="col-xs-2 control-label "  > 其他地区多次往返签证号码: </label>
                                <div class="col-xs-4">
                                    <input type="text"  name="dcwfqzhmQt"  value=""    class="col-xs-12" />
                                </div>
                            </div>
                            <div class="col-md-offset-3 col-md-9" style = "text-align:right;">
                                <hr/>
                                    <button class="btn btn-info btn-sm" id="btn-submit"  type="button">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        保存
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


<script src='assets/js/jquery.js'></script>

<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>

<script src="assets/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->
<script src="assets/js/jquery-ui.custom.js"></script>
<script src="assets/js/jquery.ui.touch-punch.js"></script>
<script src="assets/js/chosen.jquery.js"></script>
<script src="assets/js/fuelux/fuelux.spinner.js"></script>
<script src="assets/js/jquery.knob.js"></script>
<script src="assets/js/autosize.js"></script>
<script src="assets/js/jquery.inputlimiter.1.3.1.js"></script>
<script src="assets/js/jquery.maskedinput.js"></script>
<script src="assets/js/bootstrap-tag.js"></script>
<!-- jqueryValidate验证框架-->
<script src="assets/js/jqvalidate/jquery.validate.min.js"></script>
<script src="assets/js/jqvalidate/messages_zh.js"></script>
<!--弹出层 -->
<script src="assets/js/layer/layer.js"></script>
<!--date -->
<script src="assets/js/date-time/bootstrap-datepicker.js"></script>
<!--自定义js -->
<script src="assets/project/js/common-window.js"></script>
<!---date-->
<script src="assets/js/date-time/bootstrap-datepicker.js"></script>
<!-- ace scripts -->

<script>
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    $(function(){
        //日期选择器
        $('.date-picker').datepicker({
            autoclose: true,
            todayHighlight: true
        });

        var validator = $("#form").setValid({
            //校验规则
            rules: {
                xm: {
                    required: true
                },
                gender: {
                    required: true
                },
                hzhm: {
                    required: true
                },
                gj: {
                    required: true
                },
                csdd: {
                    required: true
                },
                qfdd: {
                    required: true
                },
                fzjg: {
                    required: true
                },
                hzlx: {
                    required: true
                },
                jcsj: {
                    required: true
                },
                qzsj: {
                    required: true
                },
                cfsj: {
                    required: true
                },
                cfmd: {
                    required: true
                },
                cfnr: {
                    required: true
                },
                qzhm: {
                    required: true
                }
            }
        });
        $("#btn-submit").click(function(){
            if(!$("#form").valid()){
                validator.focusInvalid();
                return;
            }
            $.ajax('hzgl/save',{
                type:'post',
                dataType:'json',
                data:$("#form").serialize(),
                success:function(res){
                    if(res && res.success){
                        parent.refreshTable();
                        closeLayer();//关闭
                        winAlert("保存成功");//弹出确认消息
                    }
                }
            });
        });
    })
</script>
</body>
</html>
