% EGR 374, Lab 3 - Bottle Rockets
% Stage2.m
%
% This script calculates the motion of the bottle rocket through the air,
% subject to gravity and wind resistance
%
% Andrew J. Guswa
% created: 9 February 2009
%
%
% Initial input variables (these are values for you to change)
V = 40;         % initial rocket speed [m/s]
theta = pi/4;   % initial launch angle [radians]
m0 = 0.25;      % mass of rocket without any water in it [kg]
Cd = 0.5;       % drag coefficient of rocket [-]
A = 0.0087;     % cross-sectional area of rocket [m^2]
%
%
% Constants
dt = 0.001;     % time step [seconds]
ro = 1.2;       % density of air [kg/m^3]
g = 9.81;       % gravitational acceleration [m/s^2]
%
%
zpos = zeros(5000,1);       % elevation of rocket [m]
xpos = zeros(5000,1);       % x-position of rocket [m]
%
jj = 1;
vx = V*cos(theta);
vz = V*sin(theta);
while zpos(jj) >= 0
   xpos(jj+1) = xpos(jj) + vx*dt;
   zpos(jj+1) = zpos(jj) + vz*dt;
   Fdrag = 0.5*Cd*ro*A*(V^2);
   vx = (V-Fdrag*dt/m0)*cos(theta);
   vz = (V-Fdrag*dt/m0)*sin(theta)-g*dt;
   theta = atan(vz/vx);
   V = sqrt(vx^2 + vz^2);
   jj = jj+1;
end
xpos = xpos(1:jj);
zpos = zpos(1:jj);
%
figure(1)
p1 = plot(xpos,zpos);
axis([0 max(xpos) 0 max(xpos)]);
t1 = title('Rocket Trajectory');
x1 = xlabel('x-position [m]');
z1 = ylabel('z-position [m]');
%
