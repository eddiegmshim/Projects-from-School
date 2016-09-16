%deterministic model of a zombie epidemic
%Eddie (Gyu Myung) Shim, Hengyi Wu, Melody J Duan
 
clear
 
a=.1;               	%rate of infection
delta=.5;           	%rate of infected dying to zombie state
kappa = .8;       	%rate of death for zombies
S=95;              	%initial susceptible
I=5;                	%initial infected
Z=0;                	%initial zombies
D=0;                	%initial dead
N=S+I+Z+D;            	%total population
 
T=50;
dt=1;
clockmax=ceil(T/dt);
 
s=zeros(1,clockmax);	%array to save values of s
i=s;
z=s;
d=s;
t=0:dt:(T-dt);
for clock=1:clockmax
	S2I=dt*a*((I+Z)/N)*S; 	%susceptible to infection per dt
	I2Z=dt*delta*I;         	%infected to zombie per dt
	Z2D=dt*kappa*((I+S)/N)*Z; 	%zombie to death per dt
	
	S=S-S2I;
	I=I+S2I-I2Z;
	Z=Z+I2Z-Z2D;
	
  
	D=D+Z2D;
	
	if (Z<0)
    	negativeZ = Z;
    	Z= Z-negativeZ;
    	D= D+negativeZ;
    	
	end
	
	s(clock)=S;
	i(clock)=I;
	z(clock)=Z;
	d(clock)=D;
end
 
figure(1)
plot(t,s,t,i,t,z,t,d,'linewidth',2)
%ylim([0 1100]);
legend('(S):susceptible population','(I):infected population','(Z):zombie population', '(D):dead population')
