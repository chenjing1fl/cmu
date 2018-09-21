package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.Xm;
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
public class XmServiceImplTest extends SpringIOC {

    XmService service;

    @Before
    public  void init(){
        service = ac.getBean(XmService.class);
    }
    //����
    @Test
    public void save() {
        Xm xm = new Xm(CmuStringUtil.UUID(),"01","01","01","01","01",
                new Date(),new Date(), BigDecimal.valueOf(1.34), "01","01","01","01","01","01","01", BigDecimal.valueOf(1.34),"01",
                "01", BigDecimal.valueOf(1.34),"01","01","01","01", null,null);
        boolean success = service.insert(xm);
        System.out.println("Xm:"+xm);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "6ae324e03fd84ace9497cbfedc908a0b";
        Xm xm = service.queryById(keyId);
        System.out.println(xm);
        Assert.assertTrue(xm!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        Xm xmParam = new Xm();
        xmParam.setXmmc("0");
        List<Xm> xms = service.list(xmParam);
        for (Xm xm : xms ) {
            System.out.println(xm);
        }
        Assert.assertTrue(xms.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "6ae324e03fd84ace9497cbfedc908a0b";
        Xm xm = service.queryById(keyId);
        System.out.println("����ǰ:"+xm);
        xm.setXmmc("02");
        boolean success = service.updateById(xm);
        System.out.println("success:"+success);
        Xm newXm = service.queryById(keyId);
        System.out.println("���º�:"+newXm);
        Assert.assertTrue(newXm.getXmmc().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        Xm xm = new Xm(keyId,"01","01","01","01","01",
                new Date(),new Date(), BigDecimal.valueOf(1.34), "01","01","01","01","01","01","01", BigDecimal.valueOf(1.34),"01",
                "01", BigDecimal.valueOf(1.34),"01","01","01","01", null,null);
        boolean success = service.insert(xm);
        System.out.println("����ɹ���"+success);
        Xm queryXm = service.queryById(keyId);
        System.out.println("������ѯ"+queryXm);
        queryXm.setValid("0");
        queryXm.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryXm);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}