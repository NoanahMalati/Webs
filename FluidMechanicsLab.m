% EGR 374, Lab 3 - Bottle Rockets
% Stage1.m
%
% This script calculates the "initial" velocity of the bottle rocket
% resulting from the discharge of water through the nozzle
%
% Andrew J. Guswa
% created: 9 February 2009
%
%
% Initial input variables (These are values for you to change)
P0 = 500000;    % initial pressure, gage [Pa]; maximum pressure allowed is 500000 Pa
Vwi = 0.00095;    % initial water volume [m^3]
m0 = 0.25;      % mass of rocket without any water in it [kg]
%
D_jet = 0.027;   % diameter of nozzle [m]; NEED to DOUBLE-CHECK with your bottle
%
%
% Constants
dt = 0.001;    % time step [seconds]
ro = 1000;      % density of water [kg/m^3]
A_jet = 0.25*pi*D_jet^2; % area of nozzle [m^2]
Vtot = 0.002;   % initial volume of bottle [m^3]
Vai = Vtot-Vwi; % initial air volume [m^3]
%
v = zeros(5000,1);       % velocity of rocket [m/s]
M = zeros(5000,1);
P = zeros(5000,1);
Vw = zeros(5000,1);
Vw(1) = Vwi;
jj = 0;
while Vw(jj+1) > 0
    jj = jj+1;
    P(jj) = P0*(Vai/(Vtot-Vw(jj)));
    M(jj) = m0 + Vw(jj)*ro;
    v_jet(jj) = sqrt(2*P(jj)/ro);
    F = ro*A_jet*(v_jet(jj))^2;
    v(jj+1) = v(jj) + (F/M(jj))*dt;
    Vw(jj+1) = Vw(jj) - v_jet(jj)*A_jet*dt;
end
v = v(1:jj+1);
M = M(1:jj);
P = P(1:jj);
%
figure(1)
p1 = plot(v);
t1 = title('Rocket Speed');
x1 = xlabel('Time [ms]');
y1 = ylabel('Speed [m/s]');
%
figure(2)
p2 = plot(M);
t1 = title('Rocket Mass');
x1 = xlabel('Time [ms]');
y1 = ylabel('Mass [kg]');
set(gca,'Ylim',[0 m0+ro*Vwi]);
%
figure(3)
p3 = plot(P/1000);
t1 = title('Air Pressure');
x1 = xlabel('Time [ms]');
y1 = ylabel('Pressure [kPa]');