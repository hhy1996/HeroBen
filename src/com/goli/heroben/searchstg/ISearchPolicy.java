package com.goli.heroben.searchstg;

import java.util.List;

import com.goli.heroben.vo.TaskBean;

public interface ISearchPolicy {
	public List<TaskBean> search(List<TaskBean>list, String key) throws Exception; 
}
