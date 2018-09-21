package cn.edu.cmu.service;

import cn.edu.cmu.base.SpringIOC;
import cn.edu.cmu.domain.HzxyQzr;
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
public class HzxyQzrServiceImplTest extends SpringIOC {

    HzxyQzrService service;

    @Before
    public  void init(){
        service = ac.getBean(HzxyQzrService.class);
    }
    //����
    @Test
    public void save() {
        HzxyQzr hzxyQzr = new HzxyQzr(CmuStringUtil.UUID(),"01", "611eece2fe8244fa8e1729e9a8d3417a","01",null,null);
        boolean success = service.insert(hzxyQzr);
        System.out.println("HzxyQzr:"+hzxyQzr);
        System.out.println("success:"+success);
        Assert.assertTrue(success);
    }
    //����������ѯ
    @Test
    public void selectByPrimaryKey() {
        String keyId = "8485978a68b84453949fd18561e9a22f";
        HzxyQzr hzxyQzr = service.queryById(keyId);
        System.out.println(hzxyQzr);
        Assert.assertTrue(hzxyQzr!=null);
    }
    //����������ѯ
    @Test
    public void list() {
        HzxyQzr hzxyQzrParam = new HzxyQzr();
        hzxyQzrParam.setXm("01");
        List<HzxyQzr> hzxyQzrs = service.list(hzxyQzrParam);
        for (HzxyQzr hzxyQzr : hzxyQzrs ) {
            System.out.println(hzxyQzr);
        }
        Assert.assertTrue(hzxyQzrs.size()>0);
    }
    //����
    @Test
    public void update() {
        String keyId = "8485978a68b84453949fd18561e9a22f";
        HzxyQzr hzxyQzr = service.queryById(keyId);
        System.out.println("����ǰ:"+hzxyQzr);
        hzxyQzr.setXm("02");
        boolean success = service.updateById(hzxyQzr);
        System.out.println("success:"+success);
        HzxyQzr newHzxyQzr = service.queryById(keyId);
        System.out.println("���º�:"+newHzxyQzr);
        Assert.assertTrue(newHzxyQzr.getXm().contains("02"));
    }
    //��������ɾ��
    @Test
    public void deleteById() {
        String keyId = CmuStringUtil.UUID();
        HzxyQzr hzxyQzr = new HzxyQzr(keyId,"01", "611eece2fe8244fa8e1729e9a8d3417a","01",null,null);
        boolean success = service.insert(hzxyQzr);
        System.out.println("����ɹ���"+success);
        HzxyQzr queryHzxyQzr = service.queryById(keyId);
        System.out.println("������ѯ"+queryHzxyQzr);
        queryHzxyQzr.setValid("0");
        queryHzxyQzr.setCreateTime(new Date());
        success = service.updateByIdAllColumn(queryHzxyQzr);
        System.out.println("ɾ���ɹ���"+success);
        Assert.assertTrue(success);
    }
}