package com.alcord.utility;

import java.util.List;

public class ListUtility {

	public static Boolean isEmpty(List list) {
		if (list == null || list.size() == 0) {
			return true;
		}
		return false;
	}
}
