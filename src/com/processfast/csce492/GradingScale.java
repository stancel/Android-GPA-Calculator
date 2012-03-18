package com.processfast.csce492;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class GradingScale implements Parcelable {
	private int a;
	private int a_minus;

	private int b_plus;
	private int b;
	private int b_minus;

	private int c_plus;
	private int c;
	private int c_minus;

	private int d_plus;
	private int d;
	private int d_minus;

	public GradingScale() {
		this.a = -1;
		this.a_minus = -1;
		this.b_plus = -1;
		this.b = -1;
		this.b_minus = -1;
		this.c_plus = -1;
		this.c = -1;
		this.c_minus = -1;
		this.d_plus = -1;
		this.d = -1;
		this.d_minus = -1;
	}

	public GradingScale(Parcel source) {
		a = source.readInt();
		a_minus = source.readInt();
		
		b_plus = source.readInt();
		b = source.readInt();
		b_minus = source.readInt();
		
		c_plus = source.readInt();
		c = source.readInt();
		c_minus = source.readInt();

		d_plus = source.readInt();
		d = source.readInt();
		d_minus = source.readInt();
		

	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getA_minus() {
		return a_minus;
	}

	public void setA_minus(int a_minus) {
		this.a_minus = a_minus;
	}

	public int getB_plus() {
		return b_plus;
	}

	public void setB_plus(int b_plus) {
		this.b_plus = b_plus;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getB_minus() {
		return b_minus;
	}

	public void setB_minus(int b_minus) {
		this.b_minus = b_minus;
	}

	public int getC_plus() {
		return c_plus;
	}

	public void setC_plus(int c_plus) {
		this.c_plus = c_plus;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getC_minus() {
		return c_minus;
	}

	public void setC_minus(int c_minus) {
		this.c_minus = c_minus;
	}

	public int getD_plus() {
		return d_plus;
	}

	public void setD_plus(int d_plus) {
		this.d_plus = d_plus;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getD_minus() {
		return d_minus;
	}

	public void setD_minus(int d_minus) {
		this.d_minus = d_minus;
	}

	public boolean isValidScale() {
		ArrayList<Integer> scale = new ArrayList<Integer>();
		scale.clear();
		if (a < 4 || b < 3 || c < 2 || d < 1) {
			return false;
		}
		scale.add(a);
		if (a_minus > 0)
			scale.add(a_minus);
		if (b_plus > 0)
			scale.add(b_plus);
		scale.add(b);
		if (b_minus > 0)
			scale.add(b_minus);
		if (c_plus > 0)
			scale.add(c_plus);
		scale.add(c);
		if (c_minus > 0)
			scale.add(c_minus);
		if (d_plus > 0)
			scale.add(d_plus);
		scale.add(d);
		if (d_minus > 0)
			scale.add(d_minus);

		for (int i = 1; i < scale.size(); i++) {
			if (scale.get(i) > scale.get(i - 1)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(a);
		dest.writeInt(a_minus);
		dest.writeInt(b_plus);
		dest.writeInt(b);
		dest.writeInt(b_minus);
		dest.writeInt(c_plus);
		dest.writeInt(c);
		dest.writeInt(c_minus);
		dest.writeInt(d_plus);
		dest.writeInt(d);
		dest.writeInt(d_minus);

	}

	public static final Parcelable.Creator<GradingScale> CREATOR = new Parcelable.Creator<GradingScale>() {

		@Override
		public GradingScale createFromParcel(Parcel source) {
			return new GradingScale(source);
		}

		@Override
		public GradingScale[] newArray(int size) {
			return new GradingScale[size];
		}

	};
	
	public String getLetterGrade(double e){
		
		BigDecimal trimmedGrade = new BigDecimal(e);
		trimmedGrade = trimmedGrade.setScale(2, BigDecimal.ROUND_UP);
		e = trimmedGrade.doubleValue();
		if(e >= a){
			return "A";
		}
		else if(a_minus > 0 && e >= a_minus){
			return "A-";
		}
		else if(b_plus > 0 && e >= b_plus){
			return "B+";
		}
		else if(e >= b){
			return "B";
		}
		else if(b_minus > 0 && e >= b_minus){
			return "B-";
		}
		else if(c_plus > 0 && e >= c_plus){
			return "C+";
		}
		else if(e >= c){
			return "C";
		}
		else if(c_minus > 0 && e >= c_minus){
			return "C-";
		}
		else if(d_plus > 0 && e >= d_plus){
			return "D+";
		}
		else if(e >= d){
			return "D";
		}
		else if(d_minus > 0 && e >= d_minus){
			return "D-";
		}
		else return "F";
	}
}
