function [v] = v2(d)
global dmin dmax vmax;
 
if (d<=dmin)
   v = 0;
else
   v = 5*(log(d/dmin)/log(dmax/dmin));
end
