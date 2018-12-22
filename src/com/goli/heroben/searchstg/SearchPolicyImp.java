package com.goli.heroben.searchstg;

import java.util.ArrayList;
import java.util.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.goli.heroben.vo.TaskBean;



public class SearchPolicyImp implements ISearchPolicy{
	public int appearNumber(String srcText, String findText) {
		int count = 0;
		int index = 0;
		while ((index = srcText.indexOf(findText, index)) != -1) {
			index = index + findText.length();
			count++;
		}
		if (count == 0) {
			char src[] = srcText.toCharArray();
			char find[] = findText.toCharArray();
			int src_len = src.length;
			int find_len = find.length;
			if (src_len < find_len) {
				if (levenshtein(srcText, findText) > 0.4)
					count++;
			} else {
				int baseLenth = 0;
				while ((baseLenth + find_len) <= src_len) {
					char com[] = new char[find_len];
					System.arraycopy(src, baseLenth, com, 0, find_len);
					String n = String.valueOf(com);
					if (levenshtein(findText, n) > 0.4) {
						count++;
					}
					baseLenth++;
				}
			}
		}
		return count;
	}
	
	public double levenshtein(String str1, String str2) {
		// 计算两个字符串的长度。
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		int len1 = c1.length;
		int len2 = c2.length;
		// 建立上面说的数组，比字符长度大一个空间
		int dif[][] = new int[len1 + 1][len2 + 1];
		// 赋初值，步骤B。
		for (int a = 0; a <= len1; a++) {
			dif[a][0] = a;
		}
		for (int a = 0; a <= len2; a++) {
			dif[0][a] = a;
		}
		// 计算两个字符是否一样，计算左上的值
		int temp;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (c1[i - 1] == c2[j - 1]) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 取三个值中最小的
				dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1, dif[i - 1][j] + 1);
			}
		}

		// 计算相似度
		double similarity = 1 - (double) dif[len1][len2] / Math.max(str1.length(), str2.length());
		return similarity;
	}
	
	public int min(int... arr) {
		int min = Integer.MAX_VALUE;
		for (int i : arr) {
			if (min > i) {
				min = i;
			}
		}
		return min;
	}

	public int max(int a1, int a2) {
		if (a1 > a2)
			return a1;
		else
			return a2;
	}

	@Override
	public List<TaskBean> search(List<TaskBean> list, String key) throws Exception {
		List<TaskBean> result = new ArrayList<TaskBean>();
		Iterator<TaskBean> it = list.iterator();
		TaskBean task = null;
		double grade;
		while(it.hasNext()){
			task = it.next();
			grade = 87.625 * appearNumber(task.getTaskType(), key) + 9.23 * appearNumber(task.getTitle(), key)
					+ appearNumber(task.getContent(), key);
			task.setScore(grade);
			result.add(task);
		}
		Collections.sort(result, new Comparator<TaskBean>() {
			@Override
			public int compare(TaskBean o1, TaskBean o2) {
				// TODO Auto-generated method stub
				return (int)(o2.getScore()-o1.getScore());
			}
		});
		Iterator<TaskBean> tas = result.iterator();
		while(tas.hasNext()){
			if(tas.next().getScore() == 0){
				tas.remove();
			}
		}
		return result;
	}
}
