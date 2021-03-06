package cn.edu.cmu.service;

import cn.edu.cmu.dao.*;
import cn.edu.cmu.domain.*;
import cn.edu.cmu.framework.CmuConstants;
import cn.edu.cmu.framework.util.CmuStringUtil;
import cn.edu.cmu.framework.util.DateUtils;
import cn.edu.cmu.framework.util.MaxNumUtils;
import cn.edu.cmu.framework.web.BaseService;
import cn.edu.cmu.vo.XmVo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class XmServiceImpl extends BaseService<Xm, XmParams, XmMapper> implements XmService {

    @Autowired
    XmMapperExt daoExt;

    @Autowired
    ZyMapper zyDao;

    @Autowired
    XmGjdqMapper gjDao;

    @Autowired
    BksXjjbsjxxMapper bksXjDao;

    @Autowired
    YjsXjjbsjxxMapper yjsXjDao;

    @Autowired
    BjsjxxMapper bjDao;//班级dao

    @Autowired
    UnicUnitMapper unitDao;//院系机构

    @Autowired
    BksZyxxsjxxMapper bksZyDao;//本科生专业

    @Autowired
    YjsZyxxsjxxMapper yjsZyDao;//研究生专业

    @Autowired
    XmXssqjlMapper sqDao;


    @Autowired
    XmXssbfjMapper xmfjDao; //学生申请项目上传附件

    @Override
    public List list(Xm Xm) {
        XmParams ex = new XmParams();
        if(Xm != null){
            XmParams.Criteria c = ex.createCriteria();
            if(StringUtils.isNotEmpty(Xm.getXmmc())){
                c.andXmmcLike("%"+Xm.getXmmc()+"%");
            }
        }
        return dao.selectByExample(ex);
    }

    @Override
    public List list(Object... conditions) throws Exception {
        XmParams params = new XmParams();
        XmParams.Criteria c1 = params.createCriteria();
        XmParams.Criteria c2 = params.or();
        if (conditions != null && conditions.length > 0 && conditions[0] != null) {
            Xm xm = (Xm) conditions[0];

            if (StringUtils.isNotEmpty(xm.getXmmc())) {
                c1.andXmmcLike("%" + xm.getXmmc() + "%");
                c1.andValidEqualTo("1");
            }
            if(StringUtils.isNotEmpty(xm.getXmzm())){
                c2.andXmzmLike("%" + xm.getXmzm() + "%");
            }
            super.addOrderBy(params, conditions);
        }

        return dao.selectByExample(params);
    }

    @Override
    public boolean save(XmVo xmVo, HttpSession session) throws Exception {

        boolean success = false;
        boolean isEdit = false;
        Xm xm = xmVo.getXm();
        String[] gbs = xmVo.getGbs();

        if(StringUtils.isEmpty(xm.getXmId())){
            //年度+90+4位流水号
            String xmbh = DateUtils.getYear() + "90" + MaxNumUtils.getMaxNum("xm",  DateUtils.getYear(), 4);
            String operatorCode = (String) session.getAttribute(CmuConstants.SESSION.USER_ID);
            logger.info("保存项目，生成项目编号,操作工号"+operatorCode+",项目编号："+xmbh);
            xm.setXmId(CmuStringUtil.UUID());
            xm.setXmbh(xmbh);
            xm.setOperatorCode(operatorCode);
        }else{
            isEdit = true;
        }

        if(isEdit){
            int count = dao.updateByPrimaryKeySelective(xm);
            success = count>0;
        }else{
            int count = dao.insertSelective(xm);
            success = count>0;
        }

        //处理从表(先删 后插)
        //国家或地区
        XmGjdqParams param = new XmGjdqParams();
        param.createCriteria().andXmidEqualTo(xm.getXmId());
        gjDao.deleteByExample(param);

        if(ArrayUtils.isNotEmpty(gbs)){
            for (String gjdm : gbs) {
                XmGjdq gj = new XmGjdq(CmuStringUtil.UUID(), gjdm, xm.getXmId(), null, null);
                gjDao.insertSelective(gj);
            }
        }

        return success;
    }

    @Override
    public List listMcDistinct() {
        return daoExt.selectXmmcDistinct();
    }

    @Override
    public List listNjDistinct() {
        return daoExt.selectNjxzDistinct();
    }


    @Override
    public List<Map> listZy(){
        return zyDao.selectZyListAll();
    }

    @Override
    public List listSqXm(Xm xm, HttpSession session) throws Exception {
        Map map = new HashMap();
        String userType = (String) session.getAttribute(CmuConstants.SESSION.USER_TYPE);
        if(!CmuConstants.SESSION.USER_TYPE_BKS.equals(userType) && !CmuConstants.SESSION.USER_TYPE_YJS.equals(userType) ){
            throw new Exception("非学生登录..");
        }

        String xh = (String) session.getAttribute(CmuConstants.SESSION.USER_ID);
        String yxsh = (String) session.getAttribute(CmuConstants.SESSION.USER_DEPT);

        map.put("gsyxdm",yxsh);
        map.put("gsxsdm",xh);
        map.put("xm",xm);

        return daoExt.selectSqxm(map);
    }

    /**
     * 初始化学生申报页面
     * @param session
     * @return
     */
    @Override
    public XmXssqjl initSqPage(HttpSession session) {
        XmXssqjl sqjl = new XmXssqjl();

        String xh = (String)session.getAttribute(CmuConstants.SESSION.USER_ID);
        String userType = (String)session.getAttribute(CmuConstants.SESSION.USER_TYPE);


        if(userType.equals(CmuConstants.SESSION.USER_TYPE_BKS)){

            //学生信息
            BksXsjbsjxx bks = (BksXsjbsjxx)session.getAttribute(CmuConstants.SESSION.USER_INFO_BKS);

            //学籍信息
            BksXjjbsjxxParams xjjbsjxxParams = new BksXjjbsjxxParams();
            xjjbsjxxParams.createCriteria().andXhEqualTo(xh);
            List xjList = bksXjDao.selectByExample(xjjbsjxxParams);
            BksXjjbsjxx xjDto = (BksXjjbsjxx) xjList.get(0);

            sqjl.setXh(xh);
            sqjl.setXm(bks.getXm());
            sqjl.setGender(bks.getXbm());
            //入学年份
            sqjl.setRxn(xjDto.getRxny());
            //年级:
            sqjl.setNj(xjDto.getSznj());

            //院系:
            sqjl.setYxdm(xjDto.getYxsh()); //院系统一转码
            //班级:
            sqjl.setBjh(xjDto.getSzbh());   //院系统一转码
            //专业 :
            sqjl.setZyh(xjDto.getZym());

            BksZyxxsjxxParams zyParam = new BksZyxxsjxxParams();
            BksZyxxsjxxParams.Criteria zyCriteria = zyParam.createCriteria();
            zyCriteria.andZyhEqualTo(sqjl.getZyh());
            zyCriteria.andDwhEqualTo(sqjl.getYxdm());//单位好
            List zyList = bksZyDao.selectByExample(zyParam);
            BksZyxxsjxx bksZy = (BksZyxxsjxx) zyList.get(0);
            sqjl.setZymc(bksZy.getZymc());





            //手机号 没有
            // 邮箱  没有



        }else if(userType.equals(CmuConstants.SESSION.USER_TYPE_YJS)){

            //学生信息
            YjsXsjbsjxx yjs = (YjsXsjbsjxx)session.getAttribute(CmuConstants.SESSION.USER_INFO_YJS);
            //学籍信息
            YjsXjjbsjxxParams xjjbsjxxParams = new YjsXjjbsjxxParams();
            xjjbsjxxParams.createCriteria().andXhEqualTo(xh);
            List xjList = yjsXjDao.selectByExample(xjjbsjxxParams);
            YjsXjjbsjxx xjDto = (YjsXjjbsjxx) xjList.get(0);


            sqjl.setXh(xh);
            sqjl.setXm(yjs.getXm());
            sqjl.setGender(yjs.getXbm());
            //入学年份
            sqjl.setRxn(xjDto.getRxny());
            //年级:
            sqjl.setNj(xjDto.getSznj());

            //院系:
            sqjl.setYxdm(xjDto.getYxsh()); //院系统一转码
            //班级:
            sqjl.setBjh(xjDto.getSzbh());   //院系统一转码

            //专业 :
            sqjl.setZyh(xjDto.getZym());


            //手机号 没有
            // 邮箱  没有

            YjsZyxxsjxxParams zyParam = new YjsZyxxsjxxParams();
            YjsZyxxsjxxParams.Criteria zyCriteria = zyParam.createCriteria();
            zyCriteria.andZyhEqualTo(sqjl.getZyh());
            zyCriteria.andDwhEqualTo(sqjl.getYxdm());//单位好
            List zyList = yjsZyDao.selectByExample(zyParam);
            YjsZyxxsjxx yjsZy = (YjsZyxxsjxx) zyList.get(0);
            sqjl.setZymc(yjsZy.getZymc());

        }


        UnicUnitParams unitParams = new UnicUnitParams();
        UnicUnitParams.Criteria unitCriteria = unitParams.createCriteria();
        unitCriteria.andUnitIdEqualTo(sqjl.getYxdm());//院系代码
        List unitList = unitDao.selectByExample(unitParams);
        UnicUnit unit = (UnicUnit) unitList.get(0);
        //院系名称
        sqjl.setYxmc(unit.getName());

        if(StringUtils.isNotEmpty(sqjl.getBjh())){

            BjsjxxParams bjsjxxParams = new BjsjxxParams();
            BjsjxxParams.Criteria bjCriteria = bjsjxxParams.createCriteria();
            bjCriteria.andBhEqualTo(sqjl.getBjh());
            List bjList = bjDao.selectByExample(bjsjxxParams);
            Bjsjxx bj = (Bjsjxx) bjList.get(0);
            //班级名称:
            if(StringUtils.isEmpty(bj.getBj())){//研究生不是单独班级表，
                sqjl.setBjmc(sqjl.getBjh());   //研究生的班级号就是班级名称
            }else{
                sqjl.setBjmc(bj.getBj());
            }
        }


        return sqjl;
    }

    @Override
    public List<Map> initGjdq(String xmid) {
        List<Map> list = daoExt.selectXmGb(xmid);
        for (Map<String,String> map : list) {
            System.out.println(map);
            String code = map.get("code");
            String name = map.get("name");
            map.put(code,name);
            map.remove("code");
            map.remove("name");
        }

        return list;
    }

    @Override
    public boolean saveSq(XmXssqjl jl, String[] fileid, String[] clsm) {
        boolean success = false;
        boolean isEdit = false;
        if(StringUtils.isEmpty(jl.getSqjlId())){
            jl.setSqjlId(CmuStringUtil.UUID());

        }else{
            isEdit = true;
        }

        if(isEdit){
            int count = sqDao.updateByPrimaryKeySelective(jl);
            success = count>0;
        }else{
            int count = sqDao.insertSelective(jl);
            success = count>0;
        }


        //从表上传材料
        XmXssbfjParams fjParam = new XmXssbfjParams();

        XmXssbfjParams.Criteria fjCriteria = fjParam.createCriteria();
        fjCriteria.andSqjlIdEqualTo(jl.getSqjlId());
        xmfjDao.deleteByExample(fjParam);
        XmXssbfj fj = null;
        for (int i = 0; i < fileid.length; i++) {
            fj = new XmXssbfj(CmuStringUtil.UUID(),jl.getSqjlId(), fileid[i], clsm[i],null,null);
            xmfjDao.insertSelective(fj);//添加附件
        }

        return success;
    }
}