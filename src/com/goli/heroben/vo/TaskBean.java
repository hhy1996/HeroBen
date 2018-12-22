package com.goli.heroben.vo;

import java.util.Date;

public class TaskBean {
	String id;
	String pubid;
	String revid;
	Date pubTime;
	Date deadTime;
	Date revTime;
	Date finTime;
	String payType;
	String title;
	String content;
	String imgSrc;
	String cost;
	String taskType;
	double score;
	String state;
	String payId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPubid() {
		return pubid;
	}

	public void setPubid(String pubid) {
		this.pubid = pubid;
	}

	public String getRevid() {
		return revid;
	}

	public void setRevid(String revid) {
		this.revid = revid;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Date getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}

	public Date getRevTime() {
		return revTime;
	}

	public void setRevTime(Date revTime) {
		this.revTime = revTime;
	}

	public Date getFinTime() {
		return finTime;
	}

	public void setFinTime(Date finTime) {
		this.finTime = finTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	@Override
	public String toString() {
		return "TaskBean [id=" + id + ", pubid=" + pubid + ", revid=" + revid + ", pubTime=" + pubTime + ", deadTime="
				+ deadTime + ", revTime=" + revTime + ", finTime=" + finTime + ", payType=" + payType + ", title="
				+ title + ", content=" + content + ", imgSrc=" + imgSrc + ", cost=" + cost + ", taskType=" + taskType
				+ ", score=" + score + ", state=" + state + ", payId=" + payId + "]";
	}
}
