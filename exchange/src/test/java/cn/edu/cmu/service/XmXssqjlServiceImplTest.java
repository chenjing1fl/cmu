package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.XmXssqjl;
import cn.edu.cmu.domain.User;
import cn.edu.cmu.framework.utils.CmuStringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Service��Ĳ�����
 *
 * ����ɾ�Ĳ�
 */
public class XmXssqjlServiceImplTest extends SpringIOC {

    XmXssqjlService service;

    @Before
    public  void init(){
        service = ac.getBean(XmXssqjlService.class);
    }
    //����
    @Test
    public void save() {
        XmXssqjl xmXssqjl = new XmXssqjl(CmuStringUtil.UUID(),"01", "01","01","01","01","01","01","01","01","01","01","01","01","01","01", BigDecimal.valueOf(1.34),
                "01","01","01","01", null,null);
        boolean success = service.insert(xmXssqjl);
        System.out.println("XmXssqjl:"+xmXssqjl);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "03d424b544914747969656add5b104a7";
        XmXssqjl xmXssqjl = service.queryById(keyId);
        System.out.println(xmXssqjl);
        Assert.assertTrue(xmXssqjl!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        XmXssqjl xmXssqjlParam = new XmXssqjl();
        xmXssqjlParam.setXm("0");
        List<XmXssqjl> xmXssqjls = service.list(xmXssqjlParam);
        for (XmXssqjl xmXssqjl : xmXssqjls ) {
            System.out.println(xmXssqjl);
        }
        Assert.assertTrue(xmXssqjls.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "03d424b544914747969656add5b104a7";
        XmXssqjl xmXssqjl = service.queryById(keyId);
        System.out.println("����ǰ:"+xmXssqjl);
        xmXssqjl.setXm("02");
        boolean success = service.updateById(xmXssqjl);
        System.out.println("success:"+success);
        XmXssqjl newXmXssqjl = service.queryById(keyId);
        System.out.println("���º�:"+newXmXssqjl);
        Assert.assertTrue(newXmXssqjl.getXm().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        XmXssqjl xmXssqjl = new XmXssqjl(keyId,"01", "01","01","01","01","01","01","01","01","01","01","01","01","01","01", BigDecimal.valueOf(1.34),
                "01","01","01","01", null,null);
        boolean success = service.insert(xmXssqjl);
        System.out.println("����ɹ���"+success);
        XmXssqjl queryXmXssqjl = service.queryById(keyId);
        System.out.println("������ѯ"+queryXmXssqjl);
        queryXmXssqjl.setValid("0");
        queryXmXssqjl.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryXmXssqjl);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}