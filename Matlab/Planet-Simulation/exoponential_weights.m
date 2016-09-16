%Eddie (Gyu Myung) Shim, Ryan Yue, William Jiang
%02/28/2016
%This script initializes a solar system of 5 plants and simulates an object
%passing through the system. The planets' masses are exponentially proportional
%by a factor of ^2, with M5 being the lightest planet.
 
clear
close all
 
G=6.674e-11;    	%Gravitational constant: m^3*kg^(-1)*s^(-2)
N=7;            	%Number of planets
Ms = 1.989e30;
M1 = (Ms/300);
M2 = (M1^(1/2));
M3 = (M2^(1/2));
M4 = (M3^(1/2));
M5 = (M4^(1/2));
Mc = M1;   		%Mass of object
%Mc = (M5);
%Mc = (Ms);
 
 
M=[Ms M1 M2 M3 M4 M5 Mc]; 	%kg
a=149e9;    	%major axis  
 
 
X=[0 0.3*a 0.6*a 0.9*a 1.2*a 1.8*a 1.9*a]; 	%m   vector of x-positions, equidistant
Y=[0 0 0 0 0 0 1.9*a];                          	%vector of y-positions
U=[0 0 0 0 0 0 -1e4];	                           %vector of horizontal velocities
 
V=[0 sqrt(G*Ms/(0.3*a)) sqrt(G*Ms/(0.6*a)) sqrt(G*Ms/(0.9*a)) sqrt(G*Ms/(1.2*a)) sqrt(G*Ms/(1.9*a)) 1.2e2];
%vector of vertical velocities. All planets set at V=sqrt(G*Ms/Ri) in order
%to create circular orbits
                                                
T=sqrt(4*pi^2*X(2)^3/(G*M(1))); 
tmax=50*T;
dt=10*1e4;
clockmax=ceil(tmax/dt);
Xsave=zeros(N,clockmax);
Ysave=Xsave;
 
set(gcf,'double','on');
hold on
for i=1:N
	h(i)=plot(X(i),Y(i),'wo','MarkerFaceColor',[rand() rand() rand()],'markersize',8);
	%use array of rand() in order to make planets randomly different colors
	%every simulation
end
for i=1:N
	htrail(i)=plot(X,Y,'color',[rand() rand() rand()]);
	%use array of rand() in order to make trails randomly different colors
	%every simulation
end
s=max(1.8*X);
axis([-s,s,-s,s]);
axis equal
axis manual
for clock=1:clockmax
	for i=1:N
    	for j=1:N
        	if (i~=j)
            	dx=X(j)-X(i);
            	dy=Y(j)-Y(i);
   	
            	R=sqrt(dx^2+dy^2);
            	U(i)=U(i)+dt*G*M(j)*dx/R^3;
            	V(i)=V(i)+dt*G*M(j)*dy/R^3;
        	end
    	end
	end
	
	%update postions after updating velocities
	for i=1:N
    	X(i)=X(i)+dt*U(i);
    	Y(i)=Y(i)+dt*V(i);    	
	end
	
	Xsave(:,clock)=X;
	Ysave(:,clock)=Y;
	for i=1:N
    	set(htrail(i),'xdata',Xsave(i,1:clock),'ydata',Ysave(i,1:clock));
    	
    	set(h(i),'xdata',X(i),'ydata',Y(i))
    	
	end
	drawnow
end
