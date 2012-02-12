package com.processfast.csce492;

import android.app.Activity;
import android.test.IsolatedContext;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Util {
	public static String getTextViewText(Activity a, int id) {
		String returned = (((TextView) a.findViewById(id)).getText())
				.toString();
		return returned;
	}

	public static Integer getTextViewInteger(Activity a, int id) {
		if ((((TextView) a.findViewById(id)).getText()).toString().length() != 0) {
			Integer returned = Integer
					.parseInt((((TextView) a.findViewById(id)).getText())
							.toString());
			return returned;
		}
		return -1;
	}
}
