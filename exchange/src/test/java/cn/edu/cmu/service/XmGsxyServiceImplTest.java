package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.XmGsxy;
import cn.edu.cmu.domain.User;
import cn.edu.cmu.framework.utils.CmuStringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;


/**
 * Service��Ĳ�����
 *
 * ����ɾ�Ĳ�
 */
public class XmGsxyServiceImplTest extends SpringIOC {

    XmGsxyService service;

    @Before
    public  void init(){
        service = ac.getBean(XmGsxyService.class);
    }
    //����
    @Test
    public void save() {
        XmGsxy xmGsxy = new XmGsxy(CmuStringUtil.UUID(),"01","01","01","01",null,null);
        boolean success = service.insert(xmGsxy);
        System.out.println("XmGsxy:"+xmGsxy);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "db3d9d9ab6084ebd853d859a7c0f5f36";
        XmGsxy xmGsxy = service.queryById(keyId);
        System.out.println(xmGsxy);
        Assert.assertTrue(xmGsxy!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        XmGsxy xmGsxyParam = new XmGsxy();
        xmGsxyParam.setXymc("0");
        List<XmGsxy> xmGsxys = service.list(xmGsxyParam);
        for (XmGsxy xmGsxy : xmGsxys ) {
            System.out.println(xmGsxy);
        }
        Assert.assertTrue(xmGsxys.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "db3d9d9ab6084ebd853d859a7c0f5f36";
        XmGsxy xmGsxy = service.queryById(keyId);
        System.out.println("����ǰ:"+xmGsxy);
        xmGsxy.setXymc("02");
        boolean success = service.updateById(xmGsxy);
        System.out.println("success:"+success);
        XmGsxy newXmGsxy = service.queryById(keyId);
        System.out.println("���º�:"+newXmGsxy);
        Assert.assertTrue(newXmGsxy.getXymc().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        XmGsxy xmGsxy = new XmGsxy(keyId,"01","01","01","01",null,null);
        boolean success = service.insert(xmGsxy);
        System.out.println("����ɹ���"+success);
        XmGsxy queryXmGsxy = service.queryById(keyId);
        System.out.println("������ѯ"+queryXmGsxy);
        queryXmGsxy.setValid("0");
        queryXmGsxy.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryXmGsxy);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}