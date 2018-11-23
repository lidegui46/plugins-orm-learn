package com.kingston.test;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

import com.kingston.orm.OrmProcessor;
import com.kingston.orm.utils.DbUtils;

public class TestDbUtils {

	@BeforeClass
	public static void init() {
		//初始化orm框架
		OrmProcessor.INSTANCE.initOrmBridges();
		try {
			// 读取配置文件并创建数据库连接池
			JAXPConfigurator.configure("proxool.xml", true);
		} catch (ProxoolException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void testQueryOne() {

	}

	@Test
	public void testQuerMap() {
		Connection conn = DbUtils.getConnection("proxool.user");
		Map<String, Object> result = DbUtils.queryMap(conn, "select p.id as pid, p.`name` as pname, h.level as hlevel from player p, house h where p.id=1");
		System.err.println(result);
	}

	@Test
	public void testQuerMapList() {
		Connection conn = DbUtils.getConnection("proxool.user");
		List<Map<String, Object>> result = DbUtils.queryMapList(conn, "select * from player ");
		System.err.println(result);
	}

}
