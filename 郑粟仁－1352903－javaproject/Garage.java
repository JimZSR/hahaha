package garage;


public abstract class Garage {
	// repairPrice 价值 
	// repairHours 一辆车的维修小时数 
	// availbleHours 小时容量 
	// m 表示只有w[i],w[i+1]...w[n]这些物品时,背包容量为j时的最大价值 
	 
	public static void knapsack(int[] repairPrice, int[] repairHours, int availbleHours, int[][] m) { 

	int n = repairPrice.length - 1; 

	int jMax = Math.min(repairHours[n] - 1, availbleHours); 

	for (int j = 0; j <= jMax; j++) 
	m[n][j] = 0; // 当w[n]>j 有 m[n][j]=0 

	for (int j = repairHours[n]; j <= availbleHours; j++) 
	m[n][j] = repairPrice[n]; 

	for (int i = n - 1; i >= 1; i--) { 
	jMax = Math.min(repairHours[i] - 1, availbleHours); 
	 
	for (int k = 0; k <= jMax; k++) 
	m[i][k] = m[i + 1][k]; 

	for (int h = repairHours[i]; h <= availbleHours; h++) { 
	 
	m[i][h] = Math.max(m[i + 1][h], m[i + 1][h - repairHours[i]] + repairPrice[i]); 
	} 

	} 
	m[0][availbleHours] = m[1][availbleHours]; 
	if (availbleHours >= repairHours[0]) 
	m[0][availbleHours] = Math.max(m[0][availbleHours], m[1][availbleHours - repairHours[0]] + repairPrice[0]); 
	System.out.println("Highest choice value = " + m[0][availbleHours]); 
	} 


	public static void traceback(int[][] m, int[] w, int c, int[] x) {// 根据最优值求出最优解 
	int n = w.length - 1; 
	for (int i = 0; i < n; i++) 
	if (m[i][c] == m[i + 1][c]) 
	x[i] = 0; 
	else { 
	x[i] = 1; 
	c -= w[i]; 
	} 
	x[n] = (m[n][c] > 0) ? 1 : 0; 
	} 

	public static void main(String[] args) { 

	int[] t = { 10,6,3,1 }; //在里面输入修每辆汽车各需要的小时数————手动修改代码输入
	int[] v = { 10,7,5,3 }; //在里面输入修每辆汽车各可以获得的报酬————手动修改代码输入

	int[][] m = new int[4][13];//第一个数字为总待修车辆数，第二个数字为总可利用小时数＋1————手动修改车辆和小时数
	knapsack(v, t, 12, m); //第三个数字为总可利用小时数————手动修改小时数
	int[] xx = new int[t.length]; 
	traceback(m, t, 12, xx); 
	System.out.println("The car you should repair is the number:");//车辆按输入顺序自动编号1，2，3，4……
	for (int i = 0; i < xx.length; i++)  
		if(xx[i]!= 0){
			System.out.println((i+1)+" ");
		}
	} 
} 
	

