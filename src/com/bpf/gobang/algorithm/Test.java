package com.bpf.gobang.algorithm;

public class Test {
	private int i, j, k, m, n;  
	private int[][] board = new int [16][16];  
	private boolean[][][] ptable = new boolean[16][16][672];  
	private boolean[][][] ctable = new boolean[16][16][672];  
	private int[][] cgrades = new int[16][16];  
	private int[][] pgrades = new int[16][16];  
	private int cgrade,pgrade;  
	private int[][] win = new int[2][672];  
	private int pcount,ccount;  
	private boolean player,computer,over,pwin,cwin,tie,start;  
	private int mat,nat,mde,nde;  

	public void ComTurn(){     //找出电脑（白子）最佳落子点  
		for(i=0;i<=15;i++)     //遍历棋盘上的所有坐标  
			for(j=0;j<=15;j++){     
				this.pgrades[i][j]=0;  //该坐标的黑子奖励积分清零  
				if(this.board[i][j] == 2)  //在还没下棋子的地方遍历  
					for(k=0;k<672;k++)    //遍历该棋盘可落子点上的黑子所有权值的连子情况，并给该落子点加上相应奖励分  
						if(this.ptable[i][j][k]){  
							switch(this.win[0][k]){     
							case 1: //一连子  
								this.pgrades[i][j]+=5;  
								break;  
							case 2: //两连子  
								this.pgrades[i][j]+=50;  
								break;  
							case 3: //三连子  
								this.pgrades[i][j]+=180;  
								break;  
							case 4: //四连子  
								this.pgrades[i][j]+=400;  
								break;  
							}  
						}  
				this.cgrades[i][j]=0;//该坐标的白子的奖励积分清零  
				if(this.board[i][j] == 2)  //在还没下棋子的地方遍历  
					for(k=0;k<672;k++)     //遍历该棋盘可落子点上的白子所有权值的连子情况，并给该落子点加上相应奖励分  
						if(this.ctable[i][j][k]){  
							switch(this.win[1][k]){    
							case 1:  //一连子  
								this.cgrades[i][j]+=5;  
								break;  
							case 2:  //两连子  
								this.cgrades[i][j]+=52;  
								break;  
							case 3: //三连子  
								this.cgrades[i][j]+=100;  
								break;  
							case 4:  //四连子  
								this.cgrades[i][j]+=400;  
								break;  
							}  
						}  
			}  
		if(this.start){      //开始时白子落子坐标  
			if(this.board[4][4]==2){  
				m = 4;  
				n = 4;  
			}else{  
				m = 5;  
				n = 5;  
			}  
			this.start = false;  
		}else{  
			for(i=0;i<16;i++)  
				for(j=0;j<16;j++)  
					if(this.board[i][j] == 2){  //找出棋盘上可落子点的黑子白子的各自最大权值，找出各自的最佳落子点  
						if(this.cgrades[i][j]>=this.cgrade){  
							this.cgrade = this.cgrades[i][j];     
							this.mat = i;  
							this.nat = j;  
						}  
						if(this.pgrades[i][j]>=this.pgrade){  
							this.pgrade = this.pgrades[i][j];     
							this.mde = i;  
							this.nde = j;  
						}  
					}  
			if(this.cgrade>=this.pgrade){   //如果白子的最佳落子点的权值比黑子的最佳落子点权值大，则电脑的最佳落子点为白子的最佳落子点，否则相反  
				m = mat;  
				n = nat;  
			}else{  
				m = mde;  
				n = nde;  
			}  
		}  
		this.cgrade = 0;          
		this.pgrade = 0;  
		this.board[m][n] = 1;  //电脑下子位置     
		ccount++;  
		if((ccount == 50) && (pcount == 50))  //平局判断  
		{  
			this.tie = true;  
			this.over = true;  
		}  
		for(i=0;i<672;i++){  
			if(this.ctable[m][n][i] && this.win[1][i] != 7)  
				this.win[1][i]++;     //给白子的所有五连子可能的加载当前连子数  
			if(this.ptable[m][n][i]){  
				this.ptable[m][n][i] = false;  
				this.win[0][i]=7;  
			}  
		}  
		this.player = true;     //该人落子  
		this.computer = false;  //电脑落子结束  
	}  
}
