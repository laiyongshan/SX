package suncere.shanxi.androidapp.customview.kjchart;


public enum ChartEnum {
	LineChart(0),
	BarChart(1),
	PercentageBaChart(2),
	BarChart_v2(3);
	ChartEnum(int value)
	{
		this.value=value;
	}
	public int Value()
	{
		return value;
	}
	private int value;
}

