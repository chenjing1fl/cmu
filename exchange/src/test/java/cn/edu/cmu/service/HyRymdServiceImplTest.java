package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.HyRymd;
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
public class HyRymdServiceImplTest extends SpringIOC {

    HyRymdService service;

    @Before
    public  void init(){
        service = ac.getBean(HyRymdService.class);
    }
    //����
    @Test
    public void save() {
        HyRymd hyRymd = new HyRymd(CmuStringUtil.UUID(), "01", "01", "01", "01", "3e0b62966c6347d49729316edfa79fda", "01", null,null);
        boolean success = service.insert(hyRymd);
        System.out.println("HyRymd:"+hyRymd);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "394fb5ed07bd4e08841726eb5e914bf0";
        HyRymd hyRymd = service.queryById(keyId);
        System.out.println(hyRymd);
        Assert.assertTrue(hyRymd!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        HyRymd hyRymdParam = new HyRymd();
        hyRymdParam.setXm("01");
        List<HyRymd> hyRymds = service.list(hyRymdParam);
        for (HyRymd hyRymd : hyRymds ) {
            System.out.println(hyRymd);
        }
        Assert.assertTrue(hyRymds.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "394fb5ed07bd4e08841726eb5e914bf0";
        HyRymd hyRymd = service.queryById(keyId);
        System.out.println("����ǰ:"+hyRymd);
        hyRymd.setXm("02");
        boolean success = service.updateById(hyRymd);
        System.out.println("success:"+success);
        HyRymd newHyRymd = service.queryById(keyId);
        System.out.println("���º�:"+newHyRymd);
        Assert.assertTrue(newHyRymd.getXm().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        HyRymd hyRymd = new HyRymd(keyId, "01", "01", "01", "01", "3e0b62966c6347d49729316edfa79fda", "01", null,null);
        boolean success = service.insert(hyRymd);
        System.out.println("����ɹ���"+success);
        HyRymd queryHyRymd = service.queryById(keyId);
        System.out.println("������ѯ"+queryHyRymd);
        queryHyRymd.setValid("0");
        queryHyRymd.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryHyRymd);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}