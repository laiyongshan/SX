package suncere.shanxi.androidapp.customview.kjchart;

import java.util.List;

public class Plot {

	public List<String> mLines;
	public int mLineColor;//折线的颜色
	public int mPointColor;//点颜色

	public Plot(List<String> mLines,int mLineColor,int mPointColor){
		this.mLines=mLines;
		this.mLineColor=mLineColor;
		this.mPointColor=mPointColor;
	}


}
