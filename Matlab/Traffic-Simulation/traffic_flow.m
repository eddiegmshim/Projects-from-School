%Simulation of traffic flow
%v1,v2
%Eddie Shim, Edd Kim, Ryan Yue
%5/2/16
 
clear
close all
 
% initialize global parameters
global dmin dmax vmax  nblocks tgreen tred  L dmin2 dmax2;
dmin=15;                 	% m
dmax=30;                 	% m
vmax=50;
 
tgreen=120;              	% s
tred=120;                	% s
L=200;                  	% m
nblocks=4;
 
dmin2 = 30;
dmax2 = 60;
 
nblocks=4;
numCars= 0;
ArrayCars = [0];
count = 0;
 
p=rand(1,1000);
 
% set positions of cars at initial time
x=zeros(1,1000);
firstcar=[1 1 1 1];
lastcar=[0 0 0 0];
% time parameters
tmax=3000;                	% s
dt=2;                  	% s
clockmax=ceil(tmax/dt);
 
% variables to record
tsave=zeros(1,clockmax);
ncw=zeros(1,clockmax);    	% number of cars waiting to enter the road
 
% rate of cars coming into the road
R=1/5;                    	% 1/s
 
figure
% set colors
grey=[0.4 0.4 0.4];
green=[0 1 0];
red=[1 0 0];
col=zeros(nblocks,3);
% set position of the figure
set(gcf, 'Position', [250  250  1000  400]);
% draw a line which represents the road
line([-50 800],[0 0],'color',grey,'linewidth',2);
xlim([-50 800]);
ylim([-0.4 1]);
axis off
hold on
% set the three traffic light positions
for i=1:nblocks
	line([i*L i*L],[0 0.2],'color',grey,'linewidth',2);
end
% put cars on road
h=plot(unique(x),zeros(1,length(unique(x))),'ok','markerfacecolor','k','markeredgecolor','k');
 
for clock=1:clockmax
	t=clock*dt;
	tsave(clock)=t;
	% set the traffic light
	s=setlight(t);                          	% define setlight in a seperate function file
	
	% set the light colors and draw the lights
	for i=1:nblocks
    	col(i,:)=s(i)*green+(1-s(i))*red;
    	plot(i*L,0.2,'color',col(i,:),'marker','o','markerfacecolor',col(i,:),'markersize',16)
    	text(i*L-2,0.2, int2str(i));
	end

	
	% calculate random arrival of cars
	if (rand<R*dt)                              % IMPORTANT: R*dt should be less than 1
    	lastcar(1)=lastcar(1)+1;
    	tenter(lastcar(1))=t;               	% record enter time
	end
	
	% move the cars by blocks if the block is not empty
	for b=nblocks:(-1):1                    	% start from the most ahead block
    	if (firstcar(b)<=lastcar(b))        	% if the block is empty, no cars to move   	
        	c=firstcar(b);
        	if (s(b))                       	% green light
            	if (c==1) || (b==nblocks)   	% no cars ahead
                	d=L;
                	%d=x(c-1)-x(c);
            	else
                	d=x(c-1)-x(c);
            	end
        	else                            	% red light
            	d=b*L-x(c);                 	% distance to light
        	end
        	
        	if (p(c)<0)
            	x(c)=x(c)+dt*v1(d);              	% define v in a seperate function file
        	else
            	x(c)=x(c)+dt*v2(d);
        	end
        	if (x(c)>b*L)                   	% 1st car passed the light
            	firstcar(b)=firstcar(b)+1;
            	if (b<nblocks)
                	lastcar(b+1)=lastcar(b+1)+1;
            	else                        	% reach destination
                	texit(c)=t;             	% record exit time
                	numCars = numCars+1;
            	end
        	end
        	c=c+1;
        	while (c<=lastcar(b))           	% update positions of the rest cars
            	
            	if (p(c)<0)
                    x(c)=x(c)+dt*v1(x(c-1)-x(c));
            	else
                    x(c)=x(c)+dt*v2(x(c-1)-x(c));
            	end
            	c=c+1;
        	end
    	end
    	
	end
	
	ArrayCars = [ArrayCars numCars];
	% update positions of cars on the figure
	set(h,'xdata',unique(x),'ydata',zeros(1,length(unique(x))));
	pause(0.1)
end
 
disp(numCars);
figure(2)
t = 1:clockmax+1;
plot(t, ArrayCars, 'linewidth', 1.5);
