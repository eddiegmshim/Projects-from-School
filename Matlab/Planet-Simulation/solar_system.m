%Eddie (Gyu Myung) Shim, Ryan Yue, William Jiang
%02/28/2016
%This script attempts to recreate our current solar system. However, the orbits %are made circular and the 2 closest planets, Mercury and Venus, are omitted %due to time step discrepancies and runtime configuration.
clear
close all
 
G=6.674e-11;    
N=9;
col=[1 0.5 0;0 0 1;0.4 0.4 0.4; 0.4 0.4 0.4; 0.5 0.4 0.3; 0.8 0.7 0.6; 0.6 0.5 0.3; 0.3 0.5 0.2; 0.9 0.9 0]; %matrix of RGB triplets for color
Ms = 1.989e30;			%Initialize the masses of the planets
Me = (Ms*3e-6);
M2 = (Me*3e-10); 
M3 = (Me*317.816);
M4 = (Me*95.1608);
M5 = (Me*14.5362);
M6 = (Me*17.1467);
M7 = (Me*0.0021918);

Mc = Ms/100;				%initialize the mass of the object
%Mc = Ms/50;				%switch between each to observe effect
%Mc = Ms;
 
M=[Ms Me M2 M3 M4 M5 M6 M7 Mc]; %matrix of the planets and their 
  position/velocity
a=149e9;        
X=[0 a 1.5234*a 5.2038*a 9.5789*a 19.2313*a 30.0668*a 39.4786*a 45*a];
Y=[0 0 0 0 0 0 0 0 20*a];
U=[0 0 0 0 0 0 0 0 -2e3];
 
V=[0 sqrt(G*Ms/(a)) sqrt(G*Ms/(1.5234*a)) sqrt(G*Ms/(5.2038*a)) sqrt(G*Ms/(9.5789*a)) sqrt(G*Ms/(19.2313*a)) sqrt(G*Ms/(30.0668*a)) sqrt(G*Ms/(39.4786*a)) 2e3];                                                 
                                                 
T=sqrt(4*pi^2*X(2)^3/(G*M(1)));  
tmax=300*T;
dt=(1e6);
clockmax=ceil(tmax/dt);
Xsave=zeros(N,clockmax);
Ysave=Xsave;
 
set(gcf,'double','on');
hold on
for i=1:N
    h(i)=plot(X(i),Y(i),'wo','MarkerFaceColor',col(i,:),'markersize',8);
end
for i=1:N
    htrail(i)=plot(X,Y,'color','k');
end
s=max(5*X);
axis([-s,s,-s,s]);
 
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
