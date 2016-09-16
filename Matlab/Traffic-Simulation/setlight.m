function [s] = setlight(t)
 
global nblocks tgreen tred;
 
tt = mod(t, tgreen + tred);
if (tt < tgreen)
	s = ones(1, nblocks);
else
	s = zeros(1, nblocks);
end
End
