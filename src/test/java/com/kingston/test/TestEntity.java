package com.kingston.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

import com.kingston.orm.OrmProcessor;
import com.kingston.orm.entity.Platform;
import com.kingston.orm.entity.Player;
import com.kingston.orm.utils.DbUtils;


public class TestEntity {

	@BeforeClass
	public static void init() {
		OrmProcessor.INSTANCE.initOrmBridges();
		try {
			// 读取配置文件并创建数据库连接池
			JAXPConfigurator.configure("proxool.xml", true);
		} catch (ProxoolException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	@Test
	public void testQuery() {
		Connection conn = DbUtils.getConnection("proxool.user");
		Player player = DbUtils.queryOne(conn, "select * from player where id=1" , Player.class);
		assertTrue(player.getName().equals("kingston"));
	}

	@Test
	public void testUpdate() {
		Connection conn = DbUtils.getConnection("proxool.user");
		Player player = DbUtils.queryOne(conn, "select * from player where id=1" , Player.class);
		player.setName("Hello");
		player.setUpdate();

		conn = DbUtils.getConnection("proxool.user");
		DbUtils.executeSql(conn, player.getSaveSql());
		player.resetDbStatus();

		//check
		conn = DbUtils.getConnection("proxool.user");
		Player tmpPlayer = DbUtils.queryOne(conn, "select * from player where id=1" , Player.class);
		assertTrue(tmpPlayer.getName().equals("Hello"));

		//rollback
		player.setName("kingston");
		player.setUpdate();

		conn = DbUtils.getConnection("proxool.user");
		DbUtils.executeSql(conn, player.getSaveSql());
		player.resetDbStatus();
	}

	@Test
	public void testInsert() {
		Connection conn = DbUtils.getConnection("proxool.user");
		Player player = new Player();
		player.setNo(666);
		player.setName("younger");
		player.setPlatform(Platform.IOS);
		player.setInsert();

		conn = DbUtils.getConnection("proxool.user");
		DbUtils.executeSql(conn, player.getSaveSql());
		player.resetDbStatus();

		//check
		conn = DbUtils.getConnection("proxool.user");
		Player tmpPlayer = DbUtils.queryOne(conn, "select * from player where id=" + player.getNo() , Player.class);
		assertTrue(tmpPlayer.getName().equals("younger"));

		//rollback
		conn = DbUtils.getConnection("proxool.user");
		player.setDelete();
		DbUtils.executeSql(conn, player.getSaveSql());
		player.resetDbStatus();
	}

	@Test
	public void testDelete() {
		Connection conn = DbUtils.getConnection("proxool.user");
		Player player = DbUtils.queryOne(conn, "select * from player where id=1" , Player.class);
		player.setDelete();
		
		conn = DbUtils.getConnection("proxool.user");
		DbUtils.executeSql(conn, player.getSaveSql());
		player.resetDbStatus();

		//check
		conn = DbUtils.getConnection("proxool.user");
		Player tmpPlayer = DbUtils.queryOne(conn, "select * from player where id=" + player.getNo() , Player.class);
		assertTrue(tmpPlayer == null);

		//rollback
		conn = DbUtils.getConnection("proxool.user");
		player.setInsert();
		DbUtils.executeSql(conn, player.getSaveSql());
		player.resetDbStatus();
	}

}
