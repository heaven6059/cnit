package com.cnit.yoyo.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.cnit.yoyo.dto.TreeNode;

public class TreeUtils {
	public static List<TreeNode> toTreeNode(List<TreeNode> trees) {
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		for (TreeNode node1 : trees) {
			boolean mark = false;
			for (TreeNode node2 : trees) {
				if (node1.getpId() != null&& node1.getpId().equals(node2.getId())) {
					mark = true;
					if (node2.getChildren() == null)
						node2.setChildren(new ArrayList<TreeNode>());
					node2.getChildren().add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		// 转为json格式
		String json = JSONArray.fromObject(nodeList).toString();
		System.out.println("json:" + json);
		return nodeList;
	}
}
