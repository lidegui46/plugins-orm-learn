package com.kingston.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.kingston.orm.cache.AbstractCacheable;

@Entity(name="player")
public class Player extends AbstractCacheable {

	@Column(name="id")
	@Id
	private long no;
	@Column
	private String name;
	@Column
	private Platform platform;
	
	public long getNo() {
		return no;
	}

	public void setNo(long id) {
		this.no = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return "Player [no=" + no + ", name=" + name + ", platform=" + platform + "]";
	}
	
}
