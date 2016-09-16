function [v] = v1(d)
global dmin dmax vmax;
 
if (d<=dmin)
   v = 0;
else
   v = (log(d/dmin)/log(dmax/dmin));
end
