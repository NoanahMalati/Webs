clear all
close all
home

f_theory=logspace (3,6,1000);
omega=2*pi*f_theory;
L=0.047;
R=2000;
C=100e-12;
Zin=R+1i*omega*L + 1./(1i*omega*C);

figure(2)
subplot(221)
loglog(f_theory,abs(Zin), 'k','LineWidth',2)
ylabel('Magnitude')
title('Z_{in} Series RLC')
set(gca,'FontSize',16)


subplot(223)
semilogx(f_theory,angle(Zin),'k','LineWidth',2)
xlabel('Frequency (HZ)')
ylabel('Angle (Radians)')
ylim([-pi pi]);



subplot(222)
I_RLCseries=0.5./(Zin);
loglog(f_theory, abs(I_RLCseries), 'k', 'LineWidth', 2)
ylabel('Magnitude')
title('Z_{in} Series RLC')
set(gca,'FontSize',16)


subplot(224)
semilogx(f_theory, angle(I_RLCseries), 'k', 'LineWidth', 2)
xlabel('Frequency (HZ)')
ylabel('Angle (Radians)')
ylim([-pi pi]);

